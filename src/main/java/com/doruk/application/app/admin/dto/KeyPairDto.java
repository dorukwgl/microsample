package com.doruk.application.app.admin.dto;

import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public record KeyPairDto(String privateKey, String publicKey) {}
