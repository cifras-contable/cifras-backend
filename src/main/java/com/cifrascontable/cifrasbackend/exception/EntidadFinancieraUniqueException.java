package com.cifrascontable.cifrasbackend.exception;

import com.cifrascontable.cifrasbackend.exception.error.ResourceUniqueException;

public class EntidadFinancieraUniqueException extends ResourceUniqueException {
    private static final String RESOURCE_NAME = "entidad_financiera";

    public EntidadFinancieraUniqueException(String fieldName, Object fieldValue) {
        super(RESOURCE_NAME, fieldName, fieldValue);
    }
}
