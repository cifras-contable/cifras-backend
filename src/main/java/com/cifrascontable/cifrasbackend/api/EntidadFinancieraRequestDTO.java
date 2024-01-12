package com.cifrascontable.cifrasbackend.api;

import com.cifrascontable.cifrasbackend.api.validation.EntidadFinancieraCreate;
import com.cifrascontable.cifrasbackend.api.validation.EntidadFinancieraUpdate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;

@Builder
public record EntidadFinancieraRequestDTO (
	@NotBlank(groups = {EntidadFinancieraCreate.class}, message = "El nombre es obligatorio.")
	@Size(max = 50, groups = {EntidadFinancieraCreate.class, EntidadFinancieraUpdate.class}, message = "El nombre no puede tener más de 50 caracteres.")
	String nombre,
	@NotBlank(groups = {EntidadFinancieraCreate.class}, message = "El tipo es obligatorio.")
	String tipo,
	@NotBlank(groups = {EntidadFinancieraCreate.class}, message = "El número de cuenta es obligatorio.")
	String numeroCuenta,
	@NotBlank(groups = {EntidadFinancieraCreate.class}, message = "El CUIT es obligatorio.")
	String cuit,
	@NotBlank(groups = {EntidadFinancieraCreate.class}, message = "El CBU es obligatorio.")
	String cbu,
	@NotBlank(groups = {EntidadFinancieraCreate.class}, message = "El alias es obligatorio.")
	String alias,
	@NotBlank(groups = {EntidadFinancieraCreate.class}, message = "El CUIT de la Empresa es obligatorio.")
	String cuitEmpresa
){}
