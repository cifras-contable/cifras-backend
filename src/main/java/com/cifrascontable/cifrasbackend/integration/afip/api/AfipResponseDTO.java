package com.cifrascontable.cifrasbackend.integration.afip.api;


import com.fasterxml.jackson.annotation.JsonProperty;

public record AfipResponseDTO(
    @JsonProperty("nombre") String nombre,
    @JsonProperty("apellido") String apellido) {

}
