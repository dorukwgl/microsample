package com.doruk.infrastructure.persistence.entity;

import com.doruk.domain.shared.enums.OrganizationType;
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
import org.babyfish.jimmer.sql.OneToMany;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

@GeneratedBy(
        type = Organizations.class
)
public interface OrganizationsDraft extends Organizations, Draft {
    OrganizationsDraft.Producer $ = Producer.INSTANCE;

    @OldChain
    OrganizationsDraft setId(UUID id);

    @OldChain
    OrganizationsDraft setName(String name);

    @OldChain
    OrganizationsDraft setType(OrganizationType type);

    @OldChain
    OrganizationsDraft setOrgCode(String orgCode);

    @OldChain
    OrganizationsDraft setCreatedAt(OffsetDateTime createdAt);

    List<LicensesDraft> licenses(boolean autoCreate);

    @OldChain
    OrganizationsDraft setLicenses(List<Licenses> licenses);

    @OldChain
    OrganizationsDraft addIntoLicenses(DraftConsumer<LicensesDraft> block);

    @OldChain
    OrganizationsDraft addIntoLicenses(Licenses base, DraftConsumer<LicensesDraft> block);

    List<UserDraft> users(boolean autoCreate);

    @OldChain
    OrganizationsDraft setUsers(List<User> users);

    @OldChain
    OrganizationsDraft addIntoUsers(DraftConsumer<UserDraft> block);

    @OldChain
    OrganizationsDraft addIntoUsers(User base, DraftConsumer<UserDraft> block);

    @GeneratedBy(
            type = Organizations.class
    )
    class Producer {
        static final Producer INSTANCE = new Producer();

        public static final int SLOT_ID = 0;

        public static final int SLOT_NAME = 1;

        public static final int SLOT_TYPE = 2;

        public static final int SLOT_ORG_CODE = 3;

        public static final int SLOT_CREATED_AT = 4;

        public static final int SLOT_LICENSES = 5;

        public static final int SLOT_USERS = 6;

        public static final ImmutableType TYPE = ImmutableType
            .newBuilder(
                "0.10.6",
                Organizations.class,
                Collections.emptyList(),
                (ctx, base) -> new DraftImpl(ctx, (Organizations)base)
            )
            .id(SLOT_ID, "id", UUID.class)
            .add(SLOT_NAME, "name", ImmutablePropCategory.SCALAR, String.class, false)
            .add(SLOT_TYPE, "type", ImmutablePropCategory.SCALAR, OrganizationType.class, false)
            .add(SLOT_ORG_CODE, "orgCode", ImmutablePropCategory.SCALAR, String.class, false)
            .add(SLOT_CREATED_AT, "createdAt", ImmutablePropCategory.SCALAR, OffsetDateTime.class, false)
            .add(SLOT_LICENSES, "licenses", OneToMany.class, Licenses.class, false)
            .add(SLOT_USERS, "users", OneToMany.class, User.class, false)
            .build();

        private Producer() {
        }

        public Organizations produce(DraftConsumer<OrganizationsDraft> block) {
            return (Organizations)Internal.produce(TYPE, null, block);
        }

        public Organizations produce(Organizations base, DraftConsumer<OrganizationsDraft> block) {
            return (Organizations)Internal.produce(TYPE, base, block);
        }

        public Organizations produce(boolean resolveImmediately,
                DraftConsumer<OrganizationsDraft> block) {
            return (Organizations)Internal.produce(TYPE, null, resolveImmediately, block);
        }

        public Organizations produce(Organizations base, boolean resolveImmediately,
                DraftConsumer<OrganizationsDraft> block) {
            return (Organizations)Internal.produce(TYPE, base, resolveImmediately, block);
        }

        /**
         * Class, not interface, for free-marker
         */
        @GeneratedBy(
                type = Organizations.class
        )
        @JsonPropertyOrder({"dummyPropForJacksonError__", "id", "name", "type", "orgCode", "createdAt", "licenses", "users"})
        public abstract static class Implementor implements Organizations, ImmutableSpi {
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
                    case SLOT_TYPE:
                    		return type();
                    case SLOT_ORG_CODE:
                    		return orgCode();
                    case SLOT_CREATED_AT:
                    		return createdAt();
                    case SLOT_LICENSES:
                    		return licenses();
                    case SLOT_USERS:
                    		return users();
                    default: throw new IllegalArgumentException("Illegal property name for \"com.doruk.infrastructure.persistence.entity.Organizations\": \"" + prop + "\"");
                }
            }

