package com.petsclinic.pets.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
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

        // Configure session management
        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED) // Create session only if required
                .maximumSessions(1) // Allow only one session per user
                .expiredUrl("/auth/login?expired=true"); // Redirect to login page when session expires

        // Disable CSRF for simplicity (enable in production for security)
        http.csrf().disable();

        return http.build();
    }
}
