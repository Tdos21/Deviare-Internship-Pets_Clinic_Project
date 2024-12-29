package com.petsclinic.pets.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path="/homepage")
public class HomeController {
    
    @GetMapping("/home")
    public String homepage() {
        // Return the view name "index" (without the extension, assuming "index.html" or "index.jsp" exists)
        return "index";
    }
}
