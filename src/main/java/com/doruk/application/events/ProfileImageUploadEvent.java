package com.doruk.application.events;

import com.doruk.application.interfaces.EventDto;
import io.micronaut.serde.annotation.Serdeable;

import java.util.Optional;

@Serdeable
public record ProfileImageUploadEvent(
        String objectKey,
        String mimeType,
        Optional<String> oldObjectKey
        ) implements EventDto {
    @Override
    public String eventSubject() {
        return "profile.image.upload.event";
    }
}
