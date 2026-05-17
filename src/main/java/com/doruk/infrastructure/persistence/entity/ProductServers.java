package com.doruk.infrastructure.persistence.entity;

import io.micronaut.core.annotation.Introspected;
import org.babyfish.jimmer.sql.*;
import org.jetbrains.annotations.Nullable;

import java.time.OffsetDateTime;
import java.util.UUID;

@Introspected
@Entity
@Table(name = "product_servers")
public interface ProductServers {
    @Id
    UUID id();

    String name();

    @Key
    @Column(name = "sku_id")
    String skuId();

    @Column(name = "host_url")
    String hostUrl();

    @Column(name = "public_key")
    String publicKey();

    @Column(name = "created_at")
    OffsetDateTime createdAt();

    @Column(name = "updated_at")
    OffsetDateTime updatedAt();
}
