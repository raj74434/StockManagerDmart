package com.storeManager.Dmart.Services;

import com.storeManager.Dmart.models.Transection;
import com.storeManager.Dmart.repositories.TransectionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


    @Service
    public class TransectionServiceImpl implements TransectionService{
    @Autowired
    private TransectionRepo transectionRepo;

    @Override
    public List<Transection> see_all_transection_between_stores(){
        return transectionRepo.findAll();
    }



}
