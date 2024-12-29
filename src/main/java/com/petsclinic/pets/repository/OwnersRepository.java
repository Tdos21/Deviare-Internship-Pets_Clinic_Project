package com.petsclinic.pets.repository;

import java.util.Optional;

//import java.util.Optional;


import org.springframework.data.repository.CrudRepository;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetails;

import com.petsclinic.pets.model.Owner;

public interface OwnersRepository extends CrudRepository<Owner, Integer> {

	Owner findByEmail(String email);

	Optional<Owner> findByOwnerId(int ownerId);

	
  

}
