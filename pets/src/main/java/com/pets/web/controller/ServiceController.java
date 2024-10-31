package com.pets.web.controller;

import com.pets.domain.dto.ServiceDTO;
import com.pets.domain.service.ServiceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/services")
public class ServiceController {
    private final ServiceService serviceService;

    public ServiceController(ServiceService serviceService) {
        this.serviceService = serviceService;
    }

    @GetMapping
    public List<ServiceDTO> getAllServices() {
        return serviceService.getAllServices();
    }

    @GetMapping("/{id}")
    public ServiceDTO getServiceById(@PathVariable Integer id) {
        return serviceService.getServiceById(id);
    }

    @PostMapping
    public ServiceDTO saveService(@RequestBody ServiceDTO serviceDTO) {
        return serviceService.saveService(serviceDTO);
    }
}