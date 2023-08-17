package com.gino.springbootmall.dao;

import com.gino.springbootmall.dto.ProductRequest;
import com.gino.springbootmall.madel.Product;
import org.springframework.stereotype.Component;


public interface ProductDao {

    Product getProductById(Integer productId);
    Integer createProduct(ProductRequest productRequest);
}
