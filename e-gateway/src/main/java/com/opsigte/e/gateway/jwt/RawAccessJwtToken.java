package com.opsigte.e.gateway.jwt;

import com.alibaba.fastjson.JSON;
import com.opsigte.e.common.core.constant.JwtInfoConstant;
import com.opsigte.e.common.core.utils.StringUtil;
import com.opsigte.e.gateway.exception.JwtTokenException;
import com.opsigte.e.gateway.jwt.model.TokenModel;
import io.jsonwebtoken.*;

import java.lang.reflect.ParameterizedType;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * <p> @ClassName: <i>RawAccessJwtToken</i></p>
 * <p> @Description: <i>解析token</i></p>
 * <p> @Author: <i>zzh</i></p>
 * <p> @Created date: <i>2019/10/7 12:01</i></p>
 * <p> @Version: <i>V1.0.0</i> </p>
 */
public class RawAccessJwtToken {

    public static void main(String[] args){

        String userId = "test";
        System.out.println("test生成的TokeModel:" + JSON.toJSONString(testCreateTokenModel(userId)));

        // String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN0IiwiY3JlYXRlZCI6MTU3MDQzODM5NDg0MiwiZXhwIjoxNTcwNDM5Mzk1fQ.6uKYuO0-WVgOeGa2jIv3nuGZ4b6q0pR0Eh7WqRbM_SmlXjGLgzNHAWtpy0wsS3uyfSCu0j16Sa1vi0-MbvpKaw";
        // System.out.println("test根据token获得的value：" + testGetTokenValue(token));

    }

    private String token;
    private String secret;

    public RawAccessJwtToken(String token) {
        this.token = token;
        this.secret = JwtTokenUtil.getJwtSecret();
    }

    /**
     * 获取token的值
     * @return token
     * @throws Exception
     */
    public String getTokenValue() throws Exception {
        return parseClaims().getSubject();
    }

    private Claims parseClaims() throws Exception {
        try {
            return Jwts.parser().setSigningKey(this.secret).parseClaimsJws(this.token).getBody();
        } catch (UnsupportedJwtException | MalformedJwtException | IllegalArgumentException
                | SignatureException ex) {
            throw new JwtTokenException(JwtTokenException.TOKEN_INVALID,"无效的Token");
        } catch (ExpiredJwtException expiredEx) {
            throw new JwtTokenException(JwtTokenException.TOKEN_EXPIRE, expiredEx.getMessage());
        }
    }


    private static TokenModel testCreateTokenModel(String userId){
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtInfoConstant.CLAIM_KEY_USERNAME, userId);
        claims.put(JwtInfoConstant.CLAIM_KEY_CREATED, new Date());
        String token = JwtTokenUtil.generateToken(claims, JwtTokenUtil.getJwtSecret(), JwtInfoConstant.JWT_TOKEN_EXPIRATION);
        return new TokenModel(userId, token, "", "", "", "", "");
    }

    private static String testGetTokenValue(String token){
        RawAccessJwtToken accessJwtToken = new RawAccessJwtToken(token);
        try {
            return accessJwtToken.getTokenValue();
        } catch (JwtTokenException e) {
            System.out.println("jwt exception:" + e);
        } catch (Exception e) {
            System.out.println("exception:" + e);
        }
        return null;
    }

    /**
     * 刷新token
     * @param expiration 过期时间
     * @return jwt
     * @throws Exception e
     */
    public String refreshToken(Long expiration) throws Exception {
        Claims claims = parseClaims();
        return JwtTokenUtil.refreshToken(claims, secret, expiration);
    }




}
