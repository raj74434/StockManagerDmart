package com.storeManager.Dmart.controllers;

import com.storeManager.Dmart.services.TransectionService;
import com.storeManager.Dmart.models.Transection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/transection")
public class TransectionController {
    @Autowired
    private TransectionService transectionService;

//    It show all transection which are happening between 2 stores
    @GetMapping("/getAllTransection")
    public ResponseEntity<List<Transection>> total_quantity_of_all_stock_for_each_store(){

        return new ResponseEntity<>(transectionService.see_all_transection_between_stores(), HttpStatus.OK);
    }

}
