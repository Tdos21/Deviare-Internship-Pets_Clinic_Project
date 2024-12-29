package com.petsclinic.pets.repository;


import org.springframework.data.repository.CrudRepository;

import com.petsclinic.pets.model.Admin;
import com.petsclinic.pets.model.Owner;

public interface AdminRepository extends CrudRepository<Admin, Integer> {

	Admin findByUsername(String username);

	

	

}
