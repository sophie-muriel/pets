package com.pets.web.controller;

import com.pets.domain.dto.LoginRequestDTO;
import com.pets.domain.dto.UserDTO;
import com.pets.domain.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/all")
    public ResponseEntity<Map<String, Object>> getAll() {
        List<UserDTO> users = userService.getAllUsers();
        Map<String, Object> response = new HashMap<>();

        if (users.isEmpty()) {
            response.put("status", "error");
            response.put("message", "No users found");
            return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
        }

        response.put("status", "success");
        response.put("data", users);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getUser(@PathVariable("id") int userId) {
        Map<String, Object> response = new HashMap<>();
        Optional<UserDTO> user = userService.getUserById(userId);

        if (user.isPresent()) {
            response.put("status", "success");
            response.put("data", user.get());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.put("status", "error");
            response.put("message", "User not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/save")
    public ResponseEntity<Map<String, Object>> save(@Valid @RequestBody UserDTO userDTO) {
        Map<String, Object> response = new HashMap<>();
        UserDTO savedUser = userService.saveUser(userDTO);

        response.put("status", "success");
        response.put("message", "User created successfully");
        response.put("data", savedUser);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody LoginRequestDTO loginRequest) {
        Map<String, Object> response = new HashMap<>();

        try {
            boolean success = userService.login(loginRequest.getLogin(), loginRequest.getPassword());
            if (success) {
                response.put("status", "success");
                response.put("message", "Login successful");
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                response.put("status", "error");
                response.put("message", "Invalid credentials");
                return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
            }
        } catch (IllegalArgumentException e) {
            response.put("status", "error");
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Map<String, Object>> edit(@PathVariable("id") int userId, @Valid @RequestBody UserDTO userDTO) {
        Map<String, Object> response = new HashMap<>();
        Optional<UserDTO> existingUser = userService.getUserById(userId);

        if (existingUser.isPresent()) {
            userDTO.setId(userId);
            UserDTO updatedUser = userService.saveUser(userDTO);

            response.put("status", "success");
            response.put("message", "User updated successfully");
            response.put("data", updatedUser);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        response.put("status", "error");
        response.put("message", "User not found");
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable("id") int userId) {
        Map<String, Object> response = new HashMap<>();
        boolean deleted = userService.deleteUser(userId);

        if (deleted) {
            response.put("status", "success");
            response.put("message", "User deleted successfully");
            return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
        }

        response.put("status", "error");
        response.put("message", "User not found");
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}