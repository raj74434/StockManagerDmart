package com.storeManager.Dmart.controllers;

import com.storeManager.Dmart.Services.StockService;
import com.storeManager.Dmart.Services.StoreService;
import com.storeManager.Dmart.dto.StoreDTO;
import com.storeManager.Dmart.models.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/store")
public class StoreController {

    @Autowired
    private StoreService storeService;

    @PostMapping("/create")
    public ResponseEntity<Store> createStore(@RequestBody StoreDTO storeDTO){
        System.out.println(storeDTO.getStoreName());
        return new ResponseEntity<>(storeService.create_new_store(storeDTO), HttpStatus.CREATED);
    }

}
