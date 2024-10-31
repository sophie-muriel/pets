package com.pets.domain.repository;

import com.pets.domain.dto.UserDTO;
import java.util.List;

public interface UserRepository {
    List<UserDTO> findAll();
    UserDTO findById(Integer id);
    UserDTO findByLogin(String login);
    UserDTO findByEmail(String email);
    UserDTO save(UserDTO user);
}