package com.petsclinic.pets.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import com.petsclinic.pets.model.Admin;
import com.petsclinic.pets.model.BookConsultation;
import com.petsclinic.pets.model.Owner;
import com.petsclinic.pets.model.Pet;
import com.petsclinic.pets.model.Response;
import com.petsclinic.pets.repository.AdminRepository;
import com.petsclinic.pets.repository.BookConsultationRepository;
import com.petsclinic.pets.repository.OwnersRepository;
import com.petsclinic.pets.repository.PetsRepository;

import org.springframework.ui.Model;

@Controller
@RequestMapping(path = "/admins")
public class AdminController {
	
	@Autowired
	AdminRepository adminRepository;
	
	@Autowired
	OwnersRepository ownerRepository;
	
	@Autowired
	PetsRepository petsRepository;
	
	@Autowired
	BookConsultationRepository appointRepository;
	
	@PostMapping(path = "/add")
    public ResponseEntity<Response> addBooking(
            @RequestParam String username,
            @RequestParam String password
            ) {
        
        Admin admin = new Admin(null, username, password);
        System.out.println("Pets Clinic: " + admin);
        
        try {
            adminRepository.save(admin);
            Response response = new Response(101, "Admin " + username+ " created successfully");
            return new ResponseEntity<Response>(response, HttpStatus.OK);
            
        } catch (Exception exception) {
            Response response = new Response(701, "Admin " + username + " creation failed . Exception: " + exception.getMessage());
            return new ResponseEntity<Response>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	
	   // Handle the login form submission
    @PostMapping(path = "/loginRequest")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Model model) {
        // Retrieve admin from the database
        Admin admin = adminRepository.findByUsername(username);

        if (admin != null && admin.getPassword().equals(password)) {
            return "adminDashboard"; // Redirect to admin dashboard
        } else {
            model.addAttribute("error", "Invalid username or password.");
            return "adminLogin"; // Reload login page with error message
        }
    }
    
    @GetMapping("/login")
    public String login() {
        return "adminLogin";
    }
    
    @GetMapping("/adminDashboard")
    public String dashboardPage() {
        return "adminDashboard";
    }
    
    @GetMapping("/ownersIndex")
    public String showAllOwners(Model model) {
        // Fetch all users from the database
        List<Owner> userList = (List<Owner>) ownerRepository.findAll();

        // Add the list of users to the model
        model.addAttribute("owners", userList);

        // Return the name of the template to display the list of users
        return "ownersList"; 
    }
    
    @GetMapping("/petsIndex")
    public String showAllPets(Model model) {
        // Fetch all users from the database
        List<Pet> petList = (List<Pet>) petsRepository.findAll();

        // Add the list of users to the model
        model.addAttribute("pets", petList);

        // Return the name of the template to display the list of users
        return "petsIndex"; 
    }
    
    @GetMapping("/bookingsIndex")
    public String showAllConsultations(Model model) {
        // Fetch all consultations from the database
        List<BookConsultation> appointList = (List<BookConsultation>) appointRepository.findAll();

        // Check if the list is empty
        if (appointList.isEmpty()) {
            System.out.println("No consultations found.");
        }

        // Add the list of consultations to the model
        model.addAttribute("consultations", appointList);

        // Return the name of the template to display the list of consultations
        return "appointList";
    }


}

