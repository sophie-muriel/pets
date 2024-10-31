package com.pets.domain.repository;

import com.pets.domain.dto.SaleDetailDTO;
import java.util.List;

public interface SaleDetailRepository {
    List<SaleDetailDTO> findAll();
    SaleDetailDTO findById(Integer id);
    List<SaleDetailDTO> findByPetId(Integer petId);
    SaleDetailDTO save(SaleDetailDTO saleDetail);
}