package com.pets.persistence.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "categories")
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String category;

    @OneToMany(mappedBy = "category")
    private List<ServiceEntity> services;

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

    public List<ServiceEntity> getServices() {
        return services;
    }
    public void setServices(List<ServiceEntity> services) {
        this.services = services;
    }
}