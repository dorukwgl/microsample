package com.doruk.application.app.notifications.service;

import com.doruk.application.app.notifications.dto.NotificationResponse;
import com.doruk.application.dto.PageQuery;
import com.doruk.application.dto.PageResponse;
import com.doruk.application.dto.StoredObject;
import com.doruk.application.enums.ObjectVisibility;
import com.doruk.application.interfaces.ObjectStorage;
import com.doruk.infrastructure.persistence.notification.NotificationRepository;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Singleton
@RequiredArgsConstructor
public class NotificationService {
    private final NotificationRepository repository;
    private final ObjectStorage storage;

    public PageResponse<NotificationResponse> getNotifications(UUID userId, PageQuery page) {
        var result = repository.findByUserId(userId, page);
        var resolved = result.data().stream()
                .map(this::resolveUrls)
                .toList();
        return new PageResponse<>(resolved, result.totalRowCount(), result.totalPageCount());
    }

    public void markAsRead(long id, UUID userId) {
        repository.markAsRead(id, userId);
    }

    public void delete(long id, UUID userId) {
        repository.delete(id, userId);
    }

    private NotificationResponse resolveUrls(NotificationResponse n) {
        return NotificationResponse.builder()
                .id(n.id())
                .title(n.title())
                .message(n.message())
                .iconUrl(n.iconUrl() != null ? storage.resolveUrl(storedObjectFromKey(n.iconUrl())) : null)
                .attachmentUrl(n.attachmentUrl() != null ? storage.resolveUrl(storedObjectFromKey(n.attachmentUrl())) : null)
                .attachmentType(n.attachmentType())
                .isRead(n.isRead())
                .createdAt(n.createdAt())
                .build();
    }

    private StoredObject storedObjectFromKey(String objectKey) {
        return StoredObject.builder()
                .objectKey(objectKey)
                .visibility(ObjectVisibility.PUBLIC)
                .size(0)
                .mimeType(null)
                .originalName(null)
                .build();
    }
}
