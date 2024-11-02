package com.pets.domain.dto;

import java.time.LocalDateTime;

public class SaleDTO {
    private Integer id;
    private Integer userId;
    private Integer clientId;
    private Double totalPrice;
    private LocalDateTime saleDate;
    private String status;
    private String paymentMethod;

    public SaleDTO(Integer id, Integer userId, Integer clientId, Double totalPrice, LocalDateTime saleDate, String status, String paymentMethod) {
        this.id = id;
        this.userId = userId;
        this.clientId = clientId;
        this.totalPrice = totalPrice;
        this.saleDate = saleDate;
        this.status = status;
        this.paymentMethod = paymentMethod;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Integer getUserId() { return userId; }
    public void setUserId(Integer userId) { this.userId = userId; }

    public Integer getClientId() { return clientId; }
    public void setClientId(Integer clientId) { this.clientId = clientId; }

    public Double getTotalPrice() { return totalPrice; }
    public void setTotalPrice(Double totalPrice) { this.totalPrice = totalPrice; }

    public LocalDateTime getSaleDate() { return saleDate; }
    public void setSaleDate(LocalDateTime saleDate) { this.saleDate = saleDate; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }
}
