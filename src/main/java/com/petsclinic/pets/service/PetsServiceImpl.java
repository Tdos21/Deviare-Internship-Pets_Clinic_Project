/**
package com.petsclinic.pets.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.petsclinic.pets.interfaces.PetService;
import com.petsclinic.pets.model.Pet;
import com.petsclinic.pets.repository.PetsRepository;

@Service
public class PetsServiceImpl implements PetService {

    private final PetsRepository petRepository;

    @Autowired
    public PetsServiceImpl(PetsRepository petRepository) {
        this.petRepository = petRepository;
    }

    @Override
    public Pet updatePet(Integer id, Pet updatedPet) {
        // Find the existing pet by ID
        return petRepository.findById(id)
                .map(existingPet -> {
                    existingPet.setPetName(updatedPet.getPetName());
                    existingPet.setPetType(updatedPet.getPetType());
                    existingPet.setPetWeight(updatedPet.getPetWeight());
                    existingPet.setHealthy(updatedPet.isHealthy());
                    existingPet.setPetAddress(updatedPet.getPetAddress());
                    existingPet.setOwnerName(updatedPet.getOwnerName());
                    return petRepository.save(existingPet);
                })
                .orElseThrow();
    }
}
**/
