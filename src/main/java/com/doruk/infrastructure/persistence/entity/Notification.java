package com.doruk.infrastructure.persistence.entity;

import com.doruk.application.enums.AttachmentType;
import org.babyfish.jimmer.sql.*;
import org.jspecify.annotations.Nullable;

import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "notifications")
public interface Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id();

    UUID userId();

    String title();

    String message();

    @Nullable
    String icon();

    @Nullable
    String attachment();

    @Nullable
    AttachmentType attachmentType();

    boolean isRead();

    OffsetDateTime createdAt();

    OffsetDateTime updatedAt();
}
