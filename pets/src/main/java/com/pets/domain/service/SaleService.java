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

    @Autowired
    private ClientService clientService;

    @Autowired
    private UserService userService;

    public List<SaleDTO> getAllSales() {
        return saleRepository.getAllSales();
    }

    public Optional<SaleDTO> getSaleById(int saleId) {
        Optional<SaleDTO> sale = saleRepository.getSaleById(saleId);
        if (sale.isEmpty()) {
            throw new IllegalArgumentException("sale not found");
        }
        return sale;
    }

    public SaleDTO saveSale(SaleDTO sale) {
        if (userService.getUserById(sale.getUserId()).isEmpty()) {
            throw new IllegalArgumentException("user not found");
        }
        if (clientService.getClientById(sale.getClientId()).isEmpty()) {
            throw new IllegalArgumentException("client not found");
        }
        return saleRepository.saveSale(sale);
    }

    public boolean deleteSale(int saleId) {
        Optional<SaleDTO> sale = getSaleById(saleId);
        if (sale.isPresent()) {
            saleRepository.deleteSale(saleId);
            return true;
        } else {
            throw new IllegalArgumentException("sale not found");
        }
    }
}