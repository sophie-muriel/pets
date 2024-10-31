package com.pets.domain.repository;

import com.pets.domain.dto.CategoryDTO;
import java.util.List;

public interface CategoryRepository {
    List<CategoryDTO> findAll();
    CategoryDTO findById(Integer id);
    CategoryDTO save(CategoryDTO category);
}