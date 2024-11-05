package com.pets.domain.dto;

import jakarta.validation.constraints.*;

public class PetDTO {
    private Integer id;

    @NotBlank(message = "Error: nombre vacío")
    @Size(max = 100, message = "Error: nombre con más de 100 caracteres")
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Error: nombre inválido")
    private String name;

    @NotNull(message = "Error: ID del dueño vacío")
    @Positive(message = "Error: ID del dueño inválido")
    private Integer ownerId;

    @NotBlank(message = "Error: especie vacía")
    @Size(max = 50, message = "Error: especie con más de 50 caracteres")
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Error: especie inválida")
    private String species;

    @NotBlank(message = "Error: género vacío")
    @Pattern(regexp = "^(Hembra|Macho)$", message = "Error: género debe ser 'Hembra' o 'Macho'")
    private String gender;

    @NotNull(message = "Error: edad vacía")
    @Positive(message = "Error: edad inválida")
    private Integer age;

    @NotNull(message = "Error: peso vacío")
    @Positive(message = "Error: peso inválido")
    private Double weight;

    @Size(max = 150, message = "Error: enlace del historial médico con más de 150 caracteres")
    private String medicalHistoryLink;

    public PetDTO(Integer id, String name, Integer ownerId, String species, String gender, Integer age, Double weight, String medicalHistoryLink) {
        this.id = id;
        this.name = name;
        this.ownerId = ownerId;
        this.species = species;
        this.gender = gender;
        this.age = age;
        this.weight = weight;
        this.medicalHistoryLink = medicalHistoryLink;
    }

    public Integer getAge() {
        return age;
    }
    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public String getMedicalHistoryLink() {
        return medicalHistoryLink;
    }
    public void setMedicalHistoryLink(String medicalHistoryLink) {
        this.medicalHistoryLink = medicalHistoryLink;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Integer getOwnerId() {
        return ownerId;
    }
    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public String getSpecies() {
        return species;
    }
    public void setSpecies(String species) {
        this.species = species;
    }

    public Double getWeight() {
        return weight;
    }
    public void setWeight(Double weight) {
        this.weight = weight;
    }
}