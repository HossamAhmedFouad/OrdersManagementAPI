package com.APIX.product.controller;

import com.APIX.product.model.Product;
import com.APIX.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/products")
@RestController
public class ProductController {
    @Autowired
    private  ProductService productService;

    @PostMapping
    public void addProduct(@RequestBody Product product){
        productService.addProduct(product);
    }

    @GetMapping
    public List<Product> getProducts(){
        return productService.getProducts();
    }
}
