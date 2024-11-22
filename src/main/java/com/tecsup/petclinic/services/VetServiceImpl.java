package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.Vet;
import com.tecsup.petclinic.exception.VetNotFoundException;
import java.util.List;
import java.util.Optional;
import com.tecsup.petclinic.repositories.VetRepository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class VetServiceImpl implements VetService {

    private final VetRepository vetRepository;

    // Constructor que inyecta VetRepository
    public VetServiceImpl(VetRepository vetRepository) {
        this.vetRepository = vetRepository;
    }

    @Override
    public Vet create(Vet vet) {
        return vetRepository.save(vet);
    }

    @Override
    public Vet update(Vet vet) {
        return vetRepository.save(vet);
    }

    @Override
    public void delete(Integer id) throws VetNotFoundException {
        Vet vet = findById(id);
        vetRepository.delete(vet);
    }

    @Override
    public Vet findById(Integer id) throws VetNotFoundException {
        Optional<Vet> vet = vetRepository.findById(id);
        if (!vet.isPresent()) {
            throw new VetNotFoundException("Veterinarian not found with ID: " + id);
        }
        return vet.get();
    }

    @Override
    public List<Vet> findByFirstName(String firstName) {
        List<Vet> vets = vetRepository.findByFirstName(firstName);
        vets.forEach(vet -> log.info("Found vet: " + vet));
        return vets;
    }

    @Override
    public List<Vet> findByLastName(String lastName) {
        List<Vet> vets = vetRepository.findByLastName(lastName);
        vets.forEach(vet -> log.info("Found vet: " + vet));
        return vets;
    }

    @Override
    public List<Vet> findAll() {
        return vetRepository.findAll();
    }
}