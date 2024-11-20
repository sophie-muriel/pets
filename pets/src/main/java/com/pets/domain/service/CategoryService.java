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
        Optional<CategoryDTO> category = categoryRepository.getCategoryById(categoryId);
        if (category.isEmpty()) {
            throw new IllegalArgumentException("category not found");
        }
        return category;
    }

    public CategoryDTO saveCategory(CategoryDTO category) {
        Optional<CategoryDTO> existingCategory = categoryRepository.findByCategory(category.getCategory());
        if (existingCategory.isPresent()) {
            throw new IllegalArgumentException("category already exists");
        }
        return categoryRepository.saveCategory(category);
    }

    public boolean deleteCategory(int categoryId) {
        Optional<CategoryDTO> category = getCategoryById(categoryId);
        if (category.isPresent()) {
            categoryRepository.deleteCategory(categoryId);
            return true;
        } else {
            throw new IllegalArgumentException("category not found");
        }
    }

    public CategoryDTO editCategory(int categoryId, CategoryDTO updatedCategory) {
        Optional<CategoryDTO> existingCategoryOptional = categoryRepository.getCategoryById(categoryId);
        if (existingCategoryOptional.isEmpty()) {
            throw new IllegalArgumentException("Category not found");
        }
        CategoryDTO existingCategory = existingCategoryOptional.get();

        if (updatedCategory.getCategory() != null
                && !updatedCategory.getCategory().equals(existingCategory.getCategory())) {
            existingCategory.setCategory(updatedCategory.getCategory());
        }

        return categoryRepository.saveCategory(existingCategory);
    }

}