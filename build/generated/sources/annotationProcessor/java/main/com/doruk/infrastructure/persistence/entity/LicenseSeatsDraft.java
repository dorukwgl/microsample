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
import org.babyfish.jimmer.sql.ManyToOne;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

@GeneratedBy(
        type = LicenseSeats.class
)
public interface LicenseSeatsDraft extends LicenseSeats, Draft {
    LicenseSeatsDraft.Producer $ = Producer.INSTANCE;

    @OldChain
    LicenseSeatsDraft setId(UUID id);

    LicensesDraft licenses();

    LicensesDraft licenses(boolean autoCreate);

    @OldChain
    LicenseSeatsDraft setLicenses(Licenses licenses);

    @OldChain
    LicenseSeatsDraft applyLicenses(DraftConsumer<LicensesDraft> block);

    @OldChain
    LicenseSeatsDraft applyLicenses(Licenses base, DraftConsumer<LicensesDraft> block);

    @OldChain
    LicenseSeatsDraft setLicenseId(UUID licenseId);

    UserDraft user();

    UserDraft user(boolean autoCreate);

    @OldChain
    LicenseSeatsDraft setUser(User user);

    @OldChain
    LicenseSeatsDraft applyUser(DraftConsumer<UserDraft> block);

    @OldChain
    LicenseSeatsDraft applyUser(User base, DraftConsumer<UserDraft> block);

    @OldChain
    LicenseSeatsDraft setUserId(UUID userId);

    @OldChain
    LicenseSeatsDraft setAssignedAt(OffsetDateTime assignedAt);

    UserDraft assignedBy();

    UserDraft assignedBy(boolean autoCreate);

    @OldChain
    LicenseSeatsDraft setAssignedBy(User assignedBy);

    @OldChain
    LicenseSeatsDraft applyAssignedBy(DraftConsumer<UserDraft> block);

    @OldChain
    LicenseSeatsDraft applyAssignedBy(User base, DraftConsumer<UserDraft> block);

    @OldChain
    LicenseSeatsDraft setAssignedById(UUID assignedById);

    @GeneratedBy(
            type = LicenseSeats.class
    )
    class Producer {
        static final Producer INSTANCE = new Producer();

        public static final int SLOT_ID = 0;

        public static final int SLOT_LICENSES = 1;

        public static final int SLOT_LICENSE_ID = 2;

        public static final int SLOT_USER = 3;

        public static final int SLOT_USER_ID = 4;

        public static final int SLOT_ASSIGNED_AT = 5;

        public static final int SLOT_ASSIGNED_BY = 6;

        public static final int SLOT_ASSIGNED_BY_ID = 7;

        public static final ImmutableType TYPE = ImmutableType
            .newBuilder(
                "0.10.6",
                LicenseSeats.class,
                Collections.emptyList(),
                (ctx, base) -> new DraftImpl(ctx, (LicenseSeats)base)
            )
            .id(SLOT_ID, "id", UUID.class)
            .add(SLOT_LICENSES, "licenses", ManyToOne.class, Licenses.class, false)
            .add(SLOT_LICENSE_ID, "licenseId", ImmutablePropCategory.SCALAR, UUID.class, false)
            .add(SLOT_USER, "user", ManyToOne.class, User.class, false)
            .add(SLOT_USER_ID, "userId", ImmutablePropCategory.SCALAR, UUID.class, false)
            .add(SLOT_ASSIGNED_AT, "assignedAt", ImmutablePropCategory.SCALAR, OffsetDateTime.class, false)
            .add(SLOT_ASSIGNED_BY, "assignedBy", ManyToOne.class, User.class, false)
            .add(SLOT_ASSIGNED_BY_ID, "assignedById", ImmutablePropCategory.SCALAR, UUID.class, false)
            .build();

        private Producer() {
        }

        public LicenseSeats produce(DraftConsumer<LicenseSeatsDraft> block) {
            return (LicenseSeats)Internal.produce(TYPE, null, block);
        }

        public LicenseSeats produce(LicenseSeats base, DraftConsumer<LicenseSeatsDraft> block) {
            return (LicenseSeats)Internal.produce(TYPE, base, block);
        }

