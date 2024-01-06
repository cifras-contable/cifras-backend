package com.cifrascontable.cifrasbackend.api.service;

import com.cifrascontable.cifrasbackend.api.EntidadFinancieraRequestDTO;
import com.cifrascontable.cifrasbackend.api.EntidadFinancieraResponseDTO;
import com.cifrascontable.cifrasbackend.exception.error.ResourceNotFoundException;
import com.cifrascontable.cifrasbackend.persistence.main.model.Empresa;
import com.cifrascontable.cifrasbackend.persistence.main.model.EntidadFinanciera;
import com.cifrascontable.cifrasbackend.persistence.main.repository.EmpresaRepository;
import com.cifrascontable.cifrasbackend.persistence.main.repository.EntidadFinancieraRepository;
import com.cifrascontable.cifrasbackend.service.EntidadFinancieraService;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EntidadFinancieraServiceTest {
    @Mock
    private EntidadFinancieraRepository entidadFinancieraRepository;
    @Mock
    private EmpresaRepository empresaRepository;

    private EntidadFinancieraService entidadFinancieraService;

    private Empresa empresa;
    private EntidadFinanciera entidadFinanciera;
    private EntidadFinancieraResponseDTO entidadFinancieraResponseDTO;
    private EntidadFinancieraRequestDTO entidadFinancieraRequestDTO;


    @BeforeEach
    public void init() {
        entidadFinancieraService = new EntidadFinancieraService(empresaRepository, entidadFinancieraRepository);
        empresa = Empresa.builder().id(Long.valueOf(1)).cuit("00-11222333-0").razonSocial("CIFRAS SAS").nombreFantasia("CIFRAS").idGrupo(Long.valueOf(1)).build();
        entidadFinanciera = EntidadFinanciera.builder()
            .id(Long.valueOf(1))
            .alias("entidad.test")
            .cbu("123456789")
            .cuit("44-556677-0")
            .nombre("Entidad Test")
            .numeroCuenta("123456/789")
            .tipo("Test")
            .empresa(empresa)
            .build();
        entidadFinancieraRequestDTO = EntidadFinancieraRequestDTO.builder()
            .alias("entidad.test")
            .cbu("123456789")
            .cuit("44-556677-0")
            .nombre("Entidad Test")
            .numeroCuenta("123456/789")
            .tipo("Test")
            .cuitEmpresa("00-11222333-0")
            .build();
    }

    @Test
    public void EntidadFinancieraService_CrearEntidadFinanciera_ReturnsEntidadFinancieraResponseDTO() {
        // Arrange
        when(empresaRepository.findByCuit(entidadFinancieraRequestDTO.cuitEmpresa())).thenReturn(Optional.of(empresa));
        when(entidadFinancieraRepository.save(Mockito.any(EntidadFinanciera.class))).thenReturn(entidadFinanciera);

        // Act
        EntidadFinancieraResponseDTO savedEntity = entidadFinancieraService.crearEntidadFinanciera(entidadFinancieraRequestDTO);

        // Assert
        Assertions.assertThat(savedEntity).isNotNull();
    }

    @Test
    public void EntidadFinancieraService_GetEntidadFinanciera_ReturnsEntidadFinancieraResponseDTO() {
        Long id = Long.valueOf(1);
        when(entidadFinancieraRepository.findById(id)).thenReturn(Optional.of(entidadFinanciera));

        EntidadFinancieraResponseDTO foundEntity = entidadFinancieraService.getEntidadFinanciera(id);

        Assertions.assertThat(foundEntity).isNotNull();
    }
}
