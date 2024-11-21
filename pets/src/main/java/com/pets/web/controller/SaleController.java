package com.pets.web.controller;

import com.pets.domain.dto.SaleDTO;
import com.pets.domain.service.SaleService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Get all sales", description = "Returns a list of sales.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sales found", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)) }),
            @ApiResponse(responseCode = "204", description = "Sales not found", content = @Content)
    })
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

    @Operation(summary = "Gets a sale", description = "Returns a specific sale.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sale found", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)) }),
            @ApiResponse(responseCode = "404", description = "Sale not found", content = @Content)
    })
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

    @Operation(summary = "Saves a sale", description = "Adds a sale to the list.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Sale saved", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)) })
    })
    @PostMapping("/save")
    public ResponseEntity<Map<String, Object>> save(@Valid @RequestBody SaleDTO saleDTO) {
        Map<String, Object> response = new HashMap<>();
        SaleDTO savedSale = saleService.saveSale(saleDTO);

        response.put("status", "success");
        response.put("message", "Sale created successfully");
        response.put("data", savedSale);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Operation(summary = "Edits a sale", description = "Edits a sale in the list.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sale updated", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)) }),
            @ApiResponse(responseCode = "404", description = "Sale not updated", content = @Content)
    })
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

    @Operation(summary = "Deletes a sale", description = "Removes a sale from the list.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sale removed", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)) }),
            @ApiResponse(responseCode = "404", description = "Sale not removed", content = @Content)
    })
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