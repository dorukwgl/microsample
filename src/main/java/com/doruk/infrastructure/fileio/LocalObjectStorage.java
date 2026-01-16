package com.doruk.infrastructure.fileio;

import com.doruk.application.dto.StoredObject;
import com.doruk.application.enums.FileType;
import com.doruk.application.enums.ObjectVisibility;
import com.doruk.application.exception.FileUploadException;
import com.doruk.application.interfaces.ObjectStorage;
import com.doruk.application.interfaces.UploadSource;
import com.doruk.infrastructure.config.AppConfig;
import io.micronaut.context.annotation.Bean;
import io.micronaut.context.annotation.Requires;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.Duration;
import java.util.Locale;
import java.util.UUID;

@Singleton
@Bean(typed = ObjectStorage.class)
@Requires(env = "object-storage-local")
@RequiredArgsConstructor
public class LocalObjectStorage implements ObjectStorage {
    private final AppConfig config;

    @Override
    public StoredObject store(
            UploadSource source,
            FileType type,
            ObjectVisibility visibility,
            long maxSize
    ) {

        validate(source, type, maxSize);

        String original = source.originalFilename();
        String ext = extractExtension(original);

        String uuid = UUID.randomUUID().toString().replace("-", "");
        String shard1 = uuid.substring(0, 2);
        String shard2 = uuid.substring(2, 4);

        String objectKey = Path.of(shard1, shard2, uuid + ext).toString();

        Path baseDir = visibility == ObjectVisibility.PUBLIC
                ? Path.of(config.publicUploadPath())
                : Path.of(config.privateUploadPath());

        Path target = baseDir.resolve(objectKey);

        try {
            Files.createDirectories(target.getParent());

            try (InputStream in = source.openStream()) {
                Files.copy(in, target, StandardCopyOption.REPLACE_EXISTING);
            }

        } catch (IOException e) {
            throw new RuntimeException("Failed to store file", e);
        }

        return new StoredObject(
                objectKey,
                original,
                source.size(),
                visibility,
                source.contentType().toLowerCase(Locale.ROOT)
        );
    }

    @Override
    public String resolveUrl(StoredObject storedObject) {
        if (storedObject.visibility() != ObjectVisibility.PUBLIC) {
            throw new IllegalStateException("Private objects cannot be resolved directly");
        }

        return Path.of(config.publicUploadPath(), storedObject.objectKey()).toString();
    }

    @Override
    public String signUrl(StoredObject storedObject, Duration ttl) {
        if (storedObject.visibility() != ObjectVisibility.PRIVATE) {
            return resolveUrl(storedObject);
        }
        return "/protected/" + storedObject.objectKey();
    }

    private void validate(UploadSource source, FileType type, long maxSize) {

        if (source.size() > maxSize) {
            throw new FileUploadException("File too large");
        }

        String mime = source.contentType().toLowerCase(Locale.ROOT);
        if (!type.isAllowed(mime)) {
            throw new FileUploadException("Unsupported content type: " + mime);
        }
    }

    private String extractExtension(String filename) {
        int idx = filename.lastIndexOf('.');
        return idx > 0 ? filename.substring(idx) : "";
    }
}
