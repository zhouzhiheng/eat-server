package com.opsigte.e.gateway.jwt;

import com.opsigte.e.common.core.constant.JwtInfoConstant;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p> @ClassName: <i>JwtTokenUtil</i></p>
 * <p> @Description: <i>token生成工具类</i></p>
 * <p> @Author: <i>zzh</i></p>
 * <p> @Created date: <i>2019/10/7 12:02</i></p>
 * <p> @Version: <i>V1.0.0</i> </p>
 */
public class JwtTokenUtil {

    public static String generateToken(Map<String, Object> claims, String singingKey, Long expiration) {
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
	 * 刷新jwt
	 * @param claims claims
	 * @param singingKey 密钥
	 * @param expiration 过期时间
	 * @return 刷新后的jwt
	 */
	static String refreshToken(Claims claims, String singingKey, Long expiration) {
		claims.put(JwtInfoConstant.CLAIM_KEY_CREATED, new Date());
		return generateToken(claims, singingKey, expiration);
	}

	/**
     * 封装查询jwt密钥的方法
     * @return jwt密钥
     */
    public static String getJwtSecret (){
        return JwtInfoConstant.JWT_SECRET;
    }
}
