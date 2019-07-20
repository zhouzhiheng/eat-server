package com.opsigte.eat.user.service;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import com.alibaba.fastjson.JSON;
import com.opsigte.eat.user.api.entity.UserEntity;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableDubbo
// MapperScan 避免了每一个 mapper上面都需要 @Mapper注解
@MapperScan(value = "com.opsigte.eat.user.service.dao")
@EnableTransactionManagement
public class EatUserServiceApplication {

    private static final Logger log = LoggerFactory.getLogger(EatUserServiceApplication.class);


    public static void main(String[] args) {

        SpringApplication springApplication = new SpringApplication(EatUserServiceApplication.class);
        ConfigurableApplicationContext run = springApplication.run(args);
        System.out.println("==============DubboProvider is started!==============");

    }

}
