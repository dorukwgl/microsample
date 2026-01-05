package com.doruk.infrastructure.fileio;

import com.doruk.application.auth.dto.UploadedFileResult;
import com.doruk.application.enums.FileType;
import com.doruk.application.exception.FileUploadException;
import com.doruk.application.interfaces.FileUploadHandler;
import com.doruk.infrastructure.config.AppConfig;
import io.micronaut.context.annotation.Bean;
import io.micronaut.http.multipart.StreamingFileUpload;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.stream.Collectors;

@Singleton
@RequiredArgsConstructor
@Bean(typed = FileUploadHandler.class)
public class GenericFileUploadHandler implements FileUploadHandler {
    private final AppConfig config;

    @Override
    public UploadedFileResult uploadSingle(StreamingFileUpload file, FileType type, long maxSize) {
        validate(file, type, maxSize);
        return processUpload(file);
    }

    @Override
    public List<UploadedFileResult> uploadMultiple(List<StreamingFileUpload> files, FileType type, long maxSize) {
        return files.stream()
                .map(f -> uploadSingle(f, type, maxSize))
                .collect(Collectors.toList());
    }

    private void validate(StreamingFileUpload file, FileType type, long maxSize) {
        if (file.getSize() > maxSize) {
            throw new FileUploadException("File too large: " + file.getSize() + " > " + maxSize);
        }

        var mime = file.getContentType()
                .orElseThrow(() -> new FileUploadException("File type not supported"));
        if (!type.isAllowed(mime.toString().toLowerCase(Locale.ROOT))) {
            throw new FileUploadException("Invalid MIME type: " + mime);
        }
    }

    private UploadedFileResult processUpload(StreamingFileUpload file) {
        String original = file.getFilename();
        String ext = original.contains(".") ? original.substring(original.lastIndexOf(".")) : "";
        String stored = UUID.randomUUID() + ext;

        Path target = Paths.get(config.tempDir(), stored);

        var success = Mono.from(file.transferTo(target.toFile()))
                .block();
        if (!Boolean.TRUE.equals(success))
            throw new RuntimeException("Failed to upload file");

        return new UploadedFileResult(original, stored, target.toAbsolutePath(), file.getSize());
    }
}
