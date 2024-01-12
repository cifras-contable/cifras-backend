package com.cifrascontable.cifrasbackend.enums;

public enum ResponseStatus {
    STATUS_SUCCESS("SUCCESS"),
    STATUS_FAILURE("FAILURE");

    private final String value;

    ResponseStatus(String value) {
        this.value = value;
    }

    public final String getValue() {
        return this.value;
    }
}
