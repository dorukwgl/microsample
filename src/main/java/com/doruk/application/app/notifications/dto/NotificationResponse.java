package com.doruk.application.app.notifications.dto;

import com.doruk.application.enums.AttachmentType;
import io.micronaut.core.annotation.Nullable;
import io.micronaut.serde.annotation.Serdeable;
import lombok.Builder;

import java.time.OffsetDateTime;

@Serdeable
@Builder
public record NotificationResponse(
        long id,
        String title,
        String message,
        @Nullable String iconUrl,
        @Nullable String attachmentUrl,
        @Nullable AttachmentType attachmentType,
        boolean isRead,
        OffsetDateTime createdAt
) {}
