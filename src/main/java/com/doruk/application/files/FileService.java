package com.doruk.application.files;

import com.doruk.application.dto.UploadedFile;
import com.doruk.application.enums.FileType;
import com.doruk.application.enums.ObjectVisibility;
import com.doruk.application.interfaces.ObjectStorage;
import com.doruk.infrastructure.config.AppConfig;
import com.doruk.infrastructure.fileio.CompletedUploadSource;
import com.doruk.infrastructure.persistence.files.FileRepository;
import io.micronaut.http.multipart.CompletedFileUpload;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Singleton
@RequiredArgsConstructor
public class FileService {
    private final ObjectStorage storage;
    private final AppConfig config;
    private final FileRepository fileRepo;

    public UploadedFile imageUploadPublic(CompletedFileUpload upload) {
        var obj = storage.store(new CompletedUploadSource(upload), FileType.IMAGE, ObjectVisibility.PUBLIC, config.imageMaxSize());
        var id = fileRepo.save(obj);
        return new UploadedFile(id, obj);
    }

    public List<UploadedFile> imageUploadPublicBatch(List<CompletedFileUpload> uploads) {
        return uploads.stream().map(this::imageUploadPublic).toList();
    }

    public UploadedFile imageUploadPrivate(CompletedFileUpload upload) {
        var obj = storage.store(new CompletedUploadSource(upload), FileType.IMAGE, ObjectVisibility.PRIVATE, config.imageMaxSize());
        var id = fileRepo.save(obj);
        return new UploadedFile(id, obj);
    }

    public List<UploadedFile> imageUploadPrivateBatch(List<CompletedFileUpload> uploads) {
        return uploads.stream().map(this::imageUploadPrivate).toList();
    }
}
