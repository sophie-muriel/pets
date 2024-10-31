package com.pets.persistence.crud;

import org.springframework.data.repository.CrudRepository;
import com.pets.persistence.entity.Service;

public interface ServiceCrudRepository extends CrudRepository<Service, Integer> {
}
