package com.APIX.product.service;

import com.APIX.CustomRepository;
import com.APIX.product.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private static CustomRepository<Product, Long> productDAO;
    @Autowired
    public ProductService(CustomRepository<Product, Long> productDAO) {
        ProductService.productDAO = productDAO;
    }
    public static boolean addProduct(Product product){
        return productDAO.save(product);
    }

    public static List<Product> getProducts(){
        return productDAO.getAll();
    }

    public static Product getProduct(Long id){
        return productDAO.getById(id);
    }

    public static boolean deleteProduct(Long id){
        return productDAO.delete(id);
    }

    public static boolean updateProduct(Product product){
        return productDAO.update(product);
    }

    public static boolean incrementProduct(Long id, int amount){
        Product product = getProduct(id);
        if(product == null) return false;
        product.setCount(product.getCount() + amount);
        return updateProduct(product);
    }

    public static boolean decrementProduct(Long id, int amount){
        Product product = getProduct(id);
        if(product == null) return false;
        if(product.getCount() - amount < 0) return false;
        product.setCount(product.getCount() - amount);
        return updateProduct(product);
    }
}
