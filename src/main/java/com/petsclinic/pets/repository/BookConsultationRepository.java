package com.petsclinic.pets.repository;


import org.springframework.data.repository.CrudRepository;

import com.petsclinic.pets.model.BookConsultation;

public interface BookConsultationRepository extends CrudRepository<BookConsultation, Integer> {

}
