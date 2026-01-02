package com.doruk.application.dto;

import com.doruk.application.enums.TemplateType;
import com.doruk.application.interfaces.EventDto;
import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public record SmsOtpDto(String id, String phone, int otp, TemplateType templateType) implements EventDto {
    @Override
    public String eventSubject() {
        return "auth.mfa.sms-otp";
    }
}
