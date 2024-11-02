package com.pets.domain.mapper;

import com.pets.domain.dto.ClientDTO;
import com.pets.persistence.entity.ClientEntity;
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
public class ClientMapperImpl implements ClientMapper {

    @Override
    public ClientDTO toClient(ClientEntity entity) {
        if ( entity == null ) {
            return null;
        }

        Integer id = null;
        String name = null;
        String email = null;
        String phoneNumber = null;
        String address = null;

        id = entity.getId();
        name = entity.getName();
        email = entity.getEmail();
        phoneNumber = entity.getPhoneNumber();
        address = entity.getAddress();

        ClientDTO clientDTO = new ClientDTO( id, name, email, phoneNumber, address );

        return clientDTO;
    }

    @Override
    public ClientEntity toEntity(ClientDTO dto) {
        if ( dto == null ) {
            return null;
        }

        ClientEntity clientEntity = new ClientEntity();

        clientEntity.setId( dto.getId() );
        clientEntity.setName( dto.getName() );
        clientEntity.setEmail( dto.getEmail() );
        clientEntity.setPhoneNumber( dto.getPhoneNumber() );
        clientEntity.setAddress( dto.getAddress() );

        return clientEntity;
    }

    @Override
    public List<ClientDTO> toClients(List<ClientEntity> entities) {
        if ( entities == null ) {
            return null;
        }

        List<ClientDTO> list = new ArrayList<ClientDTO>( entities.size() );
        for ( ClientEntity clientEntity : entities ) {
            list.add( toClient( clientEntity ) );
        }

        return list;
    }
}
