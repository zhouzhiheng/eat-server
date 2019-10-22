package com.opsigte.e.order.service;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;


/**
 * @Project: com.opsigte.e.order.service
 * @Class: EOrderServiceApplication
 * @Description: 订单服务启动类
 * @Author: opsigte
 * @Date: 2019/7/24 21:37
 * @version 1.0.0
 */
@SpringBootApplication
@MapperScan(value = "com.opsigte.e.order.service.mapper")
@EnableDubbo
@EnableTransactionManagement
public class EOrderServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EOrderServiceApplication.class, args);
        System.out.println("==============DubboProvider is started!==============");
    }

}
