package com.storeManager.Dmart.authService;

import com.storeManager.Dmart.models.User;
import com.storeManager.Dmart.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;



@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user= userRepo.findByEmail(email);

        if(user!=null) {

            List<GrantedAuthority> authorities= new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority(user.getRole()));
           return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);

//            return new CustomeUserDetail(user);


        }else
            throw new BadCredentialsException("User Details not found with this phone");


    }

}
