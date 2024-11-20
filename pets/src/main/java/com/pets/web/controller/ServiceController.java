package com.pets.web.controller;

import com.pets.domain.dto.ServiceDTO;
import com.pets.domain.service.ServiceService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/services")
public class ServiceController {

    @Autowired
    private ServiceService serviceService;

    @GetMapping("/all")
    public ResponseEntity<Map<String, Object>> getAll() {
        List<ServiceDTO> services = serviceService.getAllServices();
        Map<String, Object> response = new HashMap<>();

        if (services.isEmpty()) {
            response.put("status", "error");
            response.put("message", "No services found");
            return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
        }

        response.put("status", "success");
        response.put("data", services);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getService(@PathVariable("id") int serviceId) {
        Map<String, Object> response = new HashMap<>();
        Optional<ServiceDTO> service = serviceService.getServiceById(serviceId);

        if (service.isPresent()) {
            response.put("status", "success");
            response.put("data", service.get());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.put("status", "error");
            response.put("message", "Service not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/save")
    public ResponseEntity<Map<String, Object>> save(@Valid @RequestBody ServiceDTO serviceDTO) {
        Map<String, Object> response = new HashMap<>();
        ServiceDTO savedService = serviceService.saveService(serviceDTO);

        response.put("status", "success");
        response.put("message", "Service created successfully");
        response.put("data", savedService);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Map<String, Object>> edit(@PathVariable("id") int serviceId,
            @Valid @RequestBody ServiceDTO serviceDTO) {
        Map<String, Object> response = new HashMap<>();
        Optional<ServiceDTO> existingService = serviceService.getServiceById(serviceId);

        if (existingService.isPresent()) {
            serviceDTO.setId(serviceId);
            ServiceDTO updatedService = serviceService.editService(serviceId, serviceDTO);

            response.put("status", "success");
            response.put("message", "Service updated successfully");
            response.put("data", updatedService);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        response.put("status", "error");
        response.put("message", "Service not found");
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable("id") int serviceId) {
        Map<String, Object> response = new HashMap<>();
        boolean deleted = serviceService.deleteService(serviceId);

        if (deleted) {
            response.put("status", "success");
            response.put("message", "Service deleted successfully");
            return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
        }

        response.put("status", "error");
        response.put("message", "Service not found");
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}