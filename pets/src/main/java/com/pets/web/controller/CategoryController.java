package com.pets.web.controller;

import com.pets.domain.dto.CategoryDTO;
import com.pets.domain.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/all")
    public List<CategoryDTO> getAll() {
        return categoryService.getAllCategories();
    }

    @GetMapping("/{id}")
    public Optional<CategoryDTO> getCategory(@PathVariable("id") int categoryId) {
        return categoryService.getCategoryById(categoryId);
    }

    @PostMapping("/save")
    public CategoryDTO save(@RequestBody CategoryDTO categoryDTO) {
        return categoryService.saveCategory(categoryDTO);
    }

    @DeleteMapping("/delete/{id}")
    public boolean delete(@PathVariable("id") int categoryId) {
        return categoryService.deleteCategory(categoryId);
    }
}