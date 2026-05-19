package com.doruk.infrastructure.persistence.entity;

import com.doruk.domain.shared.enums.LicenseStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.io.Serializable;
import java.lang.CloneNotSupportedException;
import java.lang.Cloneable;
import java.lang.IllegalArgumentException;
import java.lang.IllegalStateException;
import java.lang.Integer;
import java.lang.Long;
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
import org.babyfish.jimmer.sql.ManyToOne;
import org.jetbrains.annotations.Nullable;
import org.jspecify.annotations.NonNull;

@GeneratedBy(
        type = LicenseAddons.class
)
public interface LicenseAddonsDraft extends LicenseAddons, Draft {
    LicenseAddonsDraft.Producer $ = Producer.INSTANCE;

    @OldChain
    LicenseAddonsDraft setId(UUID id);

    @OldChain
    LicenseAddonsDraft setLicenseId(UUID licenseId);

    @OldChain
    LicenseAddonsDraft setSkuId(UUID skuId);

    @OldChain
    LicenseAddonsDraft setTierId(Long tierId);

    @OldChain
    LicenseAddonsDraft setValidUntil(OffsetDateTime validUntil);

    @OldChain
    LicenseAddonsDraft setStatus(LicenseStatus status);

    @OldChain
    LicenseAddonsDraft setProductName(String productName);

    @OldChain
    LicenseAddonsDraft setTierName(String tierName);

    @OldChain
    LicenseAddonsDraft setEntitlements(String entitlements);

    @OldChain
    LicenseAddonsDraft setTotalSeats(int totalSeats);

    @OldChain
    LicenseAddonsDraft setCreatedAt(OffsetDateTime createdAt);

    LicensesDraft licenses();

    LicensesDraft licenses(boolean autoCreate);

    @OldChain
    LicenseAddonsDraft setLicenses(Licenses licenses);

    @NonNull
    @JsonIgnore
    UUID licensesId();

    @OldChain
    LicenseAddonsDraft setLicensesId(@NonNull UUID licensesId);

    @OldChain
    LicenseAddonsDraft applyLicenses(DraftConsumer<LicensesDraft> block);

    @OldChain
    LicenseAddonsDraft applyLicenses(Licenses base, DraftConsumer<LicensesDraft> block);

    @GeneratedBy(
            type = LicenseAddons.class
    )
    class Producer {
        static final Producer INSTANCE = new Producer();

        public static final int SLOT_ID = 0;

        public static final int SLOT_LICENSE_ID = 1;

        public static final int SLOT_SKU_ID = 2;

        public static final int SLOT_TIER_ID = 3;

        public static final int SLOT_VALID_UNTIL = 4;

        public static final int SLOT_STATUS = 5;

        public static final int SLOT_PRODUCT_NAME = 6;

        public static final int SLOT_TIER_NAME = 7;

        public static final int SLOT_ENTITLEMENTS = 8;

        public static final int SLOT_TOTAL_SEATS = 9;

        public static final int SLOT_CREATED_AT = 10;

        public static final int SLOT_LICENSES = 11;

        public static final ImmutableType TYPE = ImmutableType
            .newBuilder(
                "0.10.6",
                LicenseAddons.class,
                Collections.emptyList(),
                (ctx, base) -> new DraftImpl(ctx, (LicenseAddons)base)
            )
            .id(SLOT_ID, "id", UUID.class)
            .add(SLOT_LICENSE_ID, "licenseId", ImmutablePropCategory.SCALAR, UUID.class, false)
            .add(SLOT_SKU_ID, "skuId", ImmutablePropCategory.SCALAR, UUID.class, false)
            .add(SLOT_TIER_ID, "tierId", ImmutablePropCategory.SCALAR, Long.class, true)
            .add(SLOT_VALID_UNTIL, "validUntil", ImmutablePropCategory.SCALAR, OffsetDateTime.class, true)
            .add(SLOT_STATUS, "status", ImmutablePropCategory.SCALAR, LicenseStatus.class, false)
            .add(SLOT_PRODUCT_NAME, "productName", ImmutablePropCategory.SCALAR, String.class, false)
            .add(SLOT_TIER_NAME, "tierName", ImmutablePropCategory.SCALAR, String.class, false)
            .add(SLOT_ENTITLEMENTS, "entitlements", ImmutablePropCategory.SCALAR, String.class, true)
            .add(SLOT_TOTAL_SEATS, "totalSeats", ImmutablePropCategory.SCALAR, int.class, false)
            .add(SLOT_CREATED_AT, "createdAt", ImmutablePropCategory.SCALAR, OffsetDateTime.class, false)
            .add(SLOT_LICENSES, "licenses", ManyToOne.class, Licenses.class, false)
            .build();

        private Producer() {
        }

        public LicenseAddons produce(DraftConsumer<LicenseAddonsDraft> block) {
            return (LicenseAddons)Internal.produce(TYPE, null, block);
        }

        public LicenseAddons produce(LicenseAddons base, DraftConsumer<LicenseAddonsDraft> block) {
            return (LicenseAddons)Internal.produce(TYPE, base, block);
        }

        public LicenseAddons produce(boolean resolveImmediately,
                DraftConsumer<LicenseAddonsDraft> block) {
            return (LicenseAddons)Internal.produce(TYPE, null, resolveImmediately, block);
        }

        public LicenseAddons produce(LicenseAddons base, boolean resolveImmediately,
                DraftConsumer<LicenseAddonsDraft> block) {
            return (LicenseAddons)Internal.produce(TYPE, base, resolveImmediately, block);
        }

