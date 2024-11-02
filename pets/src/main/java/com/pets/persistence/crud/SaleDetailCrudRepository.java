package com.pets.persistence.crud;

import com.pets.persistence.entity.SaleDetailEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleDetailCrudRepository extends CrudRepository<SaleDetailEntity, Integer> { }