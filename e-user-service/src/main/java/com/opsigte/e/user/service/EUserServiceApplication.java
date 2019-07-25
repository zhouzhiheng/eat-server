package com.opsigte.e.user.service;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.transaction.annotation.EnableTransactionManagement;


/**
 * @Project: com.opsigte.e.user.service
 * @Class: EUserServiceApplication
 * @Description: 用户服务启动类
 * @Author: opsigte
 * @Date: 2019/7/24 21:38
 * @version 1.0.0
 */
@SpringBootApplication
@EnableDubbo
@MapperScan(value = "com.opsigte.e.user.service.mapper")
@EnableTransactionManagement
public class EUserServiceApplication {

    public static void main(String[] args) {

        SpringApplication springApplication = new SpringApplication(EUserServiceApplication.class);
        ConfigurableApplicationContext run = springApplication.run(args);
        System.out.println("==============DubboProvider is started!==============");

    }

}
