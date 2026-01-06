package com.doruk.infrastructure.persistence.users.mapper;

import com.doruk.application.users.dto.CurrentUserDto;
import com.doruk.application.users.dto.UserResponseDto;
import com.doruk.infrastructure.persistence.entity.User;
import jakarta.inject.Singleton;


@Singleton
public class UserMapper {
    public UserResponseDto toResponseDto(User user) {
        return UserResponseDto.builder()
                .id(user.id())
                .email(user.email())
                .username(user.username())
                .createdAt(user.createdAt())
                .updatedAt(user.updatedAt())
                .emailVerified(user.emailVerified())
                .phoneVerified(user.phoneVerified())
                .phone(user.phone())
                .status(user.status())
                .multiFactorAuth(user.multiFactorAuth())
                .build();
    }

    public CurrentUserDto toCurrentUserDto(User user) {
        throw new RuntimeException("user mapper: this method isn't implemented yet");
    }
}
