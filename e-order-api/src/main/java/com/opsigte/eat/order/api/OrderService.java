package com.opsigte.eat.order.api;

import com.opsigte.eat.order.api.entity.OrderEntity;

import java.util.List;

public interface OrderService {

    List<OrderEntity> getAllOrders();
}
