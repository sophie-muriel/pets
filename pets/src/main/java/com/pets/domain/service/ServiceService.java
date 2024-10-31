package com.pets.domain.service;

import com.pets.domain.dto.ServiceDTO;
import com.pets.domain.repository.ServiceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceService {
    private final ServiceRepository serviceRepository;

    public ServiceService(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    public List<ServiceDTO> getAllServices() {
        return serviceRepository.findAll();
    }

    public ServiceDTO getServiceById(Integer id) {
        return serviceRepository.findById(id);
    }

    public ServiceDTO saveService(ServiceDTO service) {
        return serviceRepository.save(service);
    }
}