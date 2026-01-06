package com.doruk.application.auth.dto;

import com.doruk.application.interfaces.EventDto;
import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public record AuthUpdateTransaction(
        String tid,
        String userId,
        int otp,
        String payload,
        Type type
) implements EventDto {
    @Override
    public String eventSubject() {
        return "auth.update.otp";
    }

    public enum Type {
        EMAIL,
        PHONE
    }
}
