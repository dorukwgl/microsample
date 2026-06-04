package com.doruk.infrastructure.fileio;

import com.doruk.application.interfaces.UploadSource;
import io.micronaut.http.multipart.CompletedFileUpload;

import java.io.BufferedInputStream;
import java.io.IOException;

public final class CompletedUploadSource implements UploadSource {

    private final CompletedFileUpload upload;

    public CompletedUploadSource(CompletedFileUpload upload) {
        this.upload = upload;
    }

    @Override
    public BufferedInputStream openStream() throws IOException {
        return new BufferedInputStream(upload.getInputStream(), 8192);
    }

    @Override
    public String originalFilename() {
        return upload.getFilename();
    }

    @Override
    public long size() {
        return upload.getSize();
    }

    @Override
    public String contentType() {
        return upload.getContentType().map(Object::toString).orElse("");
    }
}
