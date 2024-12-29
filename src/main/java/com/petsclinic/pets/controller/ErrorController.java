package com.petsclinic.pets.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorController {
	
	@RequestMapping("/auth/access-denied")
    public String accessDenied() {
        return "access-denied"; // Return the name of the view (e.g., an HTML page)
    }

}
