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

import com.petsclinic.pets.model.Owner;
import com.petsclinic.pets.model.Pet;
import com.petsclinic.pets.model.PetSicknesses;
import com.petsclinic.pets.service.PetService;

import jakarta.servlet.http.HttpSession;



@Controller
@RequestMapping(path="/pets")
public class PetsController {

	@Autowired
	private PetService petService;

	@PostMapping(path="/createPet")
	public String addPet(
	        @ModelAttribute Pet pet,
	        HttpSession session, // Use HttpSession to retrieve logged-in user
	        Model model) {
	    // Retrieve the logged-in owner from the session
	    Owner loggedInOwner = (Owner) session.getAttribute("loggedInOwner");

	    if (loggedInOwner == null) {
	        // If no user is in session, redirect to the login page
	        model.addAttribute("errorMessage", "You must be logged in to create a pet.");
	        return "ownerLogin";  // Redirect to the login page
	    }

	    try {
	        // Get the ownerId from the logged-in owner and register the pet
	        int ownerId = loggedInOwner.getOwnerId();
	        petService.registerPet(pet, ownerId);
	    } catch (Exception e) {
	        e.printStackTrace();
	        model.addAttribute("errorMessage", "Failed to save pet. Please try again.");
	        return "createPetForm";  // Redirect back to the form in case of error
	    }

	    // Redirect to the owner dashboard upon successful registration
	    return "ownerDashboard";  // Replace with the correct URL for the dashboard
	}



	@PutMapping("/edit/{petId}")
	public String editPet(@PathVariable Integer petId, @ModelAttribute Pet petDetails, Model model) {
	    System.out.println("Received petId: " + petId);

	    try {
	        Pet updatedPet = petService.editPet(petId, petDetails);

	        model.addAttribute("message", "Pet details updated successfully!");
	        return "adminDashboard"; // Redirect to adminDashboard after successful update
	    } catch (Exception e) {
	        model.addAttribute("error", "Error: " + e.getMessage());
	        return "error"; // Return to error page in case of an exception
	    }
	}





    
	@GetMapping("/editGet/{petId}")
	public String showEditForm(@PathVariable Integer petId, Model model) {
	    Pet pet = petService.getPetById(petId);
	    model.addAttribute("pet", pet);
	    return "editPetForm"; // Replace with your actual Thymeleaf template name
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

