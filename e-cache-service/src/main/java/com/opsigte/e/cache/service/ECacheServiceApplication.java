package com.opsigte.e.cache.service;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Project: com.opsigte.e.cache.service
 * @Class: ECacheServiceApplication
 * @Description: Redis
 * @Author: opsigte
 * @Date: 2019/7/24 10:28
 * @version 1.0.0
 */
@SpringBootApplication
@EnableDubbo
public class ECacheServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ECacheServiceApplication.class, args);
        System.out.println("=========Dubbo provide is start!!!=========");
    }

}
