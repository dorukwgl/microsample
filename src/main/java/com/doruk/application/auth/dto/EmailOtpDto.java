package com.doruk.application.auth.dto;

import com.doruk.application.interfaces.EventDto;
import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public record EmailOtpDto() implements EventDto {
    @Override
    public String eventSubject() {
        return "auth.mfa.email-otp";
    }
}
