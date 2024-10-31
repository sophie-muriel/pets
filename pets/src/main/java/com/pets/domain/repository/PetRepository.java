package com.pets.domain.repository;

import com.pets.domain.dto.PetDTO;
import java.util.List;

public interface PetRepository {
    List<PetDTO> findAll();
    PetDTO findById(Integer id);
    List<PetDTO> findByOwnerId(Integer ownerId);
    PetDTO save(PetDTO pet);
}
