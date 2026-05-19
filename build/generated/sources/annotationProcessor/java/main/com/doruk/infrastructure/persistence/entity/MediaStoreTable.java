package com.doruk.infrastructure.persistence.entity;

import com.doruk.application.enums.ObjectVisibility;
import java.lang.Deprecated;
import java.lang.Long;
import java.lang.Override;
import java.lang.String;
import java.time.OffsetDateTime;
import org.babyfish.jimmer.internal.GeneratedBy;
import org.babyfish.jimmer.sql.ast.PropExpression;
import org.babyfish.jimmer.sql.ast.impl.base.BaseTableOwner;
import org.babyfish.jimmer.sql.ast.impl.table.TableImplementor;
import org.babyfish.jimmer.sql.ast.table.TableEx;
import org.babyfish.jimmer.sql.ast.table.spi.AbstractTypedTable;

@GeneratedBy(
        type = MediaStore.class
)
public class MediaStoreTable extends AbstractTypedTable<MediaStore> implements MediaStoreProps {
    public static final MediaStoreTable $ = new MediaStoreTable();

    public MediaStoreTable() {
        super(MediaStore.class);
    }

    public MediaStoreTable(AbstractTypedTable.DelayedOperation<MediaStore> delayedOperation) {
        super(MediaStore.class, delayedOperation);
    }

    public MediaStoreTable(TableImplementor<MediaStore> table) {
        super(table);
    }

    protected MediaStoreTable(MediaStoreTable base, String joinDisabledReason) {
        super(base, joinDisabledReason);
    }

    protected MediaStoreTable(MediaStoreTable base, BaseTableOwner baseTableOwner) {
        super(base, baseTableOwner);
    }

    @Override
    public PropExpression.Num<Long> id() {
        return __get(MediaStoreProps.ID.unwrap());
    }

    @Override
    public PropExpression.Str objectKey() {
        return __get(MediaStoreProps.OBJECT_KEY.unwrap());
    }

    @Override
    public PropExpression.Cmp<ObjectVisibility> visibility() {
        return __get(MediaStoreProps.VISIBILITY.unwrap());
    }

    @Override
    public PropExpression.Str mimeType() {
        return __get(MediaStoreProps.MIME_TYPE.unwrap());
    }

    @Override
    public PropExpression.Num<Long> size() {
        return __get(MediaStoreProps.SIZE.unwrap());
    }

    @Override
    public PropExpression.Tp<OffsetDateTime> createdAt() {
        return __get(MediaStoreProps.CREATED_AT.unwrap());
    }

    @Override
    public PropExpression.Tp<OffsetDateTime> deletedAt() {
        return __get(MediaStoreProps.DELETED_AT.unwrap());
    }

    @Override
    public MediaStoreTableEx asTableEx() {
        return new MediaStoreTableEx(this, (String)null);
    }

    @Override
    public MediaStoreTable __disableJoin(String reason) {
        return new MediaStoreTable(this, reason);
    }

    @Override
    public MediaStoreTable __baseTableOwner(BaseTableOwner baseTableOwner) {
        return new MediaStoreTable(this, baseTableOwner);
    }

    @GeneratedBy(
            type = MediaStore.class
    )
    public static class Remote extends AbstractTypedTable<MediaStore> {
        public Remote(AbstractTypedTable.DelayedOperation delayedOperation) {
            super(MediaStore.class, delayedOperation);
        }

        public Remote(TableImplementor<MediaStore> table) {
            super(table);
        }

        public Remote(Remote base, BaseTableOwner baseTableOwner) {
            super(base, baseTableOwner);
        }

        public PropExpression.Num<Long> id() {
            return (org.babyfish.jimmer.sql.ast.PropExpression.Num<java.lang.Long>)this.<Long>get(MediaStoreProps.ID.unwrap());
        }

        @Override
        @Deprecated
        public TableEx<MediaStore> asTableEx() {
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
