package com.pets.domain.service;

import com.pets.domain.dto.ServiceDTO;
import com.pets.domain.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceService {
    @Autowired
    private ServiceRepository serviceRepository;

    public List<ServiceDTO> getAllServices() {
        return serviceRepository.getAllServices();
    }

    public Optional<ServiceDTO> getServiceById(int serviceId) {
        return serviceRepository.getServiceById(serviceId);
    }

    public ServiceDTO saveService(ServiceDTO service) {
        return serviceRepository.saveService(service);
    }

    public boolean deleteService(int serviceId) {
        return getServiceById(serviceId).map(service -> {
            serviceRepository.deleteService(serviceId);
            return true;
        }).orElse(false);
    }
}