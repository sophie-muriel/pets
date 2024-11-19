package com.pets.web.controller;

import com.pets.domain.dto.SaleDetailDTO;
import com.pets.domain.service.SaleDetailService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/sale-details")
public class SaleDetailController {

    @Autowired
    private SaleDetailService saleDetailService;

    @GetMapping("/all")
    public ResponseEntity<Map<String, Object>> getAll() {
        List<SaleDetailDTO> saleDetails = saleDetailService.getAllSaleDetails();
        Map<String, Object> response = new HashMap<>();

        if (saleDetails.isEmpty()) {
            response.put("status", "error");
            response.put("message", "No sale details found");
            return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
        }

        response.put("status", "success");
        response.put("data", saleDetails);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getSaleDetail(@PathVariable("id") int saleDetailId) {
        Map<String, Object> response = new HashMap<>();
        Optional<SaleDetailDTO> saleDetail = saleDetailService.getSaleDetailById(saleDetailId);

        if (saleDetail.isPresent()) {
            response.put("status", "success");
            response.put("data", saleDetail.get());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.put("status", "error");
            response.put("message", "Sale detail not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/save")
    public ResponseEntity<Map<String, Object>> save(@Valid @RequestBody SaleDetailDTO saleDetailDTO) {
        Map<String, Object> response = new HashMap<>();
        SaleDetailDTO savedSaleDetail = saleDetailService.saveSaleDetail(saleDetailDTO);

        response.put("status", "success");
        response.put("message", "Sale detail created successfully");
        response.put("data", savedSaleDetail);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Map<String, Object>> edit(@PathVariable("id") int saleDetailId, @Valid @RequestBody SaleDetailDTO saleDetailDTO) {
        Map<String, Object> response = new HashMap<>();
        Optional<SaleDetailDTO> existingSaleDetail = saleDetailService.getSaleDetailById(saleDetailId);

        if (existingSaleDetail.isPresent()) {
            saleDetailDTO.setId(saleDetailId);
            SaleDetailDTO updatedSaleDetail = saleDetailService.saveSaleDetail(saleDetailDTO);

            response.put("status", "success");
            response.put("message", "Sale detail updated successfully");
            response.put("data", updatedSaleDetail);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        response.put("status", "error");
        response.put("message", "Sale detail not found");
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable("id") int saleDetailId) {
        Map<String, Object> response = new HashMap<>();
        boolean deleted = saleDetailService.deleteSaleDetail(saleDetailId);

        if (deleted) {
            response.put("status", "success");
            response.put("message", "Sale detail deleted successfully");
            return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
        }

        response.put("status", "error");
        response.put("message", "Sale detail not found");
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}