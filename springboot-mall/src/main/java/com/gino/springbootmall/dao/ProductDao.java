package com.gino.springbootmall.dao;

import com.gino.springbootmall.constant.ProductCategory;
import com.gino.springbootmall.dto.ProductRequest;
import com.gino.springbootmall.madel.Product;
import org.springframework.stereotype.Component;

import java.util.List;


public interface ProductDao {

    List<Product> getProducts(ProductCategory category, String search);

    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);

    void updateProduct(Integer productId, ProductRequest productRequest);

    void deleteProductById(Integer productId);
}
