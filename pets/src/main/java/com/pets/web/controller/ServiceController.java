package com.pets.web.controller;

import com.pets.domain.dto.ServiceDTO;
import com.pets.domain.service.ServiceService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Get all services", description = "Returns a list of services.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Services found", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)) }),
            @ApiResponse(responseCode = "204", description = "Services not found", content = @Content)
    })
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

    @Operation(summary = "Gets a service", description = "Returns a specific service.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Service found", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)) }),
            @ApiResponse(responseCode = "404", description = "Service not found", content = @Content)
    })
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

    @Operation(summary = "Saves a service", description = "Adds a service to the list.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Service saved", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)) })
    })
    @PostMapping("/save")
    public ResponseEntity<Map<String, Object>> save(@Valid @RequestBody ServiceDTO serviceDTO) {
        Map<String, Object> response = new HashMap<>();
        ServiceDTO savedService = serviceService.saveService(serviceDTO);

        response.put("status", "success");
        response.put("message", "Service created successfully");
        response.put("data", savedService);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Operation(summary = "Edits a service", description = "Edits a service in the list.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Service updated", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)) }),
            @ApiResponse(responseCode = "404", description = "Service not updated", content = @Content)
    })
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

    @Operation(summary = "Deletes a service", description = "Removes a service from the list.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Service removed", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)) }),
            @ApiResponse(responseCode = "404", description = "Service not removed", content = @Content)
    })
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