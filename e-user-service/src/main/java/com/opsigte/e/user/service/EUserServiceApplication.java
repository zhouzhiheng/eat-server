package com.opsigte.e.user.service;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.ApplicationPidFileWriter;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;
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
@EnableScheduling
public class EUserServiceApplication {

    public static void main(String[] args) {

        SpringApplication springApplication = new SpringApplication(EUserServiceApplication.class);
        // 把服务启动的进程id写入到指定的pid文件中,用于友好的关闭服务进程
        springApplication.addListeners(new ApplicationPidFileWriter());
        // 可以返回一个ConfigurableApplicationContext对象,用此对象可以获取spring容器中的bean
        ConfigurableApplicationContext run = springApplication.run(args);
        System.out.println("==============DubboProvider is started!==============");
    }

}
