package com.pets.persistence.crud;

import com.pets.persistence.entity.CategoryEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryCrudRepository extends CrudRepository<CategoryEntity, Integer> {
    Optional<CategoryEntity> findByCategory(String category);
}