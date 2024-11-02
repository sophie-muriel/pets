package com.pets.domain.mapper;

import com.pets.domain.dto.SaleDTO;
import com.pets.persistence.entity.SaleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SaleMapper {
    @Mapping(target = "id", source = "id")
    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "clientId", source = "client.id")
    @Mapping(target = "totalPrice", source = "totalPrice")
    @Mapping(target = "saleDate", source = "saleDate")
    @Mapping(target = "status", source = "status")
    @Mapping(target = "paymentMethod", source = "paymentMethod")
    SaleDTO toSale(SaleEntity entity);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "user.id", source = "userId")
    @Mapping(target = "client.id", source = "clientId")
    @Mapping(target = "totalPrice", source = "totalPrice")
    @Mapping(target = "saleDate", source = "saleDate")
    @Mapping(target = "status", source = "status")
    @Mapping(target = "paymentMethod", source = "paymentMethod")
    SaleEntity toEntity(SaleDTO dto);

    List<SaleDTO> toSales(List<SaleEntity> entities);
}