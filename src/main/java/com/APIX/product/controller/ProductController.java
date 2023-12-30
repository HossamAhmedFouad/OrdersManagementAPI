package com.APIX.product.controller;

import com.APIX.product.model.Product;
import com.APIX.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("/api/products")
@RestController
public class ProductController {
    @Autowired
    private  ProductService productService;

    @PostMapping
    public ResponseEntity<String> addProduct(@RequestBody Product product) {
        if(ProductService.addProduct(product)){
            return ResponseEntity.status(HttpStatus.CREATED).body("Product Has Been Successfully Created");
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Product creation failed");
        }
    }


    @GetMapping
    public ResponseEntity<List<Product>> getProducts() {
        List<Product> products = ProductService.getProducts();
        return ResponseEntity.ok(products);
    }


    @GetMapping(path = "{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id") Long id) {
        Product product = ProductService.getProduct(id);
        if (product != null) {
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping(path = "{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable("id") Long id) {
        if (ProductService.deleteProduct(id)) {
            return ResponseEntity.status(HttpStatus.OK).body("Product Has Been Deleted Successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid product ID");
        }
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<String> updateProduct(@PathVariable("id") Long id, @RequestBody Product updatedProduct) {
        if(ProductService.getProduct(id) == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid product ID");
        updatedProduct.setId(id);

        if (ProductService.updateProduct(updatedProduct)) {
            return ResponseEntity.status(HttpStatus.OK).body("Product has been updated successfully");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid product");
        }
    }


}
