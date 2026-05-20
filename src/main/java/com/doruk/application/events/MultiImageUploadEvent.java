package com.doruk.application.events;

import com.doruk.application.interfaces.EventDto;
import io.micronaut.serde.annotation.Serdeable;

import java.util.List;

@Serdeable
public record MultiImageUploadEvent(
        List<FilePayload> files
) implements EventDto {
    @Override
    public String eventSubject() {
        return "file.image.upload.multi";
    }

    @Serdeable
    public record FilePayload(String objectKey, String mimeType) {
    }
}
