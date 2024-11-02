package com.pets.domain.mapper;

import com.pets.domain.dto.ClientDTO;
import com.pets.persistence.entity.ClientEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClientMapper {
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "phoneNumber", source = "phoneNumber")
    @Mapping(target = "address", source = "address")
    ClientDTO toClient(ClientEntity entity);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "phoneNumber", source = "phoneNumber")
    @Mapping(target = "address", source = "address")
    ClientEntity toEntity(ClientDTO dto);

    List<ClientDTO> toClients(List<ClientEntity> entities);
}