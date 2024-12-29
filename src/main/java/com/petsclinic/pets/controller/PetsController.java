package com.petsclinic.pets.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.petsclinic.pets.model.Pet;
import com.petsclinic.pets.model.PetSicknesses;
import com.petsclinic.pets.service.PetService;



@Controller
@RequestMapping("/pets")
public class PetsController {

    @Autowired
    private PetService petService;

    @PostMapping("/createPet")
    public String addPet(
            @ModelAttribute Pet pet, 
            @AuthenticationPrincipal UserDetails loggedInUser, 
            Model model) {
        // Assuming the logged-in user has an 'ownerId' associated with their account
        int ownerId = Integer.parseInt(loggedInUser.getUsername());  // Assumes the 'username' is the 'ownerId'

        try {
            // Save the pet by passing the ownerId to the service
            petService.registerPet(pet, ownerId);
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("errorMessage", "Failed to save pet. Please try again.");
            return "createPet";  // Redirect back to the form in case of error
        }

        // Redirect to the owner dashboard
        return "ownerDashboard";  // Replace with the correct URL for the dashboard
    }


    @PutMapping("/edit/{petId}")
    public ResponseEntity<?> editPet(@PathVariable Integer petId, @RequestBody Pet petDetails) {
        Pet updatedPet = null;
		try {
			updatedPet = petService.editPet(petId, petDetails);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return ResponseEntity.ok(updatedPet);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllPets() {
        List<Pet> pets = petService.getAllPets();
        return ResponseEntity.ok(pets);
    }

    @DeleteMapping("/delete/{petId}")
    public ResponseEntity<?> deletePet(@PathVariable Integer petId) {
        try {
			petService.deletePet(petId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    
    @GetMapping("/create")
    public String showCreatePet(Model model) {
        // Add an empty PetSicknesses object to the model to bind form data
        model.addAttribute("pet", new Pet());
        return "createPetForm"; // Name of the Thymeleaf template (e.g., createSick.html)
    }
}

