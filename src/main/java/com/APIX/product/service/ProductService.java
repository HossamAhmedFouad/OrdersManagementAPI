package com.APIX.product.service;

import com.APIX.CustomRepository;
import com.APIX.product.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private CustomRepository<Product, Long> productDAO;
    public boolean addProduct(Product product){
        return productDAO.save(product);
    }

    public List<Product> getProducts(){
        return productDAO.getAll();
    }

    public Product getProduct(Long id){
        return productDAO.getById(id);
    }

    public boolean deleteProduct(Long id){
        return productDAO.delete(id);
    }

    public boolean updateProduct(Product product){
        return productDAO.update(product);
    }
}
