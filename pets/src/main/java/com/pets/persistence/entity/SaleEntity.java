package com.pets.persistence.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "sale")
public class SaleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Double totalPrice;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private ClientEntity client;

    @Column(name = "sale_date", nullable = false)
    private LocalDateTime saleDate;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "payment_method")
    private String paymentMethod;

    @OneToMany(mappedBy = "sale")
    private List<SaleDetailEntity> saleDetails;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public UserEntity getUser() {
        return user;
    }
    public void setUser(UserEntity user) {
        this.user = user;
    }

    public ClientEntity getClient() {
        return client;
    }
    public void setClient(ClientEntity client) {
        this.client = client;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }
    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
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

    public String getPaymentMethod() {
        return paymentMethod;
    }
    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public List<SaleDetailEntity> getSaleDetails() {
        return saleDetails;
    }
    public void setSaleDetails(List<SaleDetailEntity> saleDetails) {
        this.saleDetails = saleDetails;
    }
}