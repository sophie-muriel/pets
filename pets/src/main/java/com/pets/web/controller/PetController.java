package com.pets.web.controller;

import com.pets.domain.dto.PetDTO;
import com.pets.domain.service.PetService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/pets")
public class PetController {

    @Autowired
    private PetService petService;

    @GetMapping("/all")
    public ResponseEntity<Map<String, Object>> getAll() {
        List<PetDTO> pets = petService.getAllPets();
        Map<String, Object> response = new HashMap<>();

        if (pets.isEmpty()) {
            response.put("status", "error");
            response.put("message", "No pets found");
            return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
        }

        response.put("status", "success");
        response.put("data", pets);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getPet(@PathVariable("id") int petId) {
        Map<String, Object> response = new HashMap<>();
        Optional<PetDTO> pet = petService.getPetById(petId);

        if (pet.isPresent()) {
            response.put("status", "success");
            response.put("data", pet.get());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.put("status", "error");
            response.put("message", "Pet not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/save")
    public ResponseEntity<Map<String, Object>> save(@Valid @RequestBody PetDTO petDTO) {
        Map<String, Object> response = new HashMap<>();
        PetDTO savedPet = petService.savePet(petDTO);

        response.put("status", "success");
        response.put("message", "Pet created successfully");
        response.put("data", savedPet);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Map<String, Object>> edit(@PathVariable("id") int petId, @Valid @RequestBody PetDTO petDTO) {
        Map<String, Object> response = new HashMap<>();
        Optional<PetDTO> existingPet = petService.getPetById(petId);

        if (existingPet.isPresent()) {
            petDTO.setId(petId);
            PetDTO updatedPet = petService.savePet(petDTO);

            response.put("status", "success");
            response.put("message", "Pet updated successfully");
            response.put("data", updatedPet);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        response.put("status", "error");
        response.put("message", "Pet not found");
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable("id") int petId) {
        Map<String, Object> response = new HashMap<>();
        boolean deleted = petService.deletePet(petId);

        if (deleted) {
            response.put("status", "success");
            response.put("message", "Pet deleted successfully");
            return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
        }

        response.put("status", "error");
        response.put("message", "Pet not found");
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}