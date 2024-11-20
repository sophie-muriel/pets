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
            throw new IllegalArgumentException("pet not found");
        }
        return pet;
    }

    public PetDTO savePet(PetDTO pet) {
        if (clientService.getClientById(pet.getOwnerId()).isEmpty()) {
            throw new IllegalArgumentException("client not found");
        }
        return petRepository.savePet(pet);
    }

    public boolean deletePet(int petId) {
        Optional<PetDTO> pet = getPetById(petId);
        if (pet.isPresent()) {
            petRepository.deletePet(petId);
            return true;
        } else {
            throw new IllegalArgumentException("pet not found");
        }
    }

    public PetDTO editPet(int petId, PetDTO updatedPet) {
        Optional<PetDTO> existingPetOptional = petRepository.getPetById(petId);
        if (existingPetOptional.isEmpty()) {
            throw new IllegalArgumentException("Pet not found");
        }
        PetDTO existingPet = existingPetOptional.get();

        if (updatedPet.getName() != null && !updatedPet.getName().equals(existingPet.getName())) {
            existingPet.setName(updatedPet.getName());
        }
        if (updatedPet.getSpecies() != null && !updatedPet.getSpecies().equals(existingPet.getSpecies())) {
            existingPet.setSpecies(updatedPet.getSpecies());
        }
        if (updatedPet.getGender() != null && !updatedPet.getGender().equals(existingPet.getGender())) {
            existingPet.setGender(updatedPet.getGender());
        }
        if (updatedPet.getAge() != null && !updatedPet.getAge().equals(existingPet.getAge())) {
            existingPet.setAge(updatedPet.getAge());
        }
        if (updatedPet.getWeight() != null && !updatedPet.getWeight().equals(existingPet.getWeight())) {
            existingPet.setWeight(updatedPet.getWeight());
        }
        if (updatedPet.getMedicalHistoryLink() != null
                && !updatedPet.getMedicalHistoryLink().equals(existingPet.getMedicalHistoryLink())) {
            existingPet.setMedicalHistoryLink(updatedPet.getMedicalHistoryLink());
        }
        if (updatedPet.getOwnerId() != null && !updatedPet.getOwnerId().equals(existingPet.getOwnerId())) {
            if (clientService.getClientById(updatedPet.getOwnerId()).isEmpty()) {
                throw new IllegalArgumentException("Client not found");
            }
            existingPet.setOwnerId(updatedPet.getOwnerId());
        }

        return petRepository.savePet(existingPet);
    }

}