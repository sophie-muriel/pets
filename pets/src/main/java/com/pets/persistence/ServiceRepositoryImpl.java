package com.pets.persistence;

import com.pets.domain.dto.ServiceDTO;
import com.pets.domain.repository.ServiceRepository;
import com.pets.persistence.crud.ServiceCrudRepository;
import com.pets.persistence.entity.ServiceEntity;
import com.pets.domain.mapper.ServiceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ServiceRepositoryImpl implements ServiceRepository {
    @Autowired
    private ServiceCrudRepository serviceCrudRepository;

    @Autowired
    private ServiceMapper serviceMapper;

    @Override
    public List<ServiceDTO> getAllServices() {
        List<ServiceEntity> services = (List<ServiceEntity>) serviceCrudRepository.findAll();
        return serviceMapper.toServices(services);
    }

    @Override
    public Optional<ServiceDTO> getServiceById(int serviceId) {
        return serviceCrudRepository.findById(serviceId).map(serviceMapper::toService);
    }

    @Override
    public ServiceDTO saveService(ServiceDTO serviceDTO) {
        ServiceEntity serviceEntity = serviceMapper.toEntity(serviceDTO);
        return serviceMapper.toService(serviceCrudRepository.save(serviceEntity));
    }

    @Override
    public void deleteService(int serviceId) {
        serviceCrudRepository.deleteById(serviceId);
    }
}