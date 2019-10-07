package com.opsigte.e.gateway;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.ApplicationPidFileWriter;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ConfigurableApplicationContext;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * <p> @ClassName: <i>EGatewayApplication</i></p>
 * <p> @Description: <i>项目统一web入口提供类</i></p>
 * <p> @Author: <i>opsigte</i></p>
 * <p> @Created date: <i>2019/8/16 9:10</i></p>
 * <p> @Version: <i>V1.0.0</i> </p>
 */

@SpringBootApplication
@EnableDubbo
@EnableSwagger2
@EnableSwaggerBootstrapUI
@ServletComponentScan
public class EGatewayApplication {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(EGatewayApplication.class);
        // 把服务启动的进程id写入到指定的pid文件中,用于友好的关闭服务进程
        application.addListeners(new ApplicationPidFileWriter());
        // 可以返回一个ConfigurableApplicationContext对象,用此对象可以获取spring容器中的bean
        ConfigurableApplicationContext run = application.run(args);
        System.out.println("=====Dubbo provider is start !!! =======");
    }

}
