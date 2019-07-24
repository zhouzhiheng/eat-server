package com.opsigte.e.order.service;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import com.opsigte.e.order.api.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableDubbo
public class EOrderServiceApplicationTests {

    @Autowired
    private OrderService orderService;

    @Test
    public void contextLoads() {
        orderService.getAllOrders();
    }

}
