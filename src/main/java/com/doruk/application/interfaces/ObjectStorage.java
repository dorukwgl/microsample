package com.doruk.application.interfaces;

import com.doruk.application.dto.StoredObject;
import com.doruk.application.enums.FileType;
import com.doruk.application.enums.ObjectVisibility;

import java.time.Duration;

public interface ObjectStorage {
    StoredObject store(UploadSource source,
                       FileType type,
                       ObjectVisibility visibility,
                       long maxSize
                       );

    String resolveUrl(StoredObject storedObject);

    String signUrl(StoredObject storedObject, Duration ttl);
}
