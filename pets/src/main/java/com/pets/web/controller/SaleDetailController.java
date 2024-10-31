package com.pets.web.controller;

import com.pets.domain.dto.SaleDetailDTO;
import com.pets.domain.service.SaleDetailService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sale-details")
public class SaleDetailController {
    private final SaleDetailService saleDetailService;

    public SaleDetailController(SaleDetailService saleDetailService) {
        this.saleDetailService = saleDetailService;
    }

    @GetMapping
    public List<SaleDetailDTO> getAllSaleDetails() {
        return saleDetailService.getAllSaleDetails();
    }

    @GetMapping("/{id}")
    public SaleDetailDTO getSaleDetailById(@PathVariable Integer id) {
        return saleDetailService.getSaleDetailById(id);
    }

    @PostMapping
    public SaleDetailDTO saveSaleDetail(@RequestBody SaleDetailDTO saleDetailDTO) {
        return saleDetailService.saveSaleDetail(saleDetailDTO);
    }
}