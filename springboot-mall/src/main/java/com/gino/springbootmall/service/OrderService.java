package com.gino.springbootmall.service;

import com.gino.springbootmall.dto.CreateOrderRequest;
import com.gino.springbootmall.dto.OrderQueryParam;
import com.gino.springbootmall.model.Order;

import java.util.List;

public interface OrderService {

    Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest);

    Order getOrderById(Integer orderId);

    List<Order> getOrders(OrderQueryParam orderQueryParam);

    Integer countOrder(OrderQueryParam orderQueryParam);
}
