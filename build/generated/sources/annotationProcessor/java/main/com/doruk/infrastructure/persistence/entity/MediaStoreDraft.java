package com.doruk.infrastructure.persistence.entity;

import com.doruk.application.enums.ObjectVisibility;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import java.lang.CloneNotSupportedException;
import java.lang.Cloneable;
import java.lang.IllegalArgumentException;
import java.lang.IllegalStateException;
import java.lang.Long;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.lang.System;
import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.Objects;
import org.babyfish.jimmer.CircularReferenceException;
import org.babyfish.jimmer.Draft;
import org.babyfish.jimmer.DraftConsumer;
import org.babyfish.jimmer.ImmutableObjects;
import org.babyfish.jimmer.UnloadedException;
import org.babyfish.jimmer.internal.GeneratedBy;
import org.babyfish.jimmer.jackson.ImmutableModuleRequiredException;
import org.babyfish.jimmer.lang.OldChain;
import org.babyfish.jimmer.meta.ImmutablePropCategory;
import org.babyfish.jimmer.meta.ImmutableType;
import org.babyfish.jimmer.meta.PropId;
import org.babyfish.jimmer.runtime.DraftContext;
import org.babyfish.jimmer.runtime.DraftSpi;
import org.babyfish.jimmer.runtime.ImmutableSpi;
import org.babyfish.jimmer.runtime.Internal;
import org.babyfish.jimmer.runtime.Visibility;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

@GeneratedBy(
        type = MediaStore.class
)
public interface MediaStoreDraft extends MediaStore, Draft {
    MediaStoreDraft.Producer $ = Producer.INSTANCE;

    @OldChain
    MediaStoreDraft setId(long id);

    @OldChain
    MediaStoreDraft setObjectKey(String objectKey);

    @OldChain
    MediaStoreDraft setVisibility(ObjectVisibility visibility);

    @OldChain
    MediaStoreDraft setMimeType(String mimeType);

    @OldChain
    MediaStoreDraft setSize(long size);

    @OldChain
    MediaStoreDraft setCreatedAt(OffsetDateTime createdAt);

    @OldChain
    MediaStoreDraft setDeletedAt(OffsetDateTime deletedAt);

    @GeneratedBy(
            type = MediaStore.class
    )
    class Producer {
        static final Producer INSTANCE = new Producer();

        public static final int SLOT_ID = 0;

        public static final int SLOT_OBJECT_KEY = 1;

        public static final int SLOT_VISIBILITY = 2;

        public static final int SLOT_MIME_TYPE = 3;

        public static final int SLOT_SIZE = 4;

        public static final int SLOT_CREATED_AT = 5;

        public static final int SLOT_DELETED_AT = 6;

        public static final ImmutableType TYPE = ImmutableType
            .newBuilder(
                "0.10.6",
                MediaStore.class,
                Collections.emptyList(),
                (ctx, base) -> new DraftImpl(ctx, (MediaStore)base)
            )
            .id(SLOT_ID, "id", long.class)
            .key(SLOT_OBJECT_KEY, "objectKey", String.class, false)
            .add(SLOT_VISIBILITY, "visibility", ImmutablePropCategory.SCALAR, ObjectVisibility.class, false)
            .add(SLOT_MIME_TYPE, "mimeType", ImmutablePropCategory.SCALAR, String.class, false)
            .add(SLOT_SIZE, "size", ImmutablePropCategory.SCALAR, long.class, false)
            .add(SLOT_CREATED_AT, "createdAt", ImmutablePropCategory.SCALAR, OffsetDateTime.class, false)
            .logicalDeleted(SLOT_DELETED_AT, "deletedAt", OffsetDateTime.class, true)
            .build();

        private Producer() {
        }

        public MediaStore produce(DraftConsumer<MediaStoreDraft> block) {
            return (MediaStore)Internal.produce(TYPE, null, block);
        }

        public MediaStore produce(MediaStore base, DraftConsumer<MediaStoreDraft> block) {
            return (MediaStore)Internal.produce(TYPE, base, block);
        }

        public MediaStore produce(boolean resolveImmediately,
                DraftConsumer<MediaStoreDraft> block) {
            return (MediaStore)Internal.produce(TYPE, null, resolveImmediately, block);
        }

        public MediaStore produce(MediaStore base, boolean resolveImmediately,
                DraftConsumer<MediaStoreDraft> block) {
            return (MediaStore)Internal.produce(TYPE, base, resolveImmediately, block);
        }

