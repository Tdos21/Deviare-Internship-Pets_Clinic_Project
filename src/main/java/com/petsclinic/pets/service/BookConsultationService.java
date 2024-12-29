package com.petsclinic.pets.service;

import com.petsclinic.pets.model.BookConsultation;
import com.petsclinic.pets.repository.BookConsultationRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookConsultationService {

    @Autowired
    private BookConsultationRepository repository;

    private final BookConsultationRepository bookRepository;

    @Autowired
    public BookConsultationService(BookConsultationRepository repository) {
        this.bookRepository = repository;
    }

    public BookConsultation createConsultation(BookConsultation consultation) {
        // Calculate total cost dynamically based on sickness severity
        double cost = calculateCost(consultation.getSickness().getSickName());
        consultation.setConsultTotalCost(cost);

        return repository.save(consultation);
    }

    public BookConsultation getConsultationById(int id) {
        return repository.findById(id).orElse(null);
    }

    public BookConsultation updateConsultation(BookConsultation consultation) {
        return repository.save(consultation);
    }

    public void deleteConsultation(int id) {
        repository.deleteById(id);
    }
    
    public List<BookConsultation> getAllConsultations() {
        return (List<BookConsultation>) repository.findAll();
    }

    private double calculateCost(String sicknessName) {
        switch (sicknessName.toLowerCase()) {
            case "mild fever":
                return 50.0; // Example cost
            case "severe illness":
                return 150.0; // Example cost
            default:
                return 100.0; // Default cost
        }
    }
}
