package com.doruk.infrastructure.messaging.publisher;

import com.doruk.application.dto.NotificationDto;
import com.doruk.application.events.NotificationEvent;
import com.doruk.application.interfaces.EventPublisher;
import io.micronaut.core.annotation.Introspected;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Introspected
@Singleton
@RequiredArgsConstructor
public class PushNotification {
    private final EventPublisher eventPublisher;

    public void publish(List<NotificationDto> dtos) {
        if (dtos == null || dtos.isEmpty()) return;
        eventPublisher.publish(new NotificationEvent(dtos));
    }
}
