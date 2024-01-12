package com.cifrascontable.cifrasbackend.api.mapper;

import com.cifrascontable.cifrasbackend.api.EntidadFinancieraRequestDTO;
import com.cifrascontable.cifrasbackend.api.EntidadFinancieraResponseDTO;
import com.cifrascontable.cifrasbackend.persistence.main.model.EntidadFinanciera;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EntidadFinancieraMapper {
    EntidadFinancieraMapper INSTANCE = Mappers.getMapper(EntidadFinancieraMapper.class);

    EntidadFinanciera entidadFinancieraRequestDTOToEntidadFinaciera(EntidadFinancieraRequestDTO entidadFinancieraRequestDTO);

    @Mapping(source = "empresa.cuit", target = "cuitEmpresa")
    EntidadFinancieraResponseDTO entidadFinancieraToEntidadFinancieraResponseDTO(EntidadFinanciera entidadFinanciera);
}
