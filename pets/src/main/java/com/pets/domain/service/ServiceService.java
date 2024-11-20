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

    public ServiceDTO editService(int serviceId, ServiceDTO updatedService) {
        Optional<ServiceDTO> existingServiceOptional = serviceRepository.getServiceById(serviceId);
        if (existingServiceOptional.isEmpty()) {
            throw new IllegalArgumentException("Service not found");
        }
        ServiceDTO existingService = existingServiceOptional.get();

        if (updatedService.getName() != null && !updatedService.getName().equals(existingService.getName())) {
            existingService.setName(updatedService.getName());
        }
        if (updatedService.getDescription() != null
                && !updatedService.getDescription().equals(existingService.getDescription())) {
            existingService.setDescription(updatedService.getDescription());
        }
        if (updatedService.getPrice() != null && !updatedService.getPrice().equals(existingService.getPrice())) {
            existingService.setPrice(updatedService.getPrice());
        }
        if (updatedService.getCategoryId() != null
                && !updatedService.getCategoryId().equals(existingService.getCategoryId())) {
            existingService.setCategoryId(updatedService.getCategoryId());
        }

        return serviceRepository.saveService(existingService);
    }
}