package com.storeManager.Dmart.Services;

import com.storeManager.Dmart.dto.StoreDTO;
import com.storeManager.Dmart.models.Store;

public interface StoreService {

    public Store create_new_store(StoreDTO storeDTO);

}
