package com.pets.domain.mapper;

import com.pets.domain.dto.UserDTO;
import com.pets.persistence.entity.UserEntity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-21T10:12:43-0500",
    comments = "version: 1.6.2, compiler: Eclipse JDT (IDE) 3.40.0.z20241023-1306, environment: Java 17.0.13 (Eclipse Adoptium)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDTO toUser(UserEntity entity) {
        if ( entity == null ) {
            return null;
        }

        String email = null;
        Integer id = null;
        String login = null;
        String name = null;
        String password = null;
        String phoneNumber = null;
        String role = null;

        email = entity.getEmail();
        id = entity.getId();
        login = entity.getLogin();
        name = entity.getName();
        password = entity.getPassword();
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

        userEntity.setEmail( dto.getEmail() );
        userEntity.setId( dto.getId() );
        userEntity.setLogin( dto.getLogin() );
        userEntity.setName( dto.getName() );
        userEntity.setPassword( dto.getPassword() );
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
