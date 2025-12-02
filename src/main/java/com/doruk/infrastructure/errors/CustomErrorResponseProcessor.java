package com.doruk.infrastructure.errors;

import com.doruk.infrastructure.util.ErrorBuilder;
import io.micronaut.context.annotation.Replaces;
import io.micronaut.http.*;
import io.micronaut.http.server.exceptions.response.*;
import io.micronaut.http.server.exceptions.response.ErrorContext;
import jakarta.inject.Singleton;

import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Replaces all Micronaut default error rendering.
 */
@Singleton
@Replaces(ErrorResponseProcessor.class)
public class CustomErrorResponseProcessor implements ErrorResponseProcessor<Map<String, Object>> {

    @Override
    public MutableHttpResponse processResponse(ErrorContext errorContext,
                                                  MutableHttpResponse response) {

        HttpRequest<?> request = errorContext.getRequest();

        Throwable error = errorContext.getRootCause()
                .orElse(new RuntimeException("Unexpected error"));

        int status = response.getStatus().getCode();

        Map<String, Object> body = ErrorBuilder.buildErrorBody(request, error.getMessage(), status);

        return response.body(body)
                .contentType(MediaType.APPLICATION_JSON_TYPE);
    }
}