        /**
         * Class, not interface, for free-marker
         */
        @GeneratedBy(
                type = MediaStore.class
        )
        @JsonPropertyOrder({"dummyPropForJacksonError__", "id", "objectKey", "visibility", "mimeType", "size", "createdAt", "deletedAt"})
        public abstract static class Implementor implements MediaStore, ImmutableSpi {
            @Override
            public final Object __get(PropId prop) {
                int __propIndex = prop.asIndex();
                switch (__propIndex) {
                    case -1:
                    		return __get(prop.asName());
                    case SLOT_ID:
                    		return (Long)id();
                    case SLOT_OBJECT_KEY:
                    		return objectKey();
                    case SLOT_VISIBILITY:
                    		return visibility();
                    case SLOT_MIME_TYPE:
                    		return mimeType();
                    case SLOT_SIZE:
                    		return (Long)size();
                    case SLOT_CREATED_AT:
                    		return createdAt();
                    case SLOT_DELETED_AT:
                    		return deletedAt();
                    default: throw new IllegalArgumentException("Illegal property name for \"com.doruk.infrastructure.persistence.entity.MediaStore\": \"" + prop + "\"");
                }
            }

            @Override
            public final Object __get(String prop) {
                switch (prop) {
                    case "id":
                    		return (Long)id();
                    case "objectKey":
                    		return objectKey();
                    case "visibility":
                    		return visibility();
                    case "mimeType":
                    		return mimeType();
                    case "size":
                    		return (Long)size();
                    case "createdAt":
                    		return createdAt();
                    case "deletedAt":
                    		return deletedAt();
                    default: throw new IllegalArgumentException("Illegal property name for \"com.doruk.infrastructure.persistence.entity.MediaStore\": \"" + prop + "\"");
                }
            }

            public final long getId() {
                return id();
            }

            public final String getObjectKey() {
                return objectKey();
            }

            public final ObjectVisibility getVisibility() {
                return visibility();
            }

            @NotNull
            public final String getMimeType() {
                return mimeType();
            }

            @NotNull
            public final long getSize() {
                return size();
            }

            @NotNull
            public final OffsetDateTime getCreatedAt() {
                return createdAt();
            }

            @Nullable
            public final OffsetDateTime getDeletedAt() {
                return deletedAt();
            }

            @Override
            public final ImmutableType __type() {
                return TYPE;
            }

            public final int getDummyPropForJacksonError__() {
                throw new ImmutableModuleRequiredException();
            }
        }

        @GeneratedBy(
                type = MediaStore.class
        )
        private static class Impl extends Implementor implements Cloneable, Serializable {
            private Visibility __visibility;

            long __idValue;

            boolean __idLoaded = false;

            String __objectKeyValue;

            ObjectVisibility __visibilityValue;

            String __mimeTypeValue;

            long __sizeValue;

            boolean __sizeLoaded = false;

            OffsetDateTime __createdAtValue;

            OffsetDateTime __deletedAtValue;

            boolean __deletedAtLoaded = false;

            @Override
            @JsonIgnore
            public long id() {
                if (!__idLoaded) {
                    throw new UnloadedException(MediaStore.class, "id");
                }
                return __idValue;
            }

            @Override
            @JsonIgnore
            public String objectKey() {
                if (__objectKeyValue == null) {
                    throw new UnloadedException(MediaStore.class, "objectKey");
                }
                return __objectKeyValue;
            }

            @Override
            @JsonIgnore
            public ObjectVisibility visibility() {
                if (__visibilityValue == null) {
                    throw new UnloadedException(MediaStore.class, "visibility");
                }
                return __visibilityValue;
            }

            @Override
            @JsonIgnore
            public String mimeType() {
                if (__mimeTypeValue == null) {
                    throw new UnloadedException(MediaStore.class, "mimeType");
                }
                return __mimeTypeValue;
            }

            @Override
            @JsonIgnore
            public long size() {
                if (!__sizeLoaded) {
                    throw new UnloadedException(MediaStore.class, "size");
                }
                return __sizeValue;
            }

            @Override
            @JsonIgnore
            public OffsetDateTime createdAt() {
                if (__createdAtValue == null) {
                    throw new UnloadedException(MediaStore.class, "createdAt");
                }
                return __createdAtValue;
            }

            @Override
            @JsonIgnore
            @Nullable
            public OffsetDateTime deletedAt() {
                if (!__deletedAtLoaded) {
                    throw new UnloadedException(MediaStore.class, "deletedAt");
                }
                return __deletedAtValue;
            }

            @Override
            public Impl clone() {
                try {
                    Impl copy = (Impl) super.clone();
                    Visibility originalVisibility = this.__visibility;
                    if (originalVisibility != null) {
                        Visibility newVisibility = Visibility.of(7);
                        for (int propId = 0; propId < 7; propId++) {
                            newVisibility.show(propId, originalVisibility.visible(propId));
                        }
                        copy.__visibility = newVisibility;
                    } else {
                        copy.__visibility = null;
                    }
                    return copy;
                } catch(CloneNotSupportedException ex) {
                    throw new AssertionError(ex);
                }
            }

