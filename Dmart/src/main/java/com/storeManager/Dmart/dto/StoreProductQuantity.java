package com.storeManager.Dmart.dto;

import com.storeManager.Dmart.models.Store;

public class StoreProductQuantity {

    private Integer storeId;

    private Integer itemQuantity;


    public StoreProductQuantity(Integer storeId,Integer itemQuantity ){
        this.itemQuantity=itemQuantity;
        this.storeId=storeId;
    }

    public StoreProductQuantity(Integer itemQuantity ){

        this.itemQuantity=itemQuantity;
    }


    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public Integer getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(Integer itemQuantity) {
        this.itemQuantity = itemQuantity;
    }
}
