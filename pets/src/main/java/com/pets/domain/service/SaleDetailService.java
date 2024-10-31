package com.pets.domain.service;

import com.pets.domain.dto.SaleDetailDTO;
import com.pets.domain.repository.SaleDetailRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaleDetailService {
    private final SaleDetailRepository saleDetailRepository;

    public SaleDetailService(SaleDetailRepository saleDetailRepository) {
        this.saleDetailRepository = saleDetailRepository;
    }

    public List<SaleDetailDTO> getAllSaleDetails() {
        return saleDetailRepository.findAll();
    }

    public SaleDetailDTO getSaleDetailById(Integer id) {
        return saleDetailRepository.findById(id);
    }

    public SaleDetailDTO saveSaleDetail(SaleDetailDTO saleDetail) {
        return saleDetailRepository.save(saleDetail);
    }
}