            @Override
            public boolean __isLoaded(PropId prop) {
                int __propIndex = prop.asIndex();
                switch (__propIndex) {
                    case -1:
                    		return __isLoaded(prop.asName());
                    case SLOT_ID:
                    		return __idLoaded;
                    case SLOT_OBJECT_KEY:
                    		return __objectKeyValue != null;
                    case SLOT_VISIBILITY:
                    		return __visibilityValue != null;
                    case SLOT_MIME_TYPE:
                    		return __mimeTypeValue != null;
                    case SLOT_SIZE:
                    		return __sizeLoaded;
                    case SLOT_CREATED_AT:
                    		return __createdAtValue != null;
                    case SLOT_DELETED_AT:
                    		return __deletedAtLoaded;
                    default: throw new IllegalArgumentException("Illegal property name for \"com.doruk.infrastructure.persistence.entity.MediaStore\": \"" + prop + "\"");
                }
            }

            @Override
            public boolean __isLoaded(String prop) {
                switch (prop) {
                    case "id":
                    		return __idLoaded;
                    case "objectKey":
                    		return __objectKeyValue != null;
                    case "visibility":
                    		return __visibilityValue != null;
                    case "mimeType":
                    		return __mimeTypeValue != null;
                    case "size":
                    		return __sizeLoaded;
                    case "createdAt":
                    		return __createdAtValue != null;
                    case "deletedAt":
                    		return __deletedAtLoaded;
                    default: throw new IllegalArgumentException("Illegal property name for \"com.doruk.infrastructure.persistence.entity.MediaStore\": \"" + prop + "\"");
                }
            }

            @Override
            public boolean __isVisible(PropId prop) {
                if (__visibility == null) {
                    return true;
                }
                int __propIndex = prop.asIndex();
                switch (__propIndex) {
                    case -1:
                    		return __isVisible(prop.asName());
                    case SLOT_ID:
                    		return __visibility.visible(SLOT_ID);
                    case SLOT_OBJECT_KEY:
                    		return __visibility.visible(SLOT_OBJECT_KEY);
                    case SLOT_VISIBILITY:
                    		return __visibility.visible(SLOT_VISIBILITY);
                    case SLOT_MIME_TYPE:
                    		return __visibility.visible(SLOT_MIME_TYPE);
                    case SLOT_SIZE:
                    		return __visibility.visible(SLOT_SIZE);
                    case SLOT_CREATED_AT:
                    		return __visibility.visible(SLOT_CREATED_AT);
                    case SLOT_DELETED_AT:
                    		return __visibility.visible(SLOT_DELETED_AT);
                    default: return true;
                }
            }

            @Override
            public boolean __isVisible(String prop) {
                if (__visibility == null) {
                    return true;
                }
                switch (prop) {
                    case "id":
                    		return __visibility.visible(SLOT_ID);
                    case "objectKey":
                    		return __visibility.visible(SLOT_OBJECT_KEY);
                    case "visibility":
                    		return __visibility.visible(SLOT_VISIBILITY);
                    case "mimeType":
                    		return __visibility.visible(SLOT_MIME_TYPE);
                    case "size":
                    		return __visibility.visible(SLOT_SIZE);
                    case "createdAt":
                    		return __visibility.visible(SLOT_CREATED_AT);
                    case "deletedAt":
                    		return __visibility.visible(SLOT_DELETED_AT);
                    default: return true;
                }
            }

            @Override
            public int hashCode() {
                int hash = __visibility != null ? __visibility.hashCode() : 0;
                if (__idLoaded) {
                    hash = 31 * hash + Long.hashCode(__idValue);
                    // If entity-id is loaded, return directly
                    return hash;
                }
                if (__objectKeyValue != null) {
                    hash = 31 * hash + __objectKeyValue.hashCode();
                }
                if (__visibilityValue != null) {
                    hash = 31 * hash + __visibilityValue.hashCode();
                }
                if (__mimeTypeValue != null) {
                    hash = 31 * hash + __mimeTypeValue.hashCode();
                }
                if (__sizeLoaded) {
                    hash = 31 * hash + Long.hashCode(__sizeValue);
                }
                if (__createdAtValue != null) {
                    hash = 31 * hash + __createdAtValue.hashCode();
                }
                if (__deletedAtLoaded && __deletedAtValue != null) {
                    hash = 31 * hash + __deletedAtValue.hashCode();
                }
                return hash;
            }

            private int __shallowHashCode() {
                int hash = __visibility != null ? __visibility.hashCode() : 0;
                if (__idLoaded) {
                    hash = 31 * hash + Long.hashCode(__idValue);
                }
                if (__objectKeyValue != null) {
                    hash = 31 * hash + System.identityHashCode(__objectKeyValue);
                }
                if (__visibilityValue != null) {
                    hash = 31 * hash + System.identityHashCode(__visibilityValue);
                }
                if (__mimeTypeValue != null) {
                    hash = 31 * hash + System.identityHashCode(__mimeTypeValue);
                }
                if (__sizeLoaded) {
                    hash = 31 * hash + Long.hashCode(__sizeValue);
                }
                if (__createdAtValue != null) {
                    hash = 31 * hash + System.identityHashCode(__createdAtValue);
                }
                if (__deletedAtLoaded) {
                    hash = 31 * hash + System.identityHashCode(__deletedAtValue);
                }
                return hash;
            }

