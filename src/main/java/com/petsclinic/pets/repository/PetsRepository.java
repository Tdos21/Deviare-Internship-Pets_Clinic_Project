package com.petsclinic.pets.repository;



import java.util.List;

//import java.util.List;


import org.springframework.data.repository.CrudRepository;

import com.petsclinic.pets.model.Owner;
import com.petsclinic.pets.model.Pet;

public interface PetsRepository extends CrudRepository<Pet, Integer> {

	List<Pet> findByOwner(Owner owner);
	
//	List<Pet> findByOwnerId(Integer ownerId);

}
