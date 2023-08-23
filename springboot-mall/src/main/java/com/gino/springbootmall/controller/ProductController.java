package com.gino.springbootmall.controller;

import com.gino.springbootmall.constant.ProductCategory;
import com.gino.springbootmall.dto.ProductQueryParams;
import com.gino.springbootmall.dto.ProductRequest;
import com.gino.springbootmall.model.Product;
import com.gino.springbootmall.service.ProductService;
import com.gino.springbootmall.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

//Min 和 MAX要記得加Validated
@Validated
@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<Page<Product>> getProducts(
            //查詢條件filtering
           @RequestParam(required = false) ProductCategory category,
           @RequestParam(required = false) String search,

           //排序sorting
           @RequestParam(defaultValue = "created_date") String orderBy,
           @RequestParam(defaultValue = "desc")String sort,
            //分頁pagination
            @RequestParam(defaultValue = "5") @Max(1000) @Min(0) Integer limit,
            @RequestParam(defaultValue = "0") @Min(0) Integer offset
    ){
        ProductQueryParams productQueryParams = new ProductQueryParams();
        productQueryParams.setCategory(category);
        productQueryParams.setSearch(search);
        productQueryParams.setSort(sort);
        productQueryParams.setOrderBy(orderBy);
        productQueryParams.setLimit(limit);
        productQueryParams.setOffset(offset);

        //取得PoductList
        List<Product> productList = productService.getProducts(productQueryParams);
        //取得Poduct總筆數
        Integer total = productService.countProduct(productQueryParams);

        //分頁
        Page<Product> page = new Page<>();
        page.setLimit(limit);
        page.setOffset(offset);
        page.setTotal(total);
        page.setResults(productList);

        return ResponseEntity.status(HttpStatus.OK).body(page);
    }


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

    @PutMapping("/products/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable Integer productId,
                                                 @RequestBody @Valid ProductRequest productRequest){
       Product product = productService.getProductById(productId);

       if(product == null){
           return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
       }

       productService.updateProduct(productId,productRequest);

       Product updatedProduct = productService.getProductById(productId);
        return ResponseEntity.status(HttpStatus.OK).body(updatedProduct);
    }

    @DeleteMapping("/products/{productId}")
    public ResponseEntity<Product> deleteProduct(@PathVariable Integer productId){
        productService.deleteProductById(productId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
