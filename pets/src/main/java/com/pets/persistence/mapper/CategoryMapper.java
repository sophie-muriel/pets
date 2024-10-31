package com.pets.persistence.mapper;

import com.pets.domain.dto.CategoryDTO;
import com.pets.persistence.entity.Category;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CategoryMapper {

    public CategoryDTO toCategory(Category category) {
        if (category == null) {
            return null;
        }
        return new CategoryDTO(category.getId(), category.getCategory());
    }

    public Category toCategory(CategoryDTO categoryDTO) {
        if (categoryDTO == null) {
            return null;
        }
        Category category = new Category();
        category.setId(categoryDTO.getId());
        category.setCategory(categoryDTO.getCategory()); // Usar setCategory()
        return category;
    }

    public List<CategoryDTO> toCategories(List<Category> categories) {
        return categories.stream()
                .map(this::toCategory)
                .collect(Collectors.toList());
    }
}