package com.storeManager.Dmart.controllers;

import com.storeManager.Dmart.services.ProductService;
import com.storeManager.Dmart.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


// All product related api will present here like add new product
@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

//    This api create a new product in our product tables
    @PostMapping("/create")
    public ResponseEntity<Product> create_new_product(@RequestBody Product product){
        return new ResponseEntity<>(productService.create_new_product(product), HttpStatus.CREATED);
    }

}