        /**
         * Class, not interface, for free-marker
         */
        @GeneratedBy(
                type = LicenseAddons.class
        )
        @JsonPropertyOrder({"dummyPropForJacksonError__", "id", "licenseId", "skuId", "tierId", "validUntil", "status", "productName", "tierName", "entitlements", "totalSeats", "createdAt", "licenses"})
        public abstract static class Implementor implements LicenseAddons, ImmutableSpi {
            @Override
            public final Object __get(PropId prop) {
                int __propIndex = prop.asIndex();
                switch (__propIndex) {
                    case -1:
                    		return __get(prop.asName());
                    case SLOT_ID:
                    		return id();
                    case SLOT_LICENSE_ID:
                    		return licenseId();
                    case SLOT_SKU_ID:
                    		return skuId();
                    case SLOT_TIER_ID:
                    		return tierId();
                    case SLOT_VALID_UNTIL:
                    		return validUntil();
                    case SLOT_STATUS:
                    		return status();
                    case SLOT_PRODUCT_NAME:
                    		return productName();
                    case SLOT_TIER_NAME:
                    		return tierName();
                    case SLOT_ENTITLEMENTS:
                    		return entitlements();
                    case SLOT_TOTAL_SEATS:
                    		return (Integer)totalSeats();
                    case SLOT_CREATED_AT:
                    		return createdAt();
                    case SLOT_LICENSES:
                    		return licenses();
                    default: throw new IllegalArgumentException("Illegal property name for \"com.doruk.infrastructure.persistence.entity.LicenseAddons\": \"" + prop + "\"");
                }
            }

            @Override
            public final Object __get(String prop) {
                switch (prop) {
                    case "id":
                    		return id();
                    case "licenseId":
                    		return licenseId();
                    case "skuId":
                    		return skuId();
                    case "tierId":
                    		return tierId();
                    case "validUntil":
                    		return validUntil();
                    case "status":
                    		return status();
                    case "productName":
                    		return productName();
                    case "tierName":
                    		return tierName();
                    case "entitlements":
                    		return entitlements();
                    case "totalSeats":
                    		return (Integer)totalSeats();
                    case "createdAt":
                    		return createdAt();
                    case "licenses":
                    		return licenses();
                    default: throw new IllegalArgumentException("Illegal property name for \"com.doruk.infrastructure.persistence.entity.LicenseAddons\": \"" + prop + "\"");
                }
            }

            public final UUID getId() {
                return id();
            }

            public final UUID getLicenseId() {
                return licenseId();
            }

            public final UUID getSkuId() {
                return skuId();
            }

            @Nullable
            public final Long getTierId() {
                return tierId();
            }

            @Nullable
            public final OffsetDateTime getValidUntil() {
                return validUntil();
            }

            public final LicenseStatus getStatus() {
                return status();
            }

            public final String getProductName() {
                return productName();
            }

            public final String getTierName() {
                return tierName();
            }

            @Nullable
            public final String getEntitlements() {
                return entitlements();
            }

            public final int getTotalSeats() {
                return totalSeats();
            }

            public final OffsetDateTime getCreatedAt() {
                return createdAt();
            }

            public final Licenses getLicenses() {
                return licenses();
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
                type = LicenseAddons.class
        )
        private static class Impl extends Implementor implements Cloneable, Serializable {
            private Visibility __visibility;

            UUID __idValue;

            UUID __licenseIdValue;

            UUID __skuIdValue;

            Long __tierIdValue;

            boolean __tierIdLoaded = false;

            OffsetDateTime __validUntilValue;

            boolean __validUntilLoaded = false;

            LicenseStatus __statusValue;

            String __productNameValue;

            String __tierNameValue;

            String __entitlementsValue;

            boolean __entitlementsLoaded = false;

            int __totalSeatsValue;

            boolean __totalSeatsLoaded = false;

            OffsetDateTime __createdAtValue;

            Licenses __licensesValue;

            @Override
            @JsonIgnore
            public UUID id() {
                if (__idValue == null) {
                    throw new UnloadedException(LicenseAddons.class, "id");
                }
                return __idValue;
            }

            @Override
            @JsonIgnore
            public UUID licenseId() {
                if (__licenseIdValue == null) {
                    throw new UnloadedException(LicenseAddons.class, "licenseId");
                }
                return __licenseIdValue;
            }

            @Override
            @JsonIgnore
            public UUID skuId() {
                if (__skuIdValue == null) {
                    throw new UnloadedException(LicenseAddons.class, "skuId");
                }
                return __skuIdValue;
            }

            @Override
            @JsonIgnore
            @org.jspecify.annotations.Nullable
            public Long tierId() {
                if (!__tierIdLoaded) {
                    throw new UnloadedException(LicenseAddons.class, "tierId");
                }
                return __tierIdValue;
            }

            @Override
            @JsonIgnore
            @org.jspecify.annotations.Nullable
            public OffsetDateTime validUntil() {
                if (!__validUntilLoaded) {
                    throw new UnloadedException(LicenseAddons.class, "validUntil");
                }
                return __validUntilValue;
            }

            @Override
            @JsonIgnore
            public LicenseStatus status() {
                if (__statusValue == null) {
                    throw new UnloadedException(LicenseAddons.class, "status");
                }
                return __statusValue;
            }

            @Override
            @JsonIgnore
            public String productName() {
                if (__productNameValue == null) {
                    throw new UnloadedException(LicenseAddons.class, "productName");
                }
                return __productNameValue;
            }

            @Override
            @JsonIgnore
            public String tierName() {
                if (__tierNameValue == null) {
                    throw new UnloadedException(LicenseAddons.class, "tierName");
                }
                return __tierNameValue;
            }

            @Override
            @JsonIgnore
            @org.jspecify.annotations.Nullable
            public String entitlements() {
                if (!__entitlementsLoaded) {
                    throw new UnloadedException(LicenseAddons.class, "entitlements");
                }
                return __entitlementsValue;
            }

            @Override
            @JsonIgnore
            public int totalSeats() {
                if (!__totalSeatsLoaded) {
                    throw new UnloadedException(LicenseAddons.class, "totalSeats");
                }
                return __totalSeatsValue;
            }

