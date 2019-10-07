package com.opsigte.e.common.core.constant;

/**
 *<p> @ClassName: <i>JwtInfoConstant</i></p>
 *<p> @Description: <i>jwt鉴权相关常量定义</i></p>
 *<p> @Author: <i>zzh</i></p>
 *<p> @Created date: <i>2019/10/6 23:36</i></p>
 *<p> @Version: <i>V1.0.0</i> </p>
 */
public class JwtInfoConstant {

    /**
     * 请求头key
     */
    public static final String JWT_TOKEN_HEADER = "Authorization";
    /**
     * 密钥
     */
    public static final String JWT_SECRET = "TCyFkoPndo2U";
    /**
     * token 过期时间，（5min）
     */
    public static final Long JWT_TOKEN_EXPIRATION = 1000L;

    public static final String CLAIM_KEY_USERNAME = "sub";
    public static final String CLAIM_KEY_CREATED = "created";
}
