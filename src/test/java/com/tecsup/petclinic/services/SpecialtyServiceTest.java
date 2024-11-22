package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.Specialty;
import com.tecsup.petclinic.exception.SpecialtyNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
public class SpecialtyServiceTest {

    @Autowired
    private SpecialtyService specialtyService;
    

    @Test
    public void testCreateSpecialty() {
        String NAME = "Dermatologia";
        String OFFICE = "Lima";
        Specialty specialty = new Specialty(NAME, OFFICE, "5", "23");
        Specialty specialtyCreated = this.specialtyService.create(specialty);
        log.info("Specialty created: " + specialtyCreated);
        assertNotNull(specialtyCreated.getId());
        assertEquals(NAME, specialtyCreated.getName());
        assertEquals(OFFICE, specialtyCreated.getOffice());
    }

    @Test
    public void testUpdateSpecialty() {
        String NAME = "otros";
        String OFFICE = "Piura";
        String UP_NAME = "otros2";
        String UP_OFFICE = "Piura2";
        Specialty specialty = new Specialty(NAME, OFFICE, "3", "22");
        log.info("Creating specialty: " + specialty);
        Specialty specialtyCreated = this.specialtyService.create(specialty);
        log.info("Specialty created: " + specialtyCreated);
        specialtyCreated.setName(UP_NAME);
        specialtyCreated.setOffice(UP_OFFICE);
        Specialty updatedSpecialty = this.specialtyService.update(specialtyCreated);
        log.info("Updated specialty: " + updatedSpecialty);
        assertEquals(UP_NAME, updatedSpecialty.getName());
        assertEquals(UP_OFFICE, updatedSpecialty.getOffice());
    }

    @Test
    public void testDeleteSpecialty() {
        String NAME = "especialidad";
        String OFFICE = "Chile";
        Specialty specialty = new Specialty(NAME, OFFICE, "11", "15");
        specialty = this.specialtyService.create(specialty);
        log.info("Specialty created: " + specialty);
        try {
            this.specialtyService.delete(specialty.getId());
        } catch (SpecialtyNotFoundException e) {
            fail(e.getMessage());
        }
        try {
            this.specialtyService.findById(specialty.getId());
            fail("Specialty should not be found after deletion");
        } catch (SpecialtyNotFoundException e) {
            assertTrue(true);
        }
    }

    @Test
    public void testDeleteSpecialtyNotFound() {
        assertThrows(SpecialtyNotFoundException.class, () -> {
            specialtyService.delete(Integer.valueOf(999));
        });
    }
}
