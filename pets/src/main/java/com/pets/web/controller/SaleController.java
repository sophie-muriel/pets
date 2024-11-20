package com.pets.web.controller;

import com.pets.domain.dto.SaleDTO;
import com.pets.domain.service.SaleService;
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
@RequestMapping("/sales")
public class SaleController {

    @Autowired
    private SaleService saleService;

    @GetMapping("/all")
    public ResponseEntity<Map<String, Object>> getAll() {
        List<SaleDTO> sales = saleService.getAllSales();
        Map<String, Object> response = new HashMap<>();

        if (sales.isEmpty()) {
            response.put("status", "error");
            response.put("message", "No sales found");
            return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
        }

        response.put("status", "success");
        response.put("data", sales);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getSale(@PathVariable("id") int saleId) {
        Map<String, Object> response = new HashMap<>();
        Optional<SaleDTO> sale = saleService.getSaleById(saleId);

        if (sale.isPresent()) {
            response.put("status", "success");
            response.put("data", sale.get());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.put("status", "error");
            response.put("message", "Sale not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/save")
    public ResponseEntity<Map<String, Object>> save(@Valid @RequestBody SaleDTO saleDTO) {
        Map<String, Object> response = new HashMap<>();
        SaleDTO savedSale = saleService.saveSale(saleDTO);

        response.put("status", "success");
        response.put("message", "Sale created successfully");
        response.put("data", savedSale);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Map<String, Object>> edit(@PathVariable("id") int saleId,
            @Valid @RequestBody SaleDTO saleDTO) {
        Map<String, Object> response = new HashMap<>();
        Optional<SaleDTO> existingSale = saleService.getSaleById(saleId);

        if (existingSale.isPresent()) {
            saleDTO.setId(saleId);
            SaleDTO updatedSale = saleService.editSale(saleId, saleDTO);

            response.put("status", "success");
            response.put("message", "Sale updated successfully");
            response.put("data", updatedSale);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        response.put("status", "error");
        response.put("message", "Sale not found");
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable("id") int saleId) {
        Map<String, Object> response = new HashMap<>();
        boolean deleted = saleService.deleteSale(saleId);

        if (deleted) {
            response.put("status", "success");
            response.put("message", "Sale deleted successfully");
            return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
        }

        response.put("status", "error");
        response.put("message", "Sale not found");
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}