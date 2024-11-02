package com.pets.persistence;

import com.pets.domain.dto.SaleDetailDTO;
import com.pets.domain.repository.SaleDetailRepository;
import com.pets.persistence.crud.SaleDetailCrudRepository;
import com.pets.persistence.entity.SaleDetailEntity;
import com.pets.domain.mapper.SaleDetailMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class SaleDetailRepositoryImpl implements SaleDetailRepository {
    @Autowired
    private SaleDetailCrudRepository saleDetailCrudRepository;

    @Autowired
    private SaleDetailMapper saleDetailMapper;

    @Override
    public List<SaleDetailDTO> getAllSaleDetails() {
        List<SaleDetailEntity> saleDetails = (List<SaleDetailEntity>) saleDetailCrudRepository.findAll();
        return saleDetailMapper.toSaleDetails(saleDetails);
    }

    @Override
    public Optional<SaleDetailDTO> getSaleDetailById(int saleDetailId) {
        return saleDetailCrudRepository.findById(saleDetailId).map(saleDetailMapper::toSaleDetail);
    }

    @Override
    public SaleDetailDTO saveSaleDetail(SaleDetailDTO saleDetailDTO) {
        SaleDetailEntity saleDetailEntity = saleDetailMapper.toEntity(saleDetailDTO);
        return saleDetailMapper.toSaleDetail(saleDetailCrudRepository.save(saleDetailEntity));
    }

    @Override
    public void deleteSaleDetail(int saleDetailId) {
        saleDetailCrudRepository.deleteById(saleDetailId);
    }
}