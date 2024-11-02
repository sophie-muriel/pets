package com.pets.domain.service;

import com.pets.domain.dto.PetDTO;
import com.pets.domain.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PetService {
    @Autowired
    private PetRepository petRepository;

    public List<PetDTO> getAllPets() {
        return petRepository.getAllPets();
    }

    public Optional<PetDTO> getPetById(int petId) {
        return petRepository.getPetById(petId);
    }

    public PetDTO savePet(PetDTO pet) {
        return petRepository.savePet(pet);
    }

    public boolean deletePet(int petId) {
        return getPetById(petId).map(pet -> {
            petRepository.deletePet(petId);
            return true;
        }).orElse(false);
    }
}