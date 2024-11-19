package com.pets.domain.mapper;

import com.pets.domain.dto.SaleDetailDTO;
import com.pets.persistence.entity.PetEntity;
import com.pets.persistence.entity.SaleDetailEntity;
import com.pets.persistence.entity.ServiceEntity;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-19T01:28:19-0500",
    comments = "version: 1.6.2, compiler: Eclipse JDT (IDE) 3.40.0.z20241023-1306, environment: Java 17.0.13 (Eclipse Adoptium)"
)
@Component
public class SaleDetailMapperImpl implements SaleDetailMapper {

    @Override
    public SaleDetailDTO toSaleDetail(SaleDetailEntity entity) {
        if ( entity == null ) {
            return null;
        }

        Integer serviceId = null;
        Integer petId = null;
        Integer id = null;
        LocalDate scheduledDate = null;

        serviceId = entityServiceId( entity );
        petId = entityPetId( entity );
        id = entity.getId();
        scheduledDate = entity.getScheduledDate();

        SaleDetailDTO saleDetailDTO = new SaleDetailDTO( id, serviceId, petId, scheduledDate );

        return saleDetailDTO;
    }

    @Override
    public SaleDetailEntity toEntity(SaleDetailDTO dto) {
        if ( dto == null ) {
            return null;
        }

        SaleDetailEntity saleDetailEntity = new SaleDetailEntity();

        saleDetailEntity.setService( saleDetailDTOToServiceEntity( dto ) );
        saleDetailEntity.setPet( saleDetailDTOToPetEntity( dto ) );
        saleDetailEntity.setId( dto.getId() );
        saleDetailEntity.setScheduledDate( dto.getScheduledDate() );

        return saleDetailEntity;
    }

    @Override
    public List<SaleDetailDTO> toSaleDetails(List<SaleDetailEntity> entities) {
        if ( entities == null ) {
            return null;
        }

        List<SaleDetailDTO> list = new ArrayList<SaleDetailDTO>( entities.size() );
        for ( SaleDetailEntity saleDetailEntity : entities ) {
            list.add( toSaleDetail( saleDetailEntity ) );
        }

        return list;
    }

    private Integer entityServiceId(SaleDetailEntity saleDetailEntity) {
        ServiceEntity service = saleDetailEntity.getService();
        if ( service == null ) {
            return null;
        }
        return service.getId();
    }

    private Integer entityPetId(SaleDetailEntity saleDetailEntity) {
        PetEntity pet = saleDetailEntity.getPet();
        if ( pet == null ) {
            return null;
        }
        return pet.getId();
    }

    protected ServiceEntity saleDetailDTOToServiceEntity(SaleDetailDTO saleDetailDTO) {
        if ( saleDetailDTO == null ) {
            return null;
        }

        ServiceEntity serviceEntity = new ServiceEntity();

        serviceEntity.setId( saleDetailDTO.getServiceId() );

        return serviceEntity;
    }

    protected PetEntity saleDetailDTOToPetEntity(SaleDetailDTO saleDetailDTO) {
        if ( saleDetailDTO == null ) {
            return null;
        }

        PetEntity petEntity = new PetEntity();

        petEntity.setId( saleDetailDTO.getPetId() );

        return petEntity;
    }
}
