package com.cifrascontable.cifrasbackend.api;

import lombok.Builder;

@Builder
public record EntidadFinancieraResponseDTO(
	Long id,
	String nombre,
	String tipo,
	String numeroCuenta,
	String cuit,
	String cbu,
	String alias,
	String cuitEmpresa
){}