        public LicenseSeats produce(boolean resolveImmediately,
                DraftConsumer<LicenseSeatsDraft> block) {
            return (LicenseSeats)Internal.produce(TYPE, null, resolveImmediately, block);
        }

        public LicenseSeats produce(LicenseSeats base, boolean resolveImmediately,
                DraftConsumer<LicenseSeatsDraft> block) {
            return (LicenseSeats)Internal.produce(TYPE, base, resolveImmediately, block);
        }

        /**
         * Class, not interface, for free-marker
         */
        @GeneratedBy(
                type = LicenseSeats.class
        )
        @JsonPropertyOrder({"dummyPropForJacksonError__", "id", "licenses", "licenseId", "user", "userId", "assignedAt", "assignedBy", "assignedById"})
        public abstract static class Implementor implements LicenseSeats, ImmutableSpi {
            @Override
            public final Object __get(PropId prop) {
                int __propIndex = prop.asIndex();
                switch (__propIndex) {
                    case -1:
                    		return __get(prop.asName());
                    case SLOT_ID:
                    		return id();
                    case SLOT_LICENSES:
                    		return licenses();
                    case SLOT_LICENSE_ID:
                    		return licenseId();
                    case SLOT_USER:
                    		return user();
                    case SLOT_USER_ID:
                    		return userId();
                    case SLOT_ASSIGNED_AT:
                    		return assignedAt();
                    case SLOT_ASSIGNED_BY:
                    		return assignedBy();
                    case SLOT_ASSIGNED_BY_ID:
                    		return assignedById();
                    default: throw new IllegalArgumentException("Illegal property name for \"com.doruk.infrastructure.persistence.entity.LicenseSeats\": \"" + prop + "\"");
                }
            }

            @Override
            public final Object __get(String prop) {
                switch (prop) {
                    case "id":
                    		return id();
                    case "licenses":
                    		return licenses();
                    case "licenseId":
                    		return licenseId();
                    case "user":
                    		return user();
                    case "userId":
                    		return userId();
                    case "assignedAt":
                    		return assignedAt();
                    case "assignedBy":
                    		return assignedBy();
                    case "assignedById":
                    		return assignedById();
                    default: throw new IllegalArgumentException("Illegal property name for \"com.doruk.infrastructure.persistence.entity.LicenseSeats\": \"" + prop + "\"");
                }
            }

            public final UUID getId() {
                return id();
            }

            public final Licenses getLicenses() {
                return licenses();
            }

            public final UUID getLicenseId() {
                return licenseId();
            }

            public final User getUser() {
                return user();
            }

            public final UUID getUserId() {
                return userId();
            }

            public final OffsetDateTime getAssignedAt() {
                return assignedAt();
            }

            public final User getAssignedBy() {
                return assignedBy();
            }

            public final UUID getAssignedById() {
                return assignedById();
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
                type = LicenseSeats.class
        )
        private static class Impl extends Implementor implements Cloneable, Serializable {
            private Visibility __visibility;

            UUID __idValue;

            Licenses __licensesValue;

            User __userValue;

            OffsetDateTime __assignedAtValue;

            User __assignedByValue;

            Impl() {
                __visibility = Visibility.of(8);
                __visibility.show(SLOT_LICENSE_ID, false);
                __visibility.show(SLOT_USER_ID, false);
                __visibility.show(SLOT_ASSIGNED_BY_ID, false);
            }

            @Override
            @JsonIgnore
            public UUID id() {
                if (__idValue == null) {
                    throw new UnloadedException(LicenseSeats.class, "id");
                }
                return __idValue;
            }

            @Override
            @JsonIgnore
            public Licenses licenses() {
                if (__licensesValue == null) {
                    throw new UnloadedException(LicenseSeats.class, "licenses");
                }
                return __licensesValue;
            }

            @Override
            @JsonIgnore
            public UUID licenseId() {
                Licenses __target = licenses();
                return __target.id();
            }

            @Override
            @JsonIgnore
            public User user() {
                if (__userValue == null) {
                    throw new UnloadedException(LicenseSeats.class, "user");
                }
                return __userValue;
            }

            @Override
            @JsonIgnore
            public UUID userId() {
                User __target = user();
                return __target.id();
            }

