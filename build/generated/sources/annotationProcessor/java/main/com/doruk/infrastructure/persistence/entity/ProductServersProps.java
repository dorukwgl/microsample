package com.doruk.infrastructure.persistence.entity;

import java.lang.String;
import java.time.OffsetDateTime;
import java.util.UUID;
import org.babyfish.jimmer.internal.GeneratedBy;
import org.babyfish.jimmer.meta.ImmutableType;
import org.babyfish.jimmer.meta.TypedProp;
import org.babyfish.jimmer.sql.ast.PropExpression;
import org.babyfish.jimmer.sql.ast.Selection;
import org.babyfish.jimmer.sql.ast.table.Props;
import org.babyfish.jimmer.sql.ast.table.PropsFor;

@GeneratedBy(
        type = ProductServers.class
)
@PropsFor(ProductServers.class)
public interface ProductServersProps extends Props, Selection<ProductServers> {
    TypedProp.Scalar<ProductServers, UUID> ID = 
        TypedProp.scalar(ImmutableType.get(ProductServers.class).getProp("id"));

    TypedProp.Scalar<ProductServers, String> NAME = 
        TypedProp.scalar(ImmutableType.get(ProductServers.class).getProp("name"));

    TypedProp.Scalar<ProductServers, String> SKU_ID = 
        TypedProp.scalar(ImmutableType.get(ProductServers.class).getProp("skuId"));

    TypedProp.Scalar<ProductServers, String> HOST_URL = 
        TypedProp.scalar(ImmutableType.get(ProductServers.class).getProp("hostUrl"));

    TypedProp.Scalar<ProductServers, String> PUBLIC_KEY = 
        TypedProp.scalar(ImmutableType.get(ProductServers.class).getProp("publicKey"));

    TypedProp.Scalar<ProductServers, OffsetDateTime> CREATED_AT = 
        TypedProp.scalar(ImmutableType.get(ProductServers.class).getProp("createdAt"));

    TypedProp.Scalar<ProductServers, OffsetDateTime> UPDATED_AT = 
        TypedProp.scalar(ImmutableType.get(ProductServers.class).getProp("updatedAt"));

    PropExpression.Cmp<UUID> id();

    PropExpression.Str name();

    PropExpression.Str skuId();

    PropExpression.Str hostUrl();

    PropExpression.Str publicKey();

    PropExpression.Tp<OffsetDateTime> createdAt();

    PropExpression.Tp<OffsetDateTime> updatedAt();
}
