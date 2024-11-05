package com.pets.web.controller;

import com.pets.domain.dto.PetDTO;
import com.pets.domain.service.PetService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pets")
@Validated
public class PetController {
    @Autowired
    private PetService petService;

    @GetMapping("/all")
    public List<PetDTO> getAll() {
        return petService.getAllPets();
    }

    @GetMapping("/{id}")
    public Optional<PetDTO> getPet(@PathVariable("id") int petId) {
        return petService.getPetById(petId);
    }

    @PostMapping("/save")
    public PetDTO save(@Valid @RequestBody PetDTO petDTO) {
        return petService.savePet(petDTO);
    }

    @DeleteMapping("/delete/{id}")
    public boolean delete(@PathVariable("id") int petId) {
        return petService.deletePet(petId);
    }
}