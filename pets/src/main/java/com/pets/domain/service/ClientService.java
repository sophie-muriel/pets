package com.pets.domain.service;

import com.pets.domain.dto.ClientDTO;
import com.pets.domain.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {
    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<ClientDTO> getAllClients() {
        return clientRepository.findAll();
    }

    public ClientDTO getClientById(Integer id) {
        return clientRepository.findById(id);
    }

    public ClientDTO saveClient(ClientDTO client) {
        return clientRepository.save(client);
    }
}