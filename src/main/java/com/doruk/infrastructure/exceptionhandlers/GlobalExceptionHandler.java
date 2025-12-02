package com.doruk.infrastructure.exceptionhandlers;

import com.doruk.infrastructure.logging.LoggingService;
import com.doruk.infrastructure.util.ErrorBuilder;
import io.micronaut.context.annotation.Requires;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.server.exceptions.ExceptionHandler;
import jakarta.inject.Singleton;

import java.util.Map;

@Singleton
@Produces
@Requires(classes = Exception.class)
public class GlobalExceptionHandler
        implements ExceptionHandler<Exception, HttpResponse<Map<String, Object>>> {

    @Override
    public HttpResponse<Map<String, Object>> handle(HttpRequest request, Exception exception) {

        LoggingService.logError(exception.getMessage(), exception);

        var defaultBody = ErrorBuilder.buildErrorBody(request, "Internal Server Error", 500);
        return HttpResponse.serverError(defaultBody);
    }
}

