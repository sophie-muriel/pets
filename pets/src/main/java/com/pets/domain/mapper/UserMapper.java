package com.pets.domain.mapper;

import com.pets.domain.dto.UserDTO;
import com.pets.persistence.entity.UserEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDTO toUser(UserEntity entity);

    UserEntity toEntity(UserDTO dto);

    List<UserDTO> toUsers(List<UserEntity> entities);
}