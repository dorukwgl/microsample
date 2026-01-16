package com.doruk.application.events;

import com.doruk.application.app.auth.dto.UploadedFileResult;
import com.doruk.application.interfaces.EventDto;
import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public record ProfileImageUpload(UploadedFileResult file, String previousImage) implements EventDto {
    @Override
    public String eventSubject() {
        return "image.profile.upload";
    }
}
