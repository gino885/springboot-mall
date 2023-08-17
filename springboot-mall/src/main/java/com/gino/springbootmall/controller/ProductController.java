package com.gino.springbootmall.controller;

import com.gino.springbootmall.dto.ProductRequest;
import com.gino.springbootmall.madel.Product;
import com.gino.springbootmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products/{productId}")
    public ResponseEntity<Product> getProduct(@PathVariable Integer productId) {
        Product product = productService.getProductById(productId);
        System.out.println(product.getCreated_date());

        if(product !=null){
            return ResponseEntity.status(HttpStatus.OK).body(product);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/products")
    public ResponseEntity<Product> createProduct(@RequestBody @Valid ProductRequest productRequest){
           Integer productId = productService.createProduct(productRequest);
           Product product = productService.getProductById(productId);

           return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

}
