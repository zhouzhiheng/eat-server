package com.opsigte.e.gateway.exception;

import com.opsigte.e.common.core.exception.BizException;

/**
 *<p> @ClassName: <i>AuthorizationException</i></p>
 *<p> @Description: <i>jwt token异常信息类</i></p>
 *<p> @Author: <i>zzh</i></p>
 *<p> @Created date: <i>2019/10/6 23:47</i></p>
 *<p> @Version: <i>V1.0.0</i> </p>
 */
public class JwtTokenException extends BizException {

    private static final long serialVersionUID = -3456206376157690259L;


    /**
     * token 无效
     */
    public static final int TOKEN_INVALID = 100010;

    /**
     * token 过期
     */
    public static final int TOKEN_EXPIRE = 100011;

    /**
     * token为空
     */
    public static final int TOKEN_IS_EMPTY = 100012;


    public JwtTokenException() {
    }

    public JwtTokenException(int code, String msgFormat, Object... args) {
        super(code, msgFormat, args);
    }

    public JwtTokenException(String message, Throwable cause) {
        super(message, cause);
    }
}
