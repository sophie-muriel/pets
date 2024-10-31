package com.pets.domain.repository;

import com.pets.domain.dto.ServiceDTO;
import java.util.List;

public interface ServiceRepository {
    List<ServiceDTO> findAll();
    ServiceDTO findById(Integer id);
    List<ServiceDTO> findByCategoryId(Integer categoryId);
    ServiceDTO save(ServiceDTO service);
}