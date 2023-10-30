package com.cifrascontable.cifrasbackend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Persona {

    private Long id;
    private String nombre;
    private String apellido;
    private String email;
    private String tipo_documento;
    private String numero_documento;

}
