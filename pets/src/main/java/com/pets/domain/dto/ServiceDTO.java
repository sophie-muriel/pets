package com.pets.domain.dto;

import jakarta.validation.constraints.*;

public class ServiceDTO {
    private Integer id;

    @NotBlank(message = "Error: nombre vacío")
    @Size(max = 100, message = "Error: nombre con más de 100 caracteres")
    private String name;

    @Size(max = 255, message = "Error: descripción con más de 255 caracteres")
    private String description;

    @NotNull(message = "Error: precio vacío")
    @Positive(message = "Error: precio inválido")
    private Double price;

    @NotNull(message = "Error: ID de categoría vacío")
    @Positive(message = "Error: ID de categoría inválido")
    private Integer categoryId;

    public ServiceDTO(Integer id, String name, String description, Double price, Integer categoryId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.categoryId = categoryId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }
    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }
}