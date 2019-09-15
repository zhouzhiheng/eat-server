package com.opsigte.e.message.queue.produce;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.ApplicationPidFileWriter;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * <p> @ClassName: <i>EMessageQueueApplication</i></p>
 * <p> @Description: <i>Rabbitmq消息队列服务启动类</i></p>
 * <p> @Author: <i>opsigte</i></p>
 * <p> @Created date: <i>2019/8/27 20:52</i></p>
 * <p> @Version: <i>V1.0.0</i> </p>
 */
@SpringBootApplication
@EnableDubbo
@EnableRabbit
@EnableScheduling
public class EMessageQueueApplication {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(EMessageQueueApplication.class);
        // 把服务启动的进程id写入到指定的pid文件中,用于友好的关闭服务进程
        springApplication.addListeners(new ApplicationPidFileWriter());
        // 可以返回一个ConfigurableApplicationContext对象,用此对象可以获取spring容器中的bean
        ConfigurableApplicationContext run = springApplication.run(args);
        System.out.println("==============DubboProvider is started!==============");
    }

}
