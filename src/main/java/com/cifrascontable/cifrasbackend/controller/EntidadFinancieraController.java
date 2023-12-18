package com.cifrascontable.cifrasbackend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cifrascontable.cifrasbackend.api.EntidadFinancieraRequestDTO;
import com.cifrascontable.cifrasbackend.api.EntidadFinancieraResponseDTO;
import com.cifrascontable.cifrasbackend.service.EntidadFinancieraService;

@RestController
@RequestMapping("/api/v1/entidades_financieras")
public class EntidadFinancieraController {
	
	private final EntidadFinancieraService entidadFinancieraService;
	
	public EntidadFinancieraController(EntidadFinancieraService entidadFinancieraService) {
		this.entidadFinancieraService = entidadFinancieraService;
	}	

	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public EntidadFinancieraResponseDTO addEntidadFinanciera(@RequestBody EntidadFinancieraRequestDTO entidadFinancieraRequestDTO) {
		return entidadFinancieraService.crearEntidadFinanciera(entidadFinancieraRequestDTO);
	}
}
