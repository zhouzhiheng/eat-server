package com.opsigte.e.message.queue.receive;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.ApplicationPidFileWriter;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class EMessageQueueReceiveApplication {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(EMessageQueueReceiveApplication.class);
        // 把服务启动的进程id写入到指定的pid文件中,用于友好的关闭服务进程
        springApplication.addListeners(new ApplicationPidFileWriter());
        // 可以返回一个ConfigurableApplicationContext对象,用此对象可以获取spring容器中的bean
        ConfigurableApplicationContext run = springApplication.run(args);
        System.out.println("==============DubboProvider is started!==============");
    }

}