            @Override
            public int __hashCode(boolean shallow) {
                return shallow ? __shallowHashCode() : hashCode();
            }

            @Override
            public boolean equals(Object obj) {
                if (obj == null || !(obj instanceof Implementor)) {
                    return false;
                }
                Implementor __other = (Implementor)obj;
                if (__isVisible(PropId.byIndex(SLOT_ID)) != __other.__isVisible(PropId.byIndex(SLOT_ID))) {
                    return false;
                }
                boolean __idLoaded = this.__idLoaded;
                if (__idLoaded != __other.__isLoaded(PropId.byIndex(SLOT_ID))) {
                    return false;
                }
                if (__idLoaded) {
                    // If entity-id is loaded, return directly
                    return __idValue == __other.id();
                }
                if (__isVisible(PropId.byIndex(SLOT_OBJECT_KEY)) != __other.__isVisible(PropId.byIndex(SLOT_OBJECT_KEY))) {
                    return false;
                }
                boolean __objectKeyLoaded = __objectKeyValue != null;
                if (__objectKeyLoaded != __other.__isLoaded(PropId.byIndex(SLOT_OBJECT_KEY))) {
                    return false;
                }
                if (__objectKeyLoaded && !Objects.equals(__objectKeyValue, __other.objectKey())) {
                    return false;
                }
                if (__isVisible(PropId.byIndex(SLOT_VISIBILITY)) != __other.__isVisible(PropId.byIndex(SLOT_VISIBILITY))) {
                    return false;
                }
                boolean __visibilityLoaded = __visibilityValue != null;
                if (__visibilityLoaded != __other.__isLoaded(PropId.byIndex(SLOT_VISIBILITY))) {
                    return false;
                }
                if (__visibilityLoaded && !Objects.equals(__visibilityValue, __other.visibility())) {
                    return false;
                }
                if (__isVisible(PropId.byIndex(SLOT_MIME_TYPE)) != __other.__isVisible(PropId.byIndex(SLOT_MIME_TYPE))) {
                    return false;
                }
                boolean __mimeTypeLoaded = __mimeTypeValue != null;
                if (__mimeTypeLoaded != __other.__isLoaded(PropId.byIndex(SLOT_MIME_TYPE))) {
                    return false;
                }
                if (__mimeTypeLoaded && !Objects.equals(__mimeTypeValue, __other.mimeType())) {
                    return false;
                }
                if (__isVisible(PropId.byIndex(SLOT_SIZE)) != __other.__isVisible(PropId.byIndex(SLOT_SIZE))) {
                    return false;
                }
                boolean __sizeLoaded = this.__sizeLoaded;
                if (__sizeLoaded != __other.__isLoaded(PropId.byIndex(SLOT_SIZE))) {
                    return false;
                }
                if (__sizeLoaded && __sizeValue != __other.size()) {
                    return false;
                }
                if (__isVisible(PropId.byIndex(SLOT_CREATED_AT)) != __other.__isVisible(PropId.byIndex(SLOT_CREATED_AT))) {
                    return false;
                }
                boolean __createdAtLoaded = __createdAtValue != null;
                if (__createdAtLoaded != __other.__isLoaded(PropId.byIndex(SLOT_CREATED_AT))) {
                    return false;
                }
                if (__createdAtLoaded && !Objects.equals(__createdAtValue, __other.createdAt())) {
                    return false;
                }
                if (__isVisible(PropId.byIndex(SLOT_DELETED_AT)) != __other.__isVisible(PropId.byIndex(SLOT_DELETED_AT))) {
                    return false;
                }
                boolean __deletedAtLoaded = this.__deletedAtLoaded;
                if (__deletedAtLoaded != __other.__isLoaded(PropId.byIndex(SLOT_DELETED_AT))) {
                    return false;
                }
                if (__deletedAtLoaded && !Objects.equals(__deletedAtValue, __other.deletedAt())) {
                    return false;
                }
                return true;
            }

