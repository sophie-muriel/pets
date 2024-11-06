package com.pets.web.controller;

import com.pets.domain.dto.CategoryDTO;
import com.pets.domain.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/all")
    public ResponseEntity<List<CategoryDTO>> getAll() {
        List<CategoryDTO> categories = categoryService.getAllCategories();
        if (categories.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> getCategory(@PathVariable("id") int categoryId) {
        Optional<CategoryDTO> category = categoryService.getCategoryById(categoryId);
        return category.map(categoryDTO
                -> new ResponseEntity<>(categoryDTO, HttpStatus.OK)).orElseGet(()
                -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/save")
    public ResponseEntity<CategoryDTO> save(@Valid @RequestBody CategoryDTO categoryDTO) {
        CategoryDTO savedCategory = categoryService.saveCategory(categoryDTO);
        return new ResponseEntity<>(savedCategory, HttpStatus.CREATED);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<CategoryDTO> edit(@PathVariable("id") int categoryId, @Valid @RequestBody CategoryDTO categoryDTO) {
        Optional<CategoryDTO> existingCategory = categoryService.getCategoryById(categoryId);
        if (existingCategory.isPresent()) {
            categoryDTO.setId(categoryId);
            CategoryDTO updatedCategory = categoryService.saveCategory(categoryDTO);
            return new ResponseEntity<>(updatedCategory, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") int categoryId) {
        boolean deleted = categoryService.deleteCategory(categoryId);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}