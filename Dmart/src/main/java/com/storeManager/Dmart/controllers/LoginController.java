package com.storeManager.Dmart.controllers;

import com.storeManager.Dmart.models.User;
import com.storeManager.Dmart.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @Autowired
    private UserRepo userRepo;

//  This api help in login
    @GetMapping("/signIn")
    public ResponseEntity<User> getLogin(Authentication auth){


        User user=userRepo.findByEmail(auth.getName());
        if(user !=null)return new ResponseEntity<>(user, HttpStatus.ACCEPTED);
        else throw new BadCredentialsException(("No user found with this number"));
    }

}
