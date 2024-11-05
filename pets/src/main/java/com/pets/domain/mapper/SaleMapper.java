package com.pets.domain.mapper;

import com.pets.domain.dto.SaleDTO;
import com.pets.persistence.entity.SaleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;

@Mapper(componentModel = "spring")
public interface SaleMapper {
    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "clientId", source = "client.id")
    SaleDTO toSale(SaleEntity entity);

    @Mapping(target = "user.id", source = "userId")
    @Mapping(target = "client.id", source = "clientId")
    SaleEntity toEntity(SaleDTO dto);
    List<SaleDTO> toSales(List<SaleEntity> entities);
}