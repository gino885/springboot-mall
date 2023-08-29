package com.gino.springbootmall.service.impl;

import com.gino.springbootmall.dao.OrderDao;
import com.gino.springbootmall.dao.ProductDao;
import com.gino.springbootmall.dao.UserDao;
import com.gino.springbootmall.dto.BuyItem;
import com.gino.springbootmall.dto.CreateOrderRequest;
import com.gino.springbootmall.dto.OrderQueryParam;
import com.gino.springbootmall.model.Order;
import com.gino.springbootmall.model.OrderItem;
import com.gino.springbootmall.model.Product;
import com.gino.springbootmall.model.User;
import com.gino.springbootmall.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderServiceImpl implements OrderService {

    private final static Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private ProductDao productDao;

    @Autowired
    private UserDao userDao;

    @Override
    public Order getOrderById(Integer orderId) {
        Order order = orderDao.getOrderById(orderId);

        List<OrderItem> orderItemList = orderDao.getOrderItemsByOrderId(orderId);

        order.setOrderItemList(orderItemList);

        return order;
    }

    @Transactional
    @Override
    public Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest) {

        User user = userDao.getUserById(userId);

        if(user == null){
            log.warn("該 userId() 不存在", userId);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        int totalAmount = 0;
        List<OrderItem> orderItemList = new ArrayList<>();

        for (BuyItem buyItem: createOrderRequest.getBuyItemList()){
            Product product = productDao.getProductById(buyItem.getProductId());

            if(product == null){
                log.warn("該商品 {} 不存在", buyItem.getProductId());
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            } else if (product.getStock() < buyItem.getQuantity()) {
                log.warn("商品 {} 庫存量不足，無法購買。剩餘庫存 {} ，欲購買數量 {}",
                        buyItem.getProductId(), product.getStock(), buyItem.getQuantity());
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }

            productDao.updateStock(product.getProduct_id(), product.getStock() - buyItem.getQuantity());

            int amount = buyItem.getQuantity() * product.getPrice();
            totalAmount = totalAmount + amount;

            OrderItem orderItem = new OrderItem();
            orderItem.setProductId(buyItem.getProductId());
            orderItem.setAmount(amount);
            orderItem.setQuantity(buyItem.getQuantity());

            orderItemList.add(orderItem);
        }

        Integer orderId = orderDao.createOrder(userId, totalAmount);

        orderDao.createOrderItems(orderId, orderItemList);
        return orderId;
    }

    @Override
    public List<Order> getOrders(OrderQueryParam orderQueryParam) {
        List<Order> orderList = orderDao.getOrders(orderQueryParam);

        for(Order order:orderList){
            List<OrderItem> orderItemList = orderDao.getOrderItemsByOrderId(order.getOrderId());

            order.setOrderItemList(orderItemList);
        }
        return orderList;
    }

    @Override
    public Integer countOrder(OrderQueryParam orderQueryParam) {

        return orderDao.countOrder(orderQueryParam);
    }
}