            @Override
            @JsonIgnore
            public OffsetDateTime createdAt() {
                if (__createdAtValue == null) {
                    throw new UnloadedException(LicenseAddons.class, "createdAt");
                }
                return __createdAtValue;
            }

            @Override
            @JsonIgnore
            public Licenses licenses() {
                if (__licensesValue == null) {
                    throw new UnloadedException(LicenseAddons.class, "licenses");
                }
                return __licensesValue;
            }

            @Override
            public Impl clone() {
                try {
                    Impl copy = (Impl) super.clone();
                    Visibility originalVisibility = this.__visibility;
                    if (originalVisibility != null) {
                        Visibility newVisibility = Visibility.of(12);
                        for (int propId = 0; propId < 12; propId++) {
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
                    case SLOT_LICENSE_ID:
                    		return __licenseIdValue != null;
                    case SLOT_SKU_ID:
                    		return __skuIdValue != null;
                    case SLOT_TIER_ID:
                    		return __tierIdLoaded;
                    case SLOT_VALID_UNTIL:
                    		return __validUntilLoaded;
                    case SLOT_STATUS:
                    		return __statusValue != null;
                    case SLOT_PRODUCT_NAME:
                    		return __productNameValue != null;
                    case SLOT_TIER_NAME:
                    		return __tierNameValue != null;
                    case SLOT_ENTITLEMENTS:
                    		return __entitlementsLoaded;
                    case SLOT_TOTAL_SEATS:
                    		return __totalSeatsLoaded;
                    case SLOT_CREATED_AT:
                    		return __createdAtValue != null;
                    case SLOT_LICENSES:
                    		return __licensesValue != null;
                    default: throw new IllegalArgumentException("Illegal property name for \"com.doruk.infrastructure.persistence.entity.LicenseAddons\": \"" + prop + "\"");
                }
            }

            @Override
            public boolean __isLoaded(String prop) {
                switch (prop) {
                    case "id":
                    		return __idValue != null;
                    case "licenseId":
                    		return __licenseIdValue != null;
                    case "skuId":
                    		return __skuIdValue != null;
                    case "tierId":
                    		return __tierIdLoaded;
                    case "validUntil":
                    		return __validUntilLoaded;
                    case "status":
                    		return __statusValue != null;
                    case "productName":
                    		return __productNameValue != null;
                    case "tierName":
                    		return __tierNameValue != null;
                    case "entitlements":
                    		return __entitlementsLoaded;
                    case "totalSeats":
                    		return __totalSeatsLoaded;
                    case "createdAt":
                    		return __createdAtValue != null;
                    case "licenses":
                    		return __licensesValue != null;
                    default: throw new IllegalArgumentException("Illegal property name for \"com.doruk.infrastructure.persistence.entity.LicenseAddons\": \"" + prop + "\"");
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
                    case SLOT_LICENSE_ID:
                    		return __visibility.visible(SLOT_LICENSE_ID);
                    case SLOT_SKU_ID:
                    		return __visibility.visible(SLOT_SKU_ID);
                    case SLOT_TIER_ID:
                    		return __visibility.visible(SLOT_TIER_ID);
                    case SLOT_VALID_UNTIL:
                    		return __visibility.visible(SLOT_VALID_UNTIL);
                    case SLOT_STATUS:
                    		return __visibility.visible(SLOT_STATUS);
                    case SLOT_PRODUCT_NAME:
                    		return __visibility.visible(SLOT_PRODUCT_NAME);
                    case SLOT_TIER_NAME:
                    		return __visibility.visible(SLOT_TIER_NAME);
                    case SLOT_ENTITLEMENTS:
                    		return __visibility.visible(SLOT_ENTITLEMENTS);
                    case SLOT_TOTAL_SEATS:
                    		return __visibility.visible(SLOT_TOTAL_SEATS);
                    case SLOT_CREATED_AT:
                    		return __visibility.visible(SLOT_CREATED_AT);
                    case SLOT_LICENSES:
                    		return __visibility.visible(SLOT_LICENSES);
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
                    case "licenseId":
                    		return __visibility.visible(SLOT_LICENSE_ID);
                    case "skuId":
                    		return __visibility.visible(SLOT_SKU_ID);
                    case "tierId":
                    		return __visibility.visible(SLOT_TIER_ID);
                    case "validUntil":
                    		return __visibility.visible(SLOT_VALID_UNTIL);
                    case "status":
                    		return __visibility.visible(SLOT_STATUS);
                    case "productName":
                    		return __visibility.visible(SLOT_PRODUCT_NAME);
                    case "tierName":
                    		return __visibility.visible(SLOT_TIER_NAME);
                    case "entitlements":
                    		return __visibility.visible(SLOT_ENTITLEMENTS);
                    case "totalSeats":
                    		return __visibility.visible(SLOT_TOTAL_SEATS);
                    case "createdAt":
                    		return __visibility.visible(SLOT_CREATED_AT);
                    case "licenses":
                    		return __visibility.visible(SLOT_LICENSES);
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
                if (__licenseIdValue != null) {
                    hash = 31 * hash + __licenseIdValue.hashCode();
                }
                if (__skuIdValue != null) {
                    hash = 31 * hash + __skuIdValue.hashCode();
                }
                if (__tierIdLoaded && __tierIdValue != null) {
                    hash = 31 * hash + __tierIdValue.hashCode();
                }
                if (__validUntilLoaded && __validUntilValue != null) {
                    hash = 31 * hash + __validUntilValue.hashCode();
                }
                if (__statusValue != null) {
                    hash = 31 * hash + __statusValue.hashCode();
                }
                if (__productNameValue != null) {
                    hash = 31 * hash + __productNameValue.hashCode();
                }
                if (__tierNameValue != null) {
                    hash = 31 * hash + __tierNameValue.hashCode();
                }
                if (__entitlementsLoaded && __entitlementsValue != null) {
                    hash = 31 * hash + __entitlementsValue.hashCode();
                }
                if (__totalSeatsLoaded) {
                    hash = 31 * hash + Integer.hashCode(__totalSeatsValue);
                }
                if (__createdAtValue != null) {
                    hash = 31 * hash + __createdAtValue.hashCode();
                }
                if (__licensesValue != null) {
                    hash = 31 * hash + __licensesValue.hashCode();
                }
                return hash;
            }

            private int __shallowHashCode() {
                int hash = __visibility != null ? __visibility.hashCode() : 0;
                if (__idValue != null) {
                    hash = 31 * hash + System.identityHashCode(__idValue);
                }
                if (__licenseIdValue != null) {
                    hash = 31 * hash + System.identityHashCode(__licenseIdValue);
                }
                if (__skuIdValue != null) {
                    hash = 31 * hash + System.identityHashCode(__skuIdValue);
                }
                if (__tierIdLoaded) {
                    hash = 31 * hash + System.identityHashCode(__tierIdValue);
                }
                if (__validUntilLoaded) {
                    hash = 31 * hash + System.identityHashCode(__validUntilValue);
                }
                if (__statusValue != null) {
                    hash = 31 * hash + System.identityHashCode(__statusValue);
                }
                if (__productNameValue != null) {
                    hash = 31 * hash + System.identityHashCode(__productNameValue);
                }
                if (__tierNameValue != null) {
                    hash = 31 * hash + System.identityHashCode(__tierNameValue);
                }
                if (__entitlementsLoaded) {
                    hash = 31 * hash + System.identityHashCode(__entitlementsValue);
                }
                if (__totalSeatsLoaded) {
                    hash = 31 * hash + Integer.hashCode(__totalSeatsValue);
                }
                if (__createdAtValue != null) {
                    hash = 31 * hash + System.identityHashCode(__createdAtValue);
                }
                if (__licensesValue != null) {
                    hash = 31 * hash + System.identityHashCode(__licensesValue);
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
                if (__isVisible(PropId.byIndex(SLOT_LICENSE_ID)) != __other.__isVisible(PropId.byIndex(SLOT_LICENSE_ID))) {
                    return false;
                }
                boolean __licenseIdLoaded = __licenseIdValue != null;
                if (__licenseIdLoaded != __other.__isLoaded(PropId.byIndex(SLOT_LICENSE_ID))) {
                    return false;
                }
                if (__licenseIdLoaded && !Objects.equals(__licenseIdValue, __other.licenseId())) {
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
                if (__isVisible(PropId.byIndex(SLOT_TIER_ID)) != __other.__isVisible(PropId.byIndex(SLOT_TIER_ID))) {
                    return false;
                }
                boolean __tierIdLoaded = this.__tierIdLoaded;
                if (__tierIdLoaded != __other.__isLoaded(PropId.byIndex(SLOT_TIER_ID))) {
                    return false;
                }
                if (__tierIdLoaded && !Objects.equals(__tierIdValue, __other.tierId())) {
                    return false;
                }
                if (__isVisible(PropId.byIndex(SLOT_VALID_UNTIL)) != __other.__isVisible(PropId.byIndex(SLOT_VALID_UNTIL))) {
                    return false;
                }
                boolean __validUntilLoaded = this.__validUntilLoaded;
                if (__validUntilLoaded != __other.__isLoaded(PropId.byIndex(SLOT_VALID_UNTIL))) {
                    return false;
                }
                if (__validUntilLoaded && !Objects.equals(__validUntilValue, __other.validUntil())) {
                    return false;
                }
                if (__isVisible(PropId.byIndex(SLOT_STATUS)) != __other.__isVisible(PropId.byIndex(SLOT_STATUS))) {
                    return false;
                }
                boolean __statusLoaded = __statusValue != null;
                if (__statusLoaded != __other.__isLoaded(PropId.byIndex(SLOT_STATUS))) {
                    return false;
                }
                if (__statusLoaded && !Objects.equals(__statusValue, __other.status())) {
                    return false;
                }
                if (__isVisible(PropId.byIndex(SLOT_PRODUCT_NAME)) != __other.__isVisible(PropId.byIndex(SLOT_PRODUCT_NAME))) {
                    return false;
                }
                boolean __productNameLoaded = __productNameValue != null;
                if (__productNameLoaded != __other.__isLoaded(PropId.byIndex(SLOT_PRODUCT_NAME))) {
                    return false;
                }
                if (__productNameLoaded && !Objects.equals(__productNameValue, __other.productName())) {
                    return false;
                }
                if (__isVisible(PropId.byIndex(SLOT_TIER_NAME)) != __other.__isVisible(PropId.byIndex(SLOT_TIER_NAME))) {
                    return false;
                }
                boolean __tierNameLoaded = __tierNameValue != null;
                if (__tierNameLoaded != __other.__isLoaded(PropId.byIndex(SLOT_TIER_NAME))) {
                    return false;
                }
                if (__tierNameLoaded && !Objects.equals(__tierNameValue, __other.tierName())) {
                    return false;
                }
                if (__isVisible(PropId.byIndex(SLOT_ENTITLEMENTS)) != __other.__isVisible(PropId.byIndex(SLOT_ENTITLEMENTS))) {
                    return false;
                }
                boolean __entitlementsLoaded = this.__entitlementsLoaded;
                if (__entitlementsLoaded != __other.__isLoaded(PropId.byIndex(SLOT_ENTITLEMENTS))) {
                    return false;
                }
                if (__entitlementsLoaded && !Objects.equals(__entitlementsValue, __other.entitlements())) {
                    return false;
                }
                if (__isVisible(PropId.byIndex(SLOT_TOTAL_SEATS)) != __other.__isVisible(PropId.byIndex(SLOT_TOTAL_SEATS))) {
                    return false;
                }
                boolean __totalSeatsLoaded = this.__totalSeatsLoaded;
                if (__totalSeatsLoaded != __other.__isLoaded(PropId.byIndex(SLOT_TOTAL_SEATS))) {
                    return false;
                }
                if (__totalSeatsLoaded && __totalSeatsValue != __other.totalSeats()) {
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
                if (__isVisible(PropId.byIndex(SLOT_LICENSES)) != __other.__isVisible(PropId.byIndex(SLOT_LICENSES))) {
                    return false;
                }
                boolean __licensesLoaded = __licensesValue != null;
                if (__licensesLoaded != __other.__isLoaded(PropId.byIndex(SLOT_LICENSES))) {
                    return false;
                }
                if (__licensesLoaded && !Objects.equals(__licensesValue, __other.licenses())) {
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
                if (__isVisible(PropId.byIndex(SLOT_LICENSE_ID)) != __other.__isVisible(PropId.byIndex(SLOT_LICENSE_ID))) {
                    return false;
                }
                boolean __licenseIdLoaded = __licenseIdValue != null;
                if (__licenseIdLoaded != __other.__isLoaded(PropId.byIndex(SLOT_LICENSE_ID))) {
                    return false;
                }
                if (__licenseIdLoaded && __licenseIdValue != __other.licenseId()) {
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
                if (__isVisible(PropId.byIndex(SLOT_TIER_ID)) != __other.__isVisible(PropId.byIndex(SLOT_TIER_ID))) {
                    return false;
                }
                boolean __tierIdLoaded = this.__tierIdLoaded;
                if (__tierIdLoaded != __other.__isLoaded(PropId.byIndex(SLOT_TIER_ID))) {
                    return false;
                }
                if (__tierIdLoaded && __tierIdValue != __other.tierId()) {
                    return false;
                }
                if (__isVisible(PropId.byIndex(SLOT_VALID_UNTIL)) != __other.__isVisible(PropId.byIndex(SLOT_VALID_UNTIL))) {
                    return false;
                }
                boolean __validUntilLoaded = this.__validUntilLoaded;
                if (__validUntilLoaded != __other.__isLoaded(PropId.byIndex(SLOT_VALID_UNTIL))) {
                    return false;
                }
                if (__validUntilLoaded && __validUntilValue != __other.validUntil()) {
                    return false;
                }
                if (__isVisible(PropId.byIndex(SLOT_STATUS)) != __other.__isVisible(PropId.byIndex(SLOT_STATUS))) {
                    return false;
                }
                boolean __statusLoaded = __statusValue != null;
                if (__statusLoaded != __other.__isLoaded(PropId.byIndex(SLOT_STATUS))) {
                    return false;
                }
                if (__statusLoaded && __statusValue != __other.status()) {
                    return false;
                }
                if (__isVisible(PropId.byIndex(SLOT_PRODUCT_NAME)) != __other.__isVisible(PropId.byIndex(SLOT_PRODUCT_NAME))) {
                    return false;
                }
                boolean __productNameLoaded = __productNameValue != null;
                if (__productNameLoaded != __other.__isLoaded(PropId.byIndex(SLOT_PRODUCT_NAME))) {
                    return false;
                }
                if (__productNameLoaded && __productNameValue != __other.productName()) {
                    return false;
                }
                if (__isVisible(PropId.byIndex(SLOT_TIER_NAME)) != __other.__isVisible(PropId.byIndex(SLOT_TIER_NAME))) {
                    return false;
                }
                boolean __tierNameLoaded = __tierNameValue != null;
                if (__tierNameLoaded != __other.__isLoaded(PropId.byIndex(SLOT_TIER_NAME))) {
                    return false;
                }
                if (__tierNameLoaded && __tierNameValue != __other.tierName()) {
                    return false;
                }
                if (__isVisible(PropId.byIndex(SLOT_ENTITLEMENTS)) != __other.__isVisible(PropId.byIndex(SLOT_ENTITLEMENTS))) {
                    return false;
                }
                boolean __entitlementsLoaded = this.__entitlementsLoaded;
                if (__entitlementsLoaded != __other.__isLoaded(PropId.byIndex(SLOT_ENTITLEMENTS))) {
                    return false;
                }
                if (__entitlementsLoaded && __entitlementsValue != __other.entitlements()) {
                    return false;
                }
                if (__isVisible(PropId.byIndex(SLOT_TOTAL_SEATS)) != __other.__isVisible(PropId.byIndex(SLOT_TOTAL_SEATS))) {
                    return false;
                }
                boolean __totalSeatsLoaded = this.__totalSeatsLoaded;
                if (__totalSeatsLoaded != __other.__isLoaded(PropId.byIndex(SLOT_TOTAL_SEATS))) {
                    return false;
                }
                if (__totalSeatsLoaded && __totalSeatsValue != __other.totalSeats()) {
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
                if (__isVisible(PropId.byIndex(SLOT_LICENSES)) != __other.__isVisible(PropId.byIndex(SLOT_LICENSES))) {
                    return false;
                }
                boolean __licensesLoaded = __licensesValue != null;
                if (__licensesLoaded != __other.__isLoaded(PropId.byIndex(SLOT_LICENSES))) {
                    return false;
                }
                if (__licensesLoaded && __licensesValue != __other.licenses()) {
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
                type = LicenseAddons.class
        )
        private static class DraftImpl extends Implementor implements DraftSpi, LicenseAddonsDraft {
            private DraftContext __ctx;

            private Impl __base;

            private Impl __modified;

            private boolean __resolving;

            private LicenseAddons __resolved;

            DraftImpl(DraftContext ctx, LicenseAddons base) {
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
            public LicenseAddonsDraft setId(UUID id) {
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
            public UUID licenseId() {
                return (__modified!= null ? __modified : __base).licenseId();
            }

            @Override
            public LicenseAddonsDraft setLicenseId(UUID licenseId) {
                if (__resolved != null) {
                    throw new IllegalStateException("The current draft has been resolved so it cannot be modified");
                }
                if (licenseId == null) {
                    throw new IllegalArgumentException(
                        "'licenseId' cannot be null, please specify non-null value or use nullable annotation to decorate this property"
                    );
                }
                Impl __tmpModified = __modified();
                __tmpModified.__licenseIdValue = licenseId;
                return this;
            }

            @Override
            @JsonIgnore
            public UUID skuId() {
                return (__modified!= null ? __modified : __base).skuId();
            }

            @Override
            public LicenseAddonsDraft setSkuId(UUID skuId) {
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
            @org.jspecify.annotations.Nullable
            public Long tierId() {
                return (__modified!= null ? __modified : __base).tierId();
            }

            @Override
            public LicenseAddonsDraft setTierId(Long tierId) {
                if (__resolved != null) {
                    throw new IllegalStateException("The current draft has been resolved so it cannot be modified");
                }
                Impl __tmpModified = __modified();
                __tmpModified.__tierIdValue = tierId;
                __tmpModified.__tierIdLoaded = true;
                return this;
            }

            @Override
            @JsonIgnore
            @org.jspecify.annotations.Nullable
            public OffsetDateTime validUntil() {
                return (__modified!= null ? __modified : __base).validUntil();
            }

            @Override
            public LicenseAddonsDraft setValidUntil(OffsetDateTime validUntil) {
                if (__resolved != null) {
                    throw new IllegalStateException("The current draft has been resolved so it cannot be modified");
                }
                Impl __tmpModified = __modified();
                __tmpModified.__validUntilValue = validUntil;
                __tmpModified.__validUntilLoaded = true;
                return this;
            }

            @Override
            @JsonIgnore
            public LicenseStatus status() {
                return (__modified!= null ? __modified : __base).status();
            }

            @Override
            public LicenseAddonsDraft setStatus(LicenseStatus status) {
                if (__resolved != null) {
                    throw new IllegalStateException("The current draft has been resolved so it cannot be modified");
                }
                if (status == null) {
                    throw new IllegalArgumentException(
                        "'status' cannot be null, please specify non-null value or use nullable annotation to decorate this property"
                    );
                }
                Impl __tmpModified = __modified();
                __tmpModified.__statusValue = status;
                return this;
            }

            @Override
            @JsonIgnore
            public String productName() {
                return (__modified!= null ? __modified : __base).productName();
            }

            @Override
            public LicenseAddonsDraft setProductName(String productName) {
                if (__resolved != null) {
                    throw new IllegalStateException("The current draft has been resolved so it cannot be modified");
                }
                if (productName == null) {
                    throw new IllegalArgumentException(
                        "'productName' cannot be null, please specify non-null value or use nullable annotation to decorate this property"
                    );
                }
                Impl __tmpModified = __modified();
                __tmpModified.__productNameValue = productName;
                return this;
            }

            @Override
            @JsonIgnore
            public String tierName() {
                return (__modified!= null ? __modified : __base).tierName();
            }

            @Override
            public LicenseAddonsDraft setTierName(String tierName) {
                if (__resolved != null) {
                    throw new IllegalStateException("The current draft has been resolved so it cannot be modified");
                }
                if (tierName == null) {
                    throw new IllegalArgumentException(
                        "'tierName' cannot be null, please specify non-null value or use nullable annotation to decorate this property"
                    );
                }
                Impl __tmpModified = __modified();
                __tmpModified.__tierNameValue = tierName;
                return this;
            }

            @Override
            @JsonIgnore
            @org.jspecify.annotations.Nullable
            public String entitlements() {
                return (__modified!= null ? __modified : __base).entitlements();
            }

            @Override
            public LicenseAddonsDraft setEntitlements(String entitlements) {
                if (__resolved != null) {
                    throw new IllegalStateException("The current draft has been resolved so it cannot be modified");
                }
                Impl __tmpModified = __modified();
                __tmpModified.__entitlementsValue = entitlements;
                __tmpModified.__entitlementsLoaded = true;
                return this;
            }

            @Override
            @JsonIgnore
            public int totalSeats() {
                return (__modified!= null ? __modified : __base).totalSeats();
            }

            @Override
            public LicenseAddonsDraft setTotalSeats(int totalSeats) {
                if (__resolved != null) {
                    throw new IllegalStateException("The current draft has been resolved so it cannot be modified");
                }
                Impl __tmpModified = __modified();
                __tmpModified.__totalSeatsValue = totalSeats;
                __tmpModified.__totalSeatsLoaded = true;
                return this;
            }

            @Override
            @JsonIgnore
            public OffsetDateTime createdAt() {
                return (__modified!= null ? __modified : __base).createdAt();
            }

            @Override
            public LicenseAddonsDraft setCreatedAt(OffsetDateTime createdAt) {
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
            public LicensesDraft licenses() {
                return __ctx.toDraftObject((__modified!= null ? __modified : __base).licenses());
            }

            @Override
            public LicensesDraft licenses(boolean autoCreate) {
                if (autoCreate && (!__isLoaded(PropId.byIndex(SLOT_LICENSES)))) {
                    setLicenses(LicensesDraft.$.produce(null, null));
                }
                return __ctx.toDraftObject((__modified!= null ? __modified : __base).licenses());
            }

            @Override
            public LicenseAddonsDraft setLicenses(Licenses licenses) {
                if (__resolved != null) {
                    throw new IllegalStateException("The current draft has been resolved so it cannot be modified");
                }
                if (licenses == null) {
                    throw new IllegalArgumentException(
                        "'licenses' cannot be null, please specify non-null value or use nullable annotation to decorate this property"
                    );
                }
                Impl __tmpModified = __modified();
                __tmpModified.__licensesValue = licenses;
                return this;
            }

            @NonNull
            @JsonIgnore
            @Override
            public UUID licensesId() {
                return licenses().id();
            }

            @OldChain
            @Override
            public LicenseAddonsDraft setLicensesId(@NonNull UUID licensesId) {
                licenses(true).setId(Objects.requireNonNull(licensesId, "\"licenses\" cannot be null"));
                return this;
            }

            @Override
            public LicenseAddonsDraft applyLicenses(DraftConsumer<LicensesDraft> block) {
                applyLicenses(null, block);
                return this;
            }

            @Override
            public LicenseAddonsDraft applyLicenses(Licenses base,
                    DraftConsumer<LicensesDraft> block) {
                setLicenses(LicensesDraft.$.produce(base, block));
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
                    case SLOT_LICENSE_ID:
                    		setLicenseId((UUID)value);break;
                    case SLOT_SKU_ID:
                    		setSkuId((UUID)value);break;
                    case SLOT_TIER_ID:
                    		setTierId((Long)value);break;
                    case SLOT_VALID_UNTIL:
                    		setValidUntil((OffsetDateTime)value);break;
                    case SLOT_STATUS:
                    		setStatus((LicenseStatus)value);break;
                    case SLOT_PRODUCT_NAME:
                    		setProductName((String)value);break;
                    case SLOT_TIER_NAME:
                    		setTierName((String)value);break;
                    case SLOT_ENTITLEMENTS:
                    		setEntitlements((String)value);break;
                    case SLOT_TOTAL_SEATS:
                    		if (value == null) throw new IllegalArgumentException("'totalSeats' cannot be null, if you want to set null, please use any annotation whose simple name is \"Nullable\" to decorate the property");
                            setTotalSeats((Integer)value);
                            break;
                    case SLOT_CREATED_AT:
                    		setCreatedAt((OffsetDateTime)value);break;
                    case SLOT_LICENSES:
                    		setLicenses((Licenses)value);break;
                    default: throw new IllegalArgumentException("Illegal property id for \"com.doruk.infrastructure.persistence.entity.LicenseAddons\": \"" + prop + "\"");
                }
            }

            @SuppressWarnings("all")
            @Override
            public void __set(String prop, Object value) {
                switch (prop) {
                    case "id":
                    		setId((UUID)value);break;
                    case "licenseId":
                    		setLicenseId((UUID)value);break;
                    case "skuId":
                    		setSkuId((UUID)value);break;
                    case "tierId":
                    		setTierId((Long)value);break;
                    case "validUntil":
                    		setValidUntil((OffsetDateTime)value);break;
                    case "status":
                    		setStatus((LicenseStatus)value);break;
                    case "productName":
                    		setProductName((String)value);break;
                    case "tierName":
                    		setTierName((String)value);break;
                    case "entitlements":
                    		setEntitlements((String)value);break;
                    case "totalSeats":
                    		if (value == null) throw new IllegalArgumentException("'totalSeats' cannot be null, if you want to set null, please use any annotation whose simple name is \"Nullable\" to decorate the property");
                            setTotalSeats((Integer)value);
                            break;
                    case "createdAt":
                    		setCreatedAt((OffsetDateTime)value);break;
                    case "licenses":
                    		setLicenses((Licenses)value);break;
                    default: throw new IllegalArgumentException("Illegal property name for \"com.doruk.infrastructure.persistence.entity.LicenseAddons\": \"" + prop + "\"");
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
                    __modified().__visibility = __visibility = Visibility.of(12);
                }
                int __propIndex = prop.asIndex();
                switch (__propIndex) {
                    case -1:
                    		__show(prop.asName(), visible);
                    return;
                    case SLOT_ID:
                    		__visibility.show(SLOT_ID, visible);break;
                    case SLOT_LICENSE_ID:
                    		__visibility.show(SLOT_LICENSE_ID, visible);break;
                    case SLOT_SKU_ID:
                    		__visibility.show(SLOT_SKU_ID, visible);break;
                    case SLOT_TIER_ID:
                    		__visibility.show(SLOT_TIER_ID, visible);break;
                    case SLOT_VALID_UNTIL:
                    		__visibility.show(SLOT_VALID_UNTIL, visible);break;
                    case SLOT_STATUS:
                    		__visibility.show(SLOT_STATUS, visible);break;
                    case SLOT_PRODUCT_NAME:
                    		__visibility.show(SLOT_PRODUCT_NAME, visible);break;
                    case SLOT_TIER_NAME:
                    		__visibility.show(SLOT_TIER_NAME, visible);break;
                    case SLOT_ENTITLEMENTS:
                    		__visibility.show(SLOT_ENTITLEMENTS, visible);break;
                    case SLOT_TOTAL_SEATS:
                    		__visibility.show(SLOT_TOTAL_SEATS, visible);break;
                    case SLOT_CREATED_AT:
                    		__visibility.show(SLOT_CREATED_AT, visible);break;
                    case SLOT_LICENSES:
                    		__visibility.show(SLOT_LICENSES, visible);break;
                    default: throw new IllegalArgumentException(
                                "Illegal property id for \"com.doruk.infrastructure.persistence.entity.LicenseAddons\": \"" + 
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
                    __modified().__visibility = __visibility = Visibility.of(12);
                }
                switch (prop) {
                    case "id":
                    		__visibility.show(SLOT_ID, visible);break;
                    case "licenseId":
                    		__visibility.show(SLOT_LICENSE_ID, visible);break;
                    case "skuId":
                    		__visibility.show(SLOT_SKU_ID, visible);break;
                    case "tierId":
                    		__visibility.show(SLOT_TIER_ID, visible);break;
                    case "validUntil":
                    		__visibility.show(SLOT_VALID_UNTIL, visible);break;
                    case "status":
                    		__visibility.show(SLOT_STATUS, visible);break;
                    case "productName":
                    		__visibility.show(SLOT_PRODUCT_NAME, visible);break;
                    case "tierName":
                    		__visibility.show(SLOT_TIER_NAME, visible);break;
                    case "entitlements":
                    		__visibility.show(SLOT_ENTITLEMENTS, visible);break;
                    case "totalSeats":
                    		__visibility.show(SLOT_TOTAL_SEATS, visible);break;
                    case "createdAt":
                    		__visibility.show(SLOT_CREATED_AT, visible);break;
                    case "licenses":
                    		__visibility.show(SLOT_LICENSES, visible);break;
                    default: throw new IllegalArgumentException(
                                "Illegal property name for \"com.doruk.infrastructure.persistence.entity.LicenseAddons\": \"" + 
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
                    case SLOT_LICENSE_ID:
                    		__modified().__licenseIdValue = null;break;
                    case SLOT_SKU_ID:
                    		__modified().__skuIdValue = null;break;
                    case SLOT_TIER_ID:
                    		__modified().__tierIdValue = null;
                    __modified().__tierIdLoaded = false;break;
                    case SLOT_VALID_UNTIL:
                    		__modified().__validUntilValue = null;
                    __modified().__validUntilLoaded = false;break;
                    case SLOT_STATUS:
                    		__modified().__statusValue = null;break;
                    case SLOT_PRODUCT_NAME:
                    		__modified().__productNameValue = null;break;
                    case SLOT_TIER_NAME:
                    		__modified().__tierNameValue = null;break;
                    case SLOT_ENTITLEMENTS:
                    		__modified().__entitlementsValue = null;
                    __modified().__entitlementsLoaded = false;break;
                    case SLOT_TOTAL_SEATS:
                    		__modified().__totalSeatsValue = 0;
                    __modified().__totalSeatsLoaded = false;break;
                    case SLOT_CREATED_AT:
                    		__modified().__createdAtValue = null;break;
                    case SLOT_LICENSES:
                    		__modified().__licensesValue = null;break;
                    default: throw new IllegalArgumentException("Illegal property id for \"com.doruk.infrastructure.persistence.entity.LicenseAddons\": \"" + prop + "\", it does not exist or its loaded state is not controllable");
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
                    case "licenseId":
                    		__modified().__licenseIdValue = null;break;
                    case "skuId":
                    		__modified().__skuIdValue = null;break;
                    case "tierId":
                    		__modified().__tierIdValue = null;
                    __modified().__tierIdLoaded = false;break;
                    case "validUntil":
                    		__modified().__validUntilValue = null;
                    __modified().__validUntilLoaded = false;break;
                    case "status":
                    		__modified().__statusValue = null;break;
                    case "productName":
                    		__modified().__productNameValue = null;break;
                    case "tierName":
                    		__modified().__tierNameValue = null;break;
                    case "entitlements":
                    		__modified().__entitlementsValue = null;
                    __modified().__entitlementsLoaded = false;break;
                    case "totalSeats":
                    		__modified().__totalSeatsValue = 0;
                    __modified().__totalSeatsLoaded = false;break;
                    case "createdAt":
                    		__modified().__createdAtValue = null;break;
                    case "licenses":
                    		__modified().__licensesValue = null;break;
                    default: throw new IllegalArgumentException("Illegal property name for \"com.doruk.infrastructure.persistence.entity.LicenseAddons\": \"" + prop + "\", it does not exist or its loaded state is not controllable");
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
                    if (__tmpModified == null) {
                        if (base.__isLoaded(PropId.byIndex(SLOT_LICENSES))) {
                            Licenses oldValue = base.licenses();
                            Licenses newValue = __ctx.resolveObject(oldValue);
                            if (oldValue != newValue) {
                                setLicenses(newValue);
                            }
                        }
                        __tmpModified = __modified;
                    }
                    else {
                        __tmpModified.__licensesValue = __ctx.resolveObject(__tmpModified.__licensesValue);
                    }
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
            type = LicenseAddons.class
    )
    class Builder {
        private final Producer.DraftImpl __draft;

        public Builder() {
            this(null);
        }

        public Builder(@org.jspecify.annotations.Nullable LicenseAddons base) {
            __draft = new Producer.DraftImpl(null, base);
        }

        public Builder id(@NonNull UUID id) {
            if (id != null) {
                __draft.setId(id);
            }
            return this;
        }

        public Builder licenseId(@NonNull UUID licenseId) {
            if (licenseId != null) {
                __draft.setLicenseId(licenseId);
            }
            return this;
        }

        public Builder skuId(@NonNull UUID skuId) {
            if (skuId != null) {
                __draft.setSkuId(skuId);
            }
            return this;
        }

        public Builder tierId(@org.jspecify.annotations.Nullable Long tierId) {
            __draft.setTierId(tierId);
            return this;
        }

        public Builder validUntil(@org.jspecify.annotations.Nullable OffsetDateTime validUntil) {
            __draft.setValidUntil(validUntil);
            return this;
        }

        public Builder status(@NonNull LicenseStatus status) {
            if (status != null) {
                __draft.setStatus(status);
            }
            return this;
        }

        public Builder productName(@NonNull String productName) {
            if (productName != null) {
                __draft.setProductName(productName);
            }
            return this;
        }

        public Builder tierName(@NonNull String tierName) {
            if (tierName != null) {
                __draft.setTierName(tierName);
            }
            return this;
        }

        public Builder entitlements(@org.jspecify.annotations.Nullable String entitlements) {
            __draft.setEntitlements(entitlements);
            return this;
        }

        public Builder totalSeats(@NonNull Integer totalSeats) {
            if (totalSeats != null) {
                __draft.setTotalSeats(totalSeats);
            }
            return this;
        }

        public Builder createdAt(@NonNull OffsetDateTime createdAt) {
            if (createdAt != null) {
                __draft.setCreatedAt(createdAt);
            }
            return this;
        }

        public Builder licenses(@NonNull Licenses licenses) {
            if (licenses != null) {
                __draft.setLicenses(licenses);
            }
            return this;
        }

        public LicenseAddons build() {
            return (LicenseAddons)__draft.__modified();
        }
    }
}
