package com.petsclinic.pets.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.petsclinic.pets.model.PetSicknesses;
import com.petsclinic.pets.repository.SicknessesRepository;

import java.util.Optional;

@RestController
@RequestMapping("/api/sickness")
public class SickController {

    @Autowired
    private SicknessesRepository sickRepository;

    // Save Sick data
    @PostMapping("/save")
    public ResponseEntity<String> saveSick(@RequestBody PetSicknesses sick) {
        sickRepository.save(sick);
        return ResponseEntity.ok("Sick data saved successfully!");
    }

    // Delete Sick data by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteSick(@PathVariable int id) {
        Optional<PetSicknesses> optionalSick = sickRepository.findById(id);
        if (optionalSick.isPresent()) {
            sickRepository.deleteById(id);
            return ResponseEntity.ok("Sick data deleted successfully!");
        } else {
            return ResponseEntity.status(404).body("Sick data not found!");
        }
    }
    
  //Get all Sick data
    @GetMapping("/all")
    public ResponseEntity<Iterable<PetSicknesses>> getAllSickData() {
     Iterable<PetSicknesses> sickList = sickRepository.findAll();
     return ResponseEntity.ok(sickList);
    }

    //Get Sick data by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getSickById(@PathVariable int id) {
     Optional<PetSicknesses> optionalSick = sickRepository.findById(id);
     if (optionalSick.isPresent()) {
         return ResponseEntity.ok(optionalSick.get());
     } else {
         return ResponseEntity.status(404).body("Sick data not found!");
     }
    }

    
    @GetMapping("/create")
    public String showCreateSickPage(Model model) {
        // Add an empty PetSicknesses object to the model to bind form data
        model.addAttribute("sick", new PetSicknesses());
        return "createSick"; // Name of the Thymeleaf template (e.g., createSick.html)
    }
}




