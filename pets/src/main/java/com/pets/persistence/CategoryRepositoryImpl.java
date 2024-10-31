package com.pets.persistence;

import com.pets.domain.dto.CategoryDTO;
import com.pets.domain.repository.CategoryRepository; // Asegúrate de que esta interfaz exista
import com.pets.persistence.crud.CategoryCrudRepository;
import com.pets.persistence.entity.Category;
import com.pets.persistence.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CategoryRepositoryImpl implements CategoryRepository {

    @Autowired
    private CategoryCrudRepository categoryCrudRepository;

    @Autowired
    private CategoryMapper mapper;

    @Override
    public List<CategoryDTO> findAll() {
        List<Category> categories = categoryCrudRepository.findAll();
        return mapper.toCategories(categories);
    }

    @Override
    public CategoryDTO findById(Integer id) {
        return categoryCrudRepository.findById(id)
                .map(mapper::toCategory) // Mapea a CategoryDTO
                .orElse(null); // Retorna null si no se encuentra
    }

    @Override
    public CategoryDTO save(CategoryDTO categoryDTO) {
        Category category = mapper.toCategory(categoryDTO); // Mapea a Category
        return mapper.toCategory(categoryCrudRepository.save(category)); // Guarda y mapea de vuelta
    }
}