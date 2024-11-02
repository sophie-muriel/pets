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
        return categoryRepository.saveCategory(category);
    }

    public boolean deleteCategory(int categoryId) {
        return getCategoryById(categoryId).map(category -> {
            categoryRepository.deleteCategory(categoryId);
            return true;
        }).orElse(false);
    }
}