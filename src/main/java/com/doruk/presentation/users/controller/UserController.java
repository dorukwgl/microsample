package com.doruk.presentation.users.controller;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.swagger.v3.oas.annotations.tags.Tag;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@Tag(name = "User Management, Registrations & Profiles")
@Controller("users")
public class UserController {
    @Get("test")
    Mono<Map<String, String>> getUuid() {
        return Mono.from(Mono.just(Map.of("uuid", UUID.randomUUID().toString())));
    }

    @Get("ntest")
    Mono<Map<?, ?>> getNTest() {
        return Mono.fromCallable(() -> Map.of("user", "david"));
    }

    @Get("date")
    Mono<LocalDateTime> getDate() {
        return Mono.just(LocalDateTime.now());
    }

    @Get("except")
    Mono<String> getTest() {
//        throw new IllegalArgumentException();
//        return Mono.just("hello");
        return Mono.defer(() -> {return Mono.just("");});
    }
}
