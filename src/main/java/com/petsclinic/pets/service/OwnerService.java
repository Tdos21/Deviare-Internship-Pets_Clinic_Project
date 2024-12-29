package com.petsclinic.pets.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petsclinic.pets.model.Owner;
import com.petsclinic.pets.repository.OwnersRepository;

@Service
public class OwnerService {

    @Autowired
    private OwnersRepository ownerRepository;

    public Owner registerOwner(String ownerFullName, String phone, String ownerAddress, 
                               Boolean isEmployed, String email, String password) throws Exception {
        Owner owner = new Owner(null, ownerFullName, phone, email, isEmployed, ownerAddress, password);
        return ownerRepository.save(owner); // Save owner and return the saved instance
    }
}
