package com.pets.persistence.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "sale_details")
public class SaleDetailEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "scheduled_date", nullable = false)
    private LocalDateTime scheduledDate;

    @ManyToOne
    @JoinColumn(name = "service_id", nullable = false)
    private ServiceEntity service;

    @ManyToOne
    @JoinColumn(name = "pet_id", nullable = false)
    private PetEntity pet;

    @ManyToOne
    @JoinColumn(name = "sale_id", nullable = false)
    private SaleEntity sale;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public PetEntity getPet() {
        return pet;
    }
    public void setPet(PetEntity pet) {
        this.pet = pet;
    }

    public SaleEntity getSale() {
        return sale;
    }
    public void setSale(SaleEntity sale) {
        this.sale = sale;
    }

    public LocalDateTime getScheduledDate() {
        return scheduledDate;
    }
    public void setScheduledDate(LocalDateTime scheduledDate) {
        this.scheduledDate = scheduledDate;
    }

    public ServiceEntity getService() {
        return service;
    }
    public void setService(ServiceEntity service) {
        this.service = service;
    }
}