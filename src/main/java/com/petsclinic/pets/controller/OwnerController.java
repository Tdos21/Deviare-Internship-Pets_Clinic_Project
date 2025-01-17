package com.petsclinic.pets.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import com.petsclinic.pets.model.Admin;
import com.petsclinic.pets.model.Owner;
import com.petsclinic.pets.model.Response;
import com.petsclinic.pets.repository.OwnersRepository;
import com.petsclinic.pets.service.OwnerService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping(path="/owners")
public class OwnerController {

    @Autowired
    private OwnersRepository ownerRepository;

    // Display the form to create a new owner
    @GetMapping("/create")
    public String getCreateForm() {
        return "createOwnerForm"; // Returns the Thymeleaf template for the form
    }

    @Autowired
    private OwnerService ownerService;

    @PostMapping(path = "/createOwner")
    public String registerOwner(
            @RequestParam("ownerFullName") String ownerFullName,
            @RequestParam("phone") String phone,
            @RequestParam("email") String email,
            @RequestParam("ownerAddress") String ownerAddress,
            @RequestParam(value = "isEmployed", required = false, defaultValue = "false") Boolean isEmployed,
            @RequestParam("password") String password,
            Model model, HttpSession session) {

        try {
            Owner owner = ownerService.registerOwner(ownerFullName, phone,email, ownerAddress, isEmployed, password);
            session.setAttribute("loggedInOwner", owner); // Optionally store owner in session
            return "ownerDashboard"; // Redirect to the Thymeleaf dashboard page
        } catch (Exception exception) {
            model.addAttribute("errorMessage", "User not saved: " + exception.getMessage());
            return "errorPage"; // Thymeleaf error page
        }
    }


    @GetMapping("/all")
    public String getAllOwners(Model model) {
        try {
            // Fetch all owners from the repository
            Iterable<Owner> owners = ownerRepository.findAll();
            model.addAttribute("owners", owners); // Add the owners to the model for the view
            return "ownersList"; // Return the view for displaying the list of owners (you will need to create this Thymeleaf template)
        } catch (Exception exception) {
            model.addAttribute("errorMessage", "Error fetching owners: " + exception.getMessage());
            return "error"; // Return an error page in case of failure
        }
    }

    
    
    /**
    // Display the login page
    @GetMapping("/login")
    public String login() {
        return "ownerLogin"; // Return the login page view
    }

    // Display the owner dashboard page
    @GetMapping("/ownerDashboard")
    public String dashboardPage() {
        return "ownerDashboard"; // Return the dashboard page view
    }
    
    
    /**
    @PostMapping(path = "/loginRequest")
    public String login(@RequestParam("email") String email,
                        @RequestParam("password") String password,
                        Model model) {
        // Retrieve admin from the database
        Owner owner = ownerRepository.findByEmail(email);

        if (owner != null && owner.getPassword().equals(password)) {
            return "ownerDashboard"; // Redirect to admin dashboard
        } else {
            model.addAttribute("error", "Invalid username or password.");
            return "login"; // Reload login page with error message
        }
    }
    **/
}
