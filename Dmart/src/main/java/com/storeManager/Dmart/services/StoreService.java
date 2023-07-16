package com.storeManager.Dmart.services;

import com.storeManager.Dmart.dto.StoreDTO;
import com.storeManager.Dmart.models.Store;

public interface StoreService {

    //These services availables in implementaion of this interface
    public Store create_new_store(StoreDTO storeDTO);

}
