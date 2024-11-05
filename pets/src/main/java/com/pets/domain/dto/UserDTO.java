package com.pets.domain.dto;

import jakarta.validation.constraints.*;

public class UserDTO {
    private Integer id;

    @NotBlank(message = "Error: login vacío")
    @Size(max = 50, message = "Error: login con más de 50 caracteres")
    private String login;

    @NotBlank(message = "Error: contraseña vacía")
    @Size(max = 50, message = "Error: contraseña con más de 50 caracteres")
    private String password;

    @NotBlank(message = "Error: nombre vacío")
    @Size(max = 100, message = "Error: nombre con más de 100 caracteres")
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Error: nombre inválido")
    private String name;

    @NotBlank(message = "Error: email vacío")
    @Email(message = "Error: email inválido")
    @Size(max = 100, message = "Error: email con más de 100 caracteres")
    private String email;

    @Size(max = 10, message = "Error: número de teléfono con más de 10 caracteres")
    @Pattern(regexp = "^[0-9]*$", message = "Error: número de teléfono inválido")
    private String phoneNumber;

    @NotBlank(message = "Error: rol vacío")
    @Pattern(regexp = "^(Empleado|Administrador|Estilista)$", message = "Error: rol debe ser 'Empleado', 'Administrador' o 'Estilista'")
    private String role;

    public UserDTO(Integer id, String login, String password, String name, String email, String phoneNumber, String role) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.role = role;
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

    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
}