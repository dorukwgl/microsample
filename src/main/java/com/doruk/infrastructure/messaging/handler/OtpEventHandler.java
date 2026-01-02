package com.doruk.infrastructure.messaging.handler;


import com.doruk.application.dto.EmailOtpDto;
import com.doruk.application.dto.SmsOtpDto;
import com.doruk.application.interfaces.MailService;
import com.doruk.application.interfaces.SmsService;
import com.doruk.infrastructure.config.AppExecutors;
import com.doruk.infrastructure.persistence.auth.AuthRepository;
import io.micronaut.nats.annotation.NatsListener;
import io.micronaut.nats.annotation.Subject;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;

import java.util.concurrent.CompletableFuture;

@Singleton
@NatsListener
@RequiredArgsConstructor
public class OtpEventHandler {
    private final SmsService smsService;
    private final MailService mailService;
    private final AuthRepository authRepository;
    private final AppExecutors executors;

    @Subject(value = "auth.mfa.sms-otp", queue = "otp-workers")
    public void handleSmsOtp(SmsOtpDto dto) {
        CompletableFuture.runAsync(() -> smsService.sendOtp(dto.phone(), dto.otp(), dto.templateType()),
                        executors.VIRTUAL())
                .join();
    }

    @Subject(value = "auth.mfa.email-otp", queue = "otp-workers")
    public void handleEmailOtp(EmailOtpDto dto) {
        CompletableFuture.runAsync(() -> {
                    var user = authRepository.getMailAddress(dto.id());
                    mailService.sendOtp(new MailService.MailParams(
                            user.getKey(),
                            user.getValue(),
                            null,
                            dto.otp()
                    ), dto.templateType());
                },
                executors.VIRTUAL()
        ).join();
    }
}
