package com.cifrascontable.cifrasbackend.exception.error;

public class ResourceUniqueException extends RuntimeException {
    private String resourceName;
    private String fieldName;
    private Object fieldValue;

    public ResourceUniqueException(String resourceName, String fieldName, Object fieldValue) {
        super(String.format("Resource %s exists with %s : '%s'", resourceName, fieldName, fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }
}
