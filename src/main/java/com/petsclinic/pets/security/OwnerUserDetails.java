package com.petsclinic.pets.security;

import com.petsclinic.pets.model.Owner;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class OwnerUserDetails implements UserDetails {

    private final Owner owner;

    public OwnerUserDetails(Owner owner) {
        this.owner = owner;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // You can map roles here if needed, or set a default role for now
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return owner.getPassword();
    }

    @Override
    public String getUsername() {
        return owner.getEmail(); // Use email as the unique identifier for login
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Add logic if you want to handle account expiration
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Add logic if you want to handle account lockout
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Add logic if you want to handle credential expiration
    }

    @Override
    public boolean isEnabled() {
        return true; // Add logic if you want to handle account activation
    }

    public Owner getOwner() {
        return owner;
    }
}
