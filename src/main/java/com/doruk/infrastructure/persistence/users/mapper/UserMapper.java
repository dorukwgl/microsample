package com.doruk.infrastructure.persistence.users.mapper;

import com.doruk.application.users.dto.UserResponseDto;
import com.doruk.infrastructure.persistence.entity.User;
import io.micronaut.context.annotation.Mapper;

public interface UserMapper {
    @Mapper
    UserResponseDto toResponseDto(User user);
}
