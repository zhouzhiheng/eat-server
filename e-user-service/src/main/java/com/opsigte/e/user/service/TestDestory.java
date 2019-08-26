package com.opsigte.e.user.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *<p> @ClassName: <i>TestDestory</i></p>
 *<p> @Description: <i></i></p>
 *<p> @Author: <i>opsigte</i></p>
 *<p> @Created date: <i>2019/8/26 10:00</i></p>
 *<p> @Version: <i>V1.0.0</i> </p>
 */
@Configuration
public class TestDestory {

    @Bean
    public TestDestoryBean testDestoryBean(){
        System.out.println("this bean is init");
        return new TestDestoryBean();
    }

}
