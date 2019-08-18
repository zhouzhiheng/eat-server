package com.opsigte.e.user.service.aop;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/*@Aspect
@Component
@Slf4j*/
public class LogAop {

    @Pointcut("execution(public * com.opsigte.e.user.service.impl.*(..))")
    public void serviceLog(){

    }

    @Pointcut("execution(public * com.opsigte.e.user.service.biz.*(..))")
    public void bizLog(){

    }


    @Around("serviceLog() || bizLog()")
    public Object aroundLog(ProceedingJoinPoint point) throws Throwable {
        Object result = point.proceed();
        //log.info("【返回值】：{}", JSON.toJSONString(result));
        return result;
    }
}
