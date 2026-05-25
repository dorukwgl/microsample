package com.doruk.application.events;

import com.doruk.application.dto.NotificationDto;
import com.doruk.application.interfaces.EventDto;
import io.micronaut.serde.annotation.Serdeable;

import java.util.List;

@Serdeable
public record NotificationEvent(List<NotificationDto> notifications) implements EventDto {
    @Override
    public String eventSubject() {
        return "event.push.notification.send";
    }
}
