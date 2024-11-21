package com.pets.web.controller;

import com.pets.domain.dto.PetDTO;
import com.pets.domain.service.PetService;

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
@RequestMapping("/pets")
public class PetController {

    @Autowired
    private PetService petService;

    @Operation(summary = "Get all pets", description = "Returns a list of pets.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pets found", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)) }),
            @ApiResponse(responseCode = "204", description = "Pets not found", content = @Content)
    })
    @GetMapping("/all")
    public ResponseEntity<Map<String, Object>> getAll() {
        List<PetDTO> pets = petService.getAllPets();
        Map<String, Object> response = new HashMap<>();

        if (pets.isEmpty()) {
            response.put("status", "error");
            response.put("message", "No pets found");
            return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
        }

        response.put("status", "success");
        response.put("data", pets);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Gets a pet", description = "Returns a specific pet.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pet found", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)) }),
            @ApiResponse(responseCode = "404", description = "Pet not found", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getPet(@PathVariable("id") int petId) {
        Map<String, Object> response = new HashMap<>();
        Optional<PetDTO> pet = petService.getPetById(petId);

        if (pet.isPresent()) {
            response.put("status", "success");
            response.put("data", pet.get());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.put("status", "error");
            response.put("message", "Pet not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Saves a pet", description = "Adds a pet to the list.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Pet saved", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)) })
    })
    @PostMapping("/save")
    public ResponseEntity<Map<String, Object>> save(@Valid @RequestBody PetDTO petDTO) {
        Map<String, Object> response = new HashMap<>();
        PetDTO savedPet = petService.savePet(petDTO);

        response.put("status", "success");
        response.put("message", "Pet created successfully");
        response.put("data", savedPet);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Operation(summary = "Edits a pet", description = "Edits a pet in the list.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pet updated", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)) }),
            @ApiResponse(responseCode = "404", description = "Pet not updated", content = @Content)
    })
    @PutMapping("/edit/{id}")
    public ResponseEntity<Map<String, Object>> edit(@PathVariable("id") int petId, @Valid @RequestBody PetDTO petDTO) {
        Map<String, Object> response = new HashMap<>();
        Optional<PetDTO> existingPet = petService.getPetById(petId);

        if (existingPet.isPresent()) {
            petDTO.setId(petId);
            PetDTO updatedPet = petService.editPet(petId, petDTO);

            response.put("status", "success");
            response.put("message", "Pet updated successfully");
            response.put("data", updatedPet);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        response.put("status", "error");
        response.put("message", "Pet not found");
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Deletes a pet", description = "Removes a pet from the list.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pet removed", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)) }),
            @ApiResponse(responseCode = "404", description = "Pet not removed", content = @Content)
    })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable("id") int petId) {
        Map<String, Object> response = new HashMap<>();
        boolean deleted = petService.deletePet(petId);

        if (deleted) {
            response.put("status", "success");
            response.put("message", "Pet deleted successfully");
            return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
        }

        response.put("status", "error");
        response.put("message", "Pet not found");
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}