package com.pets.domain.service;

import com.pets.domain.dto.UserDTO;
import com.pets.domain.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll();
    }

    public UserDTO getUserById(Integer id) {
        return userRepository.findById(id);
    }

    public UserDTO saveUser(UserDTO user) {
        return userRepository.save(user);
    }
}