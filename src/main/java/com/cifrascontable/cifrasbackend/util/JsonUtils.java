package com.cifrascontable.cifrasbackend.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.afterburner.AfterburnerModule;
import io.micrometer.common.util.StringUtils;
import java.io.IOException;

public class JsonUtils {
    private static final ObjectMapper defaultObjectMapper = new ObjectMapper();
    private final ObjectMapper objectMapper;

    public static ObjectMapper getCopyOfObjectMapper() {
        return defaultObjectMapper.copy();
    }

    public JsonUtils(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public JsonUtils() {
        this.objectMapper = defaultObjectMapper;
    }

    public <T> String serialize(T source) {
        if (source == null) {
            return null;
        } else {
            try {
                return this.objectMapper.writeValueAsString(source);
            } catch (Exception var3) {
                throw new RuntimeException(var3);
            }
        }
    }

    public <T> T deserialize(String json, Class<T> clazz) {
        return deserialize(json, (j) -> {
            return this.objectMapper.readValue(j, clazz);
        });
    }

    public <T> T deserialize(String json, TypeReference<T> type) {
        return deserialize(json, (j) -> {
            return this.objectMapper.readValue(j, type);
        });
    }

    private static <T> T deserialize(String json, JacksonFunction<T> fn) {
        if (StringUtils.isBlank(json)) {
            return null;
        } else {
            try {
                return fn.apply(json);
            } catch (Exception var3) {
                throw new RuntimeException(var3);
            }
        }
    }

    static {
        defaultObjectMapper.registerModule(new AfterburnerModule());
        defaultObjectMapper.registerModule(new JavaTimeModule());
        defaultObjectMapper.registerModule(new Jdk8Module());
        defaultObjectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        defaultObjectMapper.enable(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS);
        defaultObjectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
    }

    @FunctionalInterface
    private interface JacksonFunction<T> {
        T apply(String var1) throws IOException;
    }
}
