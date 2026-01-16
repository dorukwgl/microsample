package com.doruk.application.app.auth.dto;

import java.nio.file.Path;

public record UploadedFileResult(
        String originalName,
        String storedName,
        Path fullPath,
        long size
) {
}
