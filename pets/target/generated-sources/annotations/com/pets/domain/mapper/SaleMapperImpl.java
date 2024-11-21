package com.pets.domain.mapper;

import com.pets.domain.dto.SaleDTO;
import com.pets.persistence.entity.ClientEntity;
import com.pets.persistence.entity.SaleEntity;
import com.pets.persistence.entity.UserEntity;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-21T04:34:24-0500",
    comments = "version: 1.6.2, compiler: Eclipse JDT (IDE) 3.40.0.z20241023-1306, environment: Java 17.0.13 (Eclipse Adoptium)"
)
@Component
public class SaleMapperImpl implements SaleMapper {

    @Override
    public SaleDTO toSale(SaleEntity entity) {
        if ( entity == null ) {
            return null;
        }

        Integer userId = null;
        Integer clientId = null;
        Integer id = null;
        String paymentMethod = null;
        LocalDate saleDate = null;
        String status = null;
        Double totalPrice = null;

        userId = entityUserId( entity );
        clientId = entityClientId( entity );
        id = entity.getId();
        paymentMethod = entity.getPaymentMethod();
        saleDate = entity.getSaleDate();
        status = entity.getStatus();
        totalPrice = entity.getTotalPrice();

        SaleDTO saleDTO = new SaleDTO( id, userId, clientId, totalPrice, saleDate, status, paymentMethod );

        return saleDTO;
    }

    @Override
    public SaleEntity toEntity(SaleDTO dto) {
        if ( dto == null ) {
            return null;
        }

        SaleEntity saleEntity = new SaleEntity();

        saleEntity.setUser( saleDTOToUserEntity( dto ) );
        saleEntity.setClient( saleDTOToClientEntity( dto ) );
        saleEntity.setId( dto.getId() );
        saleEntity.setPaymentMethod( dto.getPaymentMethod() );
        saleEntity.setSaleDate( dto.getSaleDate() );
        saleEntity.setStatus( dto.getStatus() );
        saleEntity.setTotalPrice( dto.getTotalPrice() );

        return saleEntity;
    }

    @Override
    public List<SaleDTO> toSales(List<SaleEntity> entities) {
        if ( entities == null ) {
            return null;
        }

        List<SaleDTO> list = new ArrayList<SaleDTO>( entities.size() );
        for ( SaleEntity saleEntity : entities ) {
            list.add( toSale( saleEntity ) );
        }

        return list;
    }

    private Integer entityUserId(SaleEntity saleEntity) {
        UserEntity user = saleEntity.getUser();
        if ( user == null ) {
            return null;
        }
        return user.getId();
    }

    private Integer entityClientId(SaleEntity saleEntity) {
        ClientEntity client = saleEntity.getClient();
        if ( client == null ) {
            return null;
        }
        return client.getId();
    }

    protected UserEntity saleDTOToUserEntity(SaleDTO saleDTO) {
        if ( saleDTO == null ) {
            return null;
        }

        UserEntity userEntity = new UserEntity();

        userEntity.setId( saleDTO.getUserId() );

        return userEntity;
    }

    protected ClientEntity saleDTOToClientEntity(SaleDTO saleDTO) {
        if ( saleDTO == null ) {
            return null;
        }

        ClientEntity clientEntity = new ClientEntity();

        clientEntity.setId( saleDTO.getClientId() );

        return clientEntity;
    }
}
