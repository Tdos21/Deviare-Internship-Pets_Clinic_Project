package com.petsclinic.pets.service;

/**
import com.petsclinic.pets.model.Owner;
import com.petsclinic.pets.repository.OwnersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public abstract class CustomUserDetailsService implements UserDetailsService {

	/**
    @Autowired
    private OwnersRepository ownerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Owner owner = ownerRepository.findByEmail(username).orElseThrow(() -> 
                new UsernameNotFoundException("Owner not found with email: " + username));

        return User.builder()
                .username(owner.getEmail())
                .password(owner.getPassword()) // Use plain text password
                .roles("USER")
                .build();
    }
    **/

