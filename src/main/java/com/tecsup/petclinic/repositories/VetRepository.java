package com.tecsup.petclinic.repositories;

import com.tecsup.petclinic.entities.Vet;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface VetRepository extends JpaRepository<Vet, Integer> {
    List<Vet> findByFirstName(String firstName);
    List<Vet> findByLastName(String lastName);
    @Override
    List<Vet> findAll();
}