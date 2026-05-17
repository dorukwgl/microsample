package com.doruk.application.app.admin.dto;

import io.micronaut.core.annotation.Introspected;

@Introspected
public record CreateProductServerCmd(
        String name,
        String skuId,
        String hostUrl,
        String publicKey
) {
}
