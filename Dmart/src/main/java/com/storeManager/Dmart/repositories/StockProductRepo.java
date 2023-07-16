package com.storeManager.Dmart.repositories;

import com.storeManager.Dmart.dto.StoreProductQuantity;
import com.storeManager.Dmart.models.StockProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StockProductRepo extends JpaRepository<StockProduct,Integer> {



}
