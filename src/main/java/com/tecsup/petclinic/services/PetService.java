package com.tecsup.petclinic.services;

import java.util.List;

import com.tecsup.petclinic.entities.Pet;
import com.tecsup.petclinic.exception.PetNotFoundException;

public interface PetService {

	Pet create(Pet pet);

	Pet update(Pet pet);

	void delete(Integer id) throws PetNotFoundException;

	Pet findById(Integer id) throws PetNotFoundException;

	List<Pet> findByName(String name);

	List<Pet> findByTypeId(int typeId);

	List<Pet> findByOwnerId(int ownerId);

	List<Pet> findAll();
}