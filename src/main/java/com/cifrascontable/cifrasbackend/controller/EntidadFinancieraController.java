package com.cifrascontable.cifrasbackend.controller;

import com.cifrascontable.cifrasbackend.api.EntidadFinancieraRequestDTO;
import com.cifrascontable.cifrasbackend.api.EntidadFinancieraResponseDTO;
import com.cifrascontable.cifrasbackend.api.response.ApiResponse;
import com.cifrascontable.cifrasbackend.api.validation.EntidadFinancieraCreate;
import com.cifrascontable.cifrasbackend.service.EntidadFinancieraService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = {"/v1/entidades_financieras"})
@Validated
public class EntidadFinancieraController {

    private final EntidadFinancieraService entidadFinancieraService;

    public EntidadFinancieraController(EntidadFinancieraService entidadFinancieraService) {
        this.entidadFinancieraService = entidadFinancieraService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<EntidadFinancieraResponseDTO>> createEntidadFinanciera(
        @Validated(EntidadFinancieraCreate.class)
        @RequestBody EntidadFinancieraRequestDTO entidadFinancieraRequestDTO
    ) {
        ApiResponse<EntidadFinancieraResponseDTO> response = ApiResponse.created(entidadFinancieraService.crearEntidadFinanciera(entidadFinancieraRequestDTO));

        return ResponseEntity
            .status(HttpStatus.valueOf(response.getHttpStatus()))
            .body(response);
    }

    @PutMapping("/{id}") // Acá no se valida, la validación se hace en el Service
    public ResponseEntity<EntidadFinancieraResponseDTO> updateEntidadFinanciera(
            @PathVariable Long id,
            @RequestBody EntidadFinancieraRequestDTO entidadFinancieraRequestDTO
    ) {
        return new ResponseEntity<>(
            entidadFinancieraService.updateEntidadFinanciera(id, entidadFinancieraRequestDTO),
            HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<EntidadFinancieraResponseDTO>> getEntidadFinanciera(
        @PathVariable("id") Long id
    ) {
        ApiResponse<EntidadFinancieraResponseDTO> response = ApiResponse.okData(entidadFinancieraService.getEntidadFinanciera(id));

        return ResponseEntity
            .status(HttpStatus.valueOf(response.getHttpStatus()))
            .body(response);
    }

}
