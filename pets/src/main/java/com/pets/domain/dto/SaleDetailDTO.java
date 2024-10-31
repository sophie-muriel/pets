package com.pets.domain.dto;

import java.sql.Timestamp;

public class SaleDetailDTO {
    private Integer id;
    private Integer serviceId;
    private Integer petId;
    private Timestamp scheduledDate;

    public SaleDetailDTO(Integer id, Integer serviceId, Integer petId, Timestamp scheduledDate) {
        this.id = id;
        this.serviceId = serviceId;
        this.petId = petId;
        this.scheduledDate = scheduledDate;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Integer getServiceId() { return serviceId; }
    public void setServiceId(Integer serviceId) { this.serviceId = serviceId; }

    public Integer getPetId() { return petId; }
    public void setPetId(Integer petId) { this.petId = petId; }

    public Timestamp getScheduledDate() { return scheduledDate; }
    public void setScheduledDate(Timestamp scheduledDate) { this.scheduledDate = scheduledDate; }
}
