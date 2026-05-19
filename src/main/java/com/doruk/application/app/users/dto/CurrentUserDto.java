package com.doruk.application.app.users.dto;

import com.doruk.application.dto.StoredObject;
import com.doruk.domain.shared.enums.MultiAuthType;
import com.doruk.domain.shared.enums.UserAccountStatus;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.annotation.Nullable;
import lombok.Builder;

import java.time.OffsetDateTime;
import java.util.List;

@Serdeable
@Builder
public record CurrentUserDto(
        String id,
        String username,
        String email,
        String phone,
        boolean emailVerified,
        boolean phoneVerified,
        MultiAuthType multiFactorAuth,
        UserAccountStatus status,
        OffsetDateTime createdAt,
        OffsetDateTime updatedAt,
        ProfileDto profile,
        List<String> roles,
        @Nullable StoredObject profileIcon
) {
}
