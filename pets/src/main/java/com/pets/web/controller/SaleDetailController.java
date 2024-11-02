package com.pets.web.controller;

import com.pets.domain.dto.SaleDetailDTO;
import com.pets.domain.service.SaleDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/sale-details")
public class SaleDetailController {
    @Autowired
    private SaleDetailService saleDetailService;

    @GetMapping("/all")
    public List<SaleDetailDTO> getAll() {
        return saleDetailService.getAllSaleDetails();
    }

    @GetMapping("/{id}")
    public Optional<SaleDetailDTO> getSaleDetail(@PathVariable("id") int saleDetailId) {
        return saleDetailService.getSaleDetailById(saleDetailId);
    }

    @PostMapping("/save")
    public SaleDetailDTO save(@RequestBody SaleDetailDTO saleDetailDTO) {
        return saleDetailService.saveSaleDetail(saleDetailDTO);
    }

    @DeleteMapping("/delete/{id}")
    public boolean delete(@PathVariable("id") int saleDetailId) {
        return saleDetailService.deleteSaleDetail(saleDetailId);
    }
}