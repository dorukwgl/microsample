package com.doruk.infrastructure.persistence.entity;

import com.doruk.domain.shared.enums.LicenseStatus;
import com.doruk.domain.shared.enums.LicenseType;
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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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
import org.babyfish.jimmer.runtime.NonSharedList;
import org.babyfish.jimmer.runtime.Visibility;
import org.babyfish.jimmer.sql.ManyToOne;
import org.babyfish.jimmer.sql.OneToMany;
import org.jetbrains.annotations.Nullable;
import org.jspecify.annotations.NonNull;

@GeneratedBy(
        type = Licenses.class
)
public interface LicensesDraft extends Licenses, Draft {
    LicensesDraft.Producer $ = Producer.INSTANCE;

    @OldChain
    LicensesDraft setId(UUID id);

    @OldChain
    LicensesDraft setLicenseKey(String licenseKey);

    @OldChain
    LicensesDraft setOrganizationId(UUID organizationId);

    @OldChain
    LicensesDraft setSkuId(UUID skuId);

    @OldChain
    LicensesDraft setTierId(Long tierId);

    @OldChain
    LicensesDraft setType(LicenseType type);

    @OldChain
    LicensesDraft setStatus(LicenseStatus status);

    @OldChain
    LicensesDraft setProductName(String productName);

    @OldChain
    LicensesDraft setTierName(String tierName);

    @OldChain
    LicensesDraft setTotalSeats(int totalSeats);

    @OldChain
    LicensesDraft setAssignedSeats(int assignedSeats);

    @OldChain
    LicensesDraft setValidFrom(OffsetDateTime validFrom);

    @OldChain
    LicensesDraft setValidUntil(OffsetDateTime validUntil);

    @OldChain
    LicensesDraft setEntitlements(String entitlements);

    @OldChain
    LicensesDraft setCreatedAt(OffsetDateTime createdAt);

    @OldChain
    LicensesDraft setUpdatedAt(OffsetDateTime updatedAt);

    OrganizationsDraft organizations();

    OrganizationsDraft organizations(boolean autoCreate);

    @OldChain
    LicensesDraft setOrganizations(Organizations organizations);

    @NonNull
    @JsonIgnore
    UUID organizationsId();

    @OldChain
    LicensesDraft setOrganizationsId(@NonNull UUID organizationsId);

    @OldChain
    LicensesDraft applyOrganizations(DraftConsumer<OrganizationsDraft> block);

    @OldChain
    LicensesDraft applyOrganizations(Organizations base, DraftConsumer<OrganizationsDraft> block);

    List<LicenseAddonsDraft> addons(boolean autoCreate);

    @OldChain
    LicensesDraft setAddons(List<LicenseAddons> addons);

    @OldChain
    LicensesDraft addIntoAddons(DraftConsumer<LicenseAddonsDraft> block);

    @OldChain
    LicensesDraft addIntoAddons(LicenseAddons base, DraftConsumer<LicenseAddonsDraft> block);

    @GeneratedBy(
            type = Licenses.class
    )
    class Producer {
        static final Producer INSTANCE = new Producer();

        public static final int SLOT_ID = 0;

        public static final int SLOT_LICENSE_KEY = 1;

        public static final int SLOT_ORGANIZATION_ID = 2;

        public static final int SLOT_SKU_ID = 3;

        public static final int SLOT_TIER_ID = 4;

        public static final int SLOT_TYPE = 5;

        public static final int SLOT_STATUS = 6;

        public static final int SLOT_PRODUCT_NAME = 7;

        public static final int SLOT_TIER_NAME = 8;

        public static final int SLOT_TOTAL_SEATS = 9;

        public static final int SLOT_ASSIGNED_SEATS = 10;

        public static final int SLOT_VALID_FROM = 11;

        public static final int SLOT_VALID_UNTIL = 12;

        public static final int SLOT_ENTITLEMENTS = 13;

        public static final int SLOT_CREATED_AT = 14;

        public static final int SLOT_UPDATED_AT = 15;

        public static final int SLOT_ORGANIZATIONS = 16;

        public static final int SLOT_ADDONS = 17;

        public static final ImmutableType TYPE = ImmutableType
            .newBuilder(
                "0.10.7",
                Licenses.class,
                Collections.emptyList(),
                (ctx, base) -> new DraftImpl(ctx, (Licenses)base)
            )
            .id(SLOT_ID, "id", UUID.class)
            .add(SLOT_LICENSE_KEY, "licenseKey", ImmutablePropCategory.SCALAR, String.class, false)
            .add(SLOT_ORGANIZATION_ID, "organizationId", ImmutablePropCategory.SCALAR, UUID.class, false)
            .add(SLOT_SKU_ID, "skuId", ImmutablePropCategory.SCALAR, UUID.class, false)
            .add(SLOT_TIER_ID, "tierId", ImmutablePropCategory.SCALAR, Long.class, true)
            .add(SLOT_TYPE, "type", ImmutablePropCategory.SCALAR, LicenseType.class, false)
            .add(SLOT_STATUS, "status", ImmutablePropCategory.SCALAR, LicenseStatus.class, false)
            .add(SLOT_PRODUCT_NAME, "productName", ImmutablePropCategory.SCALAR, String.class, false)
            .add(SLOT_TIER_NAME, "tierName", ImmutablePropCategory.SCALAR, String.class, false)
            .add(SLOT_TOTAL_SEATS, "totalSeats", ImmutablePropCategory.SCALAR, int.class, false)
            .add(SLOT_ASSIGNED_SEATS, "assignedSeats", ImmutablePropCategory.SCALAR, int.class, false)
            .add(SLOT_VALID_FROM, "validFrom", ImmutablePropCategory.SCALAR, OffsetDateTime.class, false)
            .add(SLOT_VALID_UNTIL, "validUntil", ImmutablePropCategory.SCALAR, OffsetDateTime.class, true)
            .add(SLOT_ENTITLEMENTS, "entitlements", ImmutablePropCategory.SCALAR, String.class, true)
            .add(SLOT_CREATED_AT, "createdAt", ImmutablePropCategory.SCALAR, OffsetDateTime.class, false)
            .add(SLOT_UPDATED_AT, "updatedAt", ImmutablePropCategory.SCALAR, OffsetDateTime.class, false)
            .add(SLOT_ORGANIZATIONS, "organizations", ManyToOne.class, Organizations.class, false)
            .add(SLOT_ADDONS, "addons", OneToMany.class, LicenseAddons.class, false)
            .build();

        private Producer() {
        }

        public Licenses produce(DraftConsumer<LicensesDraft> block) {
            return (Licenses)Internal.produce(TYPE, null, block);
        }

        public Licenses produce(Licenses base, DraftConsumer<LicensesDraft> block) {
            return (Licenses)Internal.produce(TYPE, base, block);
        }

        public Licenses produce(boolean resolveImmediately, DraftConsumer<LicensesDraft> block) {
            return (Licenses)Internal.produce(TYPE, null, resolveImmediately, block);
        }

        public Licenses produce(Licenses base, boolean resolveImmediately,
                DraftConsumer<LicensesDraft> block) {
            return (Licenses)Internal.produce(TYPE, base, resolveImmediately, block);
        }

