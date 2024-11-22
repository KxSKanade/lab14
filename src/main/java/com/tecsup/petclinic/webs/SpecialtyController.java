package com.tecsup.petclinic.webs;

import com.tecsup.petclinic.entities.Specialty;
import com.tecsup.petclinic.exception.SpecialtyNotFoundException;
import com.tecsup.petclinic.services.SpecialtyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/specialties")
public class SpecialtyController {

    private final SpecialtyService specialtyService;

    public SpecialtyController(SpecialtyService specialtyService) {
        this.specialtyService = specialtyService;
    }

    @GetMapping
    public ResponseEntity<List<Specialty>> findAllSpecialties() {
        List<Specialty> specialties = specialtyService.findAll();
        return ResponseEntity.ok(specialties);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Specialty> createSpecialty(@RequestBody Specialty specialty) {
        Specialty createdSpecialty = specialtyService.create(specialty);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSpecialty);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Specialty> findSpecialtyById(@PathVariable Integer id) {
        try {
            Specialty specialty = specialtyService.findById(id);
            return ResponseEntity.ok(specialty);
        } catch (SpecialtyNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Specialty> updateSpecialty(@PathVariable Integer id, @RequestBody Specialty specialty) {
        try {
            specialty.setId(id);
            Specialty updatedSpecialty = specialtyService.update(specialty);
            return ResponseEntity.ok(updatedSpecialty);
        } catch (SpecialtyNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSpecialty(@PathVariable Integer id) {
        try {
            specialtyService.delete(id);
            return ResponseEntity.ok("Specialty deleted successfully.");
        } catch (SpecialtyNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}

