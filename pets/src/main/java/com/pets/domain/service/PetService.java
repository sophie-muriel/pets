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

    @Autowired
    private ClientService clientService;

    public List<PetDTO> getAllPets() {
        return petRepository.getAllPets();
    }

    public Optional<PetDTO> getPetById(int petId) {
        Optional<PetDTO> pet = petRepository.getPetById(petId);
        if (pet.isEmpty()) {
            throw new IllegalArgumentException("No se encontró la mascota con el ID especificado.");
        }
        return pet;
    }

    public PetDTO savePet(PetDTO pet) {
        if (clientService.getClientById(pet.getOwnerId()).isEmpty()) {
            throw new IllegalArgumentException("El ID del cliente no es válido.");
        }
        return petRepository.savePet(pet);
    }

    public boolean deletePet(int petId) {
        Optional<PetDTO> pet = getPetById(petId);
        if (pet.isPresent()) {
            petRepository.deletePet(petId);
            return true;
        } else {
            throw new IllegalArgumentException("No se encontró la mascota con el ID especificado.");
        }
    }
}