            @Override
            public final Object __get(String prop) {
                switch (prop) {
                    case "id":
                    		return id();
                    case "name":
                    		return name();
                    case "type":
                    		return type();
                    case "orgCode":
                    		return orgCode();
                    case "createdAt":
                    		return createdAt();
                    case "licenses":
                    		return licenses();
                    case "users":
                    		return users();
                    default: throw new IllegalArgumentException("Illegal property name for \"com.doruk.infrastructure.persistence.entity.Organizations\": \"" + prop + "\"");
                }
            }

            public final UUID getId() {
                return id();
            }

            public final String getName() {
                return name();
            }

            public final OrganizationType getType() {
                return type();
            }

            public final String getOrgCode() {
                return orgCode();
            }

            public final OffsetDateTime getCreatedAt() {
                return createdAt();
            }

            public final List<Licenses> getLicenses() {
                return licenses();
            }

            public final List<User> getUsers() {
                return users();
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
                type = Organizations.class
        )
        private static class Impl extends Implementor implements Cloneable, Serializable {
            private Visibility __visibility;

            UUID __idValue;

            String __nameValue;

            OrganizationType __typeValue;

            String __orgCodeValue;

            OffsetDateTime __createdAtValue;

            NonSharedList<Licenses> __licensesValue;

            NonSharedList<User> __usersValue;

            @Override
            @JsonIgnore
            public UUID id() {
                if (__idValue == null) {
                    throw new UnloadedException(Organizations.class, "id");
                }
                return __idValue;
            }

            @Override
            @JsonIgnore
            public String name() {
                if (__nameValue == null) {
                    throw new UnloadedException(Organizations.class, "name");
                }
                return __nameValue;
            }

            @Override
            @JsonIgnore
            public OrganizationType type() {
                if (__typeValue == null) {
                    throw new UnloadedException(Organizations.class, "type");
                }
                return __typeValue;
            }

            @Override
            @JsonIgnore
            public String orgCode() {
                if (__orgCodeValue == null) {
                    throw new UnloadedException(Organizations.class, "orgCode");
                }
                return __orgCodeValue;
            }

            @Override
            @JsonIgnore
            public OffsetDateTime createdAt() {
                if (__createdAtValue == null) {
                    throw new UnloadedException(Organizations.class, "createdAt");
                }
                return __createdAtValue;
            }

            @Override
            @JsonIgnore
            public List<Licenses> licenses() {
                if (__licensesValue == null) {
                    throw new UnloadedException(Organizations.class, "licenses");
                }
                return __licensesValue;
            }

            @Override
            @JsonIgnore
            public List<User> users() {
                if (__usersValue == null) {
                    throw new UnloadedException(Organizations.class, "users");
                }
                return __usersValue;
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
                    case SLOT_TYPE:
                    		return __typeValue != null;
                    case SLOT_ORG_CODE:
                    		return __orgCodeValue != null;
                    case SLOT_CREATED_AT:
                    		return __createdAtValue != null;
                    case SLOT_LICENSES:
                    		return __licensesValue != null;
                    case SLOT_USERS:
                    		return __usersValue != null;
                    default: throw new IllegalArgumentException("Illegal property name for \"com.doruk.infrastructure.persistence.entity.Organizations\": \"" + prop + "\"");
                }
            }

