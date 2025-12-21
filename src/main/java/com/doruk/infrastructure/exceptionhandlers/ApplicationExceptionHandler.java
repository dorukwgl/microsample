package com.doruk.infrastructure.exceptionhandlers;

import com.doruk.application.exception.ApplicationException;
import com.doruk.application.exception.ForbiddenException;
import com.doruk.application.exception.InvalidCredentialException;
import com.doruk.infrastructure.dto.ErrorResponse;
import io.micronaut.context.annotation.Requires;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.server.exceptions.ExceptionHandler;
import jakarta.inject.Singleton;

@Produces
@Singleton
@Requires(classes = ApplicationException.class)
public class ApplicationExceptionHandler implements ExceptionHandler<ApplicationException, HttpResponse<?>> {
    private HttpStatus getStatusCode(ApplicationException exception) {
        return switch (exception) {
            case InvalidCredentialException e -> HttpStatus.UNAUTHORIZED;
            case ForbiddenException e -> HttpStatus.FORBIDDEN;
            default -> HttpStatus.INTERNAL_SERVER_ERROR;
        };
    }

    @Override
    public HttpResponse<?> handle(HttpRequest request, ApplicationException exception) {
        return HttpResponse.status(getStatusCode(exception), exception.getMessage())
                .body(new ErrorResponse(exception.getMessage()));
    }
}
