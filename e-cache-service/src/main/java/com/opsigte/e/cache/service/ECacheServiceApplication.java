package com.opsigte.e.cache.service;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.ApplicationPidFileWriter;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * <p> @ClassName: <i>ECacheServiceApplication</i></p>
 * <p> @Description: <i>缓存服务启动类</i></p>
 * <p> @Author: <i>zzh</i></p>
 * <p> @Created date: <i>2019/10/7 16:01</i></p>
 * <p> @Version: <i>V1.0.0</i> </p>
 */
@SpringBootApplication
@EnableDubbo
public class ECacheServiceApplication {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(ECacheServiceApplication.class);
        application.addListeners(new ApplicationPidFileWriter());
        ConfigurableApplicationContext run = application.run(args);
        System.out.println("=========Dubbo provide is start!!!=========");
    }

}
