package com.storeManager.Dmart.repositories;

import com.storeManager.Dmart.models.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepo extends JpaRepository<Store,Integer> {
}
