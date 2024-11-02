package com.pets.web.controller;

import com.pets.domain.dto.SaleDTO;
import com.pets.domain.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/sales")
public class SaleController {
    @Autowired
    private SaleService saleService;

    @GetMapping("/all")
    public List<SaleDTO> getAll() {
        return saleService.getAllSales();
    }

    @GetMapping("/{id}")
    public Optional<SaleDTO> getSale(@PathVariable("id") int saleId) {
        return saleService.getSaleById(saleId);
    }

    @PostMapping("/save")
    public SaleDTO save(@RequestBody SaleDTO saleDTO) {
        return saleService.saveSale(saleDTO);
    }

    @DeleteMapping("/delete/{id}")
    public boolean delete(@PathVariable("id") int saleId) {
        return saleService.deleteSale(saleId);
    }
}