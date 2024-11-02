package com.pets.domain.dto;

public class PetDTO {
    private Integer id;
    private String name;
    private Integer ownerId;
    private String species;
    private String gender;
    private Integer age;
    private Double weight;
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
