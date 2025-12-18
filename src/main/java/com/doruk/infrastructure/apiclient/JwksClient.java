package com.doruk.infrastructure.apiclient;

import com.doruk.infrastructure.apiclient.dto.JwksRequest;
import com.doruk.infrastructure.apiclient.dto.JwksResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.client.annotation.Client;
import reactor.core.publisher.Mono;


@Client("jwks")
public interface JwksClient {
    @Post("/auth/token")
    Mono<JwksResponse> issueToken(@Body JwksRequest request);
}