            private boolean __shallowEquals(Object obj) {
                if (obj == null || !(obj instanceof Implementor)) {
                    return false;
                }
                Implementor __other = (Implementor)obj;
                if (__isVisible(PropId.byIndex(SLOT_ID)) != __other.__isVisible(PropId.byIndex(SLOT_ID))) {
                    return false;
                }
                boolean __idLoaded = this.__idLoaded;
                if (__idLoaded != __other.__isLoaded(PropId.byIndex(SLOT_ID))) {
                    return false;
                }
                if (__idLoaded && __idValue != __other.id()) {
                    return false;
                }
                if (__isVisible(PropId.byIndex(SLOT_OBJECT_KEY)) != __other.__isVisible(PropId.byIndex(SLOT_OBJECT_KEY))) {
                    return false;
                }
                boolean __objectKeyLoaded = __objectKeyValue != null;
                if (__objectKeyLoaded != __other.__isLoaded(PropId.byIndex(SLOT_OBJECT_KEY))) {
                    return false;
                }
                if (__objectKeyLoaded && __objectKeyValue != __other.objectKey()) {
                    return false;
                }
                if (__isVisible(PropId.byIndex(SLOT_VISIBILITY)) != __other.__isVisible(PropId.byIndex(SLOT_VISIBILITY))) {
                    return false;
                }
                boolean __visibilityLoaded = __visibilityValue != null;
                if (__visibilityLoaded != __other.__isLoaded(PropId.byIndex(SLOT_VISIBILITY))) {
                    return false;
                }
                if (__visibilityLoaded && __visibilityValue != __other.visibility()) {
                    return false;
                }
                if (__isVisible(PropId.byIndex(SLOT_MIME_TYPE)) != __other.__isVisible(PropId.byIndex(SLOT_MIME_TYPE))) {
                    return false;
                }
                boolean __mimeTypeLoaded = __mimeTypeValue != null;
                if (__mimeTypeLoaded != __other.__isLoaded(PropId.byIndex(SLOT_MIME_TYPE))) {
                    return false;
                }
                if (__mimeTypeLoaded && __mimeTypeValue != __other.mimeType()) {
                    return false;
                }
                if (__isVisible(PropId.byIndex(SLOT_SIZE)) != __other.__isVisible(PropId.byIndex(SLOT_SIZE))) {
                    return false;
                }
                boolean __sizeLoaded = this.__sizeLoaded;
                if (__sizeLoaded != __other.__isLoaded(PropId.byIndex(SLOT_SIZE))) {
                    return false;
                }
                if (__sizeLoaded && __sizeValue != __other.size()) {
                    return false;
                }
                if (__isVisible(PropId.byIndex(SLOT_CREATED_AT)) != __other.__isVisible(PropId.byIndex(SLOT_CREATED_AT))) {
                    return false;
                }
                boolean __createdAtLoaded = __createdAtValue != null;
                if (__createdAtLoaded != __other.__isLoaded(PropId.byIndex(SLOT_CREATED_AT))) {
                    return false;
                }
                if (__createdAtLoaded && __createdAtValue != __other.createdAt()) {
                    return false;
                }
                if (__isVisible(PropId.byIndex(SLOT_DELETED_AT)) != __other.__isVisible(PropId.byIndex(SLOT_DELETED_AT))) {
                    return false;
                }
                boolean __deletedAtLoaded = this.__deletedAtLoaded;
                if (__deletedAtLoaded != __other.__isLoaded(PropId.byIndex(SLOT_DELETED_AT))) {
                    return false;
                }
                if (__deletedAtLoaded && __deletedAtValue != __other.deletedAt()) {
                    return false;
                }
                return true;
            }

            @Override
            public boolean __equals(Object obj, boolean shallow) {
                return shallow ? __shallowEquals(obj) : equals(obj);
            }

            @Override
            public String toString() {
                return ImmutableObjects.toString(this);
            }
        }

        @GeneratedBy(
                type = MediaStore.class
        )
        private static class DraftImpl extends Implementor implements DraftSpi, MediaStoreDraft {
            private DraftContext __ctx;

            private Impl __base;

            private Impl __modified;

            private boolean __resolving;

            private MediaStore __resolved;

            DraftImpl(DraftContext ctx, MediaStore base) {
                __ctx = ctx;
                if (base != null) {
                    __base = (Impl)base;
                }
                else {
                    __modified = new Impl();
                }
            }

            @Override
            public boolean __isLoaded(PropId prop) {
                return (__modified!= null ? __modified : __base).__isLoaded(prop);
            }

            @Override
            public boolean __isLoaded(String prop) {
                return (__modified!= null ? __modified : __base).__isLoaded(prop);
            }

            @Override
            public boolean __isVisible(PropId prop) {
                return (__modified!= null ? __modified : __base).__isVisible(prop);
            }

            @Override
            public boolean __isVisible(String prop) {
                return (__modified!= null ? __modified : __base).__isVisible(prop);
            }

            @Override
            public int hashCode() {
                return (__modified!= null ? __modified : __base).hashCode();
            }

            @Override
            public int __hashCode(boolean shallow) {
                return (__modified!= null ? __modified : __base).__hashCode(shallow);
            }

