package com.pets.web.controller;

import com.pets.domain.dto.ClientDTO;
import com.pets.domain.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clients")
public class ClientController {
    @Autowired
    private ClientService clientService;

    @GetMapping("/all")
    public List<ClientDTO> getAll() {
        return clientService.getAllClients();
    }

    @GetMapping("/{id}")
    public Optional<ClientDTO> getClient(@PathVariable("id") int clientId) {
        return clientService.getClientById(clientId);
    }

    @PostMapping("/save")
    public ClientDTO save(@RequestBody ClientDTO clientDTO) {
        return clientService.saveClient(clientDTO);
    }

    @DeleteMapping("/delete/{id}")
    public boolean delete(@PathVariable("id") int clientId) {
        return clientService.deleteClient(clientId);
    }
}