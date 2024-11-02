package com.pets.domain.repository;

import com.pets.domain.dto.UserDTO;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    List<UserDTO> getAllUsers();
    Optional<UserDTO> getUserById(int userId);
    UserDTO saveUser(UserDTO user);
    void deleteUser(int userId);
}
