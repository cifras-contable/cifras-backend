package com.cifrascontable.cifrasbackend.integration.afip.api;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record AfipRequestDTO(
    @JsonProperty("cuit") Long cuit) {

}
