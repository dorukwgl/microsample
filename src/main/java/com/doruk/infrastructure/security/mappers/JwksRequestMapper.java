package com.doruk.infrastructure.security.mappers;

import com.doruk.infrastructure.apiclient.dto.JwksRequest;
import com.doruk.application.auth.dto.JwtRequest;
import io.micronaut.context.annotation.Mapper;

public interface JwksRequestMapper {
    @Mapper
    JwksRequest toJwksRequest(JwtRequest jwtRequest);
}
