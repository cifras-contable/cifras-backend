package com.cifrascontable.cifrasbackend.integration.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response <R> {

    private Map<String, List<String>> headers;
    private R body;

}