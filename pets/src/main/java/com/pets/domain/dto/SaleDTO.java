package com.pets.domain.dto;

import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

public class SaleDTO {
    private Integer id;
    @NotNull(message = "Error: ID del usuario vacío")
    @Positive(message = "Error: ID del usuario inválido")
    private Integer userId;

    @NotNull(message = "Error: ID del cliente vacío")
    @Positive(message = "Error: ID del cliente inválido")
    private Integer clientId;

    @Positive(message = "Error: precio inválido")
    private Double totalPrice;

    @NotNull(message = "Error: fecha de venta no puede estar vacía")
    private LocalDateTime saleDate;

    @NotBlank(message = "Error: estado no puede estar vacío")
    @Pattern(regexp = "^(Completada|Pendiente|Cancelada)$", message = "Error: estado debe ser 'Completada', 'Pendiente' o 'Cancelada'")
    private String status;

    @NotBlank(message = "Error: método de pago no puede estar vacío")
    @Pattern(regexp = "^(Efectivo|Tarjeta|Transferencia)$", message = "Error: método de pago debe ser 'Efectivo', 'Tarjeta' o 'Transferencia'")
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

    public LocalDateTime getSaleDate() {
        return saleDate;
    }
    public void setSaleDate(LocalDateTime saleDate) {
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