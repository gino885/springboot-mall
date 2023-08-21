package com.gino.springbootmall.service;

import com.gino.springbootmall.dto.ProductQueryParams;
import com.gino.springbootmall.dto.ProductRequest;
import com.gino.springbootmall.madel.Product;

import java.util.List;

public interface ProductService {

    List<Product> getProducts(ProductQueryParams productQueryParams);
    Product getProductById(Integer productId);
    void updateProduct(Integer productId, ProductRequest productRequest);
    Integer createProduct(ProductRequest productRequest);
    void deleteProductById(Integer productId);
}
