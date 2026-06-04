package com.doruk.presentation.system.controller;

import com.doruk.infrastructure.annotations.Routes;
import io.micronaut.http.annotation.*;
import io.micronaut.http.MediaType;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import io.swagger.v3.oas.annotations.Operation;

@Controller(Routes.SYSTEM)
@Secured(SecurityRule.IS_ANONYMOUS)
public class SysDebugController {
    @Operation(description = "Returns the active Netty transport (epoll, io_uring, or nio)")
    @Produces(MediaType.TEXT_PLAIN)
    @Get("/transport")
    String transport() {
        try {
            var uring = Class.forName("io.netty.channel.uring.IoUring");
            if ((boolean) uring.getMethod("isAvailable").invoke(null)) return "io_uring";
        } catch (Exception ignored) {}
        try {
            var epoll = Class.forName("io.netty.channel.epoll.Epoll");
            if ((boolean) epoll.getMethod("isAvailable").invoke(null)) return "epoll";
        } catch (Exception ignored) {}
        return "nio";
    }

    @Operation(description = "Returns the current thread name")
    @Produces(MediaType.TEXT_PLAIN)
    @Get("/thread")
    String thread() {
        return Thread.currentThread().getName();
    }

    @Secured(SecurityRule.IS_AUTHENTICATED)
    @Get("/testauth")
    String testAuth() {
        return "you are authenticated...";
    }

    @Post("/body-test")
    String bodyTest(@Body("help") String body) {
        return body;
    }

    @Post("/body-test2")
    String bodyTest2(@Body("first") String firstName, @Body("last") String lastName) {
        return "Hey Mr. " + firstName + " " + lastName;
    }
}
