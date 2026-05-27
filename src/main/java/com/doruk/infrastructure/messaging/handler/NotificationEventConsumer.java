package com.doruk.infrastructure.messaging.handler;

import com.doruk.application.dto.StoredObject;
import com.doruk.application.enums.AttachmentType;
import com.doruk.application.enums.ObjectVisibility;
import com.doruk.application.events.NotificationEvent;
import com.doruk.application.interfaces.ObjectStorage;
import com.doruk.infrastructure.apiclient.FirebasePushService;
import com.doruk.infrastructure.config.AppExecutors;
import com.doruk.infrastructure.persistence.notification.NotificationRepository;
import io.micronaut.context.annotation.Context;
import io.micronaut.json.JsonMapper;
import io.nats.client.Connection;

@Context
public class NotificationEventConsumer extends EventConsumer<NotificationEvent> {
    private final NotificationRepository repository;
    private final FirebasePushService firebasePushService;
    private final ObjectStorage storage;

    public NotificationEventConsumer(AppExecutors executors,
                                     Connection natsConnection,
                                     JsonMapper jsonMapper,
                                     NotificationRepository repository,
                                     FirebasePushService firebasePushService,
                                     ObjectStorage storage) {
        super(executors, natsConnection, jsonMapper);
        this.repository = repository;
        this.firebasePushService = firebasePushService;
        this.storage = storage;
    }

    @Override
    protected String subject() {
        return "event.push.notification.send";
    }

    @Override
    protected String durable() {
        return "push-notification-consumer";
    }

    @Override
    protected String fetchErrorMsg() {
        return "Fetch Error: failed to fetch message durable: push-notification-consumer";
    }

    @Override
    protected String deserializationErrorMsg() {
        return "Deserialization Error: failed to deserialize notification event, terminating";
    }

    @Override
    protected String processFailureMsg() {
        return "Push Notification processing failed";
    }

    @Override
    protected Class<NotificationEvent> getConsumedEventTypeClass() {
        return NotificationEvent.class;
    }

    @Override
    protected AckAction processEvent(NotificationEvent event) {
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

        return AckAction.ACK;
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
