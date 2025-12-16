package com.doruk.presentation.users.controller;

import com.doruk.infrastructure.dto.InfoResponse;
import com.doruk.infrastructure.persistence.entity.PermissionFetcher;
import com.doruk.infrastructure.persistence.entity.PermissionTable;
import com.doruk.presentation.users.dto.TestDto;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.inject.Inject;
import org.babyfish.jimmer.sql.JSqlClient;
import org.babyfish.jimmer.sql.ast.mutation.DeleteMode;
import org.babyfish.jimmer.sql.runtime.LogicalDeletedBehavior;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Tag(name = "User Management, Registrations & Profiles")
@Controller("users")
public class UserController {
    @Inject
    public JSqlClient client;

    @Get("test")
    Mono<Map<String, String>> getUuid() {
        return Mono.from(Mono.just(Map.of("uuid", UUID.randomUUID().toString())));
    }

    @Get("testjson")
    InfoResponse getMessage() {
        return new InfoResponse("got your message");
    }

    @Get("permissions")
    Mono<List<TestDto>> getPermissions() {
        return Mono.fromCallable(() ->
                client.createQuery(PermissionTable.$)
                        .select(PermissionTable.$.fetch(
                                PermissionFetcher.$.allScalarFields()
                        ))
                        .execute()
                        .stream()
                        .map(TestDto::new)
                        .toList()
        );
    }

    @Get("permissions/deleted")
    Mono<List<TestDto>> getDeletedPermissions() {
        return Mono.fromCallable(() ->
                client
                        .filters(f -> f.setBehavior(LogicalDeletedBehavior.REVERSED))
                        .createQuery(PermissionTable.$)
                        .select(PermissionTable.$)
                        .execute()
                        .stream()
                        .map(TestDto::new)
                        .toList()
        );
    }

    @Get("permissions/all")
    Mono<List<TestDto>> getAllPermissions() {

        return Mono.fromCallable(() ->
                client
                        .filters(f -> f.setBehavior(LogicalDeletedBehavior.IGNORED))
                        .createQuery(PermissionTable.$)
                        .select(PermissionTable.$)
                        .execute()
                        .stream()
                        .map(TestDto::new)
                        .toList()
        );
    }

    @Get("permissions/delete-safe")
    Mono<Integer> deleteSafePermissions() {
        return Mono.fromCallable(() ->
                client
                        .createDelete(PermissionTable.$)
                        .where(PermissionTable.$.name().eq("test-permissions"))
                        .execute());
    }

    @Get("permissions/delete-all")
    Mono<Integer> deletePermissions() {
        return Mono.fromCallable(() ->
                client
                        .filters(f -> f.setBehavior(LogicalDeletedBehavior.IGNORED))
                        .createDelete(PermissionTable.$)
                        .setMode(DeleteMode.PHYSICAL)
                        .disableDissociation()
                        .where(PermissionTable.$.name().eq("test-ignore"))
                        .execute()
        );
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
        return Mono.defer(() -> {
            return Mono.just("");
        });
    }
}
