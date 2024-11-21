package com.pets.web.controller;

import com.pets.domain.dto.LoginRequestDTO;
import com.pets.domain.dto.UserDTO;
import com.pets.domain.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Get all users", description = "Returns a list of users.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Users found", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)) }),
            @ApiResponse(responseCode = "204", description = "Users not found", content = @Content)
    })
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

    @Operation(summary = "Gets a user", description = "Returns a specific user.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User found", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)) }),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content)
    })
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

    @Operation(summary = "Saves a user", description = "Adds a user to the list.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User saved", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)) })
    })
    @PostMapping("/save")
    public ResponseEntity<Map<String, Object>> save(@Valid @RequestBody UserDTO userDTO) {
        Map<String, Object> response = new HashMap<>();
        UserDTO savedUser = userService.saveUser(userDTO);

        response.put("status", "success");
        response.put("message", "User created successfully");
        response.put("data", savedUser);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Operation(summary = "Logs a user in", description = "Lets a user login with the correct username and password.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User logged in", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)) }),
            @ApiResponse(responseCode = "401", description = "User not authorized", content = @Content)
    })
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody LoginRequestDTO loginRequest) {
        Map<String, Object> response = new HashMap<>();
        try {
            UserDTO user = userService.login(loginRequest.getLogin(), loginRequest.getPassword());
            response.put("status", "success");
            response.put("message", "Login successful");
            response.put("data", user);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            response.put("status", "error");
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }
    }

    @Operation(summary = "Edits a user", description = "Edits a user in the list.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User updated", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)) }),
            @ApiResponse(responseCode = "404", description = "User not updated", content = @Content)
    })
    @PutMapping("/edit/{id}")
    public ResponseEntity<Map<String, Object>> edit(@PathVariable("id") int userId,
            @Valid @RequestBody UserDTO userDTO) {
        Map<String, Object> response = new HashMap<>();
        Optional<UserDTO> existingUser = userService.getUserById(userId);

        if (existingUser.isPresent()) {
            userDTO.setId(userId);
            UserDTO updatedUser = userService.editUser(userId, userDTO);

            response.put("status", "success");
            response.put("message", "User updated successfully");
            response.put("data", updatedUser);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        response.put("status", "error");
        response.put("message", "User not found");
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Deletes a user", description = "Removes a user from the list.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User removed", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)) }),
            @ApiResponse(responseCode = "404", description = "User not removed", content = @Content)
    })
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