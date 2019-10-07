package com.opsigte.e.gateway.exception;

import com.opsigte.e.common.core.exception.BizException;

/**
 *<p> @ClassName: <i>AuthorizationException</i></p>
 *<p> @Description: <i>鉴权异常信息类</i></p>
 *<p> @Author: <i>zzh</i></p>
 *<p> @Created date: <i>2019/10/6 23:47</i></p>
 *<p> @Version: <i>V1.0.0</i> </p>
 */
public class AuthorizationException extends BizException {

    private static final long serialVersionUID = -6525089533436036596L;

    /**
     * 鉴权头为空
     */
    public static final int AUTHORIZATION_HEADER_EMPTY = 100010;


    public AuthorizationException() {
    }

    public AuthorizationException(int code, String msgFormat, Object... args) {
        super(code, msgFormat, args);
    }

    public AuthorizationException(String message, Throwable cause) {
        super(message, cause);
    }
}
