package com.pets.domain.mapper;

import com.pets.domain.dto.ServiceDTO;
import com.pets.persistence.entity.ServiceEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ServiceMapper {
    @Mapping(target = "categoryId", source = "category.id")
    ServiceDTO toService(ServiceEntity entity);

    @Mapping(target = "category.id", source = "categoryId")
    ServiceEntity toEntity(ServiceDTO dto);
    List<ServiceDTO> toServices(List<ServiceEntity> entities);
}