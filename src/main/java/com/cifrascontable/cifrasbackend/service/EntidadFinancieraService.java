package com.cifrascontable.cifrasbackend.service;

import org.springframework.stereotype.Service;

import com.cifrascontable.cifrasbackend.api.EntidadFinancieraRequestDTO;
import com.cifrascontable.cifrasbackend.api.EntidadFinancieraResponseDTO;
import com.cifrascontable.cifrasbackend.exception.error.ResourceNotFoundException;
import com.cifrascontable.cifrasbackend.persistence.Empresa;
import com.cifrascontable.cifrasbackend.persistence.EntidadFinanciera;
import com.cifrascontable.cifrasbackend.persistence.main.repository.EmpresaRepository;
import com.cifrascontable.cifrasbackend.persistence.main.repository.EntidadFinancieraRepository;

@Service
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

		Empresa empresa = empresaRepository.findByCuit(entidadFinancieraRequestDTO.getCuitEmpresa())
				.orElseThrow(() -> new ResourceNotFoundException("cuil", "empresa", "cuil"));

		EntidadFinanciera entidadFinanciera = entidadFinancieraRepository.save(EntidadFinanciera.builder()
				.alias(entidadFinancieraRequestDTO.getAlias()).cbu(entidadFinancieraRequestDTO.getCbu())
				.cuit(entidadFinancieraRequestDTO.getCuit()).nombre(entidadFinancieraRequestDTO.getNombre())
				.numeroCuenta(entidadFinancieraRequestDTO.getNumeroCuenta()).tipo(entidadFinancieraRequestDTO.getTipo())
				.empresa(empresa).build());

		return EntidadFinancieraResponseDTO.builder().alias(entidadFinanciera.getAlias())
				.cbu(entidadFinanciera.getCbu()).cuit(entidadFinanciera.getCuit()).nombre(entidadFinanciera.getNombre())
				.numeroCuenta(entidadFinanciera.getNumeroCuenta()).tipo(entidadFinanciera.getTipo()).cuitEmpresa(empresa.getCuit())
				.idEntidadFinanciera(entidadFinanciera.getId()).build();

	}
}
