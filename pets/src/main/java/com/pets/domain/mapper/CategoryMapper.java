package com.pets.domain.mapper;

import com.pets.domain.dto.CategoryDTO;
import com.pets.persistence.entity.CategoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    @Mapping(target = "id", source = "id")
    @Mapping(target = "category", source = "category")
    CategoryDTO toCategory(CategoryEntity entity);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "category", source = "category")
    CategoryEntity toEntity(CategoryDTO dto);

    List<CategoryDTO> toCategories(List<CategoryEntity> entities);
}