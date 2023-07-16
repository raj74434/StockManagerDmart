package com.storeManager.Dmart.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class AuthConfig {


        @Bean
        public SecurityFilterChain securityfilter(HttpSecurity http) throws Exception{

            http.csrf().disable()
                    .authorizeHttpRequests()
                    .antMatchers(HttpMethod.POST,"/ApI").permitAll()
                    .anyRequest().permitAll()
//                    .authenticated()
                    .and()
                    .formLogin()
                    .and()
                    .httpBasic();

            return http.build();


        }

}
