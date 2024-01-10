package com.cifrascontable.cifrasbackend.api.response;

import com.cifrascontable.cifrasbackend.enums.ResponseStatus;
import com.cifrascontable.cifrasbackend.exception.error.ValidationError;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiResponse <T> {
    // The status of the API response, indicating SUCCESS or FAILURE.
    private String status;

    // The HTTP status code associated with the API response.
    private Integer httpStatus;

    // The data payload included in the API response, holding the actual content.
    private T data;

    // Possible error message
    private String errorMessage;

    // Possible validation errors associated with the request.
    private List<ValidationError> validationErrors;

    public static <T> ApiResponse<T> okData(T data) {
        return ApiResponse.<T>builder()
            .httpStatus(HttpStatus.OK.value())
            .status(ResponseStatus.STATUS_SUCCESS.getValue())
            .data(data)
            .build();
    }

    public static <T> ApiResponse<T> okEmpty() {
        return ApiResponse.<T>builder()
            .httpStatus(HttpStatus.OK.value())
            .status(ResponseStatus.STATUS_SUCCESS.getValue())
            .build();
    }

    public static <T> ApiResponse<T> created(T data) {
        return ApiResponse.<T>builder()
            .httpStatus(HttpStatus.CREATED.value())
            .status(ResponseStatus.STATUS_SUCCESS.getValue())
            .data(data)
            .build();
    }

    public static <T> ApiResponse<T> badRequestError(String errorMessage) {
        return ApiResponse.<T>builder()
            .httpStatus(HttpStatus.BAD_REQUEST.value())
            .status(ResponseStatus.STATUS_FAILURE.getValue())
            .errorMessage(errorMessage)
            .build();
    }

    public static <T> ApiResponse<T> badRequestValidationError(List<ValidationError> validationErrors) {
        return ApiResponse.<T>builder()
            .httpStatus(HttpStatus.BAD_REQUEST.value())
            .status(ResponseStatus.STATUS_FAILURE.getValue())
            .validationErrors(validationErrors)
            .build();
    }

    public static <T> ApiResponse<T> notFound(String errorMessage) {
        return ApiResponse.<T>builder()
            .httpStatus(HttpStatus.NOT_FOUND.value())
            .status(ResponseStatus.STATUS_FAILURE.getValue())
            .errorMessage(errorMessage)
            .build();
    }
}
