package com.pets.domain.dto;

import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

public class SaleDetailDTO {
    private Integer id;

    @NotNull(message = "Error: ID del servicio vacío")
    @Positive(message = "Error: ID del servicio inválido")
    private Integer serviceId;

    @NotNull(message = "Error: ID de la mascota vacío")
    @Positive(message = "Error: ID de la mascota inválido")
    private Integer petId;

    @NotNull(message = "Error: fecha programada vacía")
    @Future(message = "Error: fecha inválida")
    private LocalDateTime scheduledDate;

    public SaleDetailDTO(Integer id, Integer serviceId, Integer petId, LocalDateTime scheduledDate) {
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

    public LocalDateTime getScheduledDate() {
        return scheduledDate;
    }
    public void setScheduledDate(LocalDateTime scheduledDate) {
        this.scheduledDate = scheduledDate;
    }

    public Integer getServiceId() {
        return serviceId;
    }
    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }
}