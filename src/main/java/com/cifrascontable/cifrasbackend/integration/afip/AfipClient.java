package com.cifrascontable.cifrasbackend.integration.afip;


import com.cifrascontable.cifrasbackend.integration.afip.api.AfipRequestDTO;
import com.cifrascontable.cifrasbackend.integration.afip.api.AfipResponseDTO;
import com.cifrascontable.cifrasbackend.integration.utils.RestConnectorClient;
import com.fasterxml.jackson.core.type.TypeReference;
import okhttp3.OkHttpClient;

public class AfipClient extends RestConnectorClient {

    private static final String GET_DATA = "/cuit";
    private static final TypeReference<AfipResponseDTO> EJEMPLO_RESPONSE_DTO_TYPE_REFERENCE =
        new TypeReference<AfipResponseDTO>() {};

    public AfipClient(OkHttpClient client, String xClient, String serviceType, String host, String contextPath) {
        super(client, xClient, serviceType, host, contextPath);
    }

    public AfipResponseDTO getData(AfipRequestDTO ejemploRequestDTO) {
        //this.doGet(GET_DATA, EJEMPLO_RESPONSE_DTO_TYPE_REFERENCE,);
        // TODO: implement me
        return null;
    }

}
