package com.pets.domain.mapper;

import com.pets.domain.dto.UserDTO;
import com.pets.persistence.entity.UserEntity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-02T16:45:42-0500",
    comments = "version: 1.6.2, compiler: javac, environment: Java 22.0.2 (Amazon.com Inc.)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDTO toUser(UserEntity entity) {
        if ( entity == null ) {
            return null;
        }

        Integer id = null;
        String login = null;
        String password = null;
        String name = null;
        String email = null;
        String phoneNumber = null;
        String role = null;

        id = entity.getId();
        login = entity.getLogin();
        password = entity.getPassword();
        name = entity.getName();
        email = entity.getEmail();
        phoneNumber = entity.getPhoneNumber();
        role = entity.getRole();

        UserDTO userDTO = new UserDTO( id, login, password, name, email, phoneNumber, role );

        return userDTO;
    }

    @Override
    public UserEntity toEntity(UserDTO dto) {
        if ( dto == null ) {
            return null;
        }

        UserEntity userEntity = new UserEntity();

        userEntity.setId( dto.getId() );
        userEntity.setLogin( dto.getLogin() );
        userEntity.setPassword( dto.getPassword() );
        userEntity.setName( dto.getName() );
        userEntity.setEmail( dto.getEmail() );
        userEntity.setPhoneNumber( dto.getPhoneNumber() );
        userEntity.setRole( dto.getRole() );

        return userEntity;
    }

    @Override
    public List<UserDTO> toUsers(List<UserEntity> entities) {
        if ( entities == null ) {
            return null;
        }

        List<UserDTO> list = new ArrayList<UserDTO>( entities.size() );
        for ( UserEntity userEntity : entities ) {
            list.add( toUser( userEntity ) );
        }

        return list;
    }
}
