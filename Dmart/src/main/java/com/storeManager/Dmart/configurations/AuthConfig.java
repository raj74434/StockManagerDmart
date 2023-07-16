package com.storeManager.Dmart.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


//Here we did configuration for spring security

@Configuration
public class AuthConfig {


        @Bean
        public SecurityFilterChain securityfilter(HttpSecurity http) throws Exception{

            http.csrf().disable()
                    .authorizeHttpRequests()
                    .antMatchers(HttpMethod.POST,"/register/asAdmin").permitAll() //allowed to access this api to everyone
                    .anyRequest().authenticated() //Other api are authenticated
                    .and()
                    .formLogin()  //help in login from browser
                    .and()
                    .httpBasic();// help in login from postman or other front end applications

            return http.build();


        }

//        It help in encode password
    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();

    }

}
