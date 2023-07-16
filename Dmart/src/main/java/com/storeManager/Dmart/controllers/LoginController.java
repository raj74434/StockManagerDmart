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


    @GetMapping("/signIn")
    public ResponseEntity<User> getLogin(Authentication auth){
        System.out.println(auth.getName()+"    "+auth.getCredentials());

        User u=userRepo.findByEmail(auth.getName());
        if(u !=null)return new ResponseEntity<>(u, HttpStatus.ACCEPTED);
        else throw new BadCredentialsException(("No user found with this number"));
    }

}
