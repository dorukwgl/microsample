package com.doruk.infrastructure.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.io.Serializable;
import java.lang.CloneNotSupportedException;
import java.lang.Cloneable;
import java.lang.IllegalStateException;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.lang.System;
import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.Objects;
import java.util.UUID;
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
        type = ProductServers.class
)
public interface ProductServersDraft extends ProductServers, Draft {
    ProductServersDraft.Producer $ = Producer.INSTANCE;

    @OldChain
    ProductServersDraft setId(UUID id);

    @OldChain
    ProductServersDraft setName(String name);

    @OldChain
    ProductServersDraft setSkuId(String skuId);

    @OldChain
    ProductServersDraft setHostUrl(String hostUrl);

    @OldChain
    ProductServersDraft setPublicKey(String publicKey);

    @OldChain
    ProductServersDraft setCreatedAt(OffsetDateTime createdAt);

    @OldChain
    ProductServersDraft setUpdatedAt(OffsetDateTime updatedAt);

    @GeneratedBy(
            type = ProductServers.class
    )
    class Producer {
        static final Producer INSTANCE = new Producer();

        public static final int SLOT_ID = 0;

        public static final int SLOT_NAME = 1;

        public static final int SLOT_SKU_ID = 2;

        public static final int SLOT_HOST_URL = 3;

        public static final int SLOT_PUBLIC_KEY = 4;

        public static final int SLOT_CREATED_AT = 5;

        public static final int SLOT_UPDATED_AT = 6;

        public static final ImmutableType TYPE = ImmutableType
            .newBuilder(
                "0.10.7",
                ProductServers.class,
                Collections.emptyList(),
                (ctx, base) -> new DraftImpl(ctx, (ProductServers)base)
            )
            .id(SLOT_ID, "id", UUID.class)
            .add(SLOT_NAME, "name", ImmutablePropCategory.SCALAR, String.class, false)
            .key(SLOT_SKU_ID, "skuId", String.class, false)
            .add(SLOT_HOST_URL, "hostUrl", ImmutablePropCategory.SCALAR, String.class, false)
            .add(SLOT_PUBLIC_KEY, "publicKey", ImmutablePropCategory.SCALAR, String.class, false)
            .add(SLOT_CREATED_AT, "createdAt", ImmutablePropCategory.SCALAR, OffsetDateTime.class, false)
            .add(SLOT_UPDATED_AT, "updatedAt", ImmutablePropCategory.SCALAR, OffsetDateTime.class, false)
            .build();

        private Producer() {
        }

        public ProductServers produce(DraftConsumer<ProductServersDraft> block) {
            return (ProductServers)Internal.produce(TYPE, null, block);
        }

        public ProductServers produce(ProductServers base,
                DraftConsumer<ProductServersDraft> block) {
            return (ProductServers)Internal.produce(TYPE, base, block);
        }

        public ProductServers produce(boolean resolveImmediately,
                DraftConsumer<ProductServersDraft> block) {
            return (ProductServers)Internal.produce(TYPE, null, resolveImmediately, block);
        }

        public ProductServers produce(ProductServers base, boolean resolveImmediately,
                DraftConsumer<ProductServersDraft> block) {
            return (ProductServers)Internal.produce(TYPE, base, resolveImmediately, block);
        }

        /**
         * Class, not interface, for free-marker
         */
        @GeneratedBy(
                type = ProductServers.class
        )
        @JsonPropertyOrder({"dummyPropForJacksonError__", "id", "name", "skuId", "hostUrl", "publicKey", "createdAt", "updatedAt"})
        public abstract static class Implementor implements ProductServers, ImmutableSpi {
            @Override
            public final Object __get(PropId prop) {
                int __propIndex = prop.asIndex();
                switch (__propIndex) {
                    case -1:
                    		return __get(prop.asName());
                    case SLOT_ID:
                    		return id();
                    case SLOT_NAME:
                    		return name();
                    case SLOT_SKU_ID:
                    		return skuId();
                    case SLOT_HOST_URL:
                    		return hostUrl();
                    case SLOT_PUBLIC_KEY:
                    		return publicKey();
                    case SLOT_CREATED_AT:
                    		return createdAt();
                    case SLOT_UPDATED_AT:
                    		return updatedAt();
                    default: throw new IllegalArgumentException("Illegal property name for \"com.doruk.infrastructure.persistence.entity.ProductServers\": \"" + prop + "\"");
                }
            }

