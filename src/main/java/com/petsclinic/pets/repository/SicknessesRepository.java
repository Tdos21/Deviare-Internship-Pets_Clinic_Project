package com.petsclinic.pets.repository;

import org.springframework.data.repository.CrudRepository;

import com.petsclinic.pets.model.PetSicknesses;

public interface SicknessesRepository extends CrudRepository<PetSicknesses, Integer> {

}
