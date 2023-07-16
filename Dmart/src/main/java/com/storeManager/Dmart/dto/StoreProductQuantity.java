package com.storeManager.Dmart.dto;

import com.storeManager.Dmart.models.Store;
import lombok.Data;

@Data
public class StoreProductQuantity {

    private Integer storeId;

    private Integer itemQuantity;


    public StoreProductQuantity(Integer storeId,Integer itemQuantity ){
        this.itemQuantity=itemQuantity;
        this.storeId=storeId;
    }

}
