package com.tecsup.petclinic.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tecsup.petclinic.entities.Pet;

@Repository
public interface PetRepository 
	extends JpaRepository<Pet, Integer> {

	List<Pet> findByName(String name);

	List<Pet> findByTypeId(int typeId);

	List<Pet> findByOwnerId(int ownerId);

	@Override
	List<Pet> findAll();

}
