package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.Vet;
import com.tecsup.petclinic.exception.VetNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
public class VetServiceTest {

    @Autowired
    private VetService vetService;





    @Test
    public void testCreateVet() {
        String FIRST_NAME = "Kanye";
        String LAST_NAME = "West";
        Vet vet = new Vet(FIRST_NAME, LAST_NAME);
        Vet vetCreated = this.vetService.create(vet);
        log.info("Veterinarian created: " + vetCreated);
        assertNotNull(vetCreated.getId());
        assertEquals(FIRST_NAME, vetCreated.getFirstName());
        assertEquals(LAST_NAME, vetCreated.getLastName());
    }

    @Test
    public void testUpdateVet() {
        String FIRST_NAME = "Kanye";
        String LAST_NAME = "West";
        String UP_FIRST_NAME = "Kanye2";
        String UP_LAST_NAME = "West2";
        Vet vet = new Vet(FIRST_NAME, LAST_NAME);
        log.info("Creating vet: " + vet);
        Vet vetCreated = this.vetService.create(vet);
        log.info("Veterinarian created: " + vetCreated);
        vetCreated.setFirstName(UP_FIRST_NAME);
        vetCreated.setLastName(UP_LAST_NAME);
        Vet updatedVet = this.vetService.update(vetCreated);
        log.info("Updated veterinarian: " + updatedVet);
        assertEquals(UP_FIRST_NAME, updatedVet.getFirstName());
        assertEquals(UP_LAST_NAME, updatedVet.getLastName());
    }

    @Test
    public void testDeleteVet() {
        String FIRST_NAME = "Sharon";
        String LAST_NAME = "Jenkins";
        Vet vet = new Vet(FIRST_NAME, LAST_NAME);
        vet = this.vetService.create(vet);
        log.info("Veterinarian created: " + vet);
        try {
            this.vetService.delete(vet.getId());
        } catch (VetNotFoundException e) {
            fail(e.getMessage());
        }
        try {
            this.vetService.findById(vet.getId());
            fail("Veterinarian should not be found after deletion");
        } catch (VetNotFoundException e) {
            assertTrue(true);
        }
    }
}
