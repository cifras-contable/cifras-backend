package com.cifrascontable.cifrasbackend.controller;

import com.cifrascontable.cifrasbackend.api.EntidadFinancieraRequestDTO;
import com.cifrascontable.cifrasbackend.api.EntidadFinancieraResponseDTO;
import com.cifrascontable.cifrasbackend.api.validation.EntidadFinancieraCreate;
import com.cifrascontable.cifrasbackend.api.validation.EntidadFinancieraUpdate;
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
    public ResponseEntity<EntidadFinancieraResponseDTO> createEntidadFinanciera(
        @Validated(EntidadFinancieraCreate.class)
        @RequestBody EntidadFinancieraRequestDTO entidadFinancieraRequestDTO
    ) {
        return new ResponseEntity<>(
            entidadFinancieraService.crearEntidadFinanciera(entidadFinancieraRequestDTO),
            HttpStatus.CREATED
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntidadFinancieraResponseDTO> updateEntidadFinanciera(
        @PathVariable Long id,
        @Validated(EntidadFinancieraUpdate.class)
        @RequestBody EntidadFinancieraRequestDTO entidadFinancieraRequestDTO
    ) {
        return new ResponseEntity<>(
            entidadFinancieraService.updateEntidadFinanciera(id, entidadFinancieraRequestDTO),
            HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntidadFinancieraResponseDTO> getEntidadFinanciera(
        @PathVariable("id") Long id
    ) {
        return new ResponseEntity<>(
            entidadFinancieraService.getEntidadFinanciera(id),
            HttpStatus.OK
        );
    }

}
