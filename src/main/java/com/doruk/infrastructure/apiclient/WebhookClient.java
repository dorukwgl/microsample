package com.doruk.infrastructure.apiclient;

import io.micronaut.serde.annotation.Serdeable;

public interface WebhookClient {

    /**
     * Enum representing webhook delivery status
     */
    enum WebhookStatus {
        SUCCESS,
        ERROR
    }

    @Serdeable
    record WebhookResponse(WebhookStatus status, String message) {};

    /**
     * Send license event to a specific webhook URL
     * @param webhookUrl The dynamic URL to send the webhook to
     * @param body The JSON payload to send
     * @return WebhookResponse containing status and message
     */
    WebhookClient.WebhookResponse sendLicenseEvent(String webhookUrl, String body);
}
