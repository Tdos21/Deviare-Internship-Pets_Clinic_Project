package com.petsclinic.pets.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.petsclinic.pets.model.BookConsultation;
import com.petsclinic.pets.repository.BookConsultationRepository;
import com.petsclinic.pets.repository.OwnersRepository;
import com.petsclinic.pets.repository.PetsRepository;
import com.petsclinic.pets.repository.SicknessesRepository;
import com.petsclinic.pets.service.BookConsultationService;


import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/api")
public class BookConsultationController {
	
	@Autowired
    private OwnersRepository ownerRepository;

    @Autowired
    private PetsRepository petRepository;

    @Autowired
    private SicknessesRepository sicknessRepository;
    
    BookConsultationRepository consultRepository;



    @Autowired
    private BookConsultationService service;

    @PostMapping(path="/bookAppoint")
    public BookConsultation createConsultation(@RequestBody BookConsultation consultation) {
        return service.createConsultation(consultation);
    }

    @GetMapping("/{id}")
    public BookConsultation getConsultation(@PathVariable int id) {
        return service.getConsultationById(id);
    }

    @GetMapping("/cunsultIndex")
    public List<BookConsultation> getAllConsultations() {
        return service.getAllConsultations();
    }

    @PutMapping(path="/updateAppoint")
    public BookConsultation updateConsultation(@RequestBody BookConsultation consultation) {
        return service.updateConsultation(consultation);
    }

    @DeleteMapping("/{id}")
    public void deleteConsultation(@PathVariable int id) {
        service.deleteConsultation(id);
    }
    
    @GetMapping("/bookPage")
    public String showLoginPage(HttpSession session) {
        // If the user is already logged in, redirect to the homepage
        if (session.getAttribute("user") != null) {
            return "redirect:/createBooking";  // Redirect to the homepage
        }
        return "ownerLogin";  // Otherwise, show the login page
    }
    
    @GetMapping("/create")
    public String createBookingForm(Model model) {
        model.addAttribute("owners", ownerRepository.findAll());
        model.addAttribute("pets", petRepository.findAll());
        model.addAttribute("sicknesses", sicknessRepository.findAll());
        model.addAttribute("booking", new BookConsultation());
        
        return "createBooking";
    }

    //private final BookConsultationRepository consultsRepository;

    private final BookConsultationService consultationService;

    @Autowired
    public BookConsultationController(BookConsultationService consultationService) {
        this.consultationService = consultationService;
    }

    @PostMapping("/createAppoint")
    public String saveBooking(@ModelAttribute BookConsultation booking, Model model) {
        // Use the service to create the consultation
        BookConsultation savedBooking = consultationService.createConsultation(booking);
        
        // Add any necessary attributes to the model
        model.addAttribute("message", "Booking saved successfully");
        model.addAttribute("consultation", savedBooking);
        
        return "ownerDashboard"; // Redirect to the Thymeleaf view
    }
}
