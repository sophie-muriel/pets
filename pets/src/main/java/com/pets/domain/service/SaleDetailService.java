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

    @Autowired
    private PetService petService;

    public List<SaleDetailDTO> getAllSaleDetails() {
        return saleDetailRepository.getAllSaleDetails();
    }

    public Optional<SaleDetailDTO> getSaleDetailById(int saleDetailId) {
        Optional<SaleDetailDTO> saleDetail = saleDetailRepository.getSaleDetailById(saleDetailId);
        if (saleDetail.isEmpty()) {
            throw new IllegalArgumentException("sale detail not found");
        }
        return saleDetail;
    }

    public SaleDetailDTO saveSaleDetail(SaleDetailDTO saleDetail) {
        if (petService.getPetById(saleDetail.getPetId()).isEmpty()) {
            throw new IllegalArgumentException("pet not found");
        }
        return saleDetailRepository.saveSaleDetail(saleDetail);
    }

    public boolean deleteSaleDetail(int saleDetailId) {
        Optional<SaleDetailDTO> saleDetail = getSaleDetailById(saleDetailId);
        if (saleDetail.isPresent()) {
            saleDetailRepository.deleteSaleDetail(saleDetailId);
            return true;
        } else {
            throw new IllegalArgumentException("sale detail not found");
        }
    }
}