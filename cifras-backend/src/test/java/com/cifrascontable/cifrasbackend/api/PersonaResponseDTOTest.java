package com.cifrascontable.cifrasbackend.api;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PersonaResponseDTOTest {

    @Test
    void testPersonaResponseDTO() {
        PersonaResponseDTO personaResponseDTO = PersonaResponseDTO.builder()
            .id(1L)
            .nombre_y_apellido("Juan", "Perez")
            .email("asd")
            .tipo_documento("dni")
            .numero_documento("1234")
            .build();
        assertEquals(1L, personaResponseDTO.id());
    }
}