package com.gino.springbootmall.service;

import com.gino.springbootmall.dto.CreateOrderRequest;
import com.gino.springbootmall.model.Order;

public interface OrderService {

    Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest);

    Order getOrderById(Integer orderId);
}
