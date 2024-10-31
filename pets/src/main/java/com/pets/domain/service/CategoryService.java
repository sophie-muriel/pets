package com.pets.domain.service;

import com.pets.domain.dto.CategoryDTO;
import com.pets.domain.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<CategoryDTO> getAllCategories() {
        return categoryRepository.findAll();
    }

    public CategoryDTO getCategoryById(Integer id) {
        return categoryRepository.findById(id);
    }

    public CategoryDTO saveCategory(CategoryDTO category) {
        return categoryRepository.save(category);
    }
}