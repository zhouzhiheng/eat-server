package com.opsigte.e.user.service;

import javax.annotation.PreDestroy;

/**
 *<p> @ClassName: <i>TestDestoryBean</i></p>
 *<p> @Description: <i></i></p>
 *<p> @Author: <i>opsigte</i></p>
 *<p> @Created date: <i>2019/8/26 10:01</i></p>
 *<p> @Version: <i>V1.0.0</i> </p>
 */
public class TestDestoryBean {

    @PreDestroy
    public void print(){
        System.out.println("this bean is destory");
    }
}
