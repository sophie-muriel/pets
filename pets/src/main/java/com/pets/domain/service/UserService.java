package com.pets.domain.service;

import com.pets.domain.dto.UserDTO;
import com.pets.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<UserDTO> getAllUsers() {
        return userRepository.getAllUsers();
    }

    public Optional<UserDTO> getUserById(int userId) {
        return userRepository.getUserById(userId);
    }

    public UserDTO saveUser(UserDTO user) {
        return userRepository.saveUser(user);
    }

    public boolean deleteUser(int userId) {
        return getUserById(userId).map(user -> {
            userRepository.deleteUser(userId);
            return true;
        }).orElse(false);
    }
}