            @Override
            public final Object __get(String prop) {
                switch (prop) {
                    case "id":
                    		return id();
                    case "name":
                    		return name();
                    case "skuId":
                    		return skuId();
                    case "hostUrl":
                    		return hostUrl();
                    case "publicKey":
                    		return publicKey();
                    case "createdAt":
                    		return createdAt();
                    case "updatedAt":
                    		return updatedAt();
                    default: throw new IllegalArgumentException("Illegal property name for \"com.doruk.infrastructure.persistence.entity.ProductServers\": \"" + prop + "\"");
                }
            }

            public final UUID getId() {
                return id();
            }

            public final String getName() {
                return name();
            }

            public final String getSkuId() {
                return skuId();
            }

            public final String getHostUrl() {
                return hostUrl();
            }

            public final String getPublicKey() {
                return publicKey();
            }

            public final OffsetDateTime getCreatedAt() {
                return createdAt();
            }

            public final OffsetDateTime getUpdatedAt() {
                return updatedAt();
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
                type = ProductServers.class
        )
        private static class Impl extends Implementor implements Cloneable, Serializable {
            private Visibility __visibility;

            UUID __idValue;

            String __nameValue;

            String __skuIdValue;

            String __hostUrlValue;

            String __publicKeyValue;

            OffsetDateTime __createdAtValue;

            OffsetDateTime __updatedAtValue;

            @Override
            @JsonIgnore
            public UUID id() {
                if (__idValue == null) {
                    throw new UnloadedException(ProductServers.class, "id");
                }
                return __idValue;
            }

            @Override
            @JsonIgnore
            public String name() {
                if (__nameValue == null) {
                    throw new UnloadedException(ProductServers.class, "name");
                }
                return __nameValue;
            }

            @Override
            @JsonIgnore
            public String skuId() {
                if (__skuIdValue == null) {
                    throw new UnloadedException(ProductServers.class, "skuId");
                }
                return __skuIdValue;
            }

            @Override
            @JsonIgnore
            public String hostUrl() {
                if (__hostUrlValue == null) {
                    throw new UnloadedException(ProductServers.class, "hostUrl");
                }
                return __hostUrlValue;
            }

            @Override
            @JsonIgnore
            public String publicKey() {
                if (__publicKeyValue == null) {
                    throw new UnloadedException(ProductServers.class, "publicKey");
                }
                return __publicKeyValue;
            }

            @Override
            @JsonIgnore
            public OffsetDateTime createdAt() {
                if (__createdAtValue == null) {
                    throw new UnloadedException(ProductServers.class, "createdAt");
                }
                return __createdAtValue;
            }

            @Override
            @JsonIgnore
            public OffsetDateTime updatedAt() {
                if (__updatedAtValue == null) {
                    throw new UnloadedException(ProductServers.class, "updatedAt");
                }
                return __updatedAtValue;
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
                    		return __idValue != null;
                    case SLOT_NAME:
                    		return __nameValue != null;
                    case SLOT_SKU_ID:
                    		return __skuIdValue != null;
                    case SLOT_HOST_URL:
                    		return __hostUrlValue != null;
                    case SLOT_PUBLIC_KEY:
                    		return __publicKeyValue != null;
                    case SLOT_CREATED_AT:
                    		return __createdAtValue != null;
                    case SLOT_UPDATED_AT:
                    		return __updatedAtValue != null;
                    default: throw new IllegalArgumentException("Illegal property name for \"com.doruk.infrastructure.persistence.entity.ProductServers\": \"" + prop + "\"");
                }
            }

