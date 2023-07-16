package com.storeManager.Dmart.repositories;

import com.storeManager.Dmart.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Product,Integer> {
}
