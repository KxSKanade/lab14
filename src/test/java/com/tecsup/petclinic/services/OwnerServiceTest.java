package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.Owner;
import com.tecsup.petclinic.exception.OwnerNotFoundException;
import com.tecsup.petclinic.repositories.OwnerRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
public class OwnerServiceTest {

    @Autowired
    private OwnerService ownerService;

    @Autowired
    private OwnerRepository ownerRepository;

    @Test
    public void testFindOwnerById() {
        // Usamos nombres aleatorios para el primer y último nombre
        String FIRST_NAME = "Alice";
        String LAST_NAME = "Johnson";

        // Crear un nuevo Owner para la prueba
        Owner owner = new Owner();
        owner.setFirstName(FIRST_NAME);
        owner.setLastName(LAST_NAME);
        Owner savedOwner = ownerService.create(owner);

        // Recuperar el Owner por su ID y verificar los datos
        Integer ID = savedOwner.getId();
        Owner foundOwner = null;
        try {
            foundOwner = ownerService.findById(ID);
        } catch (OwnerNotFoundException e) {
            fail(e.getMessage());
        }
        log.info("Owner found: " + foundOwner);
        assertEquals(FIRST_NAME, foundOwner.getFirstName());
        assertEquals(LAST_NAME, foundOwner.getLastName());

        // Limpieza: Eliminar el Owner creado
        ownerRepository.delete(savedOwner);
    }

    @Test
    public void testFindOwnerByLastName() {
        // Usamos un apellido común como ejemplo
        String LAST_NAME = "Smith";

        // Crear un Owner con el apellido deseado
        Owner owner = new Owner();
        owner.setFirstName("John");
        owner.setLastName(LAST_NAME);
        ownerService.create(owner);

        // Buscar por apellido
        List<Owner> owners = ownerService.findByLastName(LAST_NAME);
        assertTrue(owners.size() >= 1, "Debe haber al menos un Owner con el apellido Smith");

        // Limpieza: Eliminar los Owners creados en esta prueba
        ownerRepository.deleteAll(owners);
    }

    @Test
    public void testCreateOwner() {
        // Usamos nombres aleatorios para crear el Owner
        String FIRST_NAME = "Zoe";
        String LAST_NAME = "Adams";

        // Crear y guardar un nuevo Owner
        Owner owner = new Owner();
        owner.setFirstName(FIRST_NAME);
        owner.setLastName(LAST_NAME);
        Owner createdOwner = ownerService.create(owner);

        log.info("Owner created: " + createdOwner);
        assertNotNull(createdOwner.getId());
        assertEquals(FIRST_NAME, createdOwner.getFirstName());
        assertEquals(LAST_NAME, createdOwner.getLastName());

        // Limpieza: Eliminar el Owner creado
        ownerRepository.delete(createdOwner);
    }

    @Test
    public void testUpdateOwner() {
        // Usamos nombres aleatorios para crear el Owner
        String FIRST_NAME = "Evelyn";
        String LAST_NAME = "Morris";
        String UPDATED_FIRST_NAME = "Evelyn2";
        String UPDATED_LAST_NAME = "Morris2";

        // Crear y guardar un nuevo Owner
        Owner owner = new Owner();
        owner.setFirstName(FIRST_NAME);
        owner.setLastName(LAST_NAME);
        Owner createdOwner = ownerService.create(owner);
        log.info("Owner created: " + createdOwner);

        // Actualizar el Owner
        createdOwner.setFirstName(UPDATED_FIRST_NAME);
        createdOwner.setLastName(UPDATED_LAST_NAME);
        Owner updatedOwner = ownerService.update(createdOwner);

        log.info("Updated owner: " + updatedOwner);
        assertEquals(UPDATED_FIRST_NAME, updatedOwner.getFirstName());
        assertEquals(UPDATED_LAST_NAME, updatedOwner.getLastName());

        // Limpieza: Eliminar el Owner actualizado
        ownerRepository.delete(updatedOwner);
    }
}
