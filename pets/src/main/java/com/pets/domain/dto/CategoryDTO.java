package com.pets.domain.dto;

import jakarta.validation.constraints.*;

public class CategoryDTO {
    private Integer id;

    @NotBlank(message = "Error: categoría vacía")
    @Size(max = 100, message = "Error: categoría con más de 100 caracteres")
    private String category;

    public CategoryDTO(String category, Integer id) {
        this.category = category;
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}