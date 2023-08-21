package com.gino.springbootmall.dao;

import com.gino.springbootmall.dto.ProductQueryParams;
import com.gino.springbootmall.dto.ProductRequest;
import com.gino.springbootmall.madel.Product;

import java.util.List;


public interface ProductDao {

    List<Product> getProducts(ProductQueryParams productQueryParams);

    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);

    void updateProduct(Integer productId, ProductRequest productRequest);

    void deleteProductById(Integer productId);
}
