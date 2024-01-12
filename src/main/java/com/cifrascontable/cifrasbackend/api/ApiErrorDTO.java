package com.cifrascontable.cifrasbackend.api;

import com.cifrascontable.cifrasbackend.exception.error.ValidationError;
import lombok.Builder;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record ApiErrorDTO (
    LocalDateTime timestamp,
    Integer status,
    HttpStatus error,
    String message,
    String path,
    List<ValidationError> validations
) { }