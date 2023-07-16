package com.storeManager.Dmart.services;

import com.storeManager.Dmart.models.Product;
import com.storeManager.Dmart.repositories.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepo productRepo;

//    This method create a new product in database and return the new product which is saved in database
    @Override
    public Product create_new_product(Product product){
        return productRepo.save(product);
    }

}
