package com.storeManager.Dmart.controllers;

import com.storeManager.Dmart.models.User;
import com.storeManager.Dmart.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/register")
public class UserController {

    @Autowired
    private UserRepo userRepo;

//    It help in encode a password
    @Autowired
    private PasswordEncoder passwordEncoder;


//  It register user as a admin so that user able to access all Apis
    @PostMapping("/asAdmin")
    public ResponseEntity<User> register_as_admin(@RequestBody User user) throws Exception{
        User isAlreadyPresent=userRepo.findByEmail(user.getEmail());
        if(isAlreadyPresent != null) throw  new Exception("User with this email is already present");

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("ADMIN");

        return new ResponseEntity<>(userRepo.save(user), HttpStatus.CREATED);
    }

}
