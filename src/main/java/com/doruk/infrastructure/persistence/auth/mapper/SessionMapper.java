package com.doruk.infrastructure.persistence.auth.mapper;

import com.doruk.application.auth.dto.SessionDto;
import com.doruk.infrastructure.persistence.entity.Session;
import io.micronaut.context.annotation.Mapper;

public interface SessionMapper {
    @Mapper
    SessionDto toDto(Session entity);
}
