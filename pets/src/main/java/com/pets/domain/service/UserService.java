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
        Optional<UserDTO> user = userRepository.getUserById(userId);
        if (user.isEmpty()) {
            throw new IllegalArgumentException("user not found");
        }
        return user;
    }

    public boolean login(String login, String password) {
        Optional<UserDTO> temp = userRepository.findByLogin(login);
        if (temp.isPresent()) {
            UserDTO user = temp.get();
            if (user.getPassword().equals(password)) {
                return true;
            }
        }
        throw new IllegalArgumentException("invalid credentials");
    }

    public UserDTO saveUser(UserDTO user) {
        if (userRepository.findByLogin(user.getLogin()).isPresent()) {
            throw new IllegalArgumentException("login in use");
        }
        return userRepository.saveUser(user);
    }

    public boolean deleteUser(int userId) {
        Optional<UserDTO> user = getUserById(userId);
        if (user.isPresent()) {
            userRepository.deleteUser(userId);
            return true;
        } else {
            throw new IllegalArgumentException("user not found");
        }
    }
}