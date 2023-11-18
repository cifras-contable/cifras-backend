package com.cifrascontable.cifrasbackend.api;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record PersonaRequestDTO(
    @JsonProperty("nombre") String nombre,
    @JsonProperty("apellido") String apellido,
    @JsonProperty("email") String email,
    @JsonProperty("tipo_documento") String tipo_documento,
    @JsonProperty("numero_documento") String numero_documento
) {


}