package com.APIX.orders.controller;

import com.APIX.orders.model.Product;
import com.APIX.orders.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
