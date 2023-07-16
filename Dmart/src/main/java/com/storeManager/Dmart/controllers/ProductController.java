package com.storeManager.Dmart.controllers;

import com.storeManager.Dmart.Services.ProductService;
import com.storeManager.Dmart.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/create")
    public ResponseEntity<Product> create_new_product(@RequestBody Product product){
        return new ResponseEntity<>(productService.create_new_product(product), HttpStatus.CREATED);
    }

}
