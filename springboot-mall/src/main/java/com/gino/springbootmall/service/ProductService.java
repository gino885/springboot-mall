package com.gino.springbootmall.service;

import com.gino.springbootmall.constant.ProductCategory;
import com.gino.springbootmall.dto.ProductRequest;
import com.gino.springbootmall.madel.Product;

import java.util.List;

public interface ProductService {

    List<Product> getProducts(ProductCategory category,String search);
    Product getProductById(Integer productId);
    void updateProduct(Integer productId, ProductRequest productRequest);
    Integer createProduct(ProductRequest productRequest);
    void deleteProductById(Integer productId);
}
