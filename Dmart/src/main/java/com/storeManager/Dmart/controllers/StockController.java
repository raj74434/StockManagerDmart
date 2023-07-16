package com.storeManager.Dmart.controllers;

import com.storeManager.Dmart.Services.StockService;
import com.storeManager.Dmart.dto.SendOneToAntherStore;
import com.storeManager.Dmart.dto.StockProductDTO;
import com.storeManager.Dmart.dto.StoreProductQuantity;
import com.storeManager.Dmart.models.StockProduct;
import com.storeManager.Dmart.models.Transection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/stock")
public class StockController {

    @Autowired
    private StockService stockService;



    @PutMapping("/addnewstock")
    public ResponseEntity<List<StockProduct>>  add_new_product_in_stock(@RequestBody StockProductDTO stockProductDTO){

        return new ResponseEntity<>(stockService.add_new_product_in_store_stock(stockProductDTO), HttpStatus.ACCEPTED);
    }

    @PutMapping("/increaseStock")
    public ResponseEntity<List<StockProduct>>  increase_stock(@RequestBody StockProductDTO stockProductDTO){

        return new ResponseEntity<>(stockService.increase_stock_product_quantity(stockProductDTO), HttpStatus.ACCEPTED);
    }

    @PutMapping("/decreaseStock")
    public ResponseEntity<List<StockProduct>>decrease_stock_product_quantity(@RequestBody StockProductDTO stockProductDTO){
        return new ResponseEntity<>(stockService.decrease_stock_product_quantity(stockProductDTO), HttpStatus.ACCEPTED);
    }

    @PutMapping("/replaceStock")
    public ResponseEntity<List<StockProduct>>replace_old_stock_quantity(@RequestBody StockProductDTO stockProductDTO){
        return new ResponseEntity<>(stockService.replace_old_stock_product_quantity(stockProductDTO), HttpStatus.ACCEPTED);
    }


    @DeleteMapping("/deleteStock/{storeId}/{productId}")
    public ResponseEntity<List<StockProduct>>delete_single_product_from_stock(@PathVariable Integer storeId,@PathVariable Integer productId){
        return new ResponseEntity<>(stockService.delete_single_product_from_stock(storeId,productId), HttpStatus.ACCEPTED);
    }


    @PutMapping("/transferStock")
    public ResponseEntity<Transection>send_product_from_one_store_to_another(@RequestBody SendOneToAntherStore sendOneToAntherStore){
        return new ResponseEntity<>(stockService.send_item_from_one_store_to_another(
                sendOneToAntherStore.getSenderId(),
                sendOneToAntherStore.getReciverId(),
                sendOneToAntherStore.getProductId(),
                sendOneToAntherStore.getProductQuantity()), HttpStatus.ACCEPTED);
    }

    @GetMapping("/getStockOfAll")
    public ResponseEntity<List<StoreProductQuantity>>total_quantity_of_all_stock_for_each_store(){

        return new ResponseEntity<>(stockService.all_stock_of_each_store(), HttpStatus.OK);
    }

}
