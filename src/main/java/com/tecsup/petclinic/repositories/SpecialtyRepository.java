package com.tecsup.petclinic.repositories;

import com.tecsup.petclinic.entities.Specialty;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SpecialtyRepository extends JpaRepository<Specialty, Integer> {

    List<Specialty> findByName(String name);
}
