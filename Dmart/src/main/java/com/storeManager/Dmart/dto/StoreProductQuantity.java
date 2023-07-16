package com.storeManager.Dmart.dto;

import com.storeManager.Dmart.models.Store;
import lombok.Data;

// It help in update quantity of store stock by taking input from user.
@Data
public class StoreProductQuantity {

    private Integer storeId;

    private Integer itemQuantity;


    public StoreProductQuantity(Integer storeId,Integer itemQuantity ){
        this.itemQuantity=itemQuantity;
        this.storeId=storeId;
    }

}
