package com.pets.persistence.crud;

import com.pets.persistence.entity.SaleEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleCrudRepository extends CrudRepository<SaleEntity, Integer> { }