package com.pets.domain.mapper;

import com.pets.domain.dto.ServiceDTO;
import com.pets.persistence.entity.ServiceEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ServiceMapper {
    @Mapping(target = "id", source = "id")
    @Mapping(target = "categoryId", source = "category.id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "price", source = "price")
    ServiceDTO toService(ServiceEntity entity);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "category.id", source = "categoryId")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "price", source = "price")
    ServiceEntity toEntity(ServiceDTO dto);

    List<ServiceDTO> toServices(List<ServiceEntity> entities);
}