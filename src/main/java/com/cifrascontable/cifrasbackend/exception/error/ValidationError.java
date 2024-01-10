package com.cifrascontable.cifrasbackend.exception.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ValidationError {
    private String fieldName;
    private String message;
}
