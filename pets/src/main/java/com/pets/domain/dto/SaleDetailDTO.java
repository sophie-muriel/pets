package com.pets.domain.dto;

import jakarta.validation.constraints.*;
import java.time.LocalDate;

public class SaleDetailDTO {
    private Integer id;

    @NotNull @Positive
    private Integer serviceId;

    @NotNull @Positive
    private Integer petId;

    @NotNull @Future
    private LocalDate scheduledDate;

    public SaleDetailDTO(Integer id, Integer serviceId, Integer petId, LocalDate scheduledDate) {
        this.id = id;
        this.serviceId = serviceId;
        this.petId = petId;
        this.scheduledDate = scheduledDate;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPetId() {
        return petId;
    }
    public void setPetId(Integer petId) {
        this.petId = petId;
    }

    public LocalDate getScheduledDate() {
        return scheduledDate;
    }
    public void setScheduledDate(LocalDate scheduledDate) {
        this.scheduledDate = scheduledDate;
    }

    public Integer getServiceId() {
        return serviceId;
    }
    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }
}