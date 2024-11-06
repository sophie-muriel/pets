package com.pets.web.controller;

import com.pets.domain.dto.SaleDTO;
import com.pets.domain.service.SaleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/sales")
public class SaleController {

    @Autowired
    private SaleService saleService;

    @GetMapping("/all")
    public ResponseEntity<List<SaleDTO>> getAll() {
        List<SaleDTO> sales = saleService.getAllSales();
        if (sales.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(sales, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SaleDTO> getSale(@PathVariable("id") int saleId) {
        Optional<SaleDTO> sale = saleService.getSaleById(saleId);
        return sale.map(saleDTO
                -> new ResponseEntity<>(saleDTO, HttpStatus.OK)).orElseGet(()
                -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/save")
    public ResponseEntity<SaleDTO> save(@Valid @RequestBody SaleDTO saleDTO) {
        SaleDTO savedSale = saleService.saveSale(saleDTO);
        return new ResponseEntity<>(savedSale, HttpStatus.CREATED);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<SaleDTO> edit(@PathVariable("id") int saleId, @Valid @RequestBody SaleDTO saleDTO) {
        Optional<SaleDTO> existingSale = saleService.getSaleById(saleId);
        if (existingSale.isPresent()) {
            saleDTO.setId(saleId);
            SaleDTO updatedSale = saleService.saveSale(saleDTO);
            return new ResponseEntity<>(updatedSale, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") int saleId) {
        boolean deleted = saleService.deleteSale(saleId);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}