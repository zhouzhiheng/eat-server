package com.opsigte.e.gateway.jwt.manager.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.opsigte.e.cache.api.CacheService;
import com.opsigte.e.common.core.constant.JwtInfoConstant;
import com.opsigte.e.common.core.constant.redis.UserRedisConstant;
import com.opsigte.e.common.core.utils.StringUtil;
import com.opsigte.e.gateway.exception.JwtTokenException;
import com.opsigte.e.gateway.jwt.JwtTokenUtil;
import com.opsigte.e.gateway.jwt.RawAccessJwtToken;
import com.opsigte.e.gateway.jwt.manager.TokenManager;
import com.opsigte.e.gateway.jwt.model.TokenModel;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p> @ClassName: <i>RedisTokenManager</i></p>
 * <p> @Description: <i>通过Redis存储和验证token的实现类</i></p>
 * <p> @Author: <i>zzh</i></p>
 * <p> @Created date: <i>2019/10/7 13:31</i></p>
 * <p> @Version: <i>V1.0.0</i> </p>
 */
@Slf4j
@Component
public class RedisTokenManager implements TokenManager {


    @Reference(version = "1.0.0", check = false)
	private CacheService cacheService;

    /**
     * userId作为key使用jwt生成的TokenModel对象，包含uid和token字段
     * @param userId 加密的内容
     * @param signingKey 密钥,不传为默认
     * @param expiration 此token过期时间
     * @return
     */
	@Override
    public TokenModel createTokenModel(String userId, String signingKey, Long expiration) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtInfoConstant.CLAIM_KEY_USERNAME, userId);
        claims.put(JwtInfoConstant.CLAIM_KEY_CREATED, new Date());
        if (StringUtil.isBlank(signingKey)) {
            signingKey = JwtTokenUtil.getJwtSecret();
        }

        String token = JwtTokenUtil.generateToken(claims, signingKey, expiration);
        String key = new StringBuffer().append(UserRedisConstant.USER_TOKEN_KEY_PREFIX).append(userId).toString();
        log.info("generateToken ------>>> key:{}, token: {}",key,token);
        cacheService.set(key, token, expiration);
        return new TokenModel(userId, token);
    }

    private static String generateToken(Map<String, Object> claims, String singingKey, Long expiration) {
        return Jwts.builder()
            // 自定义属性
            .setClaims(claims)
            // 过期时间
            .setExpiration(new Date(Instant.now().toEpochMilli() + expiration * 1000))
            // 签名算法以及密匙
            .signWith(SignatureAlgorithm.HS512, singingKey)
            .compact();
    }


    /**
     * 将header中的字符串转换为TokenModel对象
     * @param authentication 加密后的字符串
     * @return
     */
    @Override
    public TokenModel getToken(String header) {
        if (header == null || header.length() == 0) {
            return null;
        }
        TokenModel tokenModel = null;
        try {
            tokenModel = JSON.parseObject(header, TokenModel.class);
        } catch (Exception e) {
            log.error("请求头header转换TokenModel对象异常{}", e);
        }

        return tokenModel;
    }


    /**
     * 检查请求头中的token是否正确
     * @param model
     * @return
     */
    @Override
    public boolean checkToken(TokenModel model) {
        if (model == null){
            throw new JwtTokenException(JwtTokenException.TOKEN_IS_EMPTY, "token is null");
        }

        // 检测jwt的token是否有效
        String tokenValue = null;
        RawAccessJwtToken accessJwtToken = new RawAccessJwtToken(model.getToken());
        try {
            tokenValue = accessJwtToken.getTokenValue();
        } catch (JwtTokenException e) {
            log.error("jwt checkToken:{}", e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error("jwt checkToken:{}", e.getMessage());
            throw new JwtTokenException(JwtTokenException.UNKNOW_ERROR.getCode(), e.getMessage());
        }

        if (StringUtil.isBlank(tokenValue)) {
            log.info("jwt tokenValue is empty !!!!");
            throw new JwtTokenException(JwtTokenException.TOKEN_IS_EMPTY, "tokenValue is null");
        } else {
            // 生成token的时候，是把userId作为key，所以再此比对是否相同
            if (!tokenValue.equals(model.getUid())) {
                log.info("jwt userId is not the same tokenValue");
                throw new JwtTokenException(JwtTokenException.TOKEN_INVALID, "jwt userId is not the same tokenValue");
            }
        }


        String key = new StringBuffer().append(UserRedisConstant.USER_TOKEN_KEY_PREFIX).append(model.getUid()).toString();
        // redis中是否存在此用户，并且redis中此用户的token是否和请求头中的token一致
        if (!checkRedisIsExistUser(key, model.getToken())) {
            log.info("redis中不存在此用户或者用户的token和请求头中的token不相同,redisKey:{}", key);
            throw new JwtTokenException(JwtTokenException.TOKEN_INVALID, "redis中不存在此用户或者用户的token和请求头中的token不相同");
        }

        // 如果验证成功，说明此用户进行了一次有效操作。延长过期时间 （redis和jwt）
        reSetTokenExpiration(key, accessJwtToken);

        return true;
    }


    /**
     * 删除redis中存在的用户
     * @param userId 登录用户的id
     */
    @Override
    public void deleteToken(String userId) {
        String key = new StringBuffer().append(UserRedisConstant.USER_TOKEN_KEY_PREFIX).append(userId).toString();
        cacheService.del(key);
    }

    /**
     * 延长redis和jwt中的token的过期时间
     * @param redisKey
     * @param accessJwtToken
     */
    private void reSetTokenExpiration(String redisKey, RawAccessJwtToken accessJwtToken) {
        try {
            cacheService.expire(redisKey, JwtInfoConstant.JWT_TOKEN_EXPIRATION);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 延长jwt的token时间
        try {
            accessJwtToken.refreshToken(JwtInfoConstant.JWT_TOKEN_EXPIRATION);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 检查redis中是否存在用户,以及是否和请求头中的token相同
     * @param userTokenKey
     * @param token
     * @return
     */
    private boolean checkRedisIsExistUser(String userTokenKey,String authorizationToken){

        String cacheToken = "";
        try {
            cacheToken = (String)cacheService.get(userTokenKey);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        if (cacheToken == null || !cacheToken.equals(authorizationToken)) {
            return false;
        }

        return true;
    }



}
