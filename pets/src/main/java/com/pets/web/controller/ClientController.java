package com.pets.web.controller;

import com.pets.domain.dto.ClientDTO;
import com.pets.domain.service.ClientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping("/all")
    public ResponseEntity<List<ClientDTO>> getAll() {
        List<ClientDTO> clients = clientService.getAllClients();
        if (clients.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientDTO> getClient(@PathVariable("id") int clientId) {
        Optional<ClientDTO> client = clientService.getClientById(clientId);
        return client.map(clientDTO
                -> new ResponseEntity<>(clientDTO, HttpStatus.OK)).orElseGet(()
                -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/save")
    public ResponseEntity<ClientDTO> save(@Valid @RequestBody ClientDTO clientDTO) {
        ClientDTO savedClient = clientService.saveClient(clientDTO);
        return new ResponseEntity<>(savedClient, HttpStatus.CREATED);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<ClientDTO> edit(@PathVariable("id") int clientId, @Valid @RequestBody ClientDTO clientDTO) {
        Optional<ClientDTO> existingClient = clientService.getClientById(clientId);
        if (existingClient.isPresent()) {
            clientDTO.setId(clientId);
            ClientDTO updatedClient = clientService.saveClient(clientDTO);
            return new ResponseEntity<>(updatedClient, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") int clientId) {
        boolean deleted = clientService.deleteClient(clientId);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}