        /**
         * Class, not interface, for free-marker
         */
        @GeneratedBy(
                type = Licenses.class
        )
        @JsonPropertyOrder({"dummyPropForJacksonError__", "id", "licenseKey", "organizationId", "skuId", "tierId", "type", "status", "productName", "tierName", "totalSeats", "assignedSeats", "validFrom", "validUntil", "entitlements", "createdAt", "updatedAt", "organizations", "addons"})
        public abstract static class Implementor implements Licenses, ImmutableSpi {
            @Override
            public final Object __get(PropId prop) {
                int __propIndex = prop.asIndex();
                switch (__propIndex) {
                    case -1:
                    		return __get(prop.asName());
                    case SLOT_ID:
                    		return id();
                    case SLOT_LICENSE_KEY:
                    		return licenseKey();
                    case SLOT_ORGANIZATION_ID:
                    		return organizationId();
                    case SLOT_SKU_ID:
                    		return skuId();
                    case SLOT_TIER_ID:
                    		return tierId();
                    case SLOT_TYPE:
                    		return type();
                    case SLOT_STATUS:
                    		return status();
                    case SLOT_PRODUCT_NAME:
                    		return productName();
                    case SLOT_TIER_NAME:
                    		return tierName();
                    case SLOT_TOTAL_SEATS:
                    		return (Integer)totalSeats();
                    case SLOT_ASSIGNED_SEATS:
                    		return (Integer)assignedSeats();
                    case SLOT_VALID_FROM:
                    		return validFrom();
                    case SLOT_VALID_UNTIL:
                    		return validUntil();
                    case SLOT_ENTITLEMENTS:
                    		return entitlements();
                    case SLOT_CREATED_AT:
                    		return createdAt();
                    case SLOT_UPDATED_AT:
                    		return updatedAt();
                    case SLOT_ORGANIZATIONS:
                    		return organizations();
                    case SLOT_ADDONS:
                    		return addons();
                    default: throw new IllegalArgumentException("Illegal property name for \"com.doruk.infrastructure.persistence.entity.Licenses\": \"" + prop + "\"");
                }
            }

            @Override
            public final Object __get(String prop) {
                switch (prop) {
                    case "id":
                    		return id();
                    case "licenseKey":
                    		return licenseKey();
                    case "organizationId":
                    		return organizationId();
                    case "skuId":
                    		return skuId();
                    case "tierId":
                    		return tierId();
                    case "type":
                    		return type();
                    case "status":
                    		return status();
                    case "productName":
                    		return productName();
                    case "tierName":
                    		return tierName();
                    case "totalSeats":
                    		return (Integer)totalSeats();
                    case "assignedSeats":
                    		return (Integer)assignedSeats();
                    case "validFrom":
                    		return validFrom();
                    case "validUntil":
                    		return validUntil();
                    case "entitlements":
                    		return entitlements();
                    case "createdAt":
                    		return createdAt();
                    case "updatedAt":
                    		return updatedAt();
                    case "organizations":
                    		return organizations();
                    case "addons":
                    		return addons();
                    default: throw new IllegalArgumentException("Illegal property name for \"com.doruk.infrastructure.persistence.entity.Licenses\": \"" + prop + "\"");
                }
            }

            public final UUID getId() {
                return id();
            }

            public final String getLicenseKey() {
                return licenseKey();
            }

            public final UUID getOrganizationId() {
                return organizationId();
            }

            public final UUID getSkuId() {
                return skuId();
            }

            public final Long getTierId() {
                return tierId();
            }

