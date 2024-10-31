package com.pets.persistence.crud;

import org.springframework.data.jpa.repository.JpaRepository;
import com.pets.persistence.entity.Sale;

public interface SaleCrudRepository extends JpaRepository<Sale, Integer> { }