package com.pets.web.controller;

import com.pets.domain.dto.ClientDTO;
import com.pets.domain.service.ClientService;

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
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @Operation(summary = "Get all clients", description = "Returns a list of clients.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Clients found", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)) }),
            @ApiResponse(responseCode = "204", description = "Clients not found", content = @Content)
    })
    @GetMapping("/all")
    public ResponseEntity<Map<String, Object>> getAll() {
        List<ClientDTO> clients = clientService.getAllClients();
        Map<String, Object> response = new HashMap<>();

        if (clients.isEmpty()) {
            response.put("status", "error");
            response.put("message", "No clients found");
            return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
        }

        response.put("status", "success");
        response.put("data", clients);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Gets a client", description = "Returns a specific client.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Client found", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)) }),
            @ApiResponse(responseCode = "404", description = "Client not found", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getClient(@PathVariable("id") int clientId) {
        Map<String, Object> response = new HashMap<>();
        Optional<ClientDTO> client = clientService.getClientById(clientId);

        if (client.isPresent()) {
            response.put("status", "success");
            response.put("data", client.get());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.put("status", "error");
            response.put("message", "Client not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Save a client", description = "Adds a client to the list.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Client saved", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)) })
    })
    @PostMapping("/save")
    public ResponseEntity<Map<String, Object>> save(@Valid @RequestBody ClientDTO clientDTO) {
        Map<String, Object> response = new HashMap<>();
        ClientDTO savedClient = clientService.saveClient(clientDTO);

        response.put("status", "success");
        response.put("message", "Client created successfully");
        response.put("data", savedClient);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Operation(summary = "Edits a client", description = "Edits a client in the list.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Client updated", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)) }),
            @ApiResponse(responseCode = "404", description = "Client not updated", content = @Content)
    })
    @PutMapping("/edit/{id}")
    public ResponseEntity<Map<String, Object>> edit(@PathVariable("id") int clientId,
            @Valid @RequestBody ClientDTO clientDTO) {
        Map<String, Object> response = new HashMap<>();
        Optional<ClientDTO> existingClient = clientService.getClientById(clientId);

        if (existingClient.isPresent()) {
            clientDTO.setId(clientId);
            ClientDTO updatedClient = clientService.editClient(clientId, clientDTO);

            response.put("status", "success");
            response.put("message", "Client updated successfully");
            response.put("data", updatedClient);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        response.put("status", "error");
        response.put("message", "Client not found");
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Deletes a client", description = "Removes a client from the list.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Client removed", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)) }),
            @ApiResponse(responseCode = "404", description = "Client not removed", content = @Content)
    })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable("id") int clientId) {
        Map<String, Object> response = new HashMap<>();
        boolean deleted = clientService.deleteClient(clientId);

        if (deleted) {
            response.put("status", "success");
            response.put("message", "Client deleted successfully");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        response.put("status", "error");
        response.put("message", "Client not found");
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}