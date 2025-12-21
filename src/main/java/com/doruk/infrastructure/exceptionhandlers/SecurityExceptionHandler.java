package com.doruk.infrastructure.exceptionhandlers;

import com.doruk.application.exception.ForbiddenException;
import com.doruk.application.exception.UnauthorizedException;
import com.doruk.infrastructure.dto.ErrorResponse;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Error;
import io.micronaut.http.annotation.Produces;
import jakarta.inject.Singleton;

@Produces
@Singleton
public class SecurityExceptionHandler {
    @Error(global = true, exception = UnauthorizedException.class)
    public HttpResponse<?> unauthorized() {
        return HttpResponse.unauthorized().body(new ErrorResponse(
                "Please Sign In to perform this action."
        ));
    }

    @Error(global = true, exception = ForbiddenException.class)
    public HttpResponse<?> forbidden() {
        return HttpResponse.status(HttpStatus.FORBIDDEN).body(
                new ErrorResponse(
                        "You are not supposed to perform this action."
                )
        );
    }
}
