package com.storeManager.Dmart.repositories;

import com.storeManager.Dmart.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

// Repository of Product
public interface ProductRepo extends JpaRepository<Product,Integer> {
}
