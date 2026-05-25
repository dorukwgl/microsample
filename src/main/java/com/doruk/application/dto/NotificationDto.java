package com.doruk.application.dto;

import com.doruk.application.enums.AttachmentType;
import io.micronaut.serde.annotation.Serdeable;
import org.jspecify.annotations.Nullable;

import java.util.UUID;

@Serdeable
public record NotificationDto(
        UUID userId,
        String title,
        String message,
        @Nullable String icon,
        @Nullable String attachment,
        @Nullable AttachmentType attachmentType
) {}
