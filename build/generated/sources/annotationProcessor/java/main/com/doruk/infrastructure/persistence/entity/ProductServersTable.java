package com.doruk.infrastructure.persistence.entity;

import java.lang.Deprecated;
import java.lang.Override;
import java.lang.String;
import java.time.OffsetDateTime;
import java.util.UUID;
import org.babyfish.jimmer.internal.GeneratedBy;
import org.babyfish.jimmer.sql.ast.PropExpression;
import org.babyfish.jimmer.sql.ast.impl.base.BaseTableOwner;
import org.babyfish.jimmer.sql.ast.impl.table.TableImplementor;
import org.babyfish.jimmer.sql.ast.table.TableEx;
import org.babyfish.jimmer.sql.ast.table.spi.AbstractTypedTable;

@GeneratedBy(
        type = ProductServers.class
)
public class ProductServersTable extends AbstractTypedTable<ProductServers> implements ProductServersProps {
    public static final ProductServersTable $ = new ProductServersTable();

    public ProductServersTable() {
        super(ProductServers.class);
    }

    public ProductServersTable(
            AbstractTypedTable.DelayedOperation<ProductServers> delayedOperation) {
        super(ProductServers.class, delayedOperation);
    }

    public ProductServersTable(TableImplementor<ProductServers> table) {
        super(table);
    }

    protected ProductServersTable(ProductServersTable base, String joinDisabledReason) {
        super(base, joinDisabledReason);
    }

    protected ProductServersTable(ProductServersTable base, BaseTableOwner baseTableOwner) {
        super(base, baseTableOwner);
    }

    @Override
    public PropExpression.Cmp<UUID> id() {
        return __get(ProductServersProps.ID.unwrap());
    }

    @Override
    public PropExpression.Str name() {
        return __get(ProductServersProps.NAME.unwrap());
    }

    @Override
    public PropExpression.Str skuId() {
        return __get(ProductServersProps.SKU_ID.unwrap());
    }

    @Override
    public PropExpression.Str hostUrl() {
        return __get(ProductServersProps.HOST_URL.unwrap());
    }

    @Override
    public PropExpression.Str publicKey() {
        return __get(ProductServersProps.PUBLIC_KEY.unwrap());
    }

    @Override
    public PropExpression.Tp<OffsetDateTime> createdAt() {
        return __get(ProductServersProps.CREATED_AT.unwrap());
    }

    @Override
    public PropExpression.Tp<OffsetDateTime> updatedAt() {
        return __get(ProductServersProps.UPDATED_AT.unwrap());
    }

    @Override
    public ProductServersTableEx asTableEx() {
        return new ProductServersTableEx(this, (String)null);
    }

    @Override
    public ProductServersTable __disableJoin(String reason) {
        return new ProductServersTable(this, reason);
    }

    @Override
    public ProductServersTable __baseTableOwner(BaseTableOwner baseTableOwner) {
        return new ProductServersTable(this, baseTableOwner);
    }

    @GeneratedBy(
            type = ProductServers.class
    )
    public static class Remote extends AbstractTypedTable<ProductServers> {
        public Remote(AbstractTypedTable.DelayedOperation delayedOperation) {
            super(ProductServers.class, delayedOperation);
        }

        public Remote(TableImplementor<ProductServers> table) {
            super(table);
        }

        public Remote(Remote base, BaseTableOwner baseTableOwner) {
            super(base, baseTableOwner);
        }

        public PropExpression.Cmp<UUID> id() {
            return (org.babyfish.jimmer.sql.ast.PropExpression.Cmp<java.util.UUID>)this.<UUID>get(ProductServersProps.ID.unwrap());
        }

        @Override
        @Deprecated
        public TableEx<ProductServers> asTableEx() {
            throw new UnsupportedOperationException();
        }

        @Override
        public Remote __disableJoin(String reason) {
            return this;
        }

        @Override
        public Remote __baseTableOwner(BaseTableOwner baseTableOwner) {
            return new Remote(this, baseTableOwner);
        }
    }
}
