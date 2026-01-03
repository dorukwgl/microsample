package com.doruk.application.events;

import com.doruk.application.auth.dto.UploadedFileResult;
import com.doruk.application.interfaces.EventDto;

import java.util.List;

public record MultiImageUpload(List<UploadedFileResult> files) implements EventDto {
    @Override
    public String eventSubject() {
        return "file.image.upload.multi";
    }
}
