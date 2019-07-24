package com.opsigte.e.order.api;

import com.opsigte.e.order.api.entity.OrderEntity;

import java.util.List;

/**
 * @Project: com.opsigte.e.order.api
 * @Class: OrderService
 * @Description: 订单相关接口
 * @Author: opsigte
 * @Date: 2019/7/24 21:31
 * @version 1.0.0
 */
public interface OrderService {

    List<OrderEntity> getAllOrders();
}