            @Override
            public boolean equals(Object obj) {
                return (__modified!= null ? __modified : __base).equals(obj);
            }

            @Override
            public boolean __equals(Object obj, boolean shallow) {
                return (__modified!= null ? __modified : __base).__equals(obj, shallow);
            }

            @Override
            public String toString() {
                return ImmutableObjects.toString(this);
            }

            @Override
            @JsonIgnore
            public long id() {
                return (__modified!= null ? __modified : __base).id();
            }

            @Override
            public MediaStoreDraft setId(long id) {
                if (__resolved != null) {
                    throw new IllegalStateException("The current draft has been resolved so it cannot be modified");
                }
                Impl __tmpModified = __modified();
                __tmpModified.__idValue = id;
                __tmpModified.__idLoaded = true;
                return this;
            }

            @Override
            @JsonIgnore
            public String objectKey() {
                return (__modified!= null ? __modified : __base).objectKey();
            }

            @Override
            public MediaStoreDraft setObjectKey(String objectKey) {
                if (__resolved != null) {
                    throw new IllegalStateException("The current draft has been resolved so it cannot be modified");
                }
                if (objectKey == null) {
                    throw new IllegalArgumentException(
                        "'objectKey' cannot be null, please specify non-null value or use nullable annotation to decorate this property"
                    );
                }
                Impl __tmpModified = __modified();
                __tmpModified.__objectKeyValue = objectKey;
                return this;
            }

            @Override
            @JsonIgnore
            public ObjectVisibility visibility() {
                return (__modified!= null ? __modified : __base).visibility();
            }

            @Override
            public MediaStoreDraft setVisibility(ObjectVisibility visibility) {
                if (__resolved != null) {
                    throw new IllegalStateException("The current draft has been resolved so it cannot be modified");
                }
                if (visibility == null) {
                    throw new IllegalArgumentException(
                        "'visibility' cannot be null, please specify non-null value or use nullable annotation to decorate this property"
                    );
                }
                Impl __tmpModified = __modified();
                __tmpModified.__visibilityValue = visibility;
                return this;
            }

            @Override
            @JsonIgnore
            public String mimeType() {
                return (__modified!= null ? __modified : __base).mimeType();
            }

            @Override
            public MediaStoreDraft setMimeType(String mimeType) {
                if (__resolved != null) {
                    throw new IllegalStateException("The current draft has been resolved so it cannot be modified");
                }
                if (mimeType == null) {
                    throw new IllegalArgumentException(
                        "'mimeType' cannot be null, please specify non-null value or use nullable annotation to decorate this property"
                    );
                }
                Impl __tmpModified = __modified();
                __tmpModified.__mimeTypeValue = mimeType;
                return this;
            }

            @Override
            @JsonIgnore
            public long size() {
                return (__modified!= null ? __modified : __base).size();
            }

            @Override
            public MediaStoreDraft setSize(long size) {
                if (__resolved != null) {
                    throw new IllegalStateException("The current draft has been resolved so it cannot be modified");
                }
                Impl __tmpModified = __modified();
                __tmpModified.__sizeValue = size;
                __tmpModified.__sizeLoaded = true;
                return this;
            }

            @Override
            @JsonIgnore
            public OffsetDateTime createdAt() {
                return (__modified!= null ? __modified : __base).createdAt();
            }

            @Override
            public MediaStoreDraft setCreatedAt(OffsetDateTime createdAt) {
                if (__resolved != null) {
                    throw new IllegalStateException("The current draft has been resolved so it cannot be modified");
                }
                if (createdAt == null) {
                    throw new IllegalArgumentException(
                        "'createdAt' cannot be null, please specify non-null value or use nullable annotation to decorate this property"
                    );
                }
                Impl __tmpModified = __modified();
                __tmpModified.__createdAtValue = createdAt;
                return this;
            }

            @Override
            @JsonIgnore
            @Nullable
            public OffsetDateTime deletedAt() {
                return (__modified!= null ? __modified : __base).deletedAt();
            }

            @Override
            public MediaStoreDraft setDeletedAt(OffsetDateTime deletedAt) {
                if (__resolved != null) {
                    throw new IllegalStateException("The current draft has been resolved so it cannot be modified");
                }
                Impl __tmpModified = __modified();
                __tmpModified.__deletedAtValue = deletedAt;
                __tmpModified.__deletedAtLoaded = true;
                return this;
            }

