package com.gino.springbootmall.controller;

import com.gino.springbootmall.dto.CreateOrderRequest;
import com.gino.springbootmall.dto.OrderQueryParam;
import com.gino.springbootmall.model.Order;
import com.gino.springbootmall.service.OrderService;
import com.gino.springbootmall.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
public class OderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("users/{userId}/orders")
    public ResponseEntity<Page<Order>> getOrders(
            @PathVariable Integer userId,
            @RequestParam(defaultValue = "10")  @Min(0) @Max(100) Integer limit,
            @RequestParam(defaultValue = "0") @Min(0) Integer offset
    ){
        OrderQueryParam orderQueryParam = new OrderQueryParam();
        orderQueryParam.setLimit(limit);
        orderQueryParam.setUserId(userId);
        orderQueryParam.setOffset(offset);

        List<Order> orderList = orderService.getOrders(orderQueryParam);

        Integer count = orderService.countOrder(orderQueryParam);

        Page<Order> page= new Page<>();
        page.setLimit(limit);
        page.setOffset(offset);
        page.setTotal(count);
        page.setResults(orderList);

        return ResponseEntity.status(HttpStatus.OK).body(page);
    }

    @PostMapping("users/{userId}/orders")
    public ResponseEntity<Order> createOrder(@PathVariable Integer userId,
                                             @RequestBody @Valid CreateOrderRequest createOrderRequest){

        Integer orderId = orderService.createOrder(userId, createOrderRequest);

        Order order = orderService.getOrderById(orderId);

        return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }

}
