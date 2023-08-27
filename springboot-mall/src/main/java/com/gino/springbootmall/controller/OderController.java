package com.gino.springbootmall.controller;

import com.gino.springbootmall.dto.CreateOrderRequest;
import com.gino.springbootmall.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class OderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("users/{userId}/orders")
    public ResponseEntity<Integer> createOrder(@PathVariable Integer userId,
                                             @RequestBody @Valid CreateOrderRequest createOrderRequest){

        Integer orderId = orderService.createOrder(userId, createOrderRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(orderId);
    }

}
