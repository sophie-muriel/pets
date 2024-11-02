package com.pets.domain.repository;

import com.pets.domain.dto.ServiceDTO;

import java.util.List;
import java.util.Optional;

public interface ServiceRepository {
    List<ServiceDTO> getAllServices();
    Optional<ServiceDTO> getServiceById(int serviceId);
    ServiceDTO saveService(ServiceDTO service);
    void deleteService(int serviceId);
}