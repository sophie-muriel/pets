package com.pets.web.controller;

import com.pets.domain.dto.SaleDTO;
import com.pets.domain.service.SaleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sales")
public class SaleController {
    private final SaleService saleService;

    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }

    @GetMapping
    public List<SaleDTO> getAllSales() {
        return saleService.getAllSales();
    }

    @GetMapping("/{id}")
    public SaleDTO getSaleById(@PathVariable Integer id) {
        return saleService.getSaleById(id);
    }

    @PostMapping
    public SaleDTO saveSale(@RequestBody SaleDTO saleDTO) {
        return saleService.saveSale(saleDTO);
    }
}