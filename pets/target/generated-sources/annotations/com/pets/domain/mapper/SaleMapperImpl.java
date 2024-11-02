package com.pets.domain.mapper;

import com.pets.domain.dto.SaleDTO;
import com.pets.persistence.entity.ClientEntity;
import com.pets.persistence.entity.SaleEntity;
import com.pets.persistence.entity.UserEntity;
import java.time.LocalDateTime;
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
public class SaleMapperImpl implements SaleMapper {

    @Override
    public SaleDTO toSale(SaleEntity entity) {
        if ( entity == null ) {
            return null;
        }

        Integer id = null;
        Integer userId = null;
        Integer clientId = null;
        Double totalPrice = null;
        LocalDateTime saleDate = null;
        String status = null;
        String paymentMethod = null;

        id = entity.getId();
        userId = entityUserId( entity );
        clientId = entityClientId( entity );
        totalPrice = entity.getTotalPrice();
        saleDate = entity.getSaleDate();
        status = entity.getStatus();
        paymentMethod = entity.getPaymentMethod();

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
        saleEntity.setTotalPrice( dto.getTotalPrice() );
        saleEntity.setSaleDate( dto.getSaleDate() );
        saleEntity.setStatus( dto.getStatus() );
        saleEntity.setPaymentMethod( dto.getPaymentMethod() );

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
