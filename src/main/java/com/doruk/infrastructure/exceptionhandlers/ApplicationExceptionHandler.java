package com.doruk.infrastructure.exceptionhandlers;

import com.doruk.application.exception.ApplicationException;
import com.doruk.application.exception.ForbiddenException;
import com.doruk.application.exception.InvalidCredentialException;
import com.doruk.infrastructure.dto.ErrorResponse;
import io.lettuce.core.cluster.api.StatefulRedisClusterConnection;
import io.micronaut.context.annotation.Requires;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.server.exceptions.ExceptionHandler;
import jakarta.inject.Singleton;
import javafx.util.Pair;

@Produces
@Singleton
@Requires(classes = ApplicationException.class)
public class ApplicationExceptionHandler implements ExceptionHandler<ApplicationException, HttpResponse<?>> {
    private Pair<HttpStatus, String> getStatusCode(ApplicationException exception) {
        var msg = exception.getMessage();
        return switch (exception) {
            case InvalidCredentialException _ -> new Pair<>(HttpStatus.UNAUTHORIZED, msg);
            case ForbiddenException _ -> new Pair<>(HttpStatus.FORBIDDEN, msg == null || msg.isBlank() ?
                "You are not supposed to perform this action." : msg);
            default -> throw exception; // let the global exception handler handle the server error
        };
    }

    @Override
    public HttpResponse<?> handle(HttpRequest request, ApplicationException exception) {
        var statusPair = getStatusCode(exception);
        return HttpResponse.status(statusPair.getKey(), statusPair.getValue())
                .body(new ErrorResponse(exception.getMessage()));
    }
}
