package com.gino.springbootmall.service;

import com.gino.springbootmall.dto.ProductRequest;
import com.gino.springbootmall.madel.Product;

public interface ProductService {

    Product getProductById(Integer productId);
    void updateProduct(Integer productId, ProductRequest productRequest);
    Integer createProduct(ProductRequest productRequest);
    void deleteProductById(Integer productId);
}
