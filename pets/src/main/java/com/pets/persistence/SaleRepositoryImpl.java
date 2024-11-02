package com.pets.persistence;

import com.pets.domain.dto.SaleDTO;
import com.pets.domain.repository.SaleRepository;
import com.pets.persistence.crud.SaleCrudRepository;
import com.pets.persistence.entity.SaleEntity;
import com.pets.domain.mapper.SaleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class SaleRepositoryImpl implements SaleRepository {
    @Autowired
    private SaleCrudRepository saleCrudRepository;

    @Autowired
    private SaleMapper saleMapper;

    @Override
    public List<SaleDTO> getAllSales() {
        List<SaleEntity> sales = (List<SaleEntity>) saleCrudRepository.findAll();
        return saleMapper.toSales(sales);
    }

    @Override
    public Optional<SaleDTO> getSaleById(int saleId) {
        return saleCrudRepository.findById(saleId).map(saleMapper::toSale);
    }

    @Override
    public SaleDTO saveSale(SaleDTO saleDTO) {
        SaleEntity saleEntity = saleMapper.toEntity(saleDTO);
        return saleMapper.toSale(saleCrudRepository.save(saleEntity));
    }

    @Override
    public void deleteSale(int saleId) {
        saleCrudRepository.deleteById(saleId);
    }
}