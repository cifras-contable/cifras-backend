package com.cifrascontable.cifrasbackend.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Violation {
    private String fieldName;
    private String message;
}
