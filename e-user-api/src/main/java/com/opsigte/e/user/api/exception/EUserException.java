package com.opsigte.e.user.api.exception;

import com.opsigte.e.common.core.exception.BizException;

/**
 *<p> @ClassName: <i>EUserException</i></p>
 *<p> @Description: <i>用户服务异常处理类</i></p>
 *<p> @Author: <i>opsigte</i></p>
 *<p> @Created date: <i>2019/8/19 11:24</i></p>
 *<p> @Version: <i>V1.0.0</i> </p>
 */
public class EUserException extends BizException {
    private static final long serialVersionUID = 355007592622455047L;

    public EUserException(int code, String msgFormat, Object... args) {
        super(code, msgFormat, args);
    }

    public EUserException() {
    }

    public EUserException(String message, Throwable cause) {
        super(message, cause);
    }
}
