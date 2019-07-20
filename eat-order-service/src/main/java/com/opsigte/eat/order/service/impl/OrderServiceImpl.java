package com.opsigte.eat.order.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.opsigte.eat.order.api.OrderService;
import com.opsigte.eat.order.api.entity.OrderEntity;
import com.opsigte.eat.user.api.UserService;
import com.opsigte.eat.user.api.entity.UserEntity;

import java.util.ArrayList;
import java.util.List;

@Service(version = "1.0.0")
public class OrderServiceImpl implements OrderService {

    @Reference(check = false,version = "1.0.0")
    private UserService userService;

    @Override
    public List<OrderEntity> getAllOrders() {
        List<OrderEntity> list = new ArrayList<OrderEntity>();
        OrderEntity entity = new OrderEntity();
        entity.setId("1");
        entity.setOrderNumber("12345");
        entity.setUid("1");

        OrderEntity entity1 = new OrderEntity();
        entity1.setId("2");
        entity1.setOrderNumber("54321");
        entity1.setUid("3");

        list.add(entity);
        list.add(entity1);

        List<OrderEntity> resList = new ArrayList<OrderEntity>();
        for (OrderEntity orderEntity : list) {
            UserEntity userByUid = userService.getUserByUid(orderEntity.getUid());
            if (userByUid != null) {
                orderEntity.setUserName(userByUid.getName());
            } else {
                System.out.println("没有uid为"+orderEntity.getUid()+"的用户");
            }
            resList.add(orderEntity);
        }

        return resList;
    }
}
