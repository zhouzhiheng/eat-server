package com.opsigte.e.gateway;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @Project: com.opsigte.e.gateway
 * @Class: EGatewayApplication
 * @Description: 项目统一web入口提供类
 * @Author: opsigte
 * @Date: 2019/7/24 21:27
 * @version 1.0.0
 */
@SpringBootApplication
@EnableDubbo
public class EGatewayApplication extends WebMvcConfigurationSupport {

    public static void main(String[] args) {
        SpringApplication.run(EGatewayApplication.class, args);
        System.out.println("=====Dubbo provider is start !!! =======");
    }

    @Override
    protected void configurePathMatch(PathMatchConfigurer configurer) {
        // 忽略请求后缀 如：todo.json , todo.html todo ----> 相应的todo映射方法
        configurer.setUseSuffixPatternMatch(true);
    }
}
