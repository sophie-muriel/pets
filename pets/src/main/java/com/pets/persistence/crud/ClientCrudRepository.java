package com.pets.persistence.crud;

import org.springframework.data.jpa.repository.JpaRepository;
import com.pets.persistence.entity.Client;

public interface ClientCrudRepository extends JpaRepository<Client, Integer> { }