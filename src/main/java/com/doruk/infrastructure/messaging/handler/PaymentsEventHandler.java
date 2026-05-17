/**
 * Events handled here are published by the billing server
 */

package com.doruk.infrastructure.messaging.handler;

import com.doruk.application.app.license.dto.PaymentCompletedEvent;
import com.doruk.application.app.license.dto.PaymentExpiredEvent;
import com.doruk.application.app.license.dto.PaymentRenewedEvent;
import com.doruk.application.app.license.dto.PaymentRevokedEvent;
import com.doruk.application.app.license.LicenseService;
import com.doruk.infrastructure.config.AppExecutors;
import io.micronaut.nats.annotation.NatsListener;
import io.micronaut.nats.annotation.Subject;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;

import java.util.concurrent.CompletableFuture;

@Singleton
@NatsListener
@RequiredArgsConstructor
public class PaymentsEventHandler {
    private final AppExecutors executors;
    private final LicenseService service;

    @Subject(value = "payment.completed", queue = "payment-completed-queue")
    public void handlePaymentCompleted(PaymentCompletedEvent event) {
        CompletableFuture.runAsync(() -> service.onPayment(event), executors.VIRTUAL());
    }

    @Subject(value = "payment.renewed", queue = "payment-renewed-queue")
    public void handlePaymentRenewed(PaymentRenewedEvent event) {
        CompletableFuture.runAsync(() -> service.onPaymentRenew(event), executors.VIRTUAL());
    }

    @Subject(value = "payment.expired", queue = "payment-expired-queue")
    public void handlePaymentExpired(PaymentExpiredEvent event) {
        CompletableFuture.runAsync(() -> service.onPaymentExpired(event), executors.VIRTUAL());
    }

    @Subject(value = "payment.revoked", queue = "payment-revoked-queue")
    public void handlePaymentRevoked(PaymentRevokedEvent event) {
        CompletableFuture.runAsync(() -> service.onPaymentRevoked(event), executors.VIRTUAL());
    }
}
