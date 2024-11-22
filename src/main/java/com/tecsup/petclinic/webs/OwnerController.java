package com.tecsup.petclinic.webs;

import com.tecsup.petclinic.entities.Owner;
import com.tecsup.petclinic.exception.OwnerNotFoundException;
import com.tecsup.petclinic.services.OwnerService;
import com.tecsup.petclinic.mapper.OwnerMapper;
import com.tecsup.petclinic.domain.OwnerTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/owners")
public class OwnerController {

    private final OwnerService ownerService;
    private final OwnerMapper ownerMapper;

    public OwnerController(OwnerService ownerService, OwnerMapper ownerMapper) {
        this.ownerService = ownerService;
        this.ownerMapper = ownerMapper;
    }

    @GetMapping
    public ResponseEntity<List<OwnerTO>> findAll() {
        List<Owner> owners = ownerService.findAll();
        List<OwnerTO> ownersTO = ownerMapper.toOwnerTOList(owners);
        return ResponseEntity.ok(ownersTO);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<OwnerTO> create(@RequestBody OwnerTO ownerTO) {
        Owner owner = ownerMapper.toOwner(ownerTO);
        Owner savedOwner = ownerService.create(owner);
        OwnerTO savedOwnerTO = ownerMapper.toOwnerTO(savedOwner);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedOwnerTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OwnerTO> findById(@PathVariable Integer id) {
        try {
            Owner owner = ownerService.findById(id);
            OwnerTO ownerTO = ownerMapper.toOwnerTO(owner);
            return ResponseEntity.ok(ownerTO);
        } catch (OwnerNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<OwnerTO> update(@RequestBody OwnerTO ownerTO, @PathVariable Integer id) {
        try {
            Owner owner = ownerService.findById(id);
            owner.setFirstName(ownerTO.getFirstName());
            owner.setLastName(ownerTO.getLastName());
            owner.setAddress(ownerTO.getAddress());
            owner.setCity(ownerTO.getCity());
            owner.setTelephone(ownerTO.getTelephone());
            Owner updatedOwner = ownerService.update(owner);
            OwnerTO updatedOwnerTO = ownerMapper.toOwnerTO(updatedOwner);
            return ResponseEntity.ok(updatedOwnerTO);
        } catch (OwnerNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        try {
            ownerService.delete(id);
            return ResponseEntity.ok("Deleted owner with id: " + id);
        } catch (OwnerNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}