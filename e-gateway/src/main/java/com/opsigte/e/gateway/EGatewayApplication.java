package com.opsigte.e.gateway;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
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
public class EGatewayApplication extends WebMvcConfigurationSupport {

    public static void main(String[] args) {
        SpringApplication.run(EGatewayApplication.class, args);
        System.out.println("=====Dubbo provider is start !!! =======");
    }

}
