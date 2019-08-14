package com.opsigte.e.order.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.opsigte.e.order.api.OrderService;
import com.opsigte.e.order.api.entity.OrderEntity;
import com.opsigte.e.user.api.EUserService;
import com.opsigte.e.user.api.entity.UserEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * @Project: com.opsigte.e.order.service.impl
 * @Class: OrderServiceImpl
 * @Description: 订单相关服务实现类
 * @Author: opsigte
 * @Date: 2019/7/24 21:32
 * @version 1.0.0
 */
@Service(version = "1.0.0")
public class OrderServiceImpl implements OrderService {

    @Reference(check = false,version = "1.0.0")
    private EUserService EUserService;

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
            UserEntity userByUid = EUserService.getUserByUid(orderEntity.getUid());
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
