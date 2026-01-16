package com.doruk.application.dto;

import com.doruk.application.enums.ObjectVisibility;
import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public record StoredObject(
        String objectKey,
        String originalName,
        long size,
        ObjectVisibility visibility,
        String mimeType
) {
}
