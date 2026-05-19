package com.doruk.infrastructure.persistence.entity;

import com.doruk.application.enums.ObjectVisibility;
import java.lang.Long;
import java.lang.String;
import java.time.OffsetDateTime;
import org.babyfish.jimmer.internal.GeneratedBy;
import org.babyfish.jimmer.meta.ImmutableType;
import org.babyfish.jimmer.meta.TypedProp;
import org.babyfish.jimmer.sql.ast.PropExpression;
import org.babyfish.jimmer.sql.ast.Selection;
import org.babyfish.jimmer.sql.ast.table.Props;
import org.babyfish.jimmer.sql.ast.table.PropsFor;

@GeneratedBy(
        type = MediaStore.class
)
@PropsFor(MediaStore.class)
public interface MediaStoreProps extends Props, Selection<MediaStore> {
    TypedProp.Scalar<MediaStore, Long> ID = 
        TypedProp.scalar(ImmutableType.get(MediaStore.class).getProp("id"));

    TypedProp.Scalar<MediaStore, String> OBJECT_KEY = 
        TypedProp.scalar(ImmutableType.get(MediaStore.class).getProp("objectKey"));

    TypedProp.Scalar<MediaStore, ObjectVisibility> VISIBILITY = 
        TypedProp.scalar(ImmutableType.get(MediaStore.class).getProp("visibility"));

    TypedProp.Scalar<MediaStore, String> MIME_TYPE = 
        TypedProp.scalar(ImmutableType.get(MediaStore.class).getProp("mimeType"));

    TypedProp.Scalar<MediaStore, Long> SIZE = 
        TypedProp.scalar(ImmutableType.get(MediaStore.class).getProp("size"));

    TypedProp.Scalar<MediaStore, OffsetDateTime> CREATED_AT = 
        TypedProp.scalar(ImmutableType.get(MediaStore.class).getProp("createdAt"));

    TypedProp.Scalar<MediaStore, OffsetDateTime> DELETED_AT = 
        TypedProp.scalar(ImmutableType.get(MediaStore.class).getProp("deletedAt"));

    PropExpression.Num<Long> id();

    PropExpression.Str objectKey();

    PropExpression.Cmp<ObjectVisibility> visibility();

    PropExpression.Str mimeType();

    PropExpression.Num<Long> size();

    PropExpression.Tp<OffsetDateTime> createdAt();

    PropExpression.Tp<OffsetDateTime> deletedAt();
}