            @SuppressWarnings("all")
            @Override
            public void __set(PropId prop, Object value) {
                int __propIndex = prop.asIndex();
                switch (__propIndex) {
                    case -1:
                    		__set(prop.asName(), value);
                    return;
                    case SLOT_ID:
                    		if (value == null) throw new IllegalArgumentException("'id' cannot be null, if you want to set null, please use any annotation whose simple name is \"Nullable\" to decorate the property");
                            setId((Long)value);
                            break;
                    case SLOT_OBJECT_KEY:
                    		setObjectKey((String)value);break;
                    case SLOT_VISIBILITY:
                    		setVisibility((ObjectVisibility)value);break;
                    case SLOT_MIME_TYPE:
                    		setMimeType((String)value);break;
                    case SLOT_SIZE:
                    		if (value == null) throw new IllegalArgumentException("'size' cannot be null, if you want to set null, please use any annotation whose simple name is \"Nullable\" to decorate the property");
                            setSize((Long)value);
                            break;
                    case SLOT_CREATED_AT:
                    		setCreatedAt((OffsetDateTime)value);break;
                    case SLOT_DELETED_AT:
                    		setDeletedAt((OffsetDateTime)value);break;
                    default: throw new IllegalArgumentException("Illegal property id for \"com.doruk.infrastructure.persistence.entity.MediaStore\": \"" + prop + "\"");
                }
            }

            @SuppressWarnings("all")
            @Override
            public void __set(String prop, Object value) {
                switch (prop) {
                    case "id":
                    		if (value == null) throw new IllegalArgumentException("'id' cannot be null, if you want to set null, please use any annotation whose simple name is \"Nullable\" to decorate the property");
                            setId((Long)value);
                            break;
                    case "objectKey":
                    		setObjectKey((String)value);break;
                    case "visibility":
                    		setVisibility((ObjectVisibility)value);break;
                    case "mimeType":
                    		setMimeType((String)value);break;
                    case "size":
                    		if (value == null) throw new IllegalArgumentException("'size' cannot be null, if you want to set null, please use any annotation whose simple name is \"Nullable\" to decorate the property");
                            setSize((Long)value);
                            break;
                    case "createdAt":
                    		setCreatedAt((OffsetDateTime)value);break;
                    case "deletedAt":
                    		setDeletedAt((OffsetDateTime)value);break;
                    default: throw new IllegalArgumentException("Illegal property name for \"com.doruk.infrastructure.persistence.entity.MediaStore\": \"" + prop + "\"");
                }
            }

            @Override
            public void __show(PropId prop, boolean visible) {
                if (__resolved != null) {
                    throw new IllegalStateException("The current draft has been resolved so it cannot be modified");
                }
                Visibility __visibility = (__modified!= null ? __modified : __base).__visibility;
                if (__visibility == null) {
                    if (visible) {
                        return;
                    }
                    __modified().__visibility = __visibility = Visibility.of(7);
                }
                int __propIndex = prop.asIndex();
                switch (__propIndex) {
                    case -1:
                    		__show(prop.asName(), visible);
                    return;
                    case SLOT_ID:
                    		__visibility.show(SLOT_ID, visible);break;
                    case SLOT_OBJECT_KEY:
                    		__visibility.show(SLOT_OBJECT_KEY, visible);break;
                    case SLOT_VISIBILITY:
                    		__visibility.show(SLOT_VISIBILITY, visible);break;
                    case SLOT_MIME_TYPE:
                    		__visibility.show(SLOT_MIME_TYPE, visible);break;
                    case SLOT_SIZE:
                    		__visibility.show(SLOT_SIZE, visible);break;
                    case SLOT_CREATED_AT:
                    		__visibility.show(SLOT_CREATED_AT, visible);break;
                    case SLOT_DELETED_AT:
                    		__visibility.show(SLOT_DELETED_AT, visible);break;
                    default: throw new IllegalArgumentException(
                                "Illegal property id for \"com.doruk.infrastructure.persistence.entity.MediaStore\": \"" + 
                                prop + 
                                "\",it does not exists"
                            );
                }
            }

            @Override
            public void __show(String prop, boolean visible) {
                if (__resolved != null) {
                    throw new IllegalStateException("The current draft has been resolved so it cannot be modified");
                }
                Visibility __visibility = (__modified!= null ? __modified : __base).__visibility;
                if (__visibility == null) {
                    if (visible) {
                        return;
                    }
                    __modified().__visibility = __visibility = Visibility.of(7);
                }
                switch (prop) {
                    case "id":
                    		__visibility.show(SLOT_ID, visible);break;
                    case "objectKey":
                    		__visibility.show(SLOT_OBJECT_KEY, visible);break;
                    case "visibility":
                    		__visibility.show(SLOT_VISIBILITY, visible);break;
                    case "mimeType":
                    		__visibility.show(SLOT_MIME_TYPE, visible);break;
                    case "size":
                    		__visibility.show(SLOT_SIZE, visible);break;
                    case "createdAt":
                    		__visibility.show(SLOT_CREATED_AT, visible);break;
                    case "deletedAt":
                    		__visibility.show(SLOT_DELETED_AT, visible);break;
                    default: throw new IllegalArgumentException(
                                "Illegal property name for \"com.doruk.infrastructure.persistence.entity.MediaStore\": \"" + 
                                prop + 
                                "\",it does not exists"
                            );
                }
            }