            @Override
            public boolean __isLoaded(String prop) {
                switch (prop) {
                    case "id":
                    		return __idValue != null;
                    case "name":
                    		return __nameValue != null;
                    case "type":
                    		return __typeValue != null;
                    case "orgCode":
                    		return __orgCodeValue != null;
                    case "createdAt":
                    		return __createdAtValue != null;
                    case "licenses":
                    		return __licensesValue != null;
                    case "users":
                    		return __usersValue != null;
                    default: throw new IllegalArgumentException("Illegal property name for \"com.doruk.infrastructure.persistence.entity.Organizations\": \"" + prop + "\"");
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
                    case SLOT_TYPE:
                    		return __visibility.visible(SLOT_TYPE);
                    case SLOT_ORG_CODE:
                    		return __visibility.visible(SLOT_ORG_CODE);
                    case SLOT_CREATED_AT:
                    		return __visibility.visible(SLOT_CREATED_AT);
                    case SLOT_LICENSES:
                    		return __visibility.visible(SLOT_LICENSES);
                    case SLOT_USERS:
                    		return __visibility.visible(SLOT_USERS);
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
                    case "type":
                    		return __visibility.visible(SLOT_TYPE);
                    case "orgCode":
                    		return __visibility.visible(SLOT_ORG_CODE);
                    case "createdAt":
                    		return __visibility.visible(SLOT_CREATED_AT);
                    case "licenses":
                    		return __visibility.visible(SLOT_LICENSES);
                    case "users":
                    		return __visibility.visible(SLOT_USERS);
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
                if (__typeValue != null) {
                    hash = 31 * hash + __typeValue.hashCode();
                }
                if (__orgCodeValue != null) {
                    hash = 31 * hash + __orgCodeValue.hashCode();
                }
                if (__createdAtValue != null) {
                    hash = 31 * hash + __createdAtValue.hashCode();
                }
                if (__licensesValue != null) {
                    hash = 31 * hash + __licensesValue.hashCode();
                }
                if (__usersValue != null) {
                    hash = 31 * hash + __usersValue.hashCode();
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
                if (__typeValue != null) {
                    hash = 31 * hash + System.identityHashCode(__typeValue);
                }
                if (__orgCodeValue != null) {
                    hash = 31 * hash + System.identityHashCode(__orgCodeValue);
                }
                if (__createdAtValue != null) {
                    hash = 31 * hash + System.identityHashCode(__createdAtValue);
                }
                if (__licensesValue != null) {
                    hash = 31 * hash + System.identityHashCode(__licensesValue);
                }
                if (__usersValue != null) {
                    hash = 31 * hash + System.identityHashCode(__usersValue);
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
                if (__isVisible(PropId.byIndex(SLOT_ORG_CODE)) != __other.__isVisible(PropId.byIndex(SLOT_ORG_CODE))) {
                    return false;
                }
                boolean __orgCodeLoaded = __orgCodeValue != null;
                if (__orgCodeLoaded != __other.__isLoaded(PropId.byIndex(SLOT_ORG_CODE))) {
                    return false;
                }
                if (__orgCodeLoaded && !Objects.equals(__orgCodeValue, __other.orgCode())) {
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
                if (__isVisible(PropId.byIndex(SLOT_USERS)) != __other.__isVisible(PropId.byIndex(SLOT_USERS))) {
                    return false;
                }
                boolean __usersLoaded = __usersValue != null;
                if (__usersLoaded != __other.__isLoaded(PropId.byIndex(SLOT_USERS))) {
                    return false;
                }
                if (__usersLoaded && !Objects.equals(__usersValue, __other.users())) {
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
                if (__isVisible(PropId.byIndex(SLOT_ORG_CODE)) != __other.__isVisible(PropId.byIndex(SLOT_ORG_CODE))) {
                    return false;
                }
                boolean __orgCodeLoaded = __orgCodeValue != null;
                if (__orgCodeLoaded != __other.__isLoaded(PropId.byIndex(SLOT_ORG_CODE))) {
                    return false;
                }
                if (__orgCodeLoaded && __orgCodeValue != __other.orgCode()) {
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
                if (__isVisible(PropId.byIndex(SLOT_USERS)) != __other.__isVisible(PropId.byIndex(SLOT_USERS))) {
                    return false;
                }
                boolean __usersLoaded = __usersValue != null;
                if (__usersLoaded != __other.__isLoaded(PropId.byIndex(SLOT_USERS))) {
                    return false;
                }
                if (__usersLoaded && __usersValue != __other.users()) {
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
                type = Organizations.class
        )
        private static class DraftImpl extends Implementor implements DraftSpi, OrganizationsDraft {
            private DraftContext __ctx;

            private Impl __base;

            private Impl __modified;

            private boolean __resolving;

            private Organizations __resolved;

            DraftImpl(DraftContext ctx, Organizations base) {
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
            public OrganizationsDraft setId(UUID id) {
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
            public OrganizationsDraft setName(String name) {
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
            public OrganizationType type() {
                return (__modified!= null ? __modified : __base).type();
            }

            @Override
            public OrganizationsDraft setType(OrganizationType type) {
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
            public String orgCode() {
                return (__modified!= null ? __modified : __base).orgCode();
            }

            @Override
            public OrganizationsDraft setOrgCode(String orgCode) {
                if (__resolved != null) {
                    throw new IllegalStateException("The current draft has been resolved so it cannot be modified");
                }
                if (orgCode == null) {
                    throw new IllegalArgumentException(
                        "'orgCode' cannot be null, please specify non-null value or use nullable annotation to decorate this property"
                    );
                }
                Impl __tmpModified = __modified();
                __tmpModified.__orgCodeValue = orgCode;
                return this;
            }

            @Override
            @JsonIgnore
            public OffsetDateTime createdAt() {
                return (__modified!= null ? __modified : __base).createdAt();
            }

            @Override
            public OrganizationsDraft setCreatedAt(OffsetDateTime createdAt) {
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
            public List<Licenses> licenses() {
                return __ctx.toDraftList((__modified!= null ? __modified : __base).licenses(), Licenses.class, true);
            }

            @Override
            public List<LicensesDraft> licenses(boolean autoCreate) {
                if (autoCreate && (!__isLoaded(PropId.byIndex(SLOT_LICENSES)))) {
                    setLicenses(new ArrayList<>());
                }
                return __ctx.toDraftList((__modified!= null ? __modified : __base).licenses(), Licenses.class, true);
            }

            @Override
            public OrganizationsDraft setLicenses(List<Licenses> licenses) {
                if (__resolved != null) {
                    throw new IllegalStateException("The current draft has been resolved so it cannot be modified");
                }
                if (licenses == null) {
                    throw new IllegalArgumentException(
                        "'licenses' cannot be null, please specify non-null value or use nullable annotation to decorate this property"
                    );
                }
                Impl __tmpModified = __modified();
                __tmpModified.__licensesValue = NonSharedList.of(__tmpModified.__licensesValue, licenses);
                return this;
            }

            @Override
            public OrganizationsDraft addIntoLicenses(DraftConsumer<LicensesDraft> block) {
                addIntoLicenses(null, block);
                return this;
            }

            @Override
            public OrganizationsDraft addIntoLicenses(Licenses base,
                    DraftConsumer<LicensesDraft> block) {
                licenses(true).add((LicensesDraft)LicensesDraft.$.produce(base, block));
                return this;
            }

            @Override
            @JsonIgnore
            public List<User> users() {
                return __ctx.toDraftList((__modified!= null ? __modified : __base).users(), User.class, true);
            }

            @Override
            public List<UserDraft> users(boolean autoCreate) {
                if (autoCreate && (!__isLoaded(PropId.byIndex(SLOT_USERS)))) {
                    setUsers(new ArrayList<>());
                }
                return __ctx.toDraftList((__modified!= null ? __modified : __base).users(), User.class, true);
            }

            @Override
            public OrganizationsDraft setUsers(List<User> users) {
                if (__resolved != null) {
                    throw new IllegalStateException("The current draft has been resolved so it cannot be modified");
                }
                if (users == null) {
                    throw new IllegalArgumentException(
                        "'users' cannot be null, please specify non-null value or use nullable annotation to decorate this property"
                    );
                }
                Impl __tmpModified = __modified();
                __tmpModified.__usersValue = NonSharedList.of(__tmpModified.__usersValue, users);
                return this;
            }

            @Override
            public OrganizationsDraft addIntoUsers(DraftConsumer<UserDraft> block) {
                addIntoUsers(null, block);
                return this;
            }

            @Override
            public OrganizationsDraft addIntoUsers(User base, DraftConsumer<UserDraft> block) {
                users(true).add((UserDraft)UserDraft.$.produce(base, block));
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
                    case SLOT_TYPE:
                    		setType((OrganizationType)value);break;
                    case SLOT_ORG_CODE:
                    		setOrgCode((String)value);break;
                    case SLOT_CREATED_AT:
                    		setCreatedAt((OffsetDateTime)value);break;
                    case SLOT_LICENSES:
                    		setLicenses((List<Licenses>)value);break;
                    case SLOT_USERS:
                    		setUsers((List<User>)value);break;
                    default: throw new IllegalArgumentException("Illegal property id for \"com.doruk.infrastructure.persistence.entity.Organizations\": \"" + prop + "\"");
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
                    case "type":
                    		setType((OrganizationType)value);break;
                    case "orgCode":
                    		setOrgCode((String)value);break;
                    case "createdAt":
                    		setCreatedAt((OffsetDateTime)value);break;
                    case "licenses":
                    		setLicenses((List<Licenses>)value);break;
                    case "users":
                    		setUsers((List<User>)value);break;
                    default: throw new IllegalArgumentException("Illegal property name for \"com.doruk.infrastructure.persistence.entity.Organizations\": \"" + prop + "\"");
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
                    case SLOT_TYPE:
                    		__visibility.show(SLOT_TYPE, visible);break;
                    case SLOT_ORG_CODE:
                    		__visibility.show(SLOT_ORG_CODE, visible);break;
                    case SLOT_CREATED_AT:
                    		__visibility.show(SLOT_CREATED_AT, visible);break;
                    case SLOT_LICENSES:
                    		__visibility.show(SLOT_LICENSES, visible);break;
                    case SLOT_USERS:
                    		__visibility.show(SLOT_USERS, visible);break;
                    default: throw new IllegalArgumentException(
                                "Illegal property id for \"com.doruk.infrastructure.persistence.entity.Organizations\": \"" + 
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
                    case "type":
                    		__visibility.show(SLOT_TYPE, visible);break;
                    case "orgCode":
                    		__visibility.show(SLOT_ORG_CODE, visible);break;
                    case "createdAt":
                    		__visibility.show(SLOT_CREATED_AT, visible);break;
                    case "licenses":
                    		__visibility.show(SLOT_LICENSES, visible);break;
                    case "users":
                    		__visibility.show(SLOT_USERS, visible);break;
                    default: throw new IllegalArgumentException(
                                "Illegal property name for \"com.doruk.infrastructure.persistence.entity.Organizations\": \"" + 
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
                    case SLOT_TYPE:
                    		__modified().__typeValue = null;break;
                    case SLOT_ORG_CODE:
                    		__modified().__orgCodeValue = null;break;
                    case SLOT_CREATED_AT:
                    		__modified().__createdAtValue = null;break;
                    case SLOT_LICENSES:
                    		__modified().__licensesValue = null;break;
                    case SLOT_USERS:
                    		__modified().__usersValue = null;break;
                    default: throw new IllegalArgumentException("Illegal property id for \"com.doruk.infrastructure.persistence.entity.Organizations\": \"" + prop + "\", it does not exist or its loaded state is not controllable");
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
                    case "type":
                    		__modified().__typeValue = null;break;
                    case "orgCode":
                    		__modified().__orgCodeValue = null;break;
                    case "createdAt":
                    		__modified().__createdAtValue = null;break;
                    case "licenses":
                    		__modified().__licensesValue = null;break;
                    case "users":
                    		__modified().__usersValue = null;break;
                    default: throw new IllegalArgumentException("Illegal property name for \"com.doruk.infrastructure.persistence.entity.Organizations\": \"" + prop + "\", it does not exist or its loaded state is not controllable");
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
                            List<Licenses> oldValue = base.licenses();
                            List<Licenses> newValue = __ctx.resolveList(oldValue);
                            if (oldValue != newValue) {
                                setLicenses(newValue);
                            }
                        }
                        if (base.__isLoaded(PropId.byIndex(SLOT_USERS))) {
                            List<User> oldValue = base.users();
                            List<User> newValue = __ctx.resolveList(oldValue);
                            if (oldValue != newValue) {
                                setUsers(newValue);
                            }
                        }
                        __tmpModified = __modified;
                    }
                    else {
                        __tmpModified.__licensesValue = NonSharedList.of(__tmpModified.__licensesValue, __ctx.resolveList(__tmpModified.__licensesValue));
                        __tmpModified.__usersValue = NonSharedList.of(__tmpModified.__usersValue, __ctx.resolveList(__tmpModified.__usersValue));
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
            type = Organizations.class
    )
    class Builder {
        private final Producer.DraftImpl __draft;

        public Builder() {
            this(null);
        }

        public Builder(@Nullable Organizations base) {
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

        public Builder type(@NonNull OrganizationType type) {
            if (type != null) {
                __draft.setType(type);
            }
            return this;
        }

        public Builder orgCode(@NonNull String orgCode) {
            if (orgCode != null) {
                __draft.setOrgCode(orgCode);
            }
            return this;
        }

        public Builder createdAt(@NonNull OffsetDateTime createdAt) {
            if (createdAt != null) {
                __draft.setCreatedAt(createdAt);
            }
            return this;
        }

        public Builder licenses(@NonNull List<Licenses> licenses) {
            if (licenses != null) {
                __draft.setLicenses(licenses);
            }
            return this;
        }

        public Builder users(@NonNull List<User> users) {
            if (users != null) {
                __draft.setUsers(users);
            }
            return this;
        }

        public Organizations build() {
            return (Organizations)__draft.__modified();
        }
    }
}
