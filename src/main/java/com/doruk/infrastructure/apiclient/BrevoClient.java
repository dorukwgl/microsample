package com.doruk.infrastructure.apiclient;

import com.doruk.infrastructure.apiclient.dto.BrevoMailRequest;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Header;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.client.annotation.Client;

@Client("brevo")
@Header(name = "accept", value = "application/json")
@Header(name = "content-type", value = "application/json")
public interface BrevoClient {
    @Post("/v3/emailing/email")
    void sendEmail(@Header("api-key") String apiKey, @Body BrevoMailRequest body);
}
