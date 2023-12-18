package com.cifrascontable.cifrasbackend.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EntidadFinancieraRequestDTO {

	private String nombre;
	private String tipo;
	private String numeroCuenta;
	private String cuit;
	private String cbu;
	private String alias;
	private String cuitEmpresa;
}
