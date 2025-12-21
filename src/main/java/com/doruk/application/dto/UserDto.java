package com.doruk.application.dto;

import com.doruk.domain.shared.enums.MultiAuthType;
import com.doruk.domain.shared.enums.UserAccountStatus;
import io.micronaut.serde.annotation.Serdeable;

import java.time.LocalDateTime;
import java.util.UUID;

@Serdeable
public record UserDto(
        UUID id,
        String username,
        String email,
        String phone,
        UserAccountStatus status,
        boolean isEmailVerified,
        boolean isPhoneVerified,
        MultiAuthType multiFactorAuth,
        LocalDateTime elevatedUntil
) {
}
