package com.cifrascontable.cifrasbackend.api;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record PersonaResponseDTO(
    @JsonProperty("id") Long id,
    @JsonProperty("nombre_y_apellido") String nombre_y_apellido,
    @JsonProperty("email") String email,
    @JsonProperty("tipo_documento") String tipo_documento,
    @JsonProperty("numero_documento") String numero_documento
){

    public static class PersonaResponseDTOBuilder {
        public PersonaResponseDTOBuilder nombre_y_apellido(String nombre, String apellido) {
            this.nombre_y_apellido = nombre + " " + apellido;
            return this;
        }
    }

}