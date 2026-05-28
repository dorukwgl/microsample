package com.doruk.infrastructure.apiclient;

import com.doruk.infrastructure.apiclient.dto.GoogleTokenInfoResponse;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.client.annotation.Client;

@Client("https://oauth2.googleapis.com")
public interface GoogleApiClient {

    @Get("/tokeninfo?idToken={token}")
    GoogleTokenInfoResponse verifyToken(String token);
}
