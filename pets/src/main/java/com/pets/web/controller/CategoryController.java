package com.pets.web.controller;

import com.pets.domain.dto.CategoryDTO;
import com.pets.domain.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Operation(summary = "Get all categories", description = "Returns a list of categories.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Categories found", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)) }),
            @ApiResponse(responseCode = "204", description = "Categories not found", content = @Content)
    })
    @GetMapping("/all")
    public ResponseEntity<Map<String, Object>> getAll() {
        List<CategoryDTO> categories = categoryService.getAllCategories();
        Map<String, Object> response = new HashMap<>();

        if (categories.isEmpty()) {
            response.put("status", "error");
            response.put("message", "No categories found");
            return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
        }

        response.put("status", "success");
        response.put("data", categories);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Gets a category", description = "Returns a specific category.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Category found", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)) }),
            @ApiResponse(responseCode = "404", description = "Category not found", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getCategory(@PathVariable("id") int categoryId) {
        Map<String, Object> response = new HashMap<>();
        Optional<CategoryDTO> category = categoryService.getCategoryById(categoryId);

        if (category.isPresent()) {
            response.put("status", "success");
            response.put("data", category.get());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.put("status", "error");
            response.put("message", "Category not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Saves a category", description = "Adds a category to the list.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Category saved", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)) })
    })
    @PostMapping("/save")
    public ResponseEntity<Map<String, Object>> save(@Valid @RequestBody CategoryDTO categoryDTO) {
        Map<String, Object> response = new HashMap<>();
        CategoryDTO savedCategory = categoryService.saveCategory(categoryDTO);

        response.put("status", "success");
        response.put("message", "Category created successfully");
        response.put("data", savedCategory);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Operation(summary = "Edits a category", description = "Edits a category in the list.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Category updated", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)) }),
            @ApiResponse(responseCode = "404", description = "Category not updated", content = @Content)
    })
    @PutMapping("/edit/{id}")
    public ResponseEntity<Map<String, Object>> edit(@PathVariable("id") int categoryId,
            @Valid @RequestBody CategoryDTO categoryDTO) {
        Map<String, Object> response = new HashMap<>();
        Optional<CategoryDTO> existingCategory = categoryService.getCategoryById(categoryId);

        if (existingCategory.isPresent()) {
            categoryDTO.setId(categoryId);
            CategoryDTO updatedCategory = categoryService.editCategory(categoryId, categoryDTO);

            response.put("status", "success");
            response.put("message", "Category updated successfully");
            response.put("data", updatedCategory);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        response.put("status", "error");
        response.put("message", "Category not found");
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Deletes a category", description = "Removes a category from the list.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Category removed", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)) }),
            @ApiResponse(responseCode = "404", description = "Category not removed", content = @Content)
    })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable("id") int categoryId) {
        Map<String, Object> response = new HashMap<>();
        boolean deleted = categoryService.deleteCategory(categoryId);

        if (deleted) {
            response.put("status", "success");
            response.put("message", "Category deleted successfully");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        response.put("status", "error");
        response.put("message", "Category not found");
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}