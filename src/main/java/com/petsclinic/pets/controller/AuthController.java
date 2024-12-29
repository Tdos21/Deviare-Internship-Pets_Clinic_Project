package com.petsclinic.pets.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.petsclinic.pets.model.LoginRequest;
import com.petsclinic.pets.model.Owner;
import com.petsclinic.pets.repository.AdminRepository;
import com.petsclinic.pets.repository.OwnersRepository;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/auth")
public class AuthController {
	
	
	@Autowired
	OwnersRepository ownerRepository;
	
	@PostMapping(path = "/loginRequest")
    public String login(@RequestParam("email") String email,
                        @RequestParam("password") String password,
                        Model model) {
        // Retrieve admin from the database
        Owner admin = ownerRepository.findByEmail(email);

        if (admin != null && admin.getPassword().equals(password)) {
            return "ownerDashboard"; // Redirect to admin dashboard
        } else {
            model.addAttribute("error", "Invalid username or password.");
            return "login"; // Reload login page with error message
        }
    }
    

	
	 @GetMapping("/login")
	    public String showLoginPage(HttpSession session) {
	        // If the user is already logged in, redirect to the homepage
	        if (session.getAttribute("user") != null) {
	            return "redirect:/ownerDashboard";  // Redirect to the homepage
	        }
	        return "ownerLogin";  // Otherwise, show the login page
	    }


	  @GetMapping(path = "/logout")
	    public String logout(HttpServletRequest request, HttpServletResponse response) {
	        // Invalidate the session and clear the security context
	        SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
	        logoutHandler.logout(request, response, null);

	        // Redirect to the login page after logout
	        return "redirect:/auth/login";  // Redirect to the login page after logout
	    }
    /**
   

    @PostMapping(path = "/loginRequest")
    public String login(@RequestBody LoginRequest loginRequest, Model model) {
        // Authentication handled by Spring Security, so no need for manual authentication here

        // After a successful login, Spring Security will automatically authenticate the user
        if (SecurityContextHolder.getContext().getAuthentication() != null) {
            // Add the authenticated user's details to the model for Thymeleaf
            model.addAttribute("email", loginRequest.getEmail());

            // Redirect to the ownerDashboard template (or another dashboard page)
            return "ownerDashboard";  // Redirect to the homepage after successful login
        } else {
            // Handle authentication failure (Spring Security will handle invalid login attempts)
            model.addAttribute("error", "Invalid email or password");
            return "ownerLogin";  // Return to the login page with an error
        }
    }

  
    **/

    // Optionally, you can create a Registration endpoint where new owners can sign up
}
