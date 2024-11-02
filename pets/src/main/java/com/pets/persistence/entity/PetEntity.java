package com.pets.persistence.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "pets")
public class PetEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String species;
    private String gender;
    private Integer age;
    private Double weight;

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private ClientEntity owner;

    @Column(name = "medical_history_link")
    private String medicalHistoryLink;

    @OneToMany(mappedBy = "pet")
    private List<SaleDetailEntity> saleDetails;

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

    public ClientEntity getOwner() {
        return owner;
    }
    public void setOwner(ClientEntity owner) {
        this.owner = owner;
    }

    public String getSpecies() {
        return species;
    }
    public void setSpecies(String species) {
        this.species = species;
    }

    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }
    public void setAge(Integer age) {
        this.age = age;
    }

    public Double getWeight() {
        return weight;
    }
    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getMedicalHistoryLink() {
        return medicalHistoryLink;
    }
    public void setMedicalHistoryLink(String medicalHistoryLink) {
        this.medicalHistoryLink = medicalHistoryLink;
    }

    public List<SaleDetailEntity> getSaleDetails() {
        return saleDetails;
    }
    public void setSaleDetails(List<SaleDetailEntity> saleDetails) {
        this.saleDetails = saleDetails;
    }
}


