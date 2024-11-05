package com.pets.domain.mapper;

import com.pets.domain.dto.CategoryDTO;
import com.pets.persistence.entity.CategoryEntity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-05T00:20:37-0500",
    comments = "version: 1.6.2, compiler: javac, environment: Java 22.0.2 (Amazon.com Inc.)"
)
@Component
public class CategoryMapperImpl implements CategoryMapper {

    @Override
    public CategoryDTO toCategory(CategoryEntity entity) {
        if ( entity == null ) {
            return null;
        }

        Integer id = null;
        String category = null;

        id = entity.getId();
        category = entity.getCategory();

        CategoryDTO categoryDTO = new CategoryDTO( category, id );

        return categoryDTO;
    }

    @Override
    public CategoryEntity toEntity(CategoryDTO dto) {
        if ( dto == null ) {
            return null;
        }

        CategoryEntity categoryEntity = new CategoryEntity();

        categoryEntity.setCategory( dto.getCategory() );
        categoryEntity.setId( dto.getId() );

        return categoryEntity;
    }

    @Override
    public List<CategoryDTO> toCategories(List<CategoryEntity> entities) {
        if ( entities == null ) {
            return null;
        }

        List<CategoryDTO> list = new ArrayList<CategoryDTO>( entities.size() );
        for ( CategoryEntity categoryEntity : entities ) {
            list.add( toCategory( categoryEntity ) );
        }

        return list;
    }
}
