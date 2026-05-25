package com.doruk.infrastructure.messaging.handler;

import com.doruk.application.dto.StoredObject;
import com.doruk.application.enums.AttachmentType;
import com.doruk.application.enums.ObjectVisibility;
import com.doruk.application.events.NotificationEvent;
import com.doruk.application.interfaces.ObjectStorage;
import com.doruk.infrastructure.apiclient.FirebasePushService;
import com.doruk.infrastructure.config.AppExecutors;
import com.doruk.infrastructure.logging.LoggingService;
import com.doruk.infrastructure.persistence.notification.NotificationRepository;
import io.micronaut.nats.annotation.NatsListener;
import io.micronaut.nats.annotation.Subject;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;

import java.util.concurrent.CompletableFuture;

@Singleton
@NatsListener
@RequiredArgsConstructor
public class NotificationEventHandler {
    private final NotificationRepository repository;
    private final FirebasePushService firebasePushService;
    private final ObjectStorage storage;
    private final AppExecutors executors;

    @Subject(value = "event.push.notification.send", queue = "event-push-notification-workers")
    public void handle(NotificationEvent event) {
        CompletableFuture.runAsync(() -> {
            try {
                repository.saveAll(event.notifications());

                for (var dto : event.notifications()) {
                    var tokens = repository.findDeviceTokens(dto.userId());
                    if (tokens.isEmpty()) continue;

                    String iconUrl = dto.icon() != null
                            ? storage.resolveUrl(storedObjectFromKey(dto.icon()))
                            : null;

                    String attachmentUrl = dto.attachment() != null
                            ? storage.resolveUrl(storedObjectFromKey(dto.attachment()))
                            : null;

                    boolean isImageAttachment = dto.attachmentType() == AttachmentType.IMAGE;
                    String imageUrl = isImageAttachment ? attachmentUrl : null;
                    String attachmentType = dto.attachmentType() != null ? dto.attachmentType().name() : null;

                    firebasePushService.sendBulk(tokens, dto.title(), dto.message(), iconUrl, imageUrl,
                            attachmentType, attachmentUrl);
                }
            } catch (Exception e) {
                LoggingService.logError("Notification event handling failed", e);
            }
        }, executors.VIRTUAL());

    }

    private StoredObject storedObjectFromKey(String objectKey) {
        return StoredObject.builder()
                .objectKey(objectKey)
                .visibility(ObjectVisibility.PUBLIC)
                .size(0)
                .mimeType(null)
                .originalName(null)
                .build();
    }
}
