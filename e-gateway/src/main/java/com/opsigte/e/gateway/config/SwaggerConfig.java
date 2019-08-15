package com.opsigte.e.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 *<p> @ClassName: <i>SwaggerConfig</i></p>
 *<p> @Description: <i>swagger2配置类</i></p>
 *<p> @Author: <i>opsigte</i></p>
 *<p> @Created date: <i>2019/8/15 15:55</i></p>
 *<p> @Version: <i>V1.0。0</i> </p>
 */
@Configuration
public class SwaggerConfig {


    /**
     *
     *  通过createRestApi函数创建Docket的Bean之后，
     *  apiInfo()用来创建该Api的基本信息（这些基本信息会展现在文档页面中）
     *  select()函数返回一个ApiSelectorBuilder实例用来控制哪些接口暴露给Swagger来展现，
     *  本例采用指定扫描的包路径来定义，
     *  Swagger会扫描该包下所有Controller定义的API，并产生文档内容（除了被@ApiIgnore指定的请求）。
     *
     * @return
     */
    @Bean
    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                    .apiInfo(apiInfo())
                    .select()
                    .apis(RequestHandlerSelectors.basePackage("com.opsigte.e.gateway.controller"))
                    .paths(PathSelectors.any())
                    .build();
    }


    /**
     * 设置文档信息
     * @return
     */
    public ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                // 页面标题
                .title("eat-me基于Swagger2的接口文档")
                // 描述
                .description("Swagger和Restful的接口形式整合")
                // 作者信息（可忽略）
                .contact(new Contact("opsigte","https://www.opsigte.top","opsigte994@163.com"))
                // 版本号
                .version("V1.0.0")
                .build();
    }

}
