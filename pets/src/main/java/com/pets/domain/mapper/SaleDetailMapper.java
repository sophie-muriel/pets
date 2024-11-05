package com.pets.domain.mapper;

import com.pets.domain.dto.SaleDetailDTO;
import com.pets.persistence.entity.SaleDetailEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;

@Mapper(componentModel = "spring")
public interface SaleDetailMapper {
    @Mapping(target = "serviceId", source = "service.id")
    @Mapping(target = "petId", source = "pet.id")
    SaleDetailDTO toSaleDetail(SaleDetailEntity entity);

    @Mapping(target = "service.id", source = "serviceId")
    @Mapping(target = "pet.id", source = "petId")
    SaleDetailEntity toEntity(SaleDetailDTO dto);
    List<SaleDetailDTO> toSaleDetails(List<SaleDetailEntity> entities);
}