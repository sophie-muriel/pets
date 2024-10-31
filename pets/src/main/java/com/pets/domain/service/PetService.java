package com.pets.domain.service;

import com.pets.domain.dto.PetDTO;
import com.pets.domain.repository.PetRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetService {
    private final PetRepository petRepository;

    public PetService(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    public List<PetDTO> getAllPets() {
        return petRepository.findAll();
    }

    public PetDTO getPetById(Integer id) {
        return petRepository.findById(id);
    }

    public PetDTO savePet(PetDTO pet) {
        return petRepository.save(pet);
    }
}