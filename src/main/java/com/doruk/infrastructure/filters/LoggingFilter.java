package com.doruk.infrastructure.filters;

import io.micronaut.context.annotation.Requires;
import io.micronaut.core.annotation.Order;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.MediaType;
import io.micronaut.http.MutableHttpResponse;
import io.micronaut.http.annotation.Filter;
import io.micronaut.http.filter.HttpServerFilter;
import io.micronaut.http.filter.ServerFilterChain;
import jakarta.inject.Singleton;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;

@Requires(env = "dev")
@Singleton
@Filter("/**") // @Filter("/app/**") // filter all routes
@Order(1)
public class LoggingFilter implements HttpServerFilter {
    private static final String BLUE_BOLD = "\u001B[34;1m";
    private static final String BLUE_DIM = "\u001B[34;2m";
    private static final String CYAN_BOLD = "\u001B[36;1m";
    private static final String CYAN_DIM = "\u001B[36;2m";
    private static final String RED_BOLD = "\u001B[31;1m";
    private static final String RESET = "\u001B[0m";

    @Override
    public Publisher<MutableHttpResponse<?>> doFilter(HttpRequest<?> request, ServerFilterChain chain) {
        final long startTime = System.currentTimeMillis();

        String clientIp = request.getRemoteAddress()
                .getAddress()
                .getHostAddress();

        String route = request.getUri().toString();
        String method = request.getMethodName();

        // Log request line
        System.out.printf("%s[%s: %s]: %s%n", BLUE_BOLD, method, clientIp, route);

        // Log incoming raw body if JSON
        request.getBody(String.class).ifPresent(body -> {
            String contentType = request.getContentType().orElse(MediaType.APPLICATION_JSON_TYPE).toString();
            if (contentType.contains("json"))
                System.out.printf("%s=>: %s%s%n", BLUE_DIM, body, RESET);
        });

        // Proceed with request response
        return Flux.from(chain.proceed(request))
                .map(response -> {
                    // Log response status and body
                    HttpStatus status = response.getStatus();
                    Object respBody = response.getBody().orElse(null);

                    var lineColor = status.getCode() > 400 ? RED_BOLD : CYAN_BOLD;
                    long duration = System.currentTimeMillis() - startTime;
                    System.out.printf("%s[%d~> %dms]:%s", lineColor, status.getCode(), duration, RESET);

                    if (respBody != null) {
                        var bodyColor = status.getCode() > 400 ? RED_BOLD : CYAN_DIM;
                        System.out.printf("%s: %s%s", bodyColor, respBody, RESET);
                    }
                    System.out.printf("%n");
                    return response;
                });
    }
}

