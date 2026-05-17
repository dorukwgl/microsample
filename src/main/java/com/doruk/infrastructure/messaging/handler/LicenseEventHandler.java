package com.doruk.infrastructure.messaging.handler;

import com.doruk.infrastructure.apiclient.WebhookClient;
import com.doruk.infrastructure.config.AppExecutors;
import com.doruk.infrastructure.logging.LoggingService;
import io.micronaut.nats.annotation.NatsListener;
import io.micronaut.nats.annotation.Subject;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;

import java.util.concurrent.CompletableFuture;

@Singleton
@NatsListener
@RequiredArgsConstructor
public class LicenseEventHandler {
    private final AppExecutors executors;
    private final WebhookClient webhookClient;

    @Subject(value = "license.created", queue = "license-webhook-queue")
    public void handleLicenseCreated(String body) {
        CompletableFuture.runAsync(() -> {
            try {
                LoggingService.logInfo("Processing license.created event: " + body);

                // TODO: Extract webhook URL from event payload or organization configuration
                // Example: String webhookUrl = extractWebhookUrl(body);
                // For now, use a placeholder URL
                String webhookUrl = "http://localhost:8080/webhook/license";

                var response = webhookClient.sendLicenseEvent(webhookUrl, body);

                if (response.status() == WebhookClient.WebhookStatus.SUCCESS) {
                    LoggingService.logInfo("Webhook delivered successfully: " + response.message());
                } else {
                    LoggingService.logError("Webhook delivery failed: " + response.message());
                }
            } catch (Exception e) {
                LoggingService.logError("Error processing license.created event", e);
            }
        }, executors.VIRTUAL());
    }

    @Subject(value = "license.updated", queue = "license-webhook-queue")
    public void handleLicenseUpdated(String body) {
        CompletableFuture.runAsync(() -> {
            try {
                LoggingService.logInfo("Processing license.updated event: " + body);

                // TODO: Extract webhook URL from event payload or organization configuration
                String webhookUrl = "http://localhost:8080/webhook/license";

                webhookClient.sendLicenseEvent(webhookUrl, body);

            } catch (Exception e) {
                LoggingService.logError("Error processing license.updated event", e);
            }
        }, executors.VIRTUAL());
    }

    @Subject(value = "license.expired", queue = "license-webhook-queue")
    public void handleLicenseExpired(String body) {
        CompletableFuture.runAsync(() -> {
            try {
                LoggingService.logInfo("Processing license.expired event: " + body);

                // TODO: Extract webhook URL from event payload or organization configuration
                String webhookUrl = "http://localhost:8080/webhook/license";

                webhookClient.sendLicenseEvent(webhookUrl, body);

            } catch (Exception e) {
                LoggingService.logError("Error processing license.expired event", e);
            }
        }, executors.VIRTUAL());
    }

    @Subject(value = "license.revoked", queue = "license-webhook-queue")
    public void handleLicenseRevoked(String body) {
        CompletableFuture.runAsync(() -> {
            try {
                LoggingService.logInfo("Processing license.revoked event: " + body);

                // TODO: Extract webhook URL from event payload or organization configuration
                String webhookUrl = "http://localhost:8080/webhook/license";

                webhookClient.sendLicenseEvent(webhookUrl, body);

            } catch (Exception e) {
                LoggingService.logError("Error processing license.revoked event", e);
            }
        }, executors.VIRTUAL());
    }
}
