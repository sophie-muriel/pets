package com.pets.domain.repository;

import com.pets.domain.dto.CategoryDTO;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository {
    List<CategoryDTO> getAllCategories();
    Optional<CategoryDTO> getCategoryById(int categoryId);
    CategoryDTO saveCategory(CategoryDTO category);
    void deleteCategory(int categoryId);
}
