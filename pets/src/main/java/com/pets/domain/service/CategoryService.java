package com.pets.domain.service;

import com.pets.domain.dto.CategoryDTO;
import com.pets.domain.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public List<CategoryDTO> getAllCategories() {
        return categoryRepository.getAllCategories();
    }

    public Optional<CategoryDTO> getCategoryById(int categoryId) {
        return categoryRepository.getCategoryById(categoryId);
    }

    public CategoryDTO saveCategory(CategoryDTO category) {
        Optional<CategoryDTO> existingCategory = categoryRepository.findByCategory(category.getCategory());
        if (existingCategory.isPresent()) {
            throw new IllegalArgumentException("El nombre de la categoría debe ser único.");
        }
        return categoryRepository.saveCategory(category);
    }

    public boolean deleteCategory(int categoryId) {
        Optional<CategoryDTO> category = getCategoryById(categoryId);
        if (category.isPresent()) {
            categoryRepository.deleteCategory(categoryId);
            return true;
        } else {
            throw new IllegalArgumentException("No se encontró la categoría con el ID especificado.");
        }
    }
}