            @Override
            public void __unload(PropId prop) {
                if (__resolved != null) {
                    throw new IllegalStateException("The current draft has been resolved so it cannot be modified");
                }
                int __propIndex = prop.asIndex();
                switch (__propIndex) {
                    case -1:
                    		__unload(prop.asName());
                    return;
                    case SLOT_ID:
                    		__modified().__idValue = 0;
                    __modified().__idLoaded = false;break;
                    case SLOT_OBJECT_KEY:
                    		__modified().__objectKeyValue = null;break;
                    case SLOT_VISIBILITY:
                    		__modified().__visibilityValue = null;break;
                    case SLOT_MIME_TYPE:
                    		__modified().__mimeTypeValue = null;break;
                    case SLOT_SIZE:
                    		__modified().__sizeValue = 0;
                    __modified().__sizeLoaded = false;break;
                    case SLOT_CREATED_AT:
                    		__modified().__createdAtValue = null;break;
                    case SLOT_DELETED_AT:
                    		__modified().__deletedAtValue = null;
                    __modified().__deletedAtLoaded = false;break;
                    default: throw new IllegalArgumentException("Illegal property id for \"com.doruk.infrastructure.persistence.entity.MediaStore\": \"" + prop + "\", it does not exist or its loaded state is not controllable");
                }
            }

            @Override
            public void __unload(String prop) {
                if (__resolved != null) {
                    throw new IllegalStateException("The current draft has been resolved so it cannot be modified");
                }
                switch (prop) {
                    case "id":
                    		__modified().__idValue = 0;
                    __modified().__idLoaded = false;break;
                    case "objectKey":
                    		__modified().__objectKeyValue = null;break;
                    case "visibility":
                    		__modified().__visibilityValue = null;break;
                    case "mimeType":
                    		__modified().__mimeTypeValue = null;break;
                    case "size":
                    		__modified().__sizeValue = 0;
                    __modified().__sizeLoaded = false;break;
                    case "createdAt":
                    		__modified().__createdAtValue = null;break;
                    case "deletedAt":
                    		__modified().__deletedAtValue = null;
                    __modified().__deletedAtLoaded = false;break;
                    default: throw new IllegalArgumentException("Illegal property name for \"com.doruk.infrastructure.persistence.entity.MediaStore\": \"" + prop + "\", it does not exist or its loaded state is not controllable");
                }
            }

            @Override
            public DraftContext __draftContext() {
                return __ctx;
            }

            @Override
            public Object __resolve() {
                if (__resolved != null) {
                    return __resolved;
                }
                if (__resolving) {
                    throw new CircularReferenceException();
                }
                __resolving = true;
                try {
                    Implementor base = __base;
                    Impl __tmpModified = __modified;
                    if (__base != null && __tmpModified == null) {
                        this.__resolved = base;
                        return base;
                    }
                    this.__resolved = __tmpModified;
                    return __tmpModified;
                }
                finally {
                    __resolving = false;
                }
            }

            @Override
            public boolean __isResolved() {
                return __resolved != null;
            }

            Impl __modified() {
                Impl __tmpModified = __modified;
                if (__tmpModified == null) {
                    __tmpModified = __base.clone();
                    __modified = __tmpModified;
                }
                return __tmpModified;
            }
        }
    }

    @GeneratedBy(
            type = MediaStore.class
    )
    class Builder {
        private final Producer.DraftImpl __draft;

        public Builder() {
            this(null);
        }

        public Builder(@Nullable MediaStore base) {
            __draft = new Producer.DraftImpl(null, base);
        }

        public Builder id(@NonNull Long id) {
            if (id != null) {
                __draft.setId(id);
            }
            return this;
        }

        public Builder objectKey(@NonNull String objectKey) {
            if (objectKey != null) {
                __draft.setObjectKey(objectKey);
            }
            return this;
        }

        public Builder visibility(@NonNull ObjectVisibility visibility) {
            if (visibility != null) {
                __draft.setVisibility(visibility);
            }
            return this;
        }

        @NotNull
        public Builder mimeType(@NonNull String mimeType) {
            if (mimeType != null) {
                __draft.setMimeType(mimeType);
            }
            return this;
        }

        @NotNull
        public Builder size(@NonNull Long size) {
            if (size != null) {
                __draft.setSize(size);
            }
            return this;
        }

        @NotNull
        public Builder createdAt(@NonNull OffsetDateTime createdAt) {
            if (createdAt != null) {
                __draft.setCreatedAt(createdAt);
            }
            return this;
        }

        public Builder deletedAt(@Nullable OffsetDateTime deletedAt) {
            __draft.setDeletedAt(deletedAt);
            return this;
        }

        public MediaStore build() {
            return (MediaStore)__draft.__modified();
        }
    }
}
