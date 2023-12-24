package com.APIX.product.service;

import com.APIX.product.dao.ProductDAO;
import com.APIX.product.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private  ProductDAO productDAO;
    public int addProduct(Product product){
        return productDAO.insertProduct(product);
    }

    public List<Product> getProducts(){
        return productDAO.getProducts();
    }
}
