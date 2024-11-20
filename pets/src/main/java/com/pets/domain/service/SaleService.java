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

    public SaleDTO editSale(int saleId, SaleDTO updatedSale) {
        Optional<SaleDTO> existingSaleOptional = saleRepository.getSaleById(saleId);
        if (existingSaleOptional.isEmpty()) {
            throw new IllegalArgumentException("Sale not found");
        }
        SaleDTO existingSale = existingSaleOptional.get();

        if (updatedSale.getUserId() != null && !updatedSale.getUserId().equals(existingSale.getUserId())) {
            existingSale.setUserId(updatedSale.getUserId());
        }
        if (updatedSale.getClientId() != null && !updatedSale.getClientId().equals(existingSale.getClientId())) {
            existingSale.setClientId(updatedSale.getClientId());
        }
        if (updatedSale.getTotalPrice() != null && !updatedSale.getTotalPrice().equals(existingSale.getTotalPrice())) {
            existingSale.setTotalPrice(updatedSale.getTotalPrice());
        }
        if (updatedSale.getSaleDate() != null && !updatedSale.getSaleDate().equals(existingSale.getSaleDate())) {
            existingSale.setSaleDate(updatedSale.getSaleDate());
        }
        if (updatedSale.getStatus() != null && !updatedSale.getStatus().equals(existingSale.getStatus())) {
            existingSale.setStatus(updatedSale.getStatus());
        }
        if (updatedSale.getPaymentMethod() != null
                && !updatedSale.getPaymentMethod().equals(existingSale.getPaymentMethod())) {
            existingSale.setPaymentMethod(updatedSale.getPaymentMethod());
        }

        return saleRepository.saveSale(existingSale);
    }
}