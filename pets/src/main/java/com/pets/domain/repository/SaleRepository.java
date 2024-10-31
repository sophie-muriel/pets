package com.pets.domain.repository;

import com.pets.domain.dto.SaleDTO;
import java.util.List;

public interface SaleRepository {
    List<SaleDTO> findAll();
    SaleDTO findById(Integer id);
    List<SaleDTO> findByClientId(Integer clientId);
    List<SaleDTO> findByUserId(Integer userId);
    SaleDTO save(SaleDTO sale);
}