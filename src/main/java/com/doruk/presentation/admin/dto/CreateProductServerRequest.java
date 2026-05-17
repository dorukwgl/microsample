package com.doruk.presentation.admin.dto;

import io.micronaut.serde.annotation.Serdeable;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.constraints.NotBlank;

@Serdeable
public record CreateProductServerRequest(
        @NotBlank
        @Parameter(description = "Human-readable name for the product server")
        String name,

        @NotBlank
        @Parameter(description = "SKU identifier this product server is associated with")
        String skuId,

        @NotBlank
        @Parameter(description = "Base URL where webhooks and API calls are sent")
        String hostUrl,

        @NotBlank
        @Parameter(description = "Product server's public key for verifying inbound requests")
        String publicKey
) {
}
