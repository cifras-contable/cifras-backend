package com.cifrascontable.cifrasbackend.integration.afip;

import com.fasterxml.jackson.annotation.JsonProperty;

public record EjemploResponseDTO(
    @JsonProperty("nombre") String nombre,
    @JsonProperty("apellido") String apellido) {

}
