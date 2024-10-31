package com.pets.persistence.crud;

import com.pets.persistence.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryCrudRepository extends JpaRepository<Category, Integer> { }