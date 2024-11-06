package com.pets.domain.dto;

import jakarta.validation.constraints.*;
import java.time.LocalDate;

public class SaleDTO {
    private Integer id;

    @NotNull @Positive
    private Integer userId;

    @NotNull @Positive
    private Integer clientId;

    @Positive
    private Double totalPrice;

    @NotNull
    private LocalDate saleDate;

    @NotBlank @Pattern(regexp = "^(Completed|Pending|Cancelled)$", message = "Completed, Pending, or Cancelled only")
    private String status;

    @NotBlank @Pattern(regexp = "^(Cash|Card|Transfer)$", message = "Cash, Card, or Transfer only")
    private String paymentMethod;

    public SaleDTO(Integer id, Integer userId, Integer clientId, Double totalPrice, LocalDate saleDate, String status, String paymentMethod) {
        this.id = id;
        this.userId = userId;
        this.clientId = clientId;
        this.totalPrice = totalPrice;
        this.saleDate = saleDate;
        this.status = status;
        this.paymentMethod = paymentMethod;
    }

    public Integer getClientId() {
        return clientId;
    }
    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }
    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public LocalDate getSaleDate() {
        return saleDate;
    }
    public void setSaleDate(LocalDate saleDate) {
        this.saleDate = saleDate;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }
    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getUserId() {
        return userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}