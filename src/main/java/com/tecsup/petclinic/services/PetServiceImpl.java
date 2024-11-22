package com.tecsup.petclinic.services;

import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.tecsup.petclinic.entities.Pet;
import com.tecsup.petclinic.exception.PetNotFoundException;
import com.tecsup.petclinic.repositories.PetRepository;

@Service
@Slf4j
public class PetServiceImpl implements PetService {

	PetRepository petRepository;

	public PetServiceImpl (PetRepository petRepository) {
		this. petRepository = petRepository;
	}

	@Override
	public Pet create(Pet pet) {
		return petRepository.save(pet);
	}

	@Override
	public Pet update(Pet pet) {
		return petRepository.save(pet);
	}

	@Override
	public void delete(Integer id) throws PetNotFoundException{
		Pet pet = findById(id);
		petRepository.delete(pet);
	}

	@Override
	public Pet findById(Integer id) throws PetNotFoundException {
		Optional<Pet> pet = petRepository.findById(id);
		if ( !pet.isPresent())
			throw new PetNotFoundException("Record not found...!");
		return pet.get();
	}

	@Override
	public List<Pet> findByName(String name) {
		List<Pet> pets = petRepository.findByName(name);
		pets.stream().forEach(pet -> log.info("" + pet));
		return pets;
	}

	@Override
	public List<Pet> findByTypeId(int typeId) {
		List<Pet> pets = petRepository.findByTypeId(typeId);
		pets.stream().forEach(pet -> log.info("" + pet));
		return pets;
	}

	@Override
	public List<Pet> findByOwnerId(int ownerId) {
		List<Pet> pets = petRepository.findByOwnerId(ownerId);
		pets.stream().forEach(pet -> log.info("" + pet));
		return pets;
	}

	@Override
	public List<Pet> findAll() {
		return petRepository.findAll();
	}
}