package com.doruk.application.files;

import com.doruk.application.dto.StoredObject;
import com.doruk.application.dto.UploadedFile;
import com.doruk.application.enums.FileType;
import com.doruk.application.enums.ObjectVisibility;
import com.doruk.application.interfaces.ObjectStorage;
import com.doruk.application.interfaces.UploadSource;
import com.doruk.infrastructure.persistence.files.FileRepository;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;

import java.time.Duration;

@Singleton
@RequiredArgsConstructor
public class FileApplicationService {
    private final ObjectStorage objectStorage;
    private final FileRepository fileRepository;

    public UploadedFile upload(
            UploadSource source,
            FileType type,
            ObjectVisibility visibility,
            long maxSize
    ) {
        StoredObject stored = objectStorage.store(
                source, type, visibility, maxSize
        );

        var id = fileRepository.save(stored);
        return new UploadedFile(id, stored);
    }

    public String resolveUrl(StoredObject file, Duration ttl) {
        return file.visibility() == ObjectVisibility.PUBLIC
                ? objectStorage.resolveUrl(file)
                : objectStorage.signUrl(file, ttl);
    }

    public String resolveUrl(StoredObject file) {
        return objectStorage.resolveUrl(file);
    }
}
