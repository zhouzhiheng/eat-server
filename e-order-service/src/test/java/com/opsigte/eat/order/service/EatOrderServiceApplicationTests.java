package com.opsigte.eat.order.service;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import com.opsigte.eat.order.api.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableDubbo
public class EatOrderServiceApplicationTests {

    @Autowired
    private OrderService orderService;

    @Test
    public void contextLoads() {
        orderService.getAllOrders();
    }

}
