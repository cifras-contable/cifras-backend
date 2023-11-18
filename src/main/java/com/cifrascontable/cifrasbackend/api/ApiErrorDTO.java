package com.cifrascontable.cifrasbackend.api;

import lombok.Data;

@Data
public class ApiErrorDTO {

    private int errorCode;
    private String description;

}