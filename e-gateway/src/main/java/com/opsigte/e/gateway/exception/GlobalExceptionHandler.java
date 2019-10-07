package com.opsigte.e.gateway.exception;

import com.alibaba.dubbo.rpc.RpcException;
import com.opsigte.e.common.core.web.response.ErrorCode;
import com.opsigte.e.common.core.web.response.Resp;
import com.opsigte.e.user.api.exception.EUserException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * <p> @ClassName: <i>GlobalExceptionHandler</i></p>
 * <p> @Description: <i>全局异常处理类,所有的异常都会在此被拦截并匹配被@ExceptionHandler注解的方法</i></p>
 * <p> @Author: <i>opsigte</i></p>
 * <p> @Created date: <i>2019/8/19 11:15</i></p>
 * <p> @Version: <i>V1.0.0</i> </p>
 */
@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(value = RpcException.class)
    public Resp rpcException(Exception e){
        return Resp.fail(ErrorCode.SYSTEM_ERROR, "系统错误");
    }

    @ExceptionHandler(value = NoHandlerFoundException.class)
    public Resp handlerFoundException(Exception e){
        return Resp.fail(ErrorCode.NO_HANDLER_MAPPING_ERROR);
    }


    /**
     * 用户服务异常处理
     *
     * @Title userException
     * @param [e]
     * @return com.opsigte.e.common.core.web.response.Resp
     * @throws
     */
    @ExceptionHandler(value = EUserException.class)
    public Resp userException(EUserException e) {
        if (e.getCode() == EUserException.DB_LIST_IS_NULL.getCode()) {
            return Resp.fail(ErrorCode.RPC_ERROR, "rpc调用异常");
        }
        return Resp.fail(ErrorCode.SYSTEM_ERROR, "系统错误");
    }

}
