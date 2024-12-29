package com.petsclinic.pets.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .anyRequest().permitAll() // Allow all requests without authentication
            .and()
            .formLogin()
                .loginPage("/auth/login") // Specify the login page
                .permitAll(); // Allow access to the login page for everyone

        // Disable sessions and CSRF as they're unnecessary for this setup
        http.csrf().disable();
        http.sessionManagement().disable();

        return http.build();
    }
}
