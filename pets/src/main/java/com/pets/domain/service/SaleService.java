package com.pets.domain.service;

import com.pets.domain.dto.SaleDTO;
import com.pets.domain.repository.SaleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaleService {
    private final SaleRepository saleRepository;

    public SaleService(SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }

    public List<SaleDTO> getAllSales() {
        return saleRepository.findAll();
    }

    public SaleDTO getSaleById(Integer id) {
        return saleRepository.findById(id);
    }

    public SaleDTO saveSale(SaleDTO sale) {
        return saleRepository.save(sale);
    }
}