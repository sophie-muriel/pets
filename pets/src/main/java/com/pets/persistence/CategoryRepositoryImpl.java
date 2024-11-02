package com.pets.persistence;

import com.pets.domain.dto.CategoryDTO;
import com.pets.domain.repository.CategoryRepository;
import com.pets.persistence.crud.CategoryCrudRepository;
import com.pets.persistence.entity.CategoryEntity;
import com.pets.domain.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CategoryRepositoryImpl implements CategoryRepository {
    @Autowired
    private CategoryCrudRepository categoryCrudRepository;

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<CategoryDTO> getAllCategories() {
        List<CategoryEntity> categories = (List<CategoryEntity>) categoryCrudRepository.findAll();
        return categoryMapper.toCategories(categories);
    }

    @Override
    public Optional<CategoryDTO> getCategoryById(int categoryId) {
        return categoryCrudRepository.findById(categoryId).map(categoryMapper::toCategory);
    }

    @Override
    public CategoryDTO saveCategory(CategoryDTO categoryDTO) {
        CategoryEntity categoryEntity = categoryMapper.toEntity(categoryDTO);
        return categoryMapper.toCategory(categoryCrudRepository.save(categoryEntity));
    }

    @Override
    public void deleteCategory(int categoryId) {
        categoryCrudRepository.deleteById(categoryId);
    }
}