            @Override
            public boolean __isLoaded(String prop) {
                switch (prop) {
                    case "id":
                    		return __idValue != null;
                    case "name":
                    		return __nameValue != null;
                    case "skuId":
                    		return __skuIdValue != null;
                    case "hostUrl":
                    		return __hostUrlValue != null;
                    case "publicKey":
                    		return __publicKeyValue != null;
                    case "createdAt":
                    		return __createdAtValue != null;
                    case "updatedAt":
                    		return __updatedAtValue != null;
                    default: throw new IllegalArgumentException("Illegal property name for \"com.doruk.infrastructure.persistence.entity.ProductServers\": \"" + prop + "\"");
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
                    case SLOT_NAME:
                    		return __visibility.visible(SLOT_NAME);
                    case SLOT_SKU_ID:
                    		return __visibility.visible(SLOT_SKU_ID);
                    case SLOT_HOST_URL:
                    		return __visibility.visible(SLOT_HOST_URL);
                    case SLOT_PUBLIC_KEY:
                    		return __visibility.visible(SLOT_PUBLIC_KEY);
                    case SLOT_CREATED_AT:
                    		return __visibility.visible(SLOT_CREATED_AT);
                    case SLOT_UPDATED_AT:
                    		return __visibility.visible(SLOT_UPDATED_AT);
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
                    case "name":
                    		return __visibility.visible(SLOT_NAME);
                    case "skuId":
                    		return __visibility.visible(SLOT_SKU_ID);
                    case "hostUrl":
                    		return __visibility.visible(SLOT_HOST_URL);
                    case "publicKey":
                    		return __visibility.visible(SLOT_PUBLIC_KEY);
                    case "createdAt":
                    		return __visibility.visible(SLOT_CREATED_AT);
                    case "updatedAt":
                    		return __visibility.visible(SLOT_UPDATED_AT);
                    default: return true;
                }
            }

            @Override
            public int hashCode() {
                int hash = __visibility != null ? __visibility.hashCode() : 0;
                if (__idValue != null) {
                    hash = 31 * hash + __idValue.hashCode();
                    // If entity-id is loaded, return directly
                    return hash;
                }
                if (__nameValue != null) {
                    hash = 31 * hash + __nameValue.hashCode();
                }
                if (__skuIdValue != null) {
                    hash = 31 * hash + __skuIdValue.hashCode();
                }
                if (__hostUrlValue != null) {
                    hash = 31 * hash + __hostUrlValue.hashCode();
                }
                if (__publicKeyValue != null) {
                    hash = 31 * hash + __publicKeyValue.hashCode();
                }
                if (__createdAtValue != null) {
                    hash = 31 * hash + __createdAtValue.hashCode();
                }
                if (__updatedAtValue != null) {
                    hash = 31 * hash + __updatedAtValue.hashCode();
                }
                return hash;
            }

