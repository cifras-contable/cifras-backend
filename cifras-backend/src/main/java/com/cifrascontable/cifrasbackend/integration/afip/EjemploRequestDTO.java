package com.cifrascontable.cifrasbackend.integration.afip;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record EjemploRequestDTO(
    @JsonProperty("cuit") Long cuit) {

}
