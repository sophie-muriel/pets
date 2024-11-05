package com.pets.domain.mapper;

import com.pets.domain.dto.CategoryDTO;
import com.pets.persistence.entity.CategoryEntity;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryDTO toCategory(CategoryEntity entity);
    CategoryEntity toEntity(CategoryDTO dto);
    List<CategoryDTO> toCategories(List<CategoryEntity> entities);
}