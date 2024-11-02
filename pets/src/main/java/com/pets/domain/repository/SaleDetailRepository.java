package com.pets.domain.repository;

import com.pets.domain.dto.SaleDetailDTO;

import java.util.List;
import java.util.Optional;

public interface SaleDetailRepository {
    List<SaleDetailDTO> getAllSaleDetails();
    Optional<SaleDetailDTO> getSaleDetailById(int saleDetailId);
    SaleDetailDTO saveSaleDetail(SaleDetailDTO saleDetail);
    void deleteSaleDetail(int saleDetailId);
}