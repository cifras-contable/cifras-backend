package com.cifrascontable.cifrasbackend.exception;

import com.cifrascontable.cifrasbackend.api.response.ApiResponse;
import com.cifrascontable.cifrasbackend.exception.error.ValidationError;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ErrorHandlingControllerAdvice {

    @ExceptionHandler(ConstraintViolationException.class)
    ResponseEntity<ApiResponse<Object>> onConstraintValidationException(ConstraintViolationException e) {
        List<ValidationError> validationErrors = new ArrayList<>();
        for (ConstraintViolation violation : e.getConstraintViolations()) {
            validationErrors.add(
                    new ValidationError(violation.getPropertyPath().toString(), violation.getMessage()));
        }

        ApiResponse<Object> response = ApiResponse.badRequestValidationError(validationErrors);

        return ResponseEntity
                .status(HttpStatus.valueOf(response.getHttpStatus()))
                .body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<ApiResponse<Object>> onMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<ValidationError> validationErrors = new ArrayList<>();
        for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
            validationErrors.add(
                    new ValidationError(fieldError.getField(), fieldError.getDefaultMessage())
            );
        }

        ApiResponse<Object> response = ApiResponse.badRequestValidationError(validationErrors);

        return ResponseEntity
                .status(HttpStatus.valueOf(response.getHttpStatus()))
                .body(response);
    }
}