            public final LicenseType getType() {
                return type();
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

            public final int getTotalSeats() {
                return totalSeats();
            }

            public final int getAssignedSeats() {
                return assignedSeats();
            }

            public final OffsetDateTime getValidFrom() {
                return validFrom();
            }

            @Nullable
            public final OffsetDateTime getValidUntil() {
                return validUntil();
            }

            @Nullable
            public final String getEntitlements() {
                return entitlements();
            }

            public final OffsetDateTime getCreatedAt() {
                return createdAt();
            }

            public final OffsetDateTime getUpdatedAt() {
                return updatedAt();
            }

            public final Organizations getOrganizations() {
                return organizations();
            }

            public final List<LicenseAddons> getAddons() {
                return addons();
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
                type = Licenses.class
        )
        private static class Impl extends Implementor implements Cloneable, Serializable {
            private Visibility __visibility;

            UUID __idValue;

            String __licenseKeyValue;

            UUID __organizationIdValue;

            UUID __skuIdValue;

            Long __tierIdValue;

            boolean __tierIdLoaded = false;

            LicenseType __typeValue;

            LicenseStatus __statusValue;

            String __productNameValue;

            String __tierNameValue;

            int __totalSeatsValue;

            boolean __totalSeatsLoaded = false;

            int __assignedSeatsValue;

            boolean __assignedSeatsLoaded = false;

            OffsetDateTime __validFromValue;

            OffsetDateTime __validUntilValue;

            boolean __validUntilLoaded = false;

            String __entitlementsValue;

            boolean __entitlementsLoaded = false;

            OffsetDateTime __createdAtValue;

            OffsetDateTime __updatedAtValue;

            Organizations __organizationsValue;

            NonSharedList<LicenseAddons> __addonsValue;

            @Override
            @JsonIgnore
            public UUID id() {
                if (__idValue == null) {
                    throw new UnloadedException(Licenses.class, "id");
                }
                return __idValue;
            }

            @Override
            @JsonIgnore
            public String licenseKey() {
                if (__licenseKeyValue == null) {
                    throw new UnloadedException(Licenses.class, "licenseKey");
                }
                return __licenseKeyValue;
            }

            @Override
            @JsonIgnore
            public UUID organizationId() {
                if (__organizationIdValue == null) {
                    throw new UnloadedException(Licenses.class, "organizationId");
                }
                return __organizationIdValue;
            }

            @Override
            @JsonIgnore
            public UUID skuId() {
                if (__skuIdValue == null) {
                    throw new UnloadedException(Licenses.class, "skuId");
                }
                return __skuIdValue;
            }

            @Override
            @JsonIgnore
            @org.jspecify.annotations.Nullable
            public Long tierId() {
                if (!__tierIdLoaded) {
                    throw new UnloadedException(Licenses.class, "tierId");
                }
                return __tierIdValue;
            }

            @Override
            @JsonIgnore
            public LicenseType type() {
                if (__typeValue == null) {
                    throw new UnloadedException(Licenses.class, "type");
                }
                return __typeValue;
            }

            @Override
            @JsonIgnore
            public LicenseStatus status() {
                if (__statusValue == null) {
                    throw new UnloadedException(Licenses.class, "status");
                }
                return __statusValue;
            }

            @Override
            @JsonIgnore
            public String productName() {
                if (__productNameValue == null) {
                    throw new UnloadedException(Licenses.class, "productName");
                }
                return __productNameValue;
            }

            @Override
            @JsonIgnore
            public String tierName() {
                if (__tierNameValue == null) {
                    throw new UnloadedException(Licenses.class, "tierName");
                }
                return __tierNameValue;
            }

            @Override
            @JsonIgnore
            public int totalSeats() {
                if (!__totalSeatsLoaded) {
                    throw new UnloadedException(Licenses.class, "totalSeats");
                }
                return __totalSeatsValue;
            }

            @Override
            @JsonIgnore
            public int assignedSeats() {
                if (!__assignedSeatsLoaded) {
                    throw new UnloadedException(Licenses.class, "assignedSeats");
                }
                return __assignedSeatsValue;
            }

            @Override
            @JsonIgnore
            public OffsetDateTime validFrom() {
                if (__validFromValue == null) {
                    throw new UnloadedException(Licenses.class, "validFrom");
                }
                return __validFromValue;
            }

            @Override
            @JsonIgnore
            @org.jspecify.annotations.Nullable
            public OffsetDateTime validUntil() {
                if (!__validUntilLoaded) {
                    throw new UnloadedException(Licenses.class, "validUntil");
                }
                return __validUntilValue;
            }

            @Override
            @JsonIgnore
            @org.jspecify.annotations.Nullable
            public String entitlements() {
                if (!__entitlementsLoaded) {
                    throw new UnloadedException(Licenses.class, "entitlements");
                }
                return __entitlementsValue;
            }

            @Override
            @JsonIgnore
            public OffsetDateTime createdAt() {
                if (__createdAtValue == null) {
                    throw new UnloadedException(Licenses.class, "createdAt");
                }
                return __createdAtValue;
            }

            @Override
            @JsonIgnore
            public OffsetDateTime updatedAt() {
                if (__updatedAtValue == null) {
                    throw new UnloadedException(Licenses.class, "updatedAt");
                }
                return __updatedAtValue;
            }

            @Override
            @JsonIgnore
            public Organizations organizations() {
                if (__organizationsValue == null) {
                    throw new UnloadedException(Licenses.class, "organizations");
                }
                return __organizationsValue;
            }

            @Override
            @JsonIgnore
            public List<LicenseAddons> addons() {
                if (__addonsValue == null) {
                    throw new UnloadedException(Licenses.class, "addons");
                }
                return __addonsValue;
            }

            @Override
            public Impl clone() {
                try {
                    Impl copy = (Impl) super.clone();
                    Visibility originalVisibility = this.__visibility;
                    if (originalVisibility != null) {
                        Visibility newVisibility = Visibility.of(18);
                        for (int propId = 0; propId < 18; propId++) {
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
                    case SLOT_LICENSE_KEY:
                    		return __licenseKeyValue != null;
                    case SLOT_ORGANIZATION_ID:
                    		return __organizationIdValue != null;
                    case SLOT_SKU_ID:
                    		return __skuIdValue != null;
                    case SLOT_TIER_ID:
                    		return __tierIdLoaded;
                    case SLOT_TYPE:
                    		return __typeValue != null;
                    case SLOT_STATUS:
                    		return __statusValue != null;
                    case SLOT_PRODUCT_NAME:
                    		return __productNameValue != null;
                    case SLOT_TIER_NAME:
                    		return __tierNameValue != null;
                    case SLOT_TOTAL_SEATS:
                    		return __totalSeatsLoaded;
                    case SLOT_ASSIGNED_SEATS:
                    		return __assignedSeatsLoaded;
                    case SLOT_VALID_FROM:
                    		return __validFromValue != null;
                    case SLOT_VALID_UNTIL:
                    		return __validUntilLoaded;
                    case SLOT_ENTITLEMENTS:
                    		return __entitlementsLoaded;
                    case SLOT_CREATED_AT:
                    		return __createdAtValue != null;
                    case SLOT_UPDATED_AT:
                    		return __updatedAtValue != null;
                    case SLOT_ORGANIZATIONS:
                    		return __organizationsValue != null;
                    case SLOT_ADDONS:
                    		return __addonsValue != null;
                    default: throw new IllegalArgumentException("Illegal property name for \"com.doruk.infrastructure.persistence.entity.Licenses\": \"" + prop + "\"");
                }
            }

            @Override
            public boolean __isLoaded(String prop) {
                switch (prop) {
                    case "id":
                    		return __idValue != null;
                    case "licenseKey":
                    		return __licenseKeyValue != null;
                    case "organizationId":
                    		return __organizationIdValue != null;
                    case "skuId":
                    		return __skuIdValue != null;
                    case "tierId":
                    		return __tierIdLoaded;
                    case "type":
                    		return __typeValue != null;
                    case "status":
                    		return __statusValue != null;
                    case "productName":
                    		return __productNameValue != null;
                    case "tierName":
                    		return __tierNameValue != null;
                    case "totalSeats":
                    		return __totalSeatsLoaded;
                    case "assignedSeats":
                    		return __assignedSeatsLoaded;
                    case "validFrom":
                    		return __validFromValue != null;
                    case "validUntil":
                    		return __validUntilLoaded;
                    case "entitlements":
                    		return __entitlementsLoaded;
                    case "createdAt":
                    		return __createdAtValue != null;
                    case "updatedAt":
                    		return __updatedAtValue != null;
                    case "organizations":
                    		return __organizationsValue != null;
                    case "addons":
                    		return __addonsValue != null;
                    default: throw new IllegalArgumentException("Illegal property name for \"com.doruk.infrastructure.persistence.entity.Licenses\": \"" + prop + "\"");
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
                    case SLOT_LICENSE_KEY:
                    		return __visibility.visible(SLOT_LICENSE_KEY);
                    case SLOT_ORGANIZATION_ID:
                    		return __visibility.visible(SLOT_ORGANIZATION_ID);
                    case SLOT_SKU_ID:
                    		return __visibility.visible(SLOT_SKU_ID);
                    case SLOT_TIER_ID:
                    		return __visibility.visible(SLOT_TIER_ID);
                    case SLOT_TYPE:
                    		return __visibility.visible(SLOT_TYPE);
                    case SLOT_STATUS:
                    		return __visibility.visible(SLOT_STATUS);
                    case SLOT_PRODUCT_NAME:
                    		return __visibility.visible(SLOT_PRODUCT_NAME);
                    case SLOT_TIER_NAME:
                    		return __visibility.visible(SLOT_TIER_NAME);
                    case SLOT_TOTAL_SEATS:
                    		return __visibility.visible(SLOT_TOTAL_SEATS);
                    case SLOT_ASSIGNED_SEATS:
                    		return __visibility.visible(SLOT_ASSIGNED_SEATS);
                    case SLOT_VALID_FROM:
                    		return __visibility.visible(SLOT_VALID_FROM);
                    case SLOT_VALID_UNTIL:
                    		return __visibility.visible(SLOT_VALID_UNTIL);
                    case SLOT_ENTITLEMENTS:
                    		return __visibility.visible(SLOT_ENTITLEMENTS);
                    case SLOT_CREATED_AT:
                    		return __visibility.visible(SLOT_CREATED_AT);
                    case SLOT_UPDATED_AT:
                    		return __visibility.visible(SLOT_UPDATED_AT);
                    case SLOT_ORGANIZATIONS:
                    		return __visibility.visible(SLOT_ORGANIZATIONS);
                    case SLOT_ADDONS:
                    		return __visibility.visible(SLOT_ADDONS);
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
                    case "licenseKey":
                    		return __visibility.visible(SLOT_LICENSE_KEY);
                    case "organizationId":
                    		return __visibility.visible(SLOT_ORGANIZATION_ID);
                    case "skuId":
                    		return __visibility.visible(SLOT_SKU_ID);
                    case "tierId":
                    		return __visibility.visible(SLOT_TIER_ID);
                    case "type":
                    		return __visibility.visible(SLOT_TYPE);
                    case "status":
                    		return __visibility.visible(SLOT_STATUS);
                    case "productName":
                    		return __visibility.visible(SLOT_PRODUCT_NAME);
                    case "tierName":
                    		return __visibility.visible(SLOT_TIER_NAME);
                    case "totalSeats":
                    		return __visibility.visible(SLOT_TOTAL_SEATS);
                    case "assignedSeats":
                    		return __visibility.visible(SLOT_ASSIGNED_SEATS);
                    case "validFrom":
                    		return __visibility.visible(SLOT_VALID_FROM);
                    case "validUntil":
                    		return __visibility.visible(SLOT_VALID_UNTIL);
                    case "entitlements":
                    		return __visibility.visible(SLOT_ENTITLEMENTS);
                    case "createdAt":
                    		return __visibility.visible(SLOT_CREATED_AT);
                    case "updatedAt":
                    		return __visibility.visible(SLOT_UPDATED_AT);
                    case "organizations":
                    		return __visibility.visible(SLOT_ORGANIZATIONS);
                    case "addons":
                    		return __visibility.visible(SLOT_ADDONS);
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
                if (__licenseKeyValue != null) {
                    hash = 31 * hash + __licenseKeyValue.hashCode();
                }
                if (__organizationIdValue != null) {
                    hash = 31 * hash + __organizationIdValue.hashCode();
                }
                if (__skuIdValue != null) {
                    hash = 31 * hash + __skuIdValue.hashCode();
                }
                if (__tierIdLoaded && __tierIdValue != null) {
                    hash = 31 * hash + __tierIdValue.hashCode();
                }
                if (__typeValue != null) {
                    hash = 31 * hash + __typeValue.hashCode();
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
                if (__totalSeatsLoaded) {
                    hash = 31 * hash + Integer.hashCode(__totalSeatsValue);
                }
                if (__assignedSeatsLoaded) {
                    hash = 31 * hash + Integer.hashCode(__assignedSeatsValue);
                }
                if (__validFromValue != null) {
                    hash = 31 * hash + __validFromValue.hashCode();
                }
                if (__validUntilLoaded && __validUntilValue != null) {
                    hash = 31 * hash + __validUntilValue.hashCode();
                }
                if (__entitlementsLoaded && __entitlementsValue != null) {
                    hash = 31 * hash + __entitlementsValue.hashCode();
                }
                if (__createdAtValue != null) {
                    hash = 31 * hash + __createdAtValue.hashCode();
                }
                if (__updatedAtValue != null) {
                    hash = 31 * hash + __updatedAtValue.hashCode();
                }
                if (__organizationsValue != null) {
                    hash = 31 * hash + __organizationsValue.hashCode();
                }
                if (__addonsValue != null) {
                    hash = 31 * hash + __addonsValue.hashCode();
                }
                return hash;
            }

            private int __shallowHashCode() {
                int hash = __visibility != null ? __visibility.hashCode() : 0;
                if (__idValue != null) {
                    hash = 31 * hash + System.identityHashCode(__idValue);
                }
                if (__licenseKeyValue != null) {
                    hash = 31 * hash + System.identityHashCode(__licenseKeyValue);
                }
                if (__organizationIdValue != null) {
                    hash = 31 * hash + System.identityHashCode(__organizationIdValue);
                }
                if (__skuIdValue != null) {
                    hash = 31 * hash + System.identityHashCode(__skuIdValue);
                }
                if (__tierIdLoaded) {
                    hash = 31 * hash + System.identityHashCode(__tierIdValue);
                }
                if (__typeValue != null) {
                    hash = 31 * hash + System.identityHashCode(__typeValue);
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
                if (__totalSeatsLoaded) {
                    hash = 31 * hash + Integer.hashCode(__totalSeatsValue);
                }
                if (__assignedSeatsLoaded) {
                    hash = 31 * hash + Integer.hashCode(__assignedSeatsValue);
                }
                if (__validFromValue != null) {
                    hash = 31 * hash + System.identityHashCode(__validFromValue);
                }
                if (__validUntilLoaded) {
                    hash = 31 * hash + System.identityHashCode(__validUntilValue);
                }
                if (__entitlementsLoaded) {
                    hash = 31 * hash + System.identityHashCode(__entitlementsValue);
                }
                if (__createdAtValue != null) {
                    hash = 31 * hash + System.identityHashCode(__createdAtValue);
                }
                if (__updatedAtValue != null) {
                    hash = 31 * hash + System.identityHashCode(__updatedAtValue);
                }
                if (__organizationsValue != null) {
                    hash = 31 * hash + System.identityHashCode(__organizationsValue);
                }
                if (__addonsValue != null) {
                    hash = 31 * hash + System.identityHashCode(__addonsValue);
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
                if (__isVisible(PropId.byIndex(SLOT_LICENSE_KEY)) != __other.__isVisible(PropId.byIndex(SLOT_LICENSE_KEY))) {
                    return false;
                }
                boolean __licenseKeyLoaded = __licenseKeyValue != null;
                if (__licenseKeyLoaded != __other.__isLoaded(PropId.byIndex(SLOT_LICENSE_KEY))) {
                    return false;
                }
                if (__licenseKeyLoaded && !Objects.equals(__licenseKeyValue, __other.licenseKey())) {
                    return false;
                }
                if (__isVisible(PropId.byIndex(SLOT_ORGANIZATION_ID)) != __other.__isVisible(PropId.byIndex(SLOT_ORGANIZATION_ID))) {
                    return false;
                }
                boolean __organizationIdLoaded = __organizationIdValue != null;
                if (__organizationIdLoaded != __other.__isLoaded(PropId.byIndex(SLOT_ORGANIZATION_ID))) {
                    return false;
                }
                if (__organizationIdLoaded && !Objects.equals(__organizationIdValue, __other.organizationId())) {
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
                if (__isVisible(PropId.byIndex(SLOT_TYPE)) != __other.__isVisible(PropId.byIndex(SLOT_TYPE))) {
                    return false;
                }
                boolean __typeLoaded = __typeValue != null;
                if (__typeLoaded != __other.__isLoaded(PropId.byIndex(SLOT_TYPE))) {
                    return false;
                }
                if (__typeLoaded && !Objects.equals(__typeValue, __other.type())) {
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
                if (__isVisible(PropId.byIndex(SLOT_ASSIGNED_SEATS)) != __other.__isVisible(PropId.byIndex(SLOT_ASSIGNED_SEATS))) {
                    return false;
                }
                boolean __assignedSeatsLoaded = this.__assignedSeatsLoaded;
                if (__assignedSeatsLoaded != __other.__isLoaded(PropId.byIndex(SLOT_ASSIGNED_SEATS))) {
                    return false;
                }
                if (__assignedSeatsLoaded && __assignedSeatsValue != __other.assignedSeats()) {
                    return false;
                }
                if (__isVisible(PropId.byIndex(SLOT_VALID_FROM)) != __other.__isVisible(PropId.byIndex(SLOT_VALID_FROM))) {
                    return false;
                }
                boolean __validFromLoaded = __validFromValue != null;
                if (__validFromLoaded != __other.__isLoaded(PropId.byIndex(SLOT_VALID_FROM))) {
                    return false;
                }
                if (__validFromLoaded && !Objects.equals(__validFromValue, __other.validFrom())) {
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
                if (__isVisible(PropId.byIndex(SLOT_ORGANIZATIONS)) != __other.__isVisible(PropId.byIndex(SLOT_ORGANIZATIONS))) {
                    return false;
                }
                boolean __organizationsLoaded = __organizationsValue != null;
                if (__organizationsLoaded != __other.__isLoaded(PropId.byIndex(SLOT_ORGANIZATIONS))) {
                    return false;
                }
                if (__organizationsLoaded && !Objects.equals(__organizationsValue, __other.organizations())) {
                    return false;
                }
                if (__isVisible(PropId.byIndex(SLOT_ADDONS)) != __other.__isVisible(PropId.byIndex(SLOT_ADDONS))) {
                    return false;
                }
                boolean __addonsLoaded = __addonsValue != null;
                if (__addonsLoaded != __other.__isLoaded(PropId.byIndex(SLOT_ADDONS))) {
                    return false;
                }
                if (__addonsLoaded && !Objects.equals(__addonsValue, __other.addons())) {
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
                if (__isVisible(PropId.byIndex(SLOT_LICENSE_KEY)) != __other.__isVisible(PropId.byIndex(SLOT_LICENSE_KEY))) {
                    return false;
                }
                boolean __licenseKeyLoaded = __licenseKeyValue != null;
                if (__licenseKeyLoaded != __other.__isLoaded(PropId.byIndex(SLOT_LICENSE_KEY))) {
                    return false;
                }
                if (__licenseKeyLoaded && __licenseKeyValue != __other.licenseKey()) {
                    return false;
                }
                if (__isVisible(PropId.byIndex(SLOT_ORGANIZATION_ID)) != __other.__isVisible(PropId.byIndex(SLOT_ORGANIZATION_ID))) {
                    return false;
                }
                boolean __organizationIdLoaded = __organizationIdValue != null;
                if (__organizationIdLoaded != __other.__isLoaded(PropId.byIndex(SLOT_ORGANIZATION_ID))) {
                    return false;
                }
                if (__organizationIdLoaded && __organizationIdValue != __other.organizationId()) {
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
                if (__isVisible(PropId.byIndex(SLOT_TYPE)) != __other.__isVisible(PropId.byIndex(SLOT_TYPE))) {
                    return false;
                }
                boolean __typeLoaded = __typeValue != null;
                if (__typeLoaded != __other.__isLoaded(PropId.byIndex(SLOT_TYPE))) {
                    return false;
                }
                if (__typeLoaded && __typeValue != __other.type()) {
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
                if (__isVisible(PropId.byIndex(SLOT_ASSIGNED_SEATS)) != __other.__isVisible(PropId.byIndex(SLOT_ASSIGNED_SEATS))) {
                    return false;
                }
                boolean __assignedSeatsLoaded = this.__assignedSeatsLoaded;
                if (__assignedSeatsLoaded != __other.__isLoaded(PropId.byIndex(SLOT_ASSIGNED_SEATS))) {
                    return false;
                }
                if (__assignedSeatsLoaded && __assignedSeatsValue != __other.assignedSeats()) {
                    return false;
                }
                if (__isVisible(PropId.byIndex(SLOT_VALID_FROM)) != __other.__isVisible(PropId.byIndex(SLOT_VALID_FROM))) {
                    return false;
                }
                boolean __validFromLoaded = __validFromValue != null;
                if (__validFromLoaded != __other.__isLoaded(PropId.byIndex(SLOT_VALID_FROM))) {
                    return false;
                }
                if (__validFromLoaded && __validFromValue != __other.validFrom()) {
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
                if (__isVisible(PropId.byIndex(SLOT_ORGANIZATIONS)) != __other.__isVisible(PropId.byIndex(SLOT_ORGANIZATIONS))) {
                    return false;
                }
                boolean __organizationsLoaded = __organizationsValue != null;
                if (__organizationsLoaded != __other.__isLoaded(PropId.byIndex(SLOT_ORGANIZATIONS))) {
                    return false;
                }
                if (__organizationsLoaded && __organizationsValue != __other.organizations()) {
                    return false;
                }
                if (__isVisible(PropId.byIndex(SLOT_ADDONS)) != __other.__isVisible(PropId.byIndex(SLOT_ADDONS))) {
                    return false;
                }
                boolean __addonsLoaded = __addonsValue != null;
                if (__addonsLoaded != __other.__isLoaded(PropId.byIndex(SLOT_ADDONS))) {
                    return false;
                }
                if (__addonsLoaded && __addonsValue != __other.addons()) {
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
                type = Licenses.class
        )
        private static class DraftImpl extends Implementor implements DraftSpi, LicensesDraft {
            private DraftContext __ctx;

            private Impl __base;

            private Impl __modified;

            private boolean __resolving;

            private Licenses __resolved;

            DraftImpl(DraftContext ctx, Licenses base) {
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
            public LicensesDraft setId(UUID id) {
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
            public String licenseKey() {
                return (__modified!= null ? __modified : __base).licenseKey();
            }

            @Override
            public LicensesDraft setLicenseKey(String licenseKey) {
                if (__resolved != null) {
                    throw new IllegalStateException("The current draft has been resolved so it cannot be modified");
                }
                if (licenseKey == null) {
                    throw new IllegalArgumentException(
                        "'licenseKey' cannot be null, please specify non-null value or use nullable annotation to decorate this property"
                    );
                }
                Impl __tmpModified = __modified();
                __tmpModified.__licenseKeyValue = licenseKey;
                return this;
            }

            @Override
            @JsonIgnore
            public UUID organizationId() {
                return (__modified!= null ? __modified : __base).organizationId();
            }

            @Override
            public LicensesDraft setOrganizationId(UUID organizationId) {
                if (__resolved != null) {
                    throw new IllegalStateException("The current draft has been resolved so it cannot be modified");
                }
                if (organizationId == null) {
                    throw new IllegalArgumentException(
                        "'organizationId' cannot be null, please specify non-null value or use nullable annotation to decorate this property"
                    );
                }
                Impl __tmpModified = __modified();
                __tmpModified.__organizationIdValue = organizationId;
                return this;
            }

            @Override
            @JsonIgnore
            public UUID skuId() {
                return (__modified!= null ? __modified : __base).skuId();
            }

            @Override
            public LicensesDraft setSkuId(UUID skuId) {
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
            public LicensesDraft setTierId(Long tierId) {
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
            public LicenseType type() {
                return (__modified!= null ? __modified : __base).type();
            }

            @Override
            public LicensesDraft setType(LicenseType type) {
                if (__resolved != null) {
                    throw new IllegalStateException("The current draft has been resolved so it cannot be modified");
                }
                if (type == null) {
                    throw new IllegalArgumentException(
                        "'type' cannot be null, please specify non-null value or use nullable annotation to decorate this property"
                    );
                }
                Impl __tmpModified = __modified();
                __tmpModified.__typeValue = type;
                return this;
            }

            @Override
            @JsonIgnore
            public LicenseStatus status() {
                return (__modified!= null ? __modified : __base).status();
            }

            @Override
            public LicensesDraft setStatus(LicenseStatus status) {
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
            public LicensesDraft setProductName(String productName) {
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
            public LicensesDraft setTierName(String tierName) {
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
            public int totalSeats() {
                return (__modified!= null ? __modified : __base).totalSeats();
            }

            @Override
            public LicensesDraft setTotalSeats(int totalSeats) {
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
            public int assignedSeats() {
                return (__modified!= null ? __modified : __base).assignedSeats();
            }

            @Override
            public LicensesDraft setAssignedSeats(int assignedSeats) {
                if (__resolved != null) {
                    throw new IllegalStateException("The current draft has been resolved so it cannot be modified");
                }
                Impl __tmpModified = __modified();
                __tmpModified.__assignedSeatsValue = assignedSeats;
                __tmpModified.__assignedSeatsLoaded = true;
                return this;
            }

            @Override
            @JsonIgnore
            public OffsetDateTime validFrom() {
                return (__modified!= null ? __modified : __base).validFrom();
            }

            @Override
            public LicensesDraft setValidFrom(OffsetDateTime validFrom) {
                if (__resolved != null) {
                    throw new IllegalStateException("The current draft has been resolved so it cannot be modified");
                }
                if (validFrom == null) {
                    throw new IllegalArgumentException(
                        "'validFrom' cannot be null, please specify non-null value or use nullable annotation to decorate this property"
                    );
                }
                Impl __tmpModified = __modified();
                __tmpModified.__validFromValue = validFrom;
                return this;
            }

            @Override
            @JsonIgnore
            @org.jspecify.annotations.Nullable
            public OffsetDateTime validUntil() {
                return (__modified!= null ? __modified : __base).validUntil();
            }

            @Override
            public LicensesDraft setValidUntil(OffsetDateTime validUntil) {
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
            @org.jspecify.annotations.Nullable
            public String entitlements() {
                return (__modified!= null ? __modified : __base).entitlements();
            }

            @Override
            public LicensesDraft setEntitlements(String entitlements) {
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
            public OffsetDateTime createdAt() {
                return (__modified!= null ? __modified : __base).createdAt();
            }

            @Override
            public LicensesDraft setCreatedAt(OffsetDateTime createdAt) {
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
            public LicensesDraft setUpdatedAt(OffsetDateTime updatedAt) {
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

            @Override
            @JsonIgnore
            public OrganizationsDraft organizations() {
                return __ctx.toDraftObject((__modified!= null ? __modified : __base).organizations());
            }

            @Override
            public OrganizationsDraft organizations(boolean autoCreate) {
                if (autoCreate && (!__isLoaded(PropId.byIndex(SLOT_ORGANIZATIONS)))) {
                    setOrganizations(OrganizationsDraft.$.produce(null, null));
                }
                return __ctx.toDraftObject((__modified!= null ? __modified : __base).organizations());
            }

            @Override
            public LicensesDraft setOrganizations(Organizations organizations) {
                if (__resolved != null) {
                    throw new IllegalStateException("The current draft has been resolved so it cannot be modified");
                }
                if (organizations == null) {
                    throw new IllegalArgumentException(
                        "'organizations' cannot be null, please specify non-null value or use nullable annotation to decorate this property"
                    );
                }
                Impl __tmpModified = __modified();
                __tmpModified.__organizationsValue = organizations;
                return this;
            }

            @NonNull
            @JsonIgnore
            @Override
            public UUID organizationsId() {
                return organizations().id();
            }

            @OldChain
            @Override
            public LicensesDraft setOrganizationsId(@NonNull UUID organizationsId) {
                organizations(true).setId(Objects.requireNonNull(organizationsId, "\"organizations\" cannot be null"));
                return this;
            }

            @Override
            public LicensesDraft applyOrganizations(DraftConsumer<OrganizationsDraft> block) {
                applyOrganizations(null, block);
                return this;
            }

            @Override
            public LicensesDraft applyOrganizations(Organizations base,
                    DraftConsumer<OrganizationsDraft> block) {
                setOrganizations(OrganizationsDraft.$.produce(base, block));
                return this;
            }

            @Override
            @JsonIgnore
            public List<LicenseAddons> addons() {
                return __ctx.toDraftList((__modified!= null ? __modified : __base).addons(), LicenseAddons.class, true);
            }

            @Override
            public List<LicenseAddonsDraft> addons(boolean autoCreate) {
                if (autoCreate && (!__isLoaded(PropId.byIndex(SLOT_ADDONS)))) {
                    setAddons(new ArrayList<>());
                }
                return __ctx.toDraftList((__modified!= null ? __modified : __base).addons(), LicenseAddons.class, true);
            }

            @Override
            public LicensesDraft setAddons(List<LicenseAddons> addons) {
                if (__resolved != null) {
                    throw new IllegalStateException("The current draft has been resolved so it cannot be modified");
                }
                if (addons == null) {
                    throw new IllegalArgumentException(
                        "'addons' cannot be null, please specify non-null value or use nullable annotation to decorate this property"
                    );
                }
                Impl __tmpModified = __modified();
                __tmpModified.__addonsValue = NonSharedList.of(__tmpModified.__addonsValue, addons);
                return this;
            }

            @Override
            public LicensesDraft addIntoAddons(DraftConsumer<LicenseAddonsDraft> block) {
                addIntoAddons(null, block);
                return this;
            }

            @Override
            public LicensesDraft addIntoAddons(LicenseAddons base,
                    DraftConsumer<LicenseAddonsDraft> block) {
                addons(true).add((LicenseAddonsDraft)LicenseAddonsDraft.$.produce(base, block));
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
                    case SLOT_LICENSE_KEY:
                    		setLicenseKey((String)value);break;
                    case SLOT_ORGANIZATION_ID:
                    		setOrganizationId((UUID)value);break;
                    case SLOT_SKU_ID:
                    		setSkuId((UUID)value);break;
                    case SLOT_TIER_ID:
                    		setTierId((Long)value);break;
                    case SLOT_TYPE:
                    		setType((LicenseType)value);break;
                    case SLOT_STATUS:
                    		setStatus((LicenseStatus)value);break;
                    case SLOT_PRODUCT_NAME:
                    		setProductName((String)value);break;
                    case SLOT_TIER_NAME:
                    		setTierName((String)value);break;
                    case SLOT_TOTAL_SEATS:
                    		if (value == null) throw new IllegalArgumentException("'totalSeats' cannot be null, if you want to set null, please use any annotation whose simple name is \"Nullable\" to decorate the property");
                            setTotalSeats((Integer)value);
                            break;
                    case SLOT_ASSIGNED_SEATS:
                    		if (value == null) throw new IllegalArgumentException("'assignedSeats' cannot be null, if you want to set null, please use any annotation whose simple name is \"Nullable\" to decorate the property");
                            setAssignedSeats((Integer)value);
                            break;
                    case SLOT_VALID_FROM:
                    		setValidFrom((OffsetDateTime)value);break;
                    case SLOT_VALID_UNTIL:
                    		setValidUntil((OffsetDateTime)value);break;
                    case SLOT_ENTITLEMENTS:
                    		setEntitlements((String)value);break;
                    case SLOT_CREATED_AT:
                    		setCreatedAt((OffsetDateTime)value);break;
                    case SLOT_UPDATED_AT:
                    		setUpdatedAt((OffsetDateTime)value);break;
                    case SLOT_ORGANIZATIONS:
                    		setOrganizations((Organizations)value);break;
                    case SLOT_ADDONS:
                    		setAddons((List<LicenseAddons>)value);break;
                    default: throw new IllegalArgumentException("Illegal property id for \"com.doruk.infrastructure.persistence.entity.Licenses\": \"" + prop + "\"");
                }
            }

            @SuppressWarnings("all")
            @Override
            public void __set(String prop, Object value) {
                switch (prop) {
                    case "id":
                    		setId((UUID)value);break;
                    case "licenseKey":
                    		setLicenseKey((String)value);break;
                    case "organizationId":
                    		setOrganizationId((UUID)value);break;
                    case "skuId":
                    		setSkuId((UUID)value);break;
                    case "tierId":
                    		setTierId((Long)value);break;
                    case "type":
                    		setType((LicenseType)value);break;
                    case "status":
                    		setStatus((LicenseStatus)value);break;
                    case "productName":
                    		setProductName((String)value);break;
                    case "tierName":
                    		setTierName((String)value);break;
                    case "totalSeats":
                    		if (value == null) throw new IllegalArgumentException("'totalSeats' cannot be null, if you want to set null, please use any annotation whose simple name is \"Nullable\" to decorate the property");
                            setTotalSeats((Integer)value);
                            break;
                    case "assignedSeats":
                    		if (value == null) throw new IllegalArgumentException("'assignedSeats' cannot be null, if you want to set null, please use any annotation whose simple name is \"Nullable\" to decorate the property");
                            setAssignedSeats((Integer)value);
                            break;
                    case "validFrom":
                    		setValidFrom((OffsetDateTime)value);break;
                    case "validUntil":
                    		setValidUntil((OffsetDateTime)value);break;
                    case "entitlements":
                    		setEntitlements((String)value);break;
                    case "createdAt":
                    		setCreatedAt((OffsetDateTime)value);break;
                    case "updatedAt":
                    		setUpdatedAt((OffsetDateTime)value);break;
                    case "organizations":
                    		setOrganizations((Organizations)value);break;
                    case "addons":
                    		setAddons((List<LicenseAddons>)value);break;
                    default: throw new IllegalArgumentException("Illegal property name for \"com.doruk.infrastructure.persistence.entity.Licenses\": \"" + prop + "\"");
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
                    __modified().__visibility = __visibility = Visibility.of(18);
                }
                int __propIndex = prop.asIndex();
                switch (__propIndex) {
                    case -1:
                    		__show(prop.asName(), visible);
                    return;
                    case SLOT_ID:
                    		__visibility.show(SLOT_ID, visible);break;
                    case SLOT_LICENSE_KEY:
                    		__visibility.show(SLOT_LICENSE_KEY, visible);break;
                    case SLOT_ORGANIZATION_ID:
                    		__visibility.show(SLOT_ORGANIZATION_ID, visible);break;
                    case SLOT_SKU_ID:
                    		__visibility.show(SLOT_SKU_ID, visible);break;
                    case SLOT_TIER_ID:
                    		__visibility.show(SLOT_TIER_ID, visible);break;
                    case SLOT_TYPE:
                    		__visibility.show(SLOT_TYPE, visible);break;
                    case SLOT_STATUS:
                    		__visibility.show(SLOT_STATUS, visible);break;
                    case SLOT_PRODUCT_NAME:
                    		__visibility.show(SLOT_PRODUCT_NAME, visible);break;
                    case SLOT_TIER_NAME:
                    		__visibility.show(SLOT_TIER_NAME, visible);break;
                    case SLOT_TOTAL_SEATS:
                    		__visibility.show(SLOT_TOTAL_SEATS, visible);break;
                    case SLOT_ASSIGNED_SEATS:
                    		__visibility.show(SLOT_ASSIGNED_SEATS, visible);break;
                    case SLOT_VALID_FROM:
                    		__visibility.show(SLOT_VALID_FROM, visible);break;
                    case SLOT_VALID_UNTIL:
                    		__visibility.show(SLOT_VALID_UNTIL, visible);break;
                    case SLOT_ENTITLEMENTS:
                    		__visibility.show(SLOT_ENTITLEMENTS, visible);break;
                    case SLOT_CREATED_AT:
                    		__visibility.show(SLOT_CREATED_AT, visible);break;
                    case SLOT_UPDATED_AT:
                    		__visibility.show(SLOT_UPDATED_AT, visible);break;
                    case SLOT_ORGANIZATIONS:
                    		__visibility.show(SLOT_ORGANIZATIONS, visible);break;
                    case SLOT_ADDONS:
                    		__visibility.show(SLOT_ADDONS, visible);break;
                    default: throw new IllegalArgumentException(
                                "Illegal property id for \"com.doruk.infrastructure.persistence.entity.Licenses\": \"" + 
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
                    __modified().__visibility = __visibility = Visibility.of(18);
                }
                switch (prop) {
                    case "id":
                    		__visibility.show(SLOT_ID, visible);break;
                    case "licenseKey":
                    		__visibility.show(SLOT_LICENSE_KEY, visible);break;
                    case "organizationId":
                    		__visibility.show(SLOT_ORGANIZATION_ID, visible);break;
                    case "skuId":
                    		__visibility.show(SLOT_SKU_ID, visible);break;
                    case "tierId":
                    		__visibility.show(SLOT_TIER_ID, visible);break;
                    case "type":
                    		__visibility.show(SLOT_TYPE, visible);break;
                    case "status":
                    		__visibility.show(SLOT_STATUS, visible);break;
                    case "productName":
                    		__visibility.show(SLOT_PRODUCT_NAME, visible);break;
                    case "tierName":
                    		__visibility.show(SLOT_TIER_NAME, visible);break;
                    case "totalSeats":
                    		__visibility.show(SLOT_TOTAL_SEATS, visible);break;
                    case "assignedSeats":
                    		__visibility.show(SLOT_ASSIGNED_SEATS, visible);break;
                    case "validFrom":
                    		__visibility.show(SLOT_VALID_FROM, visible);break;
                    case "validUntil":
                    		__visibility.show(SLOT_VALID_UNTIL, visible);break;
                    case "entitlements":
                    		__visibility.show(SLOT_ENTITLEMENTS, visible);break;
                    case "createdAt":
                    		__visibility.show(SLOT_CREATED_AT, visible);break;
                    case "updatedAt":
                    		__visibility.show(SLOT_UPDATED_AT, visible);break;
                    case "organizations":
                    		__visibility.show(SLOT_ORGANIZATIONS, visible);break;
                    case "addons":
                    		__visibility.show(SLOT_ADDONS, visible);break;
                    default: throw new IllegalArgumentException(
                                "Illegal property name for \"com.doruk.infrastructure.persistence.entity.Licenses\": \"" + 
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
                    case SLOT_LICENSE_KEY:
                    		__modified().__licenseKeyValue = null;break;
                    case SLOT_ORGANIZATION_ID:
                    		__modified().__organizationIdValue = null;break;
                    case SLOT_SKU_ID:
                    		__modified().__skuIdValue = null;break;
                    case SLOT_TIER_ID:
                    		__modified().__tierIdValue = null;
                    __modified().__tierIdLoaded = false;break;
                    case SLOT_TYPE:
                    		__modified().__typeValue = null;break;
                    case SLOT_STATUS:
                    		__modified().__statusValue = null;break;
                    case SLOT_PRODUCT_NAME:
                    		__modified().__productNameValue = null;break;
                    case SLOT_TIER_NAME:
                    		__modified().__tierNameValue = null;break;
                    case SLOT_TOTAL_SEATS:
                    		__modified().__totalSeatsValue = 0;
                    __modified().__totalSeatsLoaded = false;break;
                    case SLOT_ASSIGNED_SEATS:
                    		__modified().__assignedSeatsValue = 0;
                    __modified().__assignedSeatsLoaded = false;break;
                    case SLOT_VALID_FROM:
                    		__modified().__validFromValue = null;break;
                    case SLOT_VALID_UNTIL:
                    		__modified().__validUntilValue = null;
                    __modified().__validUntilLoaded = false;break;
                    case SLOT_ENTITLEMENTS:
                    		__modified().__entitlementsValue = null;
                    __modified().__entitlementsLoaded = false;break;
                    case SLOT_CREATED_AT:
                    		__modified().__createdAtValue = null;break;
                    case SLOT_UPDATED_AT:
                    		__modified().__updatedAtValue = null;break;
                    case SLOT_ORGANIZATIONS:
                    		__modified().__organizationsValue = null;break;
                    case SLOT_ADDONS:
                    		__modified().__addonsValue = null;break;
                    default: throw new IllegalArgumentException("Illegal property id for \"com.doruk.infrastructure.persistence.entity.Licenses\": \"" + prop + "\", it does not exist or its loaded state is not controllable");
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
                    case "licenseKey":
                    		__modified().__licenseKeyValue = null;break;
                    case "organizationId":
                    		__modified().__organizationIdValue = null;break;
                    case "skuId":
                    		__modified().__skuIdValue = null;break;
                    case "tierId":
                    		__modified().__tierIdValue = null;
                    __modified().__tierIdLoaded = false;break;
                    case "type":
                    		__modified().__typeValue = null;break;
                    case "status":
                    		__modified().__statusValue = null;break;
                    case "productName":
                    		__modified().__productNameValue = null;break;
                    case "tierName":
                    		__modified().__tierNameValue = null;break;
                    case "totalSeats":
                    		__modified().__totalSeatsValue = 0;
                    __modified().__totalSeatsLoaded = false;break;
                    case "assignedSeats":
                    		__modified().__assignedSeatsValue = 0;
                    __modified().__assignedSeatsLoaded = false;break;
                    case "validFrom":
                    		__modified().__validFromValue = null;break;
                    case "validUntil":
                    		__modified().__validUntilValue = null;
                    __modified().__validUntilLoaded = false;break;
                    case "entitlements":
                    		__modified().__entitlementsValue = null;
                    __modified().__entitlementsLoaded = false;break;
                    case "createdAt":
                    		__modified().__createdAtValue = null;break;
                    case "updatedAt":
                    		__modified().__updatedAtValue = null;break;
                    case "organizations":
                    		__modified().__organizationsValue = null;break;
                    case "addons":
                    		__modified().__addonsValue = null;break;
                    default: throw new IllegalArgumentException("Illegal property name for \"com.doruk.infrastructure.persistence.entity.Licenses\": \"" + prop + "\", it does not exist or its loaded state is not controllable");
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
                        if (base.__isLoaded(PropId.byIndex(SLOT_ORGANIZATIONS))) {
                            Organizations oldValue = base.organizations();
                            Organizations newValue = __ctx.resolveObject(oldValue);
                            if (oldValue != newValue) {
                                setOrganizations(newValue);
                            }
                        }
                        if (base.__isLoaded(PropId.byIndex(SLOT_ADDONS))) {
                            List<LicenseAddons> oldValue = base.addons();
                            List<LicenseAddons> newValue = __ctx.resolveList(oldValue);
                            if (oldValue != newValue) {
                                setAddons(newValue);
                            }
                        }
                        __tmpModified = __modified;
                    }
                    else {
                        __tmpModified.__organizationsValue = __ctx.resolveObject(__tmpModified.__organizationsValue);
                        __tmpModified.__addonsValue = NonSharedList.of(__tmpModified.__addonsValue, __ctx.resolveList(__tmpModified.__addonsValue));
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
            type = Licenses.class
    )
    class Builder {
        private final Producer.DraftImpl __draft;

        public Builder() {
            this(null);
        }

        public Builder(@org.jspecify.annotations.Nullable Licenses base) {
            __draft = new Producer.DraftImpl(null, base);
        }

        public Builder id(@NonNull UUID id) {
            if (id != null) {
                __draft.setId(id);
            }
            return this;
        }

        public Builder licenseKey(@NonNull String licenseKey) {
            if (licenseKey != null) {
                __draft.setLicenseKey(licenseKey);
            }
            return this;
        }

        public Builder organizationId(@NonNull UUID organizationId) {
            if (organizationId != null) {
                __draft.setOrganizationId(organizationId);
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

        public Builder type(@NonNull LicenseType type) {
            if (type != null) {
                __draft.setType(type);
            }
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

        public Builder totalSeats(@NonNull Integer totalSeats) {
            if (totalSeats != null) {
                __draft.setTotalSeats(totalSeats);
            }
            return this;
        }

        public Builder assignedSeats(@NonNull Integer assignedSeats) {
            if (assignedSeats != null) {
                __draft.setAssignedSeats(assignedSeats);
            }
            return this;
        }

        public Builder validFrom(@NonNull OffsetDateTime validFrom) {
            if (validFrom != null) {
                __draft.setValidFrom(validFrom);
            }
            return this;
        }

        public Builder validUntil(@org.jspecify.annotations.Nullable OffsetDateTime validUntil) {
            __draft.setValidUntil(validUntil);
            return this;
        }

        public Builder entitlements(@org.jspecify.annotations.Nullable String entitlements) {
            __draft.setEntitlements(entitlements);
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

        public Builder organizations(@NonNull Organizations organizations) {
            if (organizations != null) {
                __draft.setOrganizations(organizations);
            }
            return this;
        }

        public Builder addons(@NonNull List<LicenseAddons> addons) {
            if (addons != null) {
                __draft.setAddons(addons);
            }
            return this;
        }

        public Licenses build() {
            return (Licenses)__draft.__modified();
        }
    }
}
