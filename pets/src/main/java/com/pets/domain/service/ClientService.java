package com.pets.domain.service;

import com.pets.domain.dto.ClientDTO;
import com.pets.domain.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    public List<ClientDTO> getAllClients() {
        return clientRepository.getAllClients();
    }

    public Optional<ClientDTO> getClientById(int clientId) {
        return clientRepository.getClientById(clientId);
    }

    public ClientDTO saveClient(ClientDTO client) {
        return clientRepository.saveClient(client);
    }

    public boolean deleteClient(int clientId) {
        return getClientById(clientId).map(client -> {
            clientRepository.deleteClient(clientId);
            return true;
        }).orElse(false);
    }
}