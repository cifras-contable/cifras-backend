package com.cifrascontable.cifrasbackend.integration.afip;

import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.web.client.RestClient;

public class AfipClient {

    private static final String GET_DATA = "/cuit";
    private static final TypeReference<EjemploResponseDTO> EJEMPLO_RESPONSE_DTO_TYPE_REFERENCE =
        new TypeReference<EjemploResponseDTO>() {};

    public EjemploResponseDTO getData(EjemploRequestDTO ejemploRequestDTO) {
        // TODO: implementar methodo
        return null;
    }

}
