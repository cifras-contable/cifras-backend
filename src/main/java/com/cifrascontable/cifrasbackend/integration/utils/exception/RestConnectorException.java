package com.cifrascontable.cifrasbackend.integration.utils.exception;

public class RestConnectorException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public RestConnectorException() {
        super();
    }

    public RestConnectorException(String message, Throwable cause, boolean enableSuppression,
        boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public RestConnectorException(String message, Throwable cause) {
        super(message, cause);
    }

    public RestConnectorException(String message) {
        super(message);
    }

    public RestConnectorException(Throwable cause) {
        super(cause);
    }
}
