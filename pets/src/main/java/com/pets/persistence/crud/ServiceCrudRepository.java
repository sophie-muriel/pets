package com.pets.persistence.crud;

import com.pets.persistence.entity.ServiceEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceCrudRepository extends CrudRepository<ServiceEntity, Integer> { }