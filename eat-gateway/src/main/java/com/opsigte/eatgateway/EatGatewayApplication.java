package com.opsigte.eatgateway;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EnableDubbo
public class EatGatewayApplication extends WebMvcConfigurationSupport {

    public static void main(String[] args) {
        SpringApplication.run(EatGatewayApplication.class, args);
        System.out.println("=====Dubbo provider is start !!! =======");
    }

    @Override
    protected void configurePathMatch(PathMatchConfigurer configurer) {
        // 忽略请求后缀
        configurer.setUseSuffixPatternMatch(true);
    }
}
