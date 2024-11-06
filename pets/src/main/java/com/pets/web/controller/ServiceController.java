package com.pets.web.controller;

import com.pets.domain.dto.ServiceDTO;
import com.pets.domain.service.ServiceService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/services")
public class ServiceController {

    @Autowired
    private ServiceService serviceService;

    @GetMapping("/all")
    public ResponseEntity<List<ServiceDTO>> getAll() {
        List<ServiceDTO> services = serviceService.getAllServices();
        if (services.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(services, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceDTO> getService(@PathVariable("id") int serviceId) {
        Optional<ServiceDTO> service = serviceService.getServiceById(serviceId);
        return service.map(serviceDTO
                -> new ResponseEntity<>(serviceDTO, HttpStatus.OK)).orElseGet(()
                -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/save")
    public ResponseEntity<ServiceDTO> save(@Valid @RequestBody ServiceDTO serviceDTO) {
        ServiceDTO savedService = serviceService.saveService(serviceDTO);
        return new ResponseEntity<>(savedService, HttpStatus.CREATED);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<ServiceDTO> edit(@PathVariable("id") int serviceId, @Valid @RequestBody ServiceDTO serviceDTO) {
        Optional<ServiceDTO> existingService = serviceService.getServiceById(serviceId);
        if (existingService.isPresent()) {
            serviceDTO.setId(serviceId);
            ServiceDTO updatedService = serviceService.saveService(serviceDTO);
            return new ResponseEntity<>(updatedService, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") int serviceId) {
        boolean deleted = serviceService.deleteService(serviceId);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}