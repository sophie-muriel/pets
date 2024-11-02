package com.pets.persistence.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "clients")
public class ClientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String email;
    private String address;

    @Column(name = "phone_number")
    private String phoneNumber;

    @OneToMany(mappedBy = "owner")
    private List<PetEntity> pets;

    @OneToMany(mappedBy = "client")
    private List<SaleEntity> sales;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public List<PetEntity> getPets() {
        return pets;
    }
    public void setPets(List<PetEntity> pets) {
        this.pets = pets;
    }

    public List<SaleEntity> getSales() {
        return sales;
    }
    public void setSales(List<SaleEntity> sales) {
        this.sales = sales;
    }
}