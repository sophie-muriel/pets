package com.pets.domain.service;

import com.pets.domain.dto.SaleDetailDTO;
import com.pets.domain.repository.SaleDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SaleDetailService {
    @Autowired
    private SaleDetailRepository saleDetailRepository;

    public List<SaleDetailDTO> getAllSaleDetails() {
        return saleDetailRepository.getAllSaleDetails();
    }

    public Optional<SaleDetailDTO> getSaleDetailById(int saleDetailId) {
        return saleDetailRepository.getSaleDetailById(saleDetailId);
    }

    public SaleDetailDTO saveSaleDetail(SaleDetailDTO saleDetail) {
        return saleDetailRepository.saveSaleDetail(saleDetail);
    }

    public boolean deleteSaleDetail(int saleDetailId) {
        return getSaleDetailById(saleDetailId).map(saleDetail -> {
            saleDetailRepository.deleteSaleDetail(saleDetailId);
            return true;
        }).orElse(false);
    }
}