package com.pets.domain.mapper;

import com.pets.domain.dto.ServiceDTO;
import com.pets.persistence.entity.CategoryEntity;
import com.pets.persistence.entity.ServiceEntity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-02T16:45:42-0500",
    comments = "version: 1.6.2, compiler: javac, environment: Java 22.0.2 (Amazon.com Inc.)"
)
@Component
public class ServiceMapperImpl implements ServiceMapper {

    @Override
    public ServiceDTO toService(ServiceEntity entity) {
        if ( entity == null ) {
            return null;
        }

        Integer id = null;
        Integer categoryId = null;
        String name = null;
        String description = null;
        Double price = null;

        id = entity.getId();
        categoryId = entityCategoryId( entity );
        name = entity.getName();
        description = entity.getDescription();
        price = entity.getPrice();

        ServiceDTO serviceDTO = new ServiceDTO( id, name, description, price, categoryId );

        return serviceDTO;
    }

    @Override
    public ServiceEntity toEntity(ServiceDTO dto) {
        if ( dto == null ) {
            return null;
        }

        ServiceEntity serviceEntity = new ServiceEntity();

        serviceEntity.setCategory( serviceDTOToCategoryEntity( dto ) );
        serviceEntity.setId( dto.getId() );
        serviceEntity.setName( dto.getName() );
        serviceEntity.setDescription( dto.getDescription() );
        serviceEntity.setPrice( dto.getPrice() );

        return serviceEntity;
    }

    @Override
    public List<ServiceDTO> toServices(List<ServiceEntity> entities) {
        if ( entities == null ) {
            return null;
        }

        List<ServiceDTO> list = new ArrayList<ServiceDTO>( entities.size() );
        for ( ServiceEntity serviceEntity : entities ) {
            list.add( toService( serviceEntity ) );
        }

        return list;
    }

    private Integer entityCategoryId(ServiceEntity serviceEntity) {
        CategoryEntity category = serviceEntity.getCategory();
        if ( category == null ) {
            return null;
        }
        return category.getId();
    }

    protected CategoryEntity serviceDTOToCategoryEntity(ServiceDTO serviceDTO) {
        if ( serviceDTO == null ) {
            return null;
        }

        CategoryEntity categoryEntity = new CategoryEntity();

        categoryEntity.setId( serviceDTO.getCategoryId() );

        return categoryEntity;
    }
}
