package com.doruk.infrastructure.apiclient.impl;

import com.doruk.infrastructure.apiclient.WebhookClient;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.MediaType;
import io.micronaut.http.client.HttpClient;
import jakarta.inject.Singleton;

import java.net.URI;
import java.net.URL;

@Singleton
public class WebhookClientImpl implements WebhookClient {

    @Override
    public WebhookResponse sendLicenseEvent(String webhookUrl, String body) {
        try {
            URL url = URI.create(webhookUrl).toURL();

            try (HttpClient httpClient = HttpClient.create(url)) {
                HttpRequest<String> request = HttpRequest.POST(webhookUrl, body)
                        .contentType(MediaType.APPLICATION_JSON_TYPE)
                        .accept(MediaType.APPLICATION_JSON_TYPE);

                // Use non-reflective approach: get raw response and determine success by HTTP status
                HttpResponse<String> response = httpClient.toBlocking()
                        .exchange(request, String.class);

                // Check HTTP status to determine success
                HttpStatus status = response.getStatus();
                if (status.getCode() >= 200 && status.getCode() < 300)
                    return new WebhookResponse(WebhookStatus.SUCCESS, "Webhook delivered successfully");
                 else {
                    String responseBody = response.body();
                    return new WebhookResponse(
                            WebhookStatus.ERROR,
                            "HTTP " + status.getCode() + ": " + responseBody
                    );
                }
            }

        } catch (Exception e) {
            return new WebhookResponse(
                    WebhookStatus.ERROR,
                    e.getMessage()
            );
        }
    }
}