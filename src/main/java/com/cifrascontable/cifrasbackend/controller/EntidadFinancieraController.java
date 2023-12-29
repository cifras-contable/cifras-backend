package com.cifrascontable.cifrasbackend.controller;

import com.cifrascontable.cifrasbackend.api.EntidadFinancieraRequestDTO;
import com.cifrascontable.cifrasbackend.api.EntidadFinancieraResponseDTO;
import com.cifrascontable.cifrasbackend.service.EntidadFinancieraService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = {"/v1/entidades_financieras"})
public class EntidadFinancieraController {

    private final EntidadFinancieraService entidadFinancieraService;

    public EntidadFinancieraController(EntidadFinancieraService entidadFinancieraService) {
        this.entidadFinancieraService = entidadFinancieraService;
    }

    @PostMapping
    public ResponseEntity<EntidadFinancieraResponseDTO> createEntidadFinanciera(
        @RequestBody EntidadFinancieraRequestDTO entidadFinancieraRequestDTO
    ) {
        return new ResponseEntity<>(
            entidadFinancieraService.crearEntidadFinanciera(entidadFinancieraRequestDTO),
            HttpStatus.CREATED
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
