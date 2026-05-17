package com.doruk.application.app.admin.dto;

import io.micronaut.serde.annotation.Serdeable;
import lombok.Builder;

import java.time.OffsetDateTime;
import java.util.UUID;

@Serdeable
@Builder
public record ProductServerResponse(
        UUID id,
        String name,
        String skuId,
        String hostUrl,
        String publicKey,
        OffsetDateTime createdAt,
        OffsetDateTime updatedAt
) {
}
