
package com.petsclinic.pets.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petsclinic.pets.model.Owner;
import com.petsclinic.pets.model.Pet;
import com.petsclinic.pets.repository.OwnersRepository;
import com.petsclinic.pets.repository.PetsRepository;

@Service
public class PetService {

    @Autowired
    private PetsRepository petRepository;

    @Autowired
    private OwnersRepository ownerRepository;  // Directly using OwnerRepository

    // Method to register a pet
    public Pet registerPet(Pet pet, int ownerId) throws Exception {
        // Find the owner by ownerId
        Optional<Owner> ownerOpt = ownerRepository.findByOwnerId(ownerId);
        if (ownerOpt.isEmpty()) {
            throw new Exception("Owner not found with id " + ownerId);
        }

        Owner owner = ownerOpt.get();

        // Set the owner and owner's name in the pet
        pet.setOwner(owner);
        pet.setOwnerName(owner.getOwnerFullName());

        // Set the pet's address to the owner's address
        pet.setPetAddress(owner.getOwnerAddress());

        // Save the pet
        return petRepository.save(pet);
    }

    // Method to edit a pet's details
    public Pet editPet(Integer petId, Pet petDetails) throws Exception {
        Optional<Pet> petOpt = petRepository.findById(petId);
        if (petOpt.isEmpty()) {
            throw new Exception("Pet not found with id " + petId);
        }

        Pet pet = petOpt.get();
        pet.setPetId(petDetails.getPetId());
        pet.setPetName(petDetails.getPetName());
        pet.setPetWeight(petDetails.getPetWeight());
        pet.setPetType(petDetails.getPetType());
        pet.setHealthy(petDetails.isHealthy());
        pet.setPetAddress(petDetails.getPetAddress());
        pet.setOwnerName(petDetails.getOwnerName());
        return petRepository.save(pet);
    }


    // Method to get all pets
    public List<Pet> getAllPets() {
        return (List<Pet>) petRepository.findAll();
    }

    // Method to delete a pet by ID
    public void deletePet(Integer petId) throws Exception {
        Optional<Pet> petOpt = petRepository.findById(petId);
        if (petOpt.isEmpty()) {
            throw new Exception("Pet not found with id " + petId);
        }

        petRepository.deleteById(petId);
    }
    
    
    public Pet getPetById(Integer petId) {
        // Assuming the PetRepository has a findById method
        return petRepository.findById(petId).orElse(null);
    }

}