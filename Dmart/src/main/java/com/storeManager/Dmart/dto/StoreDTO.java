package com.storeManager.Dmart.dto;

import com.storeManager.Dmart.models.Product;

import java.util.ArrayList;
import java.util.List;

public class StoreDTO {

    private String storeName;

    private String storeAddress;



    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStoreAddress() {
        return storeAddress;
    }

    public void setStoreAddress(String storeAddress) {
        this.storeAddress = storeAddress;
    }


}
