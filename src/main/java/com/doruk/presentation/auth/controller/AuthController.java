package com.doruk.presentation.auth.controller;

import com.doruk.application.auth.dto.LoginResponse;
import com.doruk.application.auth.service.AuthService;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Secured(SecurityRule.IS_ANONYMOUS)
@Controller("auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService service;

    @Get("/hello")
    Mono<String> hello() {
        return Mono.fromCallable(() -> {
            System.out.println(Thread.currentThread().getName());
            return "world...";
        });
    }

    @Get("/test")
    String tests() {
        System.out.println(Thread.currentThread().getName());
        return service.test();
    }

    @Get("/login")
    LoginResponse test() {
        return service.performLogin();
    }
}
