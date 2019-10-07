package com.opsigte.e.gateway.interceptor;

import com.opsigte.e.common.core.constant.JwtInfoConstant;
import com.opsigte.e.common.core.utils.StringUtil;
import com.opsigte.e.gateway.annotation.Authorization;
import com.opsigte.e.gateway.exception.JwtTokenException;
import com.opsigte.e.gateway.jwt.manager.impl.RedisTokenManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 *<p> @ClassName: <i>AuthorizationInterceptor</i></p>
 *<p> @Description: <i></i></p>
 *<p> @Author: <i>zzh</i></p>
 *<p> @Created date: <i>2019/10/6 23:33</i></p>
 *<p> @Version: <i>V1.0.0</i> </p>
 */
@Slf4j
public class AuthorizationInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisTokenManager tokenManager;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //解决跨域
        // String origin = request.getHeader("Origin");
        // response.setHeader("Access-Control-Allow-Origin", origin);
        // response.setHeader("Access-Control-Allow-Methods", "*");
        // response.setHeader("Access-Control-Allow-Headers","Origin,Content-Type,Accept,token,X-Requested-With");
        // response.setHeader("Access-Control-Allow-Credentials", "true");

        // 如果不是映射到方法上，直接通过
        if (!(handler instanceof HandlerMethod)) {
            log.info("直接通过");
            return true;
        }

        // 验证是否有token
        String header = request.getHeader(JwtInfoConstant.JWT_TOKEN_HEADER);
        log.info("token header:{}", header);

        if (StringUtil.isBlank(header)) {
            log.info("HTTP/ErrorCode:412 Server rejects request. => Authorization is null.");
            response.setStatus(HttpServletResponse.SC_PRECONDITION_FAILED);
            return false;
        }


        // 如果方法上标注了 @Authorization注解，需要判断token是否正确
        Method method = ((HandlerMethod) handler).getMethod();
        if (method.getAnnotation(Authorization.class) != null) {
            try {
                boolean checkToken = tokenManager.checkToken(tokenManager.getToken(header));
            } catch (JwtTokenException e) {
                if (e.getCode() == JwtTokenException.TOKEN_INVALID || e.getCode() == JwtTokenException.TOKEN_IS_EMPTY) {
                    log.info("HTTP/ErrorCode:412 Server rejects request. => Token is invalid.");
                    response.setStatus(HttpServletResponse.SC_PRECONDITION_FAILED);
                    return false;
                } else if (e.getCode() == JwtTokenException.TOKEN_EXPIRE) {
                    log.info("HTTP/ErrorCode:406 Server rejects request. => Timestamp is wrong.");
                    response.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
                    return false;
                } else if (e.getCode() == JwtTokenException.UNKNOW_ERROR.getCode()) {
                    log.info("HTTP/ErrorCode:500 Server rejects request. => Sever is unknow.");
                    response.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
                    return false;
                }
            }
        }


        return true;
    }

}
