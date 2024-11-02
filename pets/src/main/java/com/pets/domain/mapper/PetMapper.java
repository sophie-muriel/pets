package com.pets.domain.mapper;

import com.pets.domain.dto.PetDTO;
import com.pets.persistence.entity.PetEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PetMapper {
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "ownerId", source = "owner.id")
    @Mapping(target = "species", source = "species")
    @Mapping(target = "gender", source = "gender")
    @Mapping(target = "age", source = "age")
    @Mapping(target = "weight", source = "weight")
    @Mapping(target = "medicalHistoryLink", source = "medicalHistoryLink")
    PetDTO toPet(PetEntity entity);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "owner.id", source = "ownerId")
    @Mapping(target = "species", source = "species")
    @Mapping(target = "gender", source = "gender")
    @Mapping(target = "age", source = "age")
    @Mapping(target = "weight", source = "weight")
    @Mapping(target = "medicalHistoryLink", source = "medicalHistoryLink")
    PetEntity toEntity(PetDTO dto);

    List<PetDTO> toPets(List<PetEntity> entities);
}