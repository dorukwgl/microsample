package com.doruk.infrastructure.messaging.handler;


import com.doruk.application.auth.dto.EmailOtpDto;
import com.doruk.application.auth.dto.SmsOtpDto;
import io.micronaut.nats.annotation.NatsListener;
import io.micronaut.nats.annotation.Subject;
import jakarta.inject.Singleton;

@Singleton
@NatsListener
public class OtpEventHandler {
    @Subject(value = "auth.mfa.sms-otp", queue = "otp-workers")
    public void handleSmsOtp(SmsOtpDto dto) {
        System.out.println("nats called me...");
        IO.println(Thread.currentThread().getName());
    }

    @Subject(value = "auth.mfa.email-otp", queue = "otp-workers")
    public void handleEmailOtp(EmailOtpDto dto) {
        IO.println("nats called me...");
        IO.println(Thread.currentThread().getName());
    }
}
