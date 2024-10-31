package com.pets.domain.repository;

import com.pets.domain.dto.ClientDTO;
import java.util.List;

public interface ClientRepository {
    List<ClientDTO> findAll();
    ClientDTO findById(Integer id);
    ClientDTO save(ClientDTO client);
}