package com.cifrascontable.cifrasbackend.exception;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ValidationErrorResponse {
    private List<Violation> violations = new ArrayList<>();
}
