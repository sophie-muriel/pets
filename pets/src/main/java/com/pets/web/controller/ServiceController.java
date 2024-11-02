package com.pets.web.controller;

import com.pets.domain.dto.ServiceDTO;
import com.pets.domain.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/services")
public class ServiceController {
    @Autowired
    private ServiceService serviceService;

    @GetMapping("/all")
    public List<ServiceDTO> getAll() {
        return serviceService.getAllServices();
    }

    @GetMapping("/{id}")
    public Optional<ServiceDTO> getService(@PathVariable("id") int serviceId) {
        return serviceService.getServiceById(serviceId);
    }

    @PostMapping("/save")
    public ServiceDTO save(@RequestBody ServiceDTO serviceDTO) {
        return serviceService.saveService(serviceDTO);
    }

    @DeleteMapping("/delete/{id}")
    public boolean delete(@PathVariable("id") int serviceId) {
        return serviceService.deleteService(serviceId);
    }
}