package com.pets.web.controller;

import com.pets.domain.dto.PetDTO;
import com.pets.domain.service.PetService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pets")
public class PetController {

    @Autowired
    private PetService petService;

    @GetMapping("/all")
    public ResponseEntity<List<PetDTO>> getAll() {
        List<PetDTO> pets = petService.getAllPets();
        if (pets.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(pets, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PetDTO> getPet(@PathVariable("id") int petId) {
        Optional<PetDTO> pet = petService.getPetById(petId);
        return pet.map(petDTO
                -> new ResponseEntity<>(petDTO, HttpStatus.OK)).orElseGet(()
                -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/save")
    public ResponseEntity<PetDTO> save(@Valid @RequestBody PetDTO petDTO) {
        PetDTO savedPet = petService.savePet(petDTO);
        return new ResponseEntity<>(savedPet, HttpStatus.CREATED);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<PetDTO> edit(@PathVariable("id") int petId, @Valid @RequestBody PetDTO petDTO) {
        Optional<PetDTO> existingPet = petService.getPetById(petId);
        if (existingPet.isPresent()) {
            petDTO.setId(petId);
            PetDTO updatedPet = petService.savePet(petDTO);
            return new ResponseEntity<>(updatedPet, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") int petId) {
        boolean deleted = petService.deletePet(petId);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}