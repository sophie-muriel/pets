package com.pets.persistence.crud;

import org.springframework.data.jpa.repository.JpaRepository;
import com.pets.persistence.entity.Pet;

public interface PetCrudRepository extends JpaRepository<Pet, Integer> { }
