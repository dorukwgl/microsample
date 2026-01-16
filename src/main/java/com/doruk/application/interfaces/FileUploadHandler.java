package com.doruk.application.interfaces;

import com.doruk.application.app.auth.dto.UploadedFileResult;
import com.doruk.application.enums.FileType;
import io.micronaut.http.multipart.StreamingFileUpload;

import java.util.List;

public interface FileUploadHandler {
    UploadedFileResult uploadSingle(StreamingFileUpload file, FileType type, long maxSize);

    List<UploadedFileResult> uploadMultiple(List<StreamingFileUpload> files, FileType type, long maxSize);
}
