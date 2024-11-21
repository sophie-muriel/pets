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
    date = "2024-11-21T10:12:43-0500",
    comments = "version: 1.6.2, compiler: Eclipse JDT (IDE) 3.40.0.z20241023-1306, environment: Java 17.0.13 (Eclipse Adoptium)"
)
@Component
public class ServiceMapperImpl implements ServiceMapper {

    @Override
    public ServiceDTO toService(ServiceEntity entity) {
        if ( entity == null ) {
            return null;
        }

        Integer categoryId = null;
        String description = null;
        Integer id = null;
        String name = null;
        Double price = null;

        categoryId = entityCategoryId( entity );
        description = entity.getDescription();
        id = entity.getId();
        name = entity.getName();
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
        serviceEntity.setDescription( dto.getDescription() );
        serviceEntity.setId( dto.getId() );
        serviceEntity.setName( dto.getName() );
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
