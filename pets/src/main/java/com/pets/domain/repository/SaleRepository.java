package com.pets.domain.repository;

import com.pets.domain.dto.SaleDTO;
import java.util.List;
import java.util.Optional;

public interface SaleRepository {
    List<SaleDTO> getAllSales();
    Optional<SaleDTO> getSaleById(int saleId);
    SaleDTO saveSale(SaleDTO sale);
    void deleteSale(int saleId);
}