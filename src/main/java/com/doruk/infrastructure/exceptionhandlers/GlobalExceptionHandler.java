package com.doruk.infrastructure.exceptionhandlers;

import io.micronaut.context.annotation.Requires;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.server.exceptions.ExceptionHandler;
import jakarta.inject.Singleton;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@Singleton
@Produces
@Requires(classes = Exception.class)
public class GlobalExceptionHandler
        implements ExceptionHandler<Exception, HttpResponse<Map<String, Object>>> {

    @Override
    public HttpResponse<Map<String, Object>> handle(HttpRequest request, Exception exception) {

        // Use Micronaut’s default problem details
        Map<String, Object> defaultBody = new HashMap<>();

        defaultBody.put("error", exception.getMessage());
        defaultBody.put("path", request.getPath());
        defaultBody.put("status", 500);
        defaultBody.put("timestamp", Instant.now().toString());

        return HttpResponse.serverError(defaultBody);
    }
}

