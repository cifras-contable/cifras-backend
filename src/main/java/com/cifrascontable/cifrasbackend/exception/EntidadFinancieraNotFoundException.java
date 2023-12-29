package com.cifrascontable.cifrasbackend.exception;

import com.cifrascontable.cifrasbackend.exception.error.ResourceNotFoundException;

public class EntidadFinancieraNotFoundException extends ResourceNotFoundException {

    private static final String RESOURCE_NAME = "entidad_financiera";


    public EntidadFinancieraNotFoundException( String fieldName, Object fieldValue) {
        super(RESOURCE_NAME, fieldName, fieldValue);
    }
}