            @Override
            @JsonIgnore
            public OffsetDateTime assignedAt() {
                if (__assignedAtValue == null) {
                    throw new UnloadedException(LicenseSeats.class, "assignedAt");
                }
                return __assignedAtValue;
            }

            @Override
            @JsonIgnore
            public User assignedBy() {
                if (__assignedByValue == null) {
                    throw new UnloadedException(LicenseSeats.class, "assignedBy");
                }
                return __assignedByValue;
            }

            @Override
            @JsonIgnore
            public UUID assignedById() {
                User __target = assignedBy();
                return __target.id();
            }

            @Override
            public Impl clone() {
                try {
                    Impl copy = (Impl) super.clone();
                    Visibility originalVisibility = this.__visibility;
                    if (originalVisibility != null) {
                        Visibility newVisibility = Visibility.of(8);
                        for (int propId = 0; propId < 8; propId++) {
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
                    case SLOT_LICENSES:
                    		return __licensesValue != null;
                    case SLOT_LICENSE_ID:
                    		return __isLoaded(PropId.byIndex(SLOT_LICENSES)) && (licenses() == null || 
                            	((ImmutableSpi)licenses()).__isLoaded(PropId.byIndex(LicensesDraft.Producer.SLOT_ID)));
                    case SLOT_USER:
                    		return __userValue != null;
                    case SLOT_USER_ID:
                    		return __isLoaded(PropId.byIndex(SLOT_USER)) && (user() == null || 
                            	((ImmutableSpi)user()).__isLoaded(PropId.byIndex(UserDraft.Producer.SLOT_ID)));
                    case SLOT_ASSIGNED_AT:
                    		return __assignedAtValue != null;
                    case SLOT_ASSIGNED_BY:
                    		return __assignedByValue != null;
                    case SLOT_ASSIGNED_BY_ID:
                    		return __isLoaded(PropId.byIndex(SLOT_ASSIGNED_BY)) && (assignedBy() == null || 
                            	((ImmutableSpi)assignedBy()).__isLoaded(PropId.byIndex(UserDraft.Producer.SLOT_ID)));
                    default: throw new IllegalArgumentException("Illegal property name for \"com.doruk.infrastructure.persistence.entity.LicenseSeats\": \"" + prop + "\"");
                }
            }

            @Override
            public boolean __isLoaded(String prop) {
                switch (prop) {
                    case "id":
                    		return __idValue != null;
                    case "licenses":
                    		return __licensesValue != null;
                    case "licenseId":
                    		return __isLoaded(PropId.byIndex(SLOT_LICENSES)) && (licenses() == null || 
                            	((ImmutableSpi)licenses()).__isLoaded(PropId.byIndex(LicensesDraft.Producer.SLOT_ID)));
                    case "user":
                    		return __userValue != null;
                    case "userId":
                    		return __isLoaded(PropId.byIndex(SLOT_USER)) && (user() == null || 
                            	((ImmutableSpi)user()).__isLoaded(PropId.byIndex(UserDraft.Producer.SLOT_ID)));
                    case "assignedAt":
                    		return __assignedAtValue != null;
                    case "assignedBy":
                    		return __assignedByValue != null;
                    case "assignedById":
                    		return __isLoaded(PropId.byIndex(SLOT_ASSIGNED_BY)) && (assignedBy() == null || 
                            	((ImmutableSpi)assignedBy()).__isLoaded(PropId.byIndex(UserDraft.Producer.SLOT_ID)));
                    default: throw new IllegalArgumentException("Illegal property name for \"com.doruk.infrastructure.persistence.entity.LicenseSeats\": \"" + prop + "\"");
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
                    case SLOT_LICENSES:
                    		return __visibility.visible(SLOT_LICENSES);
                    case SLOT_LICENSE_ID:
                    		return __visibility.visible(SLOT_LICENSE_ID);
                    case SLOT_USER:
                    		return __visibility.visible(SLOT_USER);
                    case SLOT_USER_ID:
                    		return __visibility.visible(SLOT_USER_ID);
                    case SLOT_ASSIGNED_AT:
                    		return __visibility.visible(SLOT_ASSIGNED_AT);
                    case SLOT_ASSIGNED_BY:
                    		return __visibility.visible(SLOT_ASSIGNED_BY);
                    case SLOT_ASSIGNED_BY_ID:
                    		return __visibility.visible(SLOT_ASSIGNED_BY_ID);
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
                    case "licenses":
                    		return __visibility.visible(SLOT_LICENSES);
                    case "licenseId":
                    		return __visibility.visible(SLOT_LICENSE_ID);
                    case "user":
                    		return __visibility.visible(SLOT_USER);
                    case "userId":
                    		return __visibility.visible(SLOT_USER_ID);
                    case "assignedAt":
                    		return __visibility.visible(SLOT_ASSIGNED_AT);
                    case "assignedBy":
                    		return __visibility.visible(SLOT_ASSIGNED_BY);
                    case "assignedById":
                    		return __visibility.visible(SLOT_ASSIGNED_BY_ID);
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
                if (__licensesValue != null) {
                    hash = 31 * hash + __licensesValue.hashCode();
                }
                if (__userValue != null) {
                    hash = 31 * hash + __userValue.hashCode();
                }
                if (__assignedAtValue != null) {
                    hash = 31 * hash + __assignedAtValue.hashCode();
                }
                if (__assignedByValue != null) {
                    hash = 31 * hash + __assignedByValue.hashCode();
                }
                return hash;
            }

            private int __shallowHashCode() {
                int hash = __visibility != null ? __visibility.hashCode() : 0;
                if (__idValue != null) {
                    hash = 31 * hash + System.identityHashCode(__idValue);
                }
                if (__licensesValue != null) {
                    hash = 31 * hash + System.identityHashCode(__licensesValue);
                }
                if (__userValue != null) {
                    hash = 31 * hash + System.identityHashCode(__userValue);
                }
                if (__assignedAtValue != null) {
                    hash = 31 * hash + System.identityHashCode(__assignedAtValue);
                }
                if (__assignedByValue != null) {
                    hash = 31 * hash + System.identityHashCode(__assignedByValue);
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
                if (__isVisible(PropId.byIndex(SLOT_LICENSE_ID)) != __other.__isVisible(PropId.byIndex(SLOT_LICENSE_ID))) {
                    return false;
                }
                if (__isVisible(PropId.byIndex(SLOT_USER)) != __other.__isVisible(PropId.byIndex(SLOT_USER))) {
                    return false;
                }
                boolean __userLoaded = __userValue != null;
                if (__userLoaded != __other.__isLoaded(PropId.byIndex(SLOT_USER))) {
                    return false;
                }
                if (__userLoaded && !Objects.equals(__userValue, __other.user())) {
                    return false;
                }
                if (__isVisible(PropId.byIndex(SLOT_USER_ID)) != __other.__isVisible(PropId.byIndex(SLOT_USER_ID))) {
                    return false;
                }
                if (__isVisible(PropId.byIndex(SLOT_ASSIGNED_AT)) != __other.__isVisible(PropId.byIndex(SLOT_ASSIGNED_AT))) {
                    return false;
                }
                boolean __assignedAtLoaded = __assignedAtValue != null;
                if (__assignedAtLoaded != __other.__isLoaded(PropId.byIndex(SLOT_ASSIGNED_AT))) {
                    return false;
                }
                if (__assignedAtLoaded && !Objects.equals(__assignedAtValue, __other.assignedAt())) {
                    return false;
                }
                if (__isVisible(PropId.byIndex(SLOT_ASSIGNED_BY)) != __other.__isVisible(PropId.byIndex(SLOT_ASSIGNED_BY))) {
                    return false;
                }
                boolean __assignedByLoaded = __assignedByValue != null;
                if (__assignedByLoaded != __other.__isLoaded(PropId.byIndex(SLOT_ASSIGNED_BY))) {
                    return false;
                }
                if (__assignedByLoaded && !Objects.equals(__assignedByValue, __other.assignedBy())) {
                    return false;
                }
                if (__isVisible(PropId.byIndex(SLOT_ASSIGNED_BY_ID)) != __other.__isVisible(PropId.byIndex(SLOT_ASSIGNED_BY_ID))) {
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
                if (__isVisible(PropId.byIndex(SLOT_LICENSE_ID)) != __other.__isVisible(PropId.byIndex(SLOT_LICENSE_ID))) {
                    return false;
                }
                if (__isVisible(PropId.byIndex(SLOT_USER)) != __other.__isVisible(PropId.byIndex(SLOT_USER))) {
                    return false;
                }
                boolean __userLoaded = __userValue != null;
                if (__userLoaded != __other.__isLoaded(PropId.byIndex(SLOT_USER))) {
                    return false;
                }
                if (__userLoaded && __userValue != __other.user()) {
                    return false;
                }
                if (__isVisible(PropId.byIndex(SLOT_USER_ID)) != __other.__isVisible(PropId.byIndex(SLOT_USER_ID))) {
                    return false;
                }
                if (__isVisible(PropId.byIndex(SLOT_ASSIGNED_AT)) != __other.__isVisible(PropId.byIndex(SLOT_ASSIGNED_AT))) {
                    return false;
                }
                boolean __assignedAtLoaded = __assignedAtValue != null;
                if (__assignedAtLoaded != __other.__isLoaded(PropId.byIndex(SLOT_ASSIGNED_AT))) {
                    return false;
                }
                if (__assignedAtLoaded && __assignedAtValue != __other.assignedAt()) {
                    return false;
                }
                if (__isVisible(PropId.byIndex(SLOT_ASSIGNED_BY)) != __other.__isVisible(PropId.byIndex(SLOT_ASSIGNED_BY))) {
                    return false;
                }
                boolean __assignedByLoaded = __assignedByValue != null;
                if (__assignedByLoaded != __other.__isLoaded(PropId.byIndex(SLOT_ASSIGNED_BY))) {
                    return false;
                }
                if (__assignedByLoaded && __assignedByValue != __other.assignedBy()) {
                    return false;
                }
                if (__isVisible(PropId.byIndex(SLOT_ASSIGNED_BY_ID)) != __other.__isVisible(PropId.byIndex(SLOT_ASSIGNED_BY_ID))) {
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
                type = LicenseSeats.class
        )
        private static class DraftImpl extends Implementor implements DraftSpi, LicenseSeatsDraft {
            private DraftContext __ctx;

            private Impl __base;

            private Impl __modified;

            private boolean __resolving;

            private LicenseSeats __resolved;

            DraftImpl(DraftContext ctx, LicenseSeats base) {
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
            public LicenseSeatsDraft setId(UUID id) {
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
            public LicenseSeatsDraft setLicenses(Licenses licenses) {
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

            @Override
            public LicenseSeatsDraft applyLicenses(DraftConsumer<LicensesDraft> block) {
                applyLicenses(null, block);
                return this;
            }

            @Override
            public LicenseSeatsDraft applyLicenses(Licenses base,
                    DraftConsumer<LicensesDraft> block) {
                setLicenses(LicensesDraft.$.produce(base, block));
                return this;
            }

            @Override
            @JsonIgnore
            public UUID licenseId() {
                Licenses __target = licenses();
                return __target.id();
            }

            @Override
            public LicenseSeatsDraft setLicenseId(UUID licenseId) {
                if (__resolved != null) {
                    throw new IllegalStateException("The current draft has been resolved so it cannot be modified");
                }
                if (licenseId != null) {
                    setLicenses(ImmutableObjects.makeIdOnly(Licenses.class, licenseId));
                } else {
                    setLicenses(null);
                }
                return this;
            }

            @Override
            @JsonIgnore
            public UserDraft user() {
                return __ctx.toDraftObject((__modified!= null ? __modified : __base).user());
            }

            @Override
            public UserDraft user(boolean autoCreate) {
                if (autoCreate && (!__isLoaded(PropId.byIndex(SLOT_USER)))) {
                    setUser(UserDraft.$.produce(null, null));
                }
                return __ctx.toDraftObject((__modified!= null ? __modified : __base).user());
            }

            @Override
            public LicenseSeatsDraft setUser(User user) {
                if (__resolved != null) {
                    throw new IllegalStateException("The current draft has been resolved so it cannot be modified");
                }
                if (user == null) {
                    throw new IllegalArgumentException(
                        "'user' cannot be null, please specify non-null value or use nullable annotation to decorate this property"
                    );
                }
                Impl __tmpModified = __modified();
                __tmpModified.__userValue = user;
                return this;
            }

            @Override
            public LicenseSeatsDraft applyUser(DraftConsumer<UserDraft> block) {
                applyUser(null, block);
                return this;
            }

            @Override
            public LicenseSeatsDraft applyUser(User base, DraftConsumer<UserDraft> block) {
                setUser(UserDraft.$.produce(base, block));
                return this;
            }

            @Override
            @JsonIgnore
            public UUID userId() {
                User __target = user();
                return __target.id();
            }

            @Override
            public LicenseSeatsDraft setUserId(UUID userId) {
                if (__resolved != null) {
                    throw new IllegalStateException("The current draft has been resolved so it cannot be modified");
                }
                if (userId != null) {
                    setUser(ImmutableObjects.makeIdOnly(User.class, userId));
                } else {
                    setUser(null);
                }
                return this;
            }

            @Override
            @JsonIgnore
            public OffsetDateTime assignedAt() {
                return (__modified!= null ? __modified : __base).assignedAt();
            }

            @Override
            public LicenseSeatsDraft setAssignedAt(OffsetDateTime assignedAt) {
                if (__resolved != null) {
                    throw new IllegalStateException("The current draft has been resolved so it cannot be modified");
                }
                if (assignedAt == null) {
                    throw new IllegalArgumentException(
                        "'assignedAt' cannot be null, please specify non-null value or use nullable annotation to decorate this property"
                    );
                }
                Impl __tmpModified = __modified();
                __tmpModified.__assignedAtValue = assignedAt;
                return this;
            }

            @Override
            @JsonIgnore
            public UserDraft assignedBy() {
                return __ctx.toDraftObject((__modified!= null ? __modified : __base).assignedBy());
            }

            @Override
            public UserDraft assignedBy(boolean autoCreate) {
                if (autoCreate && (!__isLoaded(PropId.byIndex(SLOT_ASSIGNED_BY)))) {
                    setAssignedBy(UserDraft.$.produce(null, null));
                }
                return __ctx.toDraftObject((__modified!= null ? __modified : __base).assignedBy());
            }

            @Override
            public LicenseSeatsDraft setAssignedBy(User assignedBy) {
                if (__resolved != null) {
                    throw new IllegalStateException("The current draft has been resolved so it cannot be modified");
                }
                if (assignedBy == null) {
                    throw new IllegalArgumentException(
                        "'assignedBy' cannot be null, please specify non-null value or use nullable annotation to decorate this property"
                    );
                }
                Impl __tmpModified = __modified();
                __tmpModified.__assignedByValue = assignedBy;
                return this;
            }

            @Override
            public LicenseSeatsDraft applyAssignedBy(DraftConsumer<UserDraft> block) {
                applyAssignedBy(null, block);
                return this;
            }

            @Override
            public LicenseSeatsDraft applyAssignedBy(User base, DraftConsumer<UserDraft> block) {
                setAssignedBy(UserDraft.$.produce(base, block));
                return this;
            }

            @Override
            @JsonIgnore
            public UUID assignedById() {
                User __target = assignedBy();
                return __target.id();
            }

            @Override
            public LicenseSeatsDraft setAssignedById(UUID assignedById) {
                if (__resolved != null) {
                    throw new IllegalStateException("The current draft has been resolved so it cannot be modified");
                }
                if (assignedById != null) {
                    setAssignedBy(ImmutableObjects.makeIdOnly(User.class, assignedById));
                } else {
                    setAssignedBy(null);
                }
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
                    case SLOT_LICENSES:
                    		setLicenses((Licenses)value);break;
                    case SLOT_LICENSE_ID:
                    		setLicenseId((UUID)value);break;
                    case SLOT_USER:
                    		setUser((User)value);break;
                    case SLOT_USER_ID:
                    		setUserId((UUID)value);break;
                    case SLOT_ASSIGNED_AT:
                    		setAssignedAt((OffsetDateTime)value);break;
                    case SLOT_ASSIGNED_BY:
                    		setAssignedBy((User)value);break;
                    case SLOT_ASSIGNED_BY_ID:
                    		setAssignedById((UUID)value);break;
                    default: throw new IllegalArgumentException("Illegal property id for \"com.doruk.infrastructure.persistence.entity.LicenseSeats\": \"" + prop + "\"");
                }
            }

            @SuppressWarnings("all")
            @Override
            public void __set(String prop, Object value) {
                switch (prop) {
                    case "id":
                    		setId((UUID)value);break;
                    case "licenses":
                    		setLicenses((Licenses)value);break;
                    case "licenseId":
                    		setLicenseId((UUID)value);break;
                    case "user":
                    		setUser((User)value);break;
                    case "userId":
                    		setUserId((UUID)value);break;
                    case "assignedAt":
                    		setAssignedAt((OffsetDateTime)value);break;
                    case "assignedBy":
                    		setAssignedBy((User)value);break;
                    case "assignedById":
                    		setAssignedById((UUID)value);break;
                    default: throw new IllegalArgumentException("Illegal property name for \"com.doruk.infrastructure.persistence.entity.LicenseSeats\": \"" + prop + "\"");
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
                    __modified().__visibility = __visibility = Visibility.of(8);
                }
                int __propIndex = prop.asIndex();
                switch (__propIndex) {
                    case -1:
                    		__show(prop.asName(), visible);
                    return;
                    case SLOT_ID:
                    		__visibility.show(SLOT_ID, visible);break;
                    case SLOT_LICENSES:
                    		__visibility.show(SLOT_LICENSES, visible);break;
                    case SLOT_LICENSE_ID:
                    		__visibility.show(SLOT_LICENSE_ID, visible);break;
                    case SLOT_USER:
                    		__visibility.show(SLOT_USER, visible);break;
                    case SLOT_USER_ID:
                    		__visibility.show(SLOT_USER_ID, visible);break;
                    case SLOT_ASSIGNED_AT:
                    		__visibility.show(SLOT_ASSIGNED_AT, visible);break;
                    case SLOT_ASSIGNED_BY:
                    		__visibility.show(SLOT_ASSIGNED_BY, visible);break;
                    case SLOT_ASSIGNED_BY_ID:
                    		__visibility.show(SLOT_ASSIGNED_BY_ID, visible);break;
                    default: throw new IllegalArgumentException(
                                "Illegal property id for \"com.doruk.infrastructure.persistence.entity.LicenseSeats\": \"" + 
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
                    __modified().__visibility = __visibility = Visibility.of(8);
                }
                switch (prop) {
                    case "id":
                    		__visibility.show(SLOT_ID, visible);break;
                    case "licenses":
                    		__visibility.show(SLOT_LICENSES, visible);break;
                    case "licenseId":
                    		__visibility.show(SLOT_LICENSE_ID, visible);break;
                    case "user":
                    		__visibility.show(SLOT_USER, visible);break;
                    case "userId":
                    		__visibility.show(SLOT_USER_ID, visible);break;
                    case "assignedAt":
                    		__visibility.show(SLOT_ASSIGNED_AT, visible);break;
                    case "assignedBy":
                    		__visibility.show(SLOT_ASSIGNED_BY, visible);break;
                    case "assignedById":
                    		__visibility.show(SLOT_ASSIGNED_BY_ID, visible);break;
                    default: throw new IllegalArgumentException(
                                "Illegal property name for \"com.doruk.infrastructure.persistence.entity.LicenseSeats\": \"" + 
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
                    case SLOT_LICENSES:
                    		__modified().__licensesValue = null;break;
                    case SLOT_LICENSE_ID:
                    		__unload(PropId.byIndex(SLOT_LICENSES));break;
                    case SLOT_USER:
                    		__modified().__userValue = null;break;
                    case SLOT_USER_ID:
                    		__unload(PropId.byIndex(SLOT_USER));break;
                    case SLOT_ASSIGNED_AT:
                    		__modified().__assignedAtValue = null;break;
                    case SLOT_ASSIGNED_BY:
                    		__modified().__assignedByValue = null;break;
                    case SLOT_ASSIGNED_BY_ID:
                    		__unload(PropId.byIndex(SLOT_ASSIGNED_BY));break;
                    default: throw new IllegalArgumentException("Illegal property id for \"com.doruk.infrastructure.persistence.entity.LicenseSeats\": \"" + prop + "\", it does not exist or its loaded state is not controllable");
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
                    case "licenses":
                    		__modified().__licensesValue = null;break;
                    case "licenseId":
                    		__unload(PropId.byIndex(SLOT_LICENSES));break;
                    case "user":
                    		__modified().__userValue = null;break;
                    case "userId":
                    		__unload(PropId.byIndex(SLOT_USER));break;
                    case "assignedAt":
                    		__modified().__assignedAtValue = null;break;
                    case "assignedBy":
                    		__modified().__assignedByValue = null;break;
                    case "assignedById":
                    		__unload(PropId.byIndex(SLOT_ASSIGNED_BY));break;
                    default: throw new IllegalArgumentException("Illegal property name for \"com.doruk.infrastructure.persistence.entity.LicenseSeats\": \"" + prop + "\", it does not exist or its loaded state is not controllable");
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
                        if (base.__isLoaded(PropId.byIndex(SLOT_USER))) {
                            User oldValue = base.user();
                            User newValue = __ctx.resolveObject(oldValue);
                            if (oldValue != newValue) {
                                setUser(newValue);
                            }
                        }
                        if (base.__isLoaded(PropId.byIndex(SLOT_ASSIGNED_BY))) {
                            User oldValue = base.assignedBy();
                            User newValue = __ctx.resolveObject(oldValue);
                            if (oldValue != newValue) {
                                setAssignedBy(newValue);
                            }
                        }
                        __tmpModified = __modified;
                    }
                    else {
                        __tmpModified.__licensesValue = __ctx.resolveObject(__tmpModified.__licensesValue);
                        __tmpModified.__userValue = __ctx.resolveObject(__tmpModified.__userValue);
                        __tmpModified.__assignedByValue = __ctx.resolveObject(__tmpModified.__assignedByValue);
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
            type = LicenseSeats.class
    )
    class Builder {
        private final Producer.DraftImpl __draft;

        public Builder() {
            this(null);
        }

        public Builder(@Nullable LicenseSeats base) {
            __draft = new Producer.DraftImpl(null, base);
            __draft.__show(PropId.byIndex(Producer.SLOT_LICENSES), false);
            __draft.__show(PropId.byIndex(Producer.SLOT_LICENSE_ID), false);
            __draft.__show(PropId.byIndex(Producer.SLOT_USER), false);
            __draft.__show(PropId.byIndex(Producer.SLOT_USER_ID), false);
            __draft.__show(PropId.byIndex(Producer.SLOT_ASSIGNED_BY), false);
            __draft.__show(PropId.byIndex(Producer.SLOT_ASSIGNED_BY_ID), false);
        }

        public Builder id(@NonNull UUID id) {
            if (id != null) {
                __draft.setId(id);
            }
            return this;
        }

        public Builder licenses(@NonNull Licenses licenses) {
            if (licenses != null) {
                __draft.setLicenses(licenses);
                __draft.__show(PropId.byIndex(Producer.SLOT_LICENSES), true);
            }
            return this;
        }

        public Builder licenseId(@NonNull UUID licenseId) {
            if (licenseId != null) {
                __draft.setLicenseId(licenseId);
                __draft.__show(PropId.byIndex(Producer.SLOT_LICENSE_ID), true);
            }
            return this;
        }

        public Builder user(@NonNull User user) {
            if (user != null) {
                __draft.setUser(user);
                __draft.__show(PropId.byIndex(Producer.SLOT_USER), true);
            }
            return this;
        }

        public Builder userId(@NonNull UUID userId) {
            if (userId != null) {
                __draft.setUserId(userId);
                __draft.__show(PropId.byIndex(Producer.SLOT_USER_ID), true);
            }
            return this;
        }

        public Builder assignedAt(@NonNull OffsetDateTime assignedAt) {
            if (assignedAt != null) {
                __draft.setAssignedAt(assignedAt);
            }
            return this;
        }

        public Builder assignedBy(@NonNull User assignedBy) {
            if (assignedBy != null) {
                __draft.setAssignedBy(assignedBy);
                __draft.__show(PropId.byIndex(Producer.SLOT_ASSIGNED_BY), true);
            }
            return this;
        }

        public Builder assignedById(@NonNull UUID assignedById) {
            if (assignedById != null) {
                __draft.setAssignedById(assignedById);
                __draft.__show(PropId.byIndex(Producer.SLOT_ASSIGNED_BY_ID), true);
            }
            return this;
        }

        public LicenseSeats build() {
            return (LicenseSeats)__draft.__modified();
        }
    }
}
