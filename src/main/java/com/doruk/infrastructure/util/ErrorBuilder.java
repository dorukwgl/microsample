package com.doruk.infrastructure.util;

import io.micronaut.http.HttpRequest;

import java.time.Instant;
import java.util.Map;

public class ErrorBuilder {
    public static Map<String, Object> buildErrorBody(HttpRequest<?> request, String message, int status) {
        return Map.of(
                "error", message,
                "status", status,
                "path", request.getPath(),
                "timestamp", Instant.now().toString()
        );
    }
}