            private int __shallowHashCode() {
                int hash = __visibility != null ? __visibility.hashCode() : 0;
                if (__idValue != null) {
                    hash = 31 * hash + System.identityHashCode(__idValue);
                }
                if (__nameValue != null) {
                    hash = 31 * hash + System.identityHashCode(__nameValue);
                }
                if (__skuIdValue != null) {
                    hash = 31 * hash + System.identityHashCode(__skuIdValue);
                }
                if (__hostUrlValue != null) {
                    hash = 31 * hash + System.identityHashCode(__hostUrlValue);
                }
                if (__publicKeyValue != null) {
                    hash = 31 * hash + System.identityHashCode(__publicKeyValue);
                }
                if (__createdAtValue != null) {
                    hash = 31 * hash + System.identityHashCode(__createdAtValue);
                }
                if (__updatedAtValue != null) {
                    hash = 31 * hash + System.identityHashCode(__updatedAtValue);
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
                boolean __idLoaded = __idValue != null;
                if (__idLoaded != __other.__isLoaded(PropId.byIndex(SLOT_ID))) {
                    return false;
                }
                if (__idLoaded) {
                    // If entity-id is loaded, return directly
                    return Objects.equals(__idValue, __other.id());
                }
                if (__isVisible(PropId.byIndex(SLOT_NAME)) != __other.__isVisible(PropId.byIndex(SLOT_NAME))) {
                    return false;
                }
                boolean __nameLoaded = __nameValue != null;
                if (__nameLoaded != __other.__isLoaded(PropId.byIndex(SLOT_NAME))) {
                    return false;
                }
                if (__nameLoaded && !Objects.equals(__nameValue, __other.name())) {
                    return false;
                }
                if (__isVisible(PropId.byIndex(SLOT_SKU_ID)) != __other.__isVisible(PropId.byIndex(SLOT_SKU_ID))) {
                    return false;
                }
                boolean __skuIdLoaded = __skuIdValue != null;
                if (__skuIdLoaded != __other.__isLoaded(PropId.byIndex(SLOT_SKU_ID))) {
                    return false;
                }
                if (__skuIdLoaded && !Objects.equals(__skuIdValue, __other.skuId())) {
                    return false;
                }
                if (__isVisible(PropId.byIndex(SLOT_HOST_URL)) != __other.__isVisible(PropId.byIndex(SLOT_HOST_URL))) {
                    return false;
                }
                boolean __hostUrlLoaded = __hostUrlValue != null;
                if (__hostUrlLoaded != __other.__isLoaded(PropId.byIndex(SLOT_HOST_URL))) {
                    return false;
                }
                if (__hostUrlLoaded && !Objects.equals(__hostUrlValue, __other.hostUrl())) {
                    return false;
                }
                if (__isVisible(PropId.byIndex(SLOT_PUBLIC_KEY)) != __other.__isVisible(PropId.byIndex(SLOT_PUBLIC_KEY))) {
                    return false;
                }
                boolean __publicKeyLoaded = __publicKeyValue != null;
                if (__publicKeyLoaded != __other.__isLoaded(PropId.byIndex(SLOT_PUBLIC_KEY))) {
                    return false;
                }
                if (__publicKeyLoaded && !Objects.equals(__publicKeyValue, __other.publicKey())) {
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
                if (__isVisible(PropId.byIndex(SLOT_UPDATED_AT)) != __other.__isVisible(PropId.byIndex(SLOT_UPDATED_AT))) {
                    return false;
                }
                boolean __updatedAtLoaded = __updatedAtValue != null;
                if (__updatedAtLoaded != __other.__isLoaded(PropId.byIndex(SLOT_UPDATED_AT))) {
                    return false;
                }
                if (__updatedAtLoaded && !Objects.equals(__updatedAtValue, __other.updatedAt())) {
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
                boolean __idLoaded = __idValue != null;
                if (__idLoaded != __other.__isLoaded(PropId.byIndex(SLOT_ID))) {
                    return false;
                }
                if (__idLoaded && __idValue != __other.id()) {
                    return false;
                }
                if (__isVisible(PropId.byIndex(SLOT_NAME)) != __other.__isVisible(PropId.byIndex(SLOT_NAME))) {
                    return false;
                }
                boolean __nameLoaded = __nameValue != null;
                if (__nameLoaded != __other.__isLoaded(PropId.byIndex(SLOT_NAME))) {
                    return false;
                }
                if (__nameLoaded && __nameValue != __other.name()) {
                    return false;
                }
                if (__isVisible(PropId.byIndex(SLOT_SKU_ID)) != __other.__isVisible(PropId.byIndex(SLOT_SKU_ID))) {
                    return false;
                }
                boolean __skuIdLoaded = __skuIdValue != null;
                if (__skuIdLoaded != __other.__isLoaded(PropId.byIndex(SLOT_SKU_ID))) {
                    return false;
                }
                if (__skuIdLoaded && __skuIdValue != __other.skuId()) {
                    return false;
                }
                if (__isVisible(PropId.byIndex(SLOT_HOST_URL)) != __other.__isVisible(PropId.byIndex(SLOT_HOST_URL))) {
                    return false;
                }
                boolean __hostUrlLoaded = __hostUrlValue != null;
                if (__hostUrlLoaded != __other.__isLoaded(PropId.byIndex(SLOT_HOST_URL))) {
                    return false;
                }
                if (__hostUrlLoaded && __hostUrlValue != __other.hostUrl()) {
                    return false;
                }
                if (__isVisible(PropId.byIndex(SLOT_PUBLIC_KEY)) != __other.__isVisible(PropId.byIndex(SLOT_PUBLIC_KEY))) {
                    return false;
                }
                boolean __publicKeyLoaded = __publicKeyValue != null;
                if (__publicKeyLoaded != __other.__isLoaded(PropId.byIndex(SLOT_PUBLIC_KEY))) {
                    return false;
                }
                if (__publicKeyLoaded && __publicKeyValue != __other.publicKey()) {
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
                if (__isVisible(PropId.byIndex(SLOT_UPDATED_AT)) != __other.__isVisible(PropId.byIndex(SLOT_UPDATED_AT))) {
                    return false;
                }
                boolean __updatedAtLoaded = __updatedAtValue != null;
                if (__updatedAtLoaded != __other.__isLoaded(PropId.byIndex(SLOT_UPDATED_AT))) {
                    return false;
                }
                if (__updatedAtLoaded && __updatedAtValue != __other.updatedAt()) {
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
                type = ProductServers.class
        )
        private static class DraftImpl extends Implementor implements DraftSpi, ProductServersDraft {
            private DraftContext __ctx;

            private Impl __base;

            private Impl __modified;

            private boolean __resolving;

            private ProductServers __resolved;

            DraftImpl(DraftContext ctx, ProductServers base) {
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
            public UUID id() {
                return (__modified!= null ? __modified : __base).id();
            }

            @Override
            public ProductServersDraft setId(UUID id) {
                if (__resolved != null) {
                    throw new IllegalStateException("The current draft has been resolved so it cannot be modified");
                }
                if (id == null) {
                    throw new IllegalArgumentException(
                        "'id' cannot be null, please specify non-null value or use nullable annotation to decorate this property"
                    );
                }
                Impl __tmpModified = __modified();
                __tmpModified.__idValue = id;
                return this;
            }

            @Override
            @JsonIgnore
            public String name() {
                return (__modified!= null ? __modified : __base).name();
            }

            @Override
            public ProductServersDraft setName(String name) {
                if (__resolved != null) {
                    throw new IllegalStateException("The current draft has been resolved so it cannot be modified");
                }
                if (name == null) {
                    throw new IllegalArgumentException(
                        "'name' cannot be null, please specify non-null value or use nullable annotation to decorate this property"
                    );
                }
                Impl __tmpModified = __modified();
                __tmpModified.__nameValue = name;
                return this;
            }

            @Override
            @JsonIgnore
            public String skuId() {
                return (__modified!= null ? __modified : __base).skuId();
            }

            @Override
            public ProductServersDraft setSkuId(String skuId) {
                if (__resolved != null) {
                    throw new IllegalStateException("The current draft has been resolved so it cannot be modified");
                }
                if (skuId == null) {
                    throw new IllegalArgumentException(
                        "'skuId' cannot be null, please specify non-null value or use nullable annotation to decorate this property"
                    );
                }
                Impl __tmpModified = __modified();
                __tmpModified.__skuIdValue = skuId;
                return this;
            }

            @Override
            @JsonIgnore
            public String hostUrl() {
                return (__modified!= null ? __modified : __base).hostUrl();
            }

            @Override
            public ProductServersDraft setHostUrl(String hostUrl) {
                if (__resolved != null) {
                    throw new IllegalStateException("The current draft has been resolved so it cannot be modified");
                }
                if (hostUrl == null) {
                    throw new IllegalArgumentException(
                        "'hostUrl' cannot be null, please specify non-null value or use nullable annotation to decorate this property"
                    );
                }
                Impl __tmpModified = __modified();
                __tmpModified.__hostUrlValue = hostUrl;
                return this;
            }

            @Override
            @JsonIgnore
            public String publicKey() {
                return (__modified!= null ? __modified : __base).publicKey();
            }

            @Override
            public ProductServersDraft setPublicKey(String publicKey) {
                if (__resolved != null) {
                    throw new IllegalStateException("The current draft has been resolved so it cannot be modified");
                }
                if (publicKey == null) {
                    throw new IllegalArgumentException(
                        "'publicKey' cannot be null, please specify non-null value or use nullable annotation to decorate this property"
                    );
                }
                Impl __tmpModified = __modified();
                __tmpModified.__publicKeyValue = publicKey;
                return this;
            }

            @Override
            @JsonIgnore
            public OffsetDateTime createdAt() {
                return (__modified!= null ? __modified : __base).createdAt();
            }

            @Override
            public ProductServersDraft setCreatedAt(OffsetDateTime createdAt) {
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
            public OffsetDateTime updatedAt() {
                return (__modified!= null ? __modified : __base).updatedAt();
            }

            @Override
            public ProductServersDraft setUpdatedAt(OffsetDateTime updatedAt) {
                if (__resolved != null) {
                    throw new IllegalStateException("The current draft has been resolved so it cannot be modified");
                }
                if (updatedAt == null) {
                    throw new IllegalArgumentException(
                        "'updatedAt' cannot be null, please specify non-null value or use nullable annotation to decorate this property"
                    );
                }
                Impl __tmpModified = __modified();
                __tmpModified.__updatedAtValue = updatedAt;
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
                    		setId((UUID)value);break;
                    case SLOT_NAME:
                    		setName((String)value);break;
                    case SLOT_SKU_ID:
                    		setSkuId((String)value);break;
                    case SLOT_HOST_URL:
                    		setHostUrl((String)value);break;
                    case SLOT_PUBLIC_KEY:
                    		setPublicKey((String)value);break;
                    case SLOT_CREATED_AT:
                    		setCreatedAt((OffsetDateTime)value);break;
                    case SLOT_UPDATED_AT:
                    		setUpdatedAt((OffsetDateTime)value);break;
                    default: throw new IllegalArgumentException("Illegal property id for \"com.doruk.infrastructure.persistence.entity.ProductServers\": \"" + prop + "\"");
                }
            }

            @SuppressWarnings("all")
            @Override
            public void __set(String prop, Object value) {
                switch (prop) {
                    case "id":
                    		setId((UUID)value);break;
                    case "name":
                    		setName((String)value);break;
                    case "skuId":
                    		setSkuId((String)value);break;
                    case "hostUrl":
                    		setHostUrl((String)value);break;
                    case "publicKey":
                    		setPublicKey((String)value);break;
                    case "createdAt":
                    		setCreatedAt((OffsetDateTime)value);break;
                    case "updatedAt":
                    		setUpdatedAt((OffsetDateTime)value);break;
                    default: throw new IllegalArgumentException("Illegal property name for \"com.doruk.infrastructure.persistence.entity.ProductServers\": \"" + prop + "\"");
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
                    case SLOT_NAME:
                    		__visibility.show(SLOT_NAME, visible);break;
                    case SLOT_SKU_ID:
                    		__visibility.show(SLOT_SKU_ID, visible);break;
                    case SLOT_HOST_URL:
                    		__visibility.show(SLOT_HOST_URL, visible);break;
                    case SLOT_PUBLIC_KEY:
                    		__visibility.show(SLOT_PUBLIC_KEY, visible);break;
                    case SLOT_CREATED_AT:
                    		__visibility.show(SLOT_CREATED_AT, visible);break;
                    case SLOT_UPDATED_AT:
                    		__visibility.show(SLOT_UPDATED_AT, visible);break;
                    default: throw new IllegalArgumentException(
                                "Illegal property id for \"com.doruk.infrastructure.persistence.entity.ProductServers\": \"" + 
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
                    case "name":
                    		__visibility.show(SLOT_NAME, visible);break;
                    case "skuId":
                    		__visibility.show(SLOT_SKU_ID, visible);break;
                    case "hostUrl":
                    		__visibility.show(SLOT_HOST_URL, visible);break;
                    case "publicKey":
                    		__visibility.show(SLOT_PUBLIC_KEY, visible);break;
                    case "createdAt":
                    		__visibility.show(SLOT_CREATED_AT, visible);break;
                    case "updatedAt":
                    		__visibility.show(SLOT_UPDATED_AT, visible);break;
                    default: throw new IllegalArgumentException(
                                "Illegal property name for \"com.doruk.infrastructure.persistence.entity.ProductServers\": \"" + 
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
                    		__modified().__idValue = null;break;
                    case SLOT_NAME:
                    		__modified().__nameValue = null;break;
                    case SLOT_SKU_ID:
                    		__modified().__skuIdValue = null;break;
                    case SLOT_HOST_URL:
                    		__modified().__hostUrlValue = null;break;
                    case SLOT_PUBLIC_KEY:
                    		__modified().__publicKeyValue = null;break;
                    case SLOT_CREATED_AT:
                    		__modified().__createdAtValue = null;break;
                    case SLOT_UPDATED_AT:
                    		__modified().__updatedAtValue = null;break;
                    default: throw new IllegalArgumentException("Illegal property id for \"com.doruk.infrastructure.persistence.entity.ProductServers\": \"" + prop + "\", it does not exist or its loaded state is not controllable");
                }
            }

            @Override
            public void __unload(String prop) {
                if (__resolved != null) {
                    throw new IllegalStateException("The current draft has been resolved so it cannot be modified");
                }
                switch (prop) {
                    case "id":
                    		__modified().__idValue = null;break;
                    case "name":
                    		__modified().__nameValue = null;break;
                    case "skuId":
                    		__modified().__skuIdValue = null;break;
                    case "hostUrl":
                    		__modified().__hostUrlValue = null;break;
                    case "publicKey":
                    		__modified().__publicKeyValue = null;break;
                    case "createdAt":
                    		__modified().__createdAtValue = null;break;
                    case "updatedAt":
                    		__modified().__updatedAtValue = null;break;
                    default: throw new IllegalArgumentException("Illegal property name for \"com.doruk.infrastructure.persistence.entity.ProductServers\": \"" + prop + "\", it does not exist or its loaded state is not controllable");
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
            type = ProductServers.class
    )
    class Builder {
        private final Producer.DraftImpl __draft;

        public Builder() {
            this(null);
        }

        public Builder(@Nullable ProductServers base) {
            __draft = new Producer.DraftImpl(null, base);
        }

        public Builder id(@NonNull UUID id) {
            if (id != null) {
                __draft.setId(id);
            }
            return this;
        }

        public Builder name(@NonNull String name) {
            if (name != null) {
                __draft.setName(name);
            }
            return this;
        }

        public Builder skuId(@NonNull String skuId) {
            if (skuId != null) {
                __draft.setSkuId(skuId);
            }
            return this;
        }

        public Builder hostUrl(@NonNull String hostUrl) {
            if (hostUrl != null) {
                __draft.setHostUrl(hostUrl);
            }
            return this;
        }

        public Builder publicKey(@NonNull String publicKey) {
            if (publicKey != null) {
                __draft.setPublicKey(publicKey);
            }
            return this;
        }

        public Builder createdAt(@NonNull OffsetDateTime createdAt) {
            if (createdAt != null) {
                __draft.setCreatedAt(createdAt);
            }
            return this;
        }

        public Builder updatedAt(@NonNull OffsetDateTime updatedAt) {
            if (updatedAt != null) {
                __draft.setUpdatedAt(updatedAt);
            }
            return this;
        }

        public ProductServers build() {
            return (ProductServers)__draft.__modified();
        }
    }
}
