package com.pets.web.controller;

import com.pets.domain.dto.UserDTO;
import com.pets.domain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/all")
    public List<UserDTO> getAll() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public Optional<UserDTO> getUser(@PathVariable("id") int userId) {
        return userService.getUserById(userId);
    }

    @PostMapping("/save")
    public UserDTO save(@RequestBody UserDTO userDTO) {
        return userService.saveUser(userDTO);
    }

    @DeleteMapping("/delete/{id}")
    public boolean delete(@PathVariable("id") int userId) {
        return userService.deleteUser(userId);
    }
}