package com.pets.domain.mapper;

import com.pets.domain.dto.PetDTO;
import com.pets.persistence.entity.ClientEntity;
import com.pets.persistence.entity.PetEntity;
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
public class PetMapperImpl implements PetMapper {

    @Override
    public PetDTO toPet(PetEntity entity) {
        if ( entity == null ) {
            return null;
        }

        Integer id = null;
        String name = null;
        Integer ownerId = null;
        String species = null;
        String gender = null;
        Integer age = null;
        Double weight = null;
        String medicalHistoryLink = null;

        id = entity.getId();
        name = entity.getName();
        ownerId = entityOwnerId( entity );
        species = entity.getSpecies();
        gender = entity.getGender();
        age = entity.getAge();
        weight = entity.getWeight();
        medicalHistoryLink = entity.getMedicalHistoryLink();

        PetDTO petDTO = new PetDTO( id, name, ownerId, species, gender, age, weight, medicalHistoryLink );

        return petDTO;
    }

    @Override
    public PetEntity toEntity(PetDTO dto) {
        if ( dto == null ) {
            return null;
        }

        PetEntity petEntity = new PetEntity();

        petEntity.setOwner( petDTOToClientEntity( dto ) );
        petEntity.setId( dto.getId() );
        petEntity.setName( dto.getName() );
        petEntity.setSpecies( dto.getSpecies() );
        petEntity.setGender( dto.getGender() );
        petEntity.setAge( dto.getAge() );
        petEntity.setWeight( dto.getWeight() );
        petEntity.setMedicalHistoryLink( dto.getMedicalHistoryLink() );

        return petEntity;
    }

    @Override
    public List<PetDTO> toPets(List<PetEntity> entities) {
        if ( entities == null ) {
            return null;
        }

        List<PetDTO> list = new ArrayList<PetDTO>( entities.size() );
        for ( PetEntity petEntity : entities ) {
            list.add( toPet( petEntity ) );
        }

        return list;
    }

    private Integer entityOwnerId(PetEntity petEntity) {
        ClientEntity owner = petEntity.getOwner();
        if ( owner == null ) {
            return null;
        }
        return owner.getId();
    }

    protected ClientEntity petDTOToClientEntity(PetDTO petDTO) {
        if ( petDTO == null ) {
            return null;
        }

        ClientEntity clientEntity = new ClientEntity();

        clientEntity.setId( petDTO.getOwnerId() );

        return clientEntity;
    }
}
