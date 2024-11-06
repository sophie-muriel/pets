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

    @Autowired
    private CategoryService categoryService;

    public List<ServiceDTO> getAllServices() {
        return serviceRepository.getAllServices();
    }

    public Optional<ServiceDTO> getServiceById(int serviceId) {
        Optional<ServiceDTO> service = serviceRepository.getServiceById(serviceId);
        if (service.isEmpty()) {
            throw new IllegalArgumentException("service not found");
        }
        return service;
    }

    public ServiceDTO saveService(ServiceDTO service) {
        if (categoryService.getCategoryById(service.getCategoryId()).isEmpty()) {
            throw new IllegalArgumentException("category not found");
        }
        return serviceRepository.saveService(service);
    }

    public boolean deleteService(int serviceId) {
        Optional<ServiceDTO> service = getServiceById(serviceId);
        if (service.isPresent()) {
            serviceRepository.deleteService(serviceId);
            return true;
        } else {
            throw new IllegalArgumentException("service not found");
        }
    }
}