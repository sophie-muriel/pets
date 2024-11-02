package com.pets.domain.service;

import com.pets.domain.dto.SaleDTO;
import com.pets.domain.repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SaleService {
    @Autowired
    private SaleRepository saleRepository;

    public List<SaleDTO> getAllSales() {
        return saleRepository.getAllSales();
    }

    public Optional<SaleDTO> getSaleById(int saleId) {
        return saleRepository.getSaleById(saleId);
    }

    public SaleDTO saveSale(SaleDTO sale) {
        return saleRepository.saveSale(sale);
    }

    public boolean deleteSale(int saleId) {
        return getSaleById(saleId).map(sale -> {
            saleRepository.deleteSale(saleId);
            return true;
        }).orElse(false);
    }
}