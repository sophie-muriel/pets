package com.pets.domain.dto;

import jakarta.validation.constraints.*;

public class UserDTO {
    private Integer id;

    @NotBlank
    @Size(max = 50)
    private String login;

    @NotBlank
    @Size(max = 50)
    private String password;

    @NotBlank
    @Size(max = 100)
    @Pattern(regexp = "^[a-zA-Z\\s]+$")
    private String name;

    @NotBlank
    @Email
    @Size(max = 100)
    private String email;

    @Size(max = 10)
    @Pattern(regexp = "^[0-9]*$")
    private String phoneNumber;

    @NotBlank
    @Pattern(regexp = "^(Employee|Admin|Stylist)$", message = "Employee, Admin, or Stylist only")
    private String role;

    public UserDTO(Integer id, String login, String password, String name, String email, String phoneNumber,
            String role) {
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