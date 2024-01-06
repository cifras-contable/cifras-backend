package com.cifrascontable.cifrasbackend.service;

import com.cifrascontable.cifrasbackend.api.EntidadFinancieraRequestDTO;
import com.cifrascontable.cifrasbackend.api.EntidadFinancieraResponseDTO;
import com.cifrascontable.cifrasbackend.api.validation.EntidadFinancieraUpdate;
import com.cifrascontable.cifrasbackend.exception.EntidadFinancieraNotFoundException;
import com.cifrascontable.cifrasbackend.exception.error.ResourceNotFoundException;
import com.cifrascontable.cifrasbackend.persistence.main.model.Empresa;
import com.cifrascontable.cifrasbackend.persistence.main.model.EntidadFinanciera;
import com.cifrascontable.cifrasbackend.persistence.main.repository.EmpresaRepository;
import com.cifrascontable.cifrasbackend.persistence.main.repository.EntidadFinancieraRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class EntidadFinancieraService {

	private final EmpresaRepository empresaRepository;
	private final EntidadFinancieraRepository entidadFinancieraRepository;

	public EntidadFinancieraService(EmpresaRepository empresaRepository,
			EntidadFinancieraRepository entidadFinancieraRepository) {
		this.empresaRepository = empresaRepository;
		this.entidadFinancieraRepository = entidadFinancieraRepository;
	}

	public EntidadFinancieraResponseDTO crearEntidadFinanciera(
			EntidadFinancieraRequestDTO entidadFinancieraRequestDTO) {

		Empresa empresa = empresaRepository.findByCuit(entidadFinancieraRequestDTO.cuitEmpresa())
				.orElseThrow(() -> new ResourceNotFoundException("cuil", "empresa", "cuil"));

		EntidadFinanciera entidadFinanciera = entidadFinancieraRepository.save(
			EntidadFinanciera.builder()
				.alias(entidadFinancieraRequestDTO.alias())
				.cbu(entidadFinancieraRequestDTO.cbu())
				.cuit(entidadFinancieraRequestDTO.cuit())
				.nombre(entidadFinancieraRequestDTO.nombre())
				.numeroCuenta(entidadFinancieraRequestDTO.numeroCuenta())
				.tipo(entidadFinancieraRequestDTO.tipo())
				.empresa(empresa)
				.build()
		);

		return EntidadFinancieraResponseDTO.builder()
			.alias(entidadFinanciera.getAlias())
			.cbu(entidadFinanciera.getCbu())
			.cuit(entidadFinanciera.getCuit())
			.nombre(entidadFinanciera.getNombre())
			.numeroCuenta(entidadFinanciera.getNumeroCuenta())
			.tipo(entidadFinanciera.getTipo())
			.cuitEmpresa(empresa.getCuit())
			.id(entidadFinanciera.getId())
			.build();

	}

	// Acá salta la validación con un ConstraintViolationException
	// (este endpoint usa el validacion group EntidadFinancieraUpdate que sólo exige el campo cuitEmpresa)
	@Validated(EntidadFinancieraUpdate.class)
	public EntidadFinancieraResponseDTO updateEntidadFinanciera(
		Long id,
		@Valid EntidadFinancieraRequestDTO entidadFinancieraRequestDTO
	) {
		EntidadFinanciera entidadFinanciera = entidadFinancieraRepository.findById(id)
				.orElseThrow(() -> new EntidadFinancieraNotFoundException("id", id));

		// lógica de update

		return EntidadFinancieraResponseDTO.builder().build();
	}

	public EntidadFinancieraResponseDTO getEntidadFinanciera(Long id) {

		EntidadFinanciera entidadFinanciera = entidadFinancieraRepository.findById(id)
			.orElseThrow(() -> new EntidadFinancieraNotFoundException("id", id));

		return EntidadFinancieraResponseDTO.builder()
			.alias(entidadFinanciera.getAlias())
			.cbu(entidadFinanciera.getCbu())
			.cuit(entidadFinanciera.getCuit())
			.nombre(entidadFinanciera.getNombre())
			.numeroCuenta(entidadFinanciera.getNumeroCuenta())
			.tipo(entidadFinanciera.getTipo())
			.cuitEmpresa(entidadFinanciera.getEmpresa().getCuit())
			.id(entidadFinanciera.getId())
			.build();

	}
}
