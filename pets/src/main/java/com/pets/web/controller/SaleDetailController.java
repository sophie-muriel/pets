package com.pets.web.controller;

import com.pets.domain.dto.SaleDetailDTO;
import com.pets.domain.service.SaleDetailService;

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
@RequestMapping("/sale-details")
public class SaleDetailController {

    @Autowired
    private SaleDetailService saleDetailService;

    @Operation(summary = "Get all sale details", description = "Returns a list of sale details.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sale details found", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)) }),
            @ApiResponse(responseCode = "204", description = "Sale details not found", content = @Content)
    })
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

    @Operation(summary = "Gets a sale detail", description = "Returns a specific sale detail.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sale detail found", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)) }),
            @ApiResponse(responseCode = "404", description = "Sale detail not found", content = @Content)
    })
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

    @Operation(summary = "Saves a sale detail", description = "Adds a sale detail to the list.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Sale detail saved", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)) })
    })
    @PostMapping("/save")
    public ResponseEntity<Map<String, Object>> save(@Valid @RequestBody SaleDetailDTO saleDetailDTO) {
        Map<String, Object> response = new HashMap<>();
        SaleDetailDTO savedSaleDetail = saleDetailService.saveSaleDetail(saleDetailDTO);

        response.put("status", "success");
        response.put("message", "Sale detail created successfully");
        response.put("data", savedSaleDetail);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Operation(summary = "Edits a sale detail", description = "Edits a sale detail in the list.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sale detail updated", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)) }),
            @ApiResponse(responseCode = "404", description = "Sale detail not updated", content = @Content)
    })
    @PutMapping("/edit/{id}")
    public ResponseEntity<Map<String, Object>> edit(@PathVariable("id") int saleDetailId,
            @Valid @RequestBody SaleDetailDTO saleDetailDTO) {
        Map<String, Object> response = new HashMap<>();
        Optional<SaleDetailDTO> existingSaleDetail = saleDetailService.getSaleDetailById(saleDetailId);

        if (existingSaleDetail.isPresent()) {
            saleDetailDTO.setId(saleDetailId);
            SaleDetailDTO updatedSaleDetail = saleDetailService.editSaleDetail(saleDetailId, saleDetailDTO);

            response.put("status", "success");
            response.put("message", "Sale detail updated successfully");
            response.put("data", updatedSaleDetail);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        response.put("status", "error");
        response.put("message", "Sale detail not found");
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Deletes a sale detail", description = "Removes a sale detail from the list.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sale detail removed", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)) }),
            @ApiResponse(responseCode = "404", description = "Sale detail not removed", content = @Content)
    })
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