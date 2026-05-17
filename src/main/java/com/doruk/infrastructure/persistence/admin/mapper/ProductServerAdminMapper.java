package com.doruk.infrastructure.persistence.admin.mapper;

import com.doruk.application.app.admin.dto.ProductServerResponse;
import com.doruk.infrastructure.persistence.entity.ProductServers;
import jakarta.inject.Singleton;

@Singleton
public class ProductServerAdminMapper {
    public ProductServerResponse toResponse(ProductServers entity) {
        return ProductServerResponse.builder()
                .id(entity.id())
                .name(entity.name())
                .skuId(entity.skuId())
                .hostUrl(entity.hostUrl())
                .publicKey(entity.publicKey())
                .createdAt(entity.createdAt())
                .updatedAt(entity.updatedAt())
                .build();
    }
}
