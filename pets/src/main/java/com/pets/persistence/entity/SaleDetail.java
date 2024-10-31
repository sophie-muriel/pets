package com.pets.persistence.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "sale_detail")
public class SaleDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "service_id", nullable = false)
    private Service service;

    @ManyToOne
    @JoinColumn(name = "pet_id", nullable = false)
    private Pet pet;

    @ManyToOne
    @JoinColumn(name = "sale", nullable = false)
    private Sale sale;

    @Column(name = "scheduled_date", nullable = false)
    private java.sql.Timestamp scheduledDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public java.sql.Timestamp getScheduledDate() {
        return scheduledDate;
    }

    public void setScheduledDate(java.sql.Timestamp scheduledDate) {
        this.scheduledDate = scheduledDate;
    }
}