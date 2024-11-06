package com.pets.web.controller;

import com.pets.domain.dto.SaleDetailDTO;
import com.pets.domain.service.SaleDetailService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/sale-details")
public class SaleDetailController {

    @Autowired
    private SaleDetailService saleDetailService;

    @GetMapping("/all")
    public ResponseEntity<List<SaleDetailDTO>> getAll() {
        List<SaleDetailDTO> saleDetails = saleDetailService.getAllSaleDetails();
        if (saleDetails.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(saleDetails, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SaleDetailDTO> getSaleDetail(@PathVariable("id") int saleDetailId) {
        Optional<SaleDetailDTO> saleDetail = saleDetailService.getSaleDetailById(saleDetailId);
        return saleDetail.map(saleDetailDTO
                -> new ResponseEntity<>(saleDetailDTO, HttpStatus.OK)).orElseGet(()
                -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/save")
    public ResponseEntity<SaleDetailDTO> save(@Valid @RequestBody SaleDetailDTO saleDetailDTO) {
        SaleDetailDTO savedSaleDetail = saleDetailService.saveSaleDetail(saleDetailDTO);
        return new ResponseEntity<>(savedSaleDetail, HttpStatus.CREATED);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<SaleDetailDTO> edit(@PathVariable("id") int saleDetailId, @Valid @RequestBody SaleDetailDTO saleDetailDTO) {
        Optional<SaleDetailDTO> existingSaleDetail = saleDetailService.getSaleDetailById(saleDetailId);
        if (existingSaleDetail.isPresent()) {
            saleDetailDTO.setId(saleDetailId);
            SaleDetailDTO updatedSaleDetail = saleDetailService.saveSaleDetail(saleDetailDTO);
            return new ResponseEntity<>(updatedSaleDetail, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") int saleDetailId) {
        boolean deleted = saleDetailService.deleteSaleDetail(saleDetailId);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}