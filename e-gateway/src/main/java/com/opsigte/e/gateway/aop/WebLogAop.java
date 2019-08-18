package com.opsigte.e.gateway.aop;

import com.alibaba.dubbo.rpc.RpcContext;
import com.alibaba.fastjson.JSON;
import com.opsigte.e.common.core.constant.CommonConstant;
import com.opsigte.e.common.core.utils.UUIDUtil;
import eu.bitwalker.useragentutils.UserAgent;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Objects;

/**
 *<p> @ClassName: <i>AopLog</i></p>
 *<p> @Description: <i>使用 aop 切面记录请求日志信息</i></p>
 *<p> @Author: <i>opsigte</i></p>
 *<p> @Created date: <i>2019/8/16 11:58</i></p>
 *<p> @Version: <i>V1.0。0</i> </p>
 */
@Aspect
@Component
@Slf4j
public class WebLogAop {
    private static final String START_TIME = "request-start";

    /**
     * 切入点
     */
    @Pointcut("execution(public * com.opsigte.e.gateway.controller.*Controller.*(..))")
    public void log(){

    }

    /**
     * 前置操作
     *
     * @param point 切入点
     */
    @Before("log()")
    public void beforeLog(JoinPoint point) {
        requestSetTraceId();
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = Objects.requireNonNull(attributes).getRequest();

        log.info("【请求 URL】：{}", request.getRequestURL());
        log.info("【请求 IP】：{}", request.getRemoteAddr());
        log.info("【请求方法】：{}.{}", point.getSignature().getDeclaringTypeName(), point.getSignature().getName());

        Map<String, String[]> parameterMap = request.getParameterMap();
        log.info("【请求参数】：{}", JSON.toJSONString(parameterMap));

        Long start = System.currentTimeMillis();
        request.setAttribute(START_TIME, start);
    }

    /**
     * 为每一个请求都设置一个新的traceId
     */
    private void requestSetTraceId(){
        /**
         * TODO 在此可以获取用户id
         */
        /*String token = request.getParameter("token");
        String userId = StrKit.notBlank(token) ? CommonUtil.getUserId(token) : "VISITOR";
        String uuid = CommonUtil.getUuid();*/

        String traceId = UUIDUtil.generatorTraceId();
        RpcContext.getContext().setAttachment(CommonConstant.TRACEIDKEY,traceId);
        MDC.put(CommonConstant.TRACEIDKEY,traceId);
    }

    /**
     * 环绕操作
     *
     * @param point 切入点
     * @return 原方法返回值
     * @throws Throwable 异常信息
     */
    @Around("log()")
    public Object aroundLog(ProceedingJoinPoint point) throws Throwable {
        Object result = point.proceed();
        log.info("【返回值】：{}", JSON.toJSONString(result));
        return result;
    }

    /**
     * 后置操作
     */
    @AfterReturning("log()")
    public void afterReturning() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = Objects.requireNonNull(attributes).getRequest();

        Long start = (Long) request.getAttribute(START_TIME);
        Long end = System.currentTimeMillis();
        log.info("【请求耗时】：{}毫秒", end - start);

        String header = request.getHeader("User-Agent");
        UserAgent userAgent = UserAgent.parseUserAgentString(header);
        log.info("【浏览器类型】：{}，【操作系统】：{}，【原始User-Agent】：{}", userAgent.getBrowser().toString(), userAgent.getOperatingSystem().toString(), header);
    }
}
