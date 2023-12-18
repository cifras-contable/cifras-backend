package com.cifrascontable.cifrasbackend.api;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class EntidadFinancieraResponseDTO extends EntidadFinancieraRequestDTO {
	
	private Long idEntidadFinanciera;
	
	@Builder
	public EntidadFinancieraResponseDTO(String nombre, String tipo, String numeroCuenta, String cuit, String cbu,
			String alias, String cuitEmpresa, Long idEntidadFinanciera) {
		super(nombre, tipo, numeroCuenta, cuit, cbu, alias, cuitEmpresa);
		this.idEntidadFinanciera = idEntidadFinanciera;
	}
}
