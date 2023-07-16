package com.storeManager.Dmart.services;

import com.storeManager.Dmart.dto.StoreDTO;
import com.storeManager.Dmart.models.Store;
import com.storeManager.Dmart.repositories.StoreRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StoreServiceImpl implements  StoreService{

    @Autowired
    private StoreRepo storeRepo;
    @Autowired
    private ModelMapper modelMapper;


//    It create a new store in database
    public Store create_new_store(StoreDTO storeDTO){
        Store store_for_save=modelMapper.map(storeDTO,Store.class);
        return storeRepo.save(store_for_save);
    }

}
