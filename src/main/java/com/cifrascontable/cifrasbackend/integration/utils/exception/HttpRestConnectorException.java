package com.cifrascontable.cifrasbackend.integration.utils.exception;


import lombok.Getter;
import okhttp3.Headers;
import org.springframework.http.HttpStatus;

@Getter
public class HttpRestConnectorException extends RestConnectorException {

    private static final long serialVersionUID = 1L;

    private final int statusCode;

    private final String responseBody;

    private final Headers headers;

    /**
     * Construct a new instance of {@code HttpRestConnectorClientException}
     * based on an {@link HttpStatus}, status text, and response body content.
     *
     * @param statusCode
     *            the status code
     * @param headers
     *            the response headers
     * @param responseBody
     *            the response body content, may be {@code null}
     */
    public HttpRestConnectorException(int statusCode, Headers headers, String responseBody) {
        super(statusCode + " " + responseBody);
        this.statusCode = statusCode;
        this.headers = headers;
        this.responseBody = responseBody != null ? responseBody : "";
    }


}
