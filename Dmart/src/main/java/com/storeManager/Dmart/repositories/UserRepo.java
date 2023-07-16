package com.storeManager.Dmart.repositories;

import com.storeManager.Dmart.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

//  Repository of User
public interface UserRepo extends JpaRepository<User,Integer> {

    public User findByEmail(String email);

}
