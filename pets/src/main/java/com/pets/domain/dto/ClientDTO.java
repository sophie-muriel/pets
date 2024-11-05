package com.pets.domain.dto;

import jakarta.validation.constraints.*;

public class ClientDTO {
    private Integer id;

    @NotBlank(message = "Error: nombre vacío")
    @Size(max = 100, message = "Error: nombre con más de 100 caracteres")
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Error: nombre inválido")
    private String name;

    @NotBlank(message = "Error: email vacío")
    @Email(message = "Error: email inválido")
    @Size(max = 100, message = "Error: email con más de 100 caracteres")
    private String email;

    @Pattern(regexp = "^\\d{0,10}$", message = "Error: número de teléfono inválido")
    private String phoneNumber;

    @NotBlank(message = "Error: dirección vacía")
    @Size(max = 255, message = "Error: dirección con más de 255 caracteres")
    private String address;

    public ClientDTO(Integer id, String name, String email, String phoneNumber, String address) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

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

    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}