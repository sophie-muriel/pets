package com.pets.persistence;

import com.pets.domain.dto.UserDTO;
import com.pets.domain.repository.UserRepository;
import com.pets.persistence.crud.UserCrudRepository;
import com.pets.persistence.entity.UserEntity;
import com.pets.domain.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {
    @Autowired
    private UserCrudRepository userCrudRepository;

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<UserDTO> getAllUsers() {
        List<UserEntity> users = (List<UserEntity>) userCrudRepository.findAll();
        return userMapper.toUsers(users);
    }

    @Override
    public Optional<UserDTO> getUserById(int userId) {
        return userCrudRepository.findById(userId).map(userMapper::toUser);
    }

    @Override
    public UserDTO saveUser(UserDTO userDTO) {
        UserEntity userEntity = userMapper.toEntity(userDTO);
        return userMapper.toUser(userCrudRepository.save(userEntity));
    }

    @Override
    public void deleteUser(int userId) {
        userCrudRepository.deleteById(userId);
    }
}