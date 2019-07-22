package com.opsigte.eatgateway;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubbo
public class EatGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(EatGatewayApplication.class, args);
        System.out.println("=====Dubbo provider is start !!! =======");
    }

}
