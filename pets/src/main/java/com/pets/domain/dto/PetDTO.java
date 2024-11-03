package com.pets.domain.dto;

import javax.validation.constraints.*;

public class PetDTO {
    private Integer id;

    @NotBlank(message = "El nombre no puede estar vacío")
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "El nombre debe contener solo letras y espacios")
    private String name;

    @NotNull(message = "El ID del propietario no puede ser nulo")
    @Positive(message = "El ID del propietario debe ser un entero positivo")
    private Integer ownerId;

    @NotBlank(message = "La especie no puede estar vacía")
    private String species;

    @NotBlank(message = "El género no puede estar vacío")
    @Pattern(regexp = "^(Masculino|Femenino)$", message = "El género debe ser 'Masculino' o 'Femenino'")
    private String gender;

    @Min(value = 0, message = "La edad debe ser un número entero no negativo")
    private Integer age;

    @Positive(message = "El peso debe ser un número positivo")
    private Double weight;

    @NotBlank(message = "El enlace del historial médico no puede estar vacío")
    @Pattern(regexp = "^(http|https)://.*$", message = "El enlace del historial médico debe ser una URL válida")
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

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Integer getOwnerId() { return ownerId; }
    public void setOwnerId(Integer ownerId) { this.ownerId = ownerId; }

    public String getSpecies() { return species; }
    public void setSpecies(String species) { this.species = species; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public Integer getAge() { return age; }
    public void setAge(Integer age) { this.age = age; }

    public Double getWeight() { return weight; }
    public void setWeight(Double weight) { this.weight = weight; }

    public String getMedicalHistoryLink() { return medicalHistoryLink; }
    public void setMedicalHistoryLink(String medicalHistoryLink) { this.medicalHistoryLink = medicalHistoryLink; }
}
