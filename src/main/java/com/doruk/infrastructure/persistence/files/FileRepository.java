package com.doruk.infrastructure.persistence.files;

import com.doruk.application.dto.StoredObject;
import com.doruk.infrastructure.persistence.entity.MediaStoreDraft;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import org.babyfish.jimmer.sql.JSqlClient;

@Singleton
@RequiredArgsConstructor
public class FileRepository {
    private final JSqlClient client;

    public long save(StoredObject fileObject) {
        var draft = MediaStoreDraft.$.produce(m -> m
                .setSize(fileObject.size())
                .setMimeType(fileObject.mimeType())
                .setObjectKey(fileObject.objectKey())
                .setVisibility(fileObject.visibility())
        );

        return client.saveCommand(draft).execute().getModifiedEntity().id();
    }
}
