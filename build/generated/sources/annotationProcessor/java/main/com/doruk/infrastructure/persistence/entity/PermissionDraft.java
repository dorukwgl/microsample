package com.doruk.infrastructure.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.annotation.Nullable;
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
import org.babyfish.jimmer.CircularReferenceException;
import org.babyfish.jimmer.Draft;
import org.babyfish.jimmer.DraftConsumer;
import org.babyfish.jimmer.ImmutableObjects;
import org.babyfish.jimmer.UnloadedException;
import org.babyfish.jimmer.internal.GeneratedBy;
import org.babyfish.jimmer.jackson.ImmutableModuleRequiredException;
import org.babyfish.jimmer.lang.OldChain;
import org.babyfish.jimmer.meta.ImmutableType;
import org.babyfish.jimmer.meta.PropId;
import org.babyfish.jimmer.runtime.DraftContext;
import org.babyfish.jimmer.runtime.DraftSpi;
import org.babyfish.jimmer.runtime.ImmutableSpi;
import org.babyfish.jimmer.runtime.Internal;
import org.babyfish.jimmer.runtime.NonSharedList;
import org.babyfish.jimmer.runtime.Visibility;
import org.babyfish.jimmer.sql.ManyToMany;
import org.jspecify.annotations.NonNull;

@GeneratedBy(
        type = Permission.class
)
public interface PermissionDraft extends Permission, Draft {
    PermissionDraft.Producer $ = Producer.INSTANCE;

    @OldChain
    PermissionDraft setName(String name);

    List<RoleDraft> roles(boolean autoCreate);

    @OldChain
    PermissionDraft setRoles(List<Role> roles);

    @OldChain
    PermissionDraft addIntoRoles(DraftConsumer<RoleDraft> block);

    @OldChain
    PermissionDraft addIntoRoles(Role base, DraftConsumer<RoleDraft> block);

    @OldChain
    PermissionDraft setDeletedAt(OffsetDateTime deletedAt);

    @GeneratedBy(
            type = Permission.class
    )
    class Producer {
        static final Producer INSTANCE = new Producer();

        public static final int SLOT_NAME = 0;

        public static final int SLOT_ROLES = 1;

        public static final int SLOT_DELETED_AT = 2;

        public static final ImmutableType TYPE = ImmutableType
            .newBuilder(
                "0.10.7",
                Permission.class,
                Collections.emptyList(),
                (ctx, base) -> new DraftImpl(ctx, (Permission)base)
            )
            .id(SLOT_NAME, "name", String.class)
            .add(SLOT_ROLES, "roles", ManyToMany.class, Role.class, false)
            .logicalDeleted(SLOT_DELETED_AT, "deletedAt", OffsetDateTime.class, true)
            .build();

        private Producer() {
        }

        public Permission produce(DraftConsumer<PermissionDraft> block) {
            return (Permission)Internal.produce(TYPE, null, block);
        }

        public Permission produce(Permission base, DraftConsumer<PermissionDraft> block) {
            return (Permission)Internal.produce(TYPE, base, block);
        }

        public Permission produce(boolean resolveImmediately,
                DraftConsumer<PermissionDraft> block) {
            return (Permission)Internal.produce(TYPE, null, resolveImmediately, block);
        }

        public Permission produce(Permission base, boolean resolveImmediately,
                DraftConsumer<PermissionDraft> block) {
            return (Permission)Internal.produce(TYPE, base, resolveImmediately, block);
        }

        /**
         * Class, not interface, for free-marker
         */
        @GeneratedBy(
                type = Permission.class
        )
        @JsonPropertyOrder({"dummyPropForJacksonError__", "name", "roles", "deletedAt"})
        public abstract static class Implementor implements Permission, ImmutableSpi {
            @Override
            public final Object __get(PropId prop) {
                int __propIndex = prop.asIndex();
                switch (__propIndex) {
                    case -1:
                    		return __get(prop.asName());
                    case SLOT_NAME:
                    		return name();
                    case SLOT_ROLES:
                    		return roles();
                    case SLOT_DELETED_AT:
                    		return deletedAt();
                    default: throw new IllegalArgumentException("Illegal property name for \"com.doruk.infrastructure.persistence.entity.Permission\": \"" + prop + "\"");
                }
            }

            @Override
            public final Object __get(String prop) {
                switch (prop) {
                    case "name":
                    		return name();
                    case "roles":
                    		return roles();
                    case "deletedAt":
                    		return deletedAt();
                    default: throw new IllegalArgumentException("Illegal property name for \"com.doruk.infrastructure.persistence.entity.Permission\": \"" + prop + "\"");
                }
            }

            public final String getName() {
                return name();
            }

            public final List<Role> getRoles() {
                return roles();
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
                type = Permission.class
        )
        private static class Impl extends Implementor implements Cloneable, Serializable {
            private Visibility __visibility;

            String __nameValue;

            NonSharedList<Role> __rolesValue;

            OffsetDateTime __deletedAtValue;

            boolean __deletedAtLoaded = false;

            @Override
            @JsonIgnore
            public String name() {
                if (__nameValue == null) {
                    throw new UnloadedException(Permission.class, "name");
                }
                return __nameValue;
            }

            @Override
            @JsonIgnore
            public List<Role> roles() {
                if (__rolesValue == null) {
                    throw new UnloadedException(Permission.class, "roles");
                }
                return __rolesValue;
            }

            @Override
            @JsonIgnore
            @org.jspecify.annotations.Nullable
            public OffsetDateTime deletedAt() {
                if (!__deletedAtLoaded) {
                    throw new UnloadedException(Permission.class, "deletedAt");
                }
                return __deletedAtValue;
            }

            @Override
            public Impl clone() {
                try {
                    Impl copy = (Impl) super.clone();
                    Visibility originalVisibility = this.__visibility;
                    if (originalVisibility != null) {
                        Visibility newVisibility = Visibility.of(3);
                        for (int propId = 0; propId < 3; propId++) {
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
                    case SLOT_NAME:
                    		return __nameValue != null;
                    case SLOT_ROLES:
                    		return __rolesValue != null;
                    case SLOT_DELETED_AT:
                    		return __deletedAtLoaded;
                    default: throw new IllegalArgumentException("Illegal property name for \"com.doruk.infrastructure.persistence.entity.Permission\": \"" + prop + "\"");
                }
            }

            @Override
            public boolean __isLoaded(String prop) {
                switch (prop) {
                    case "name":
                    		return __nameValue != null;
                    case "roles":
                    		return __rolesValue != null;
                    case "deletedAt":
                    		return __deletedAtLoaded;
                    default: throw new IllegalArgumentException("Illegal property name for \"com.doruk.infrastructure.persistence.entity.Permission\": \"" + prop + "\"");
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
                    case SLOT_NAME:
                    		return __visibility.visible(SLOT_NAME);
                    case SLOT_ROLES:
                    		return __visibility.visible(SLOT_ROLES);
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
                    case "name":
                    		return __visibility.visible(SLOT_NAME);
                    case "roles":
                    		return __visibility.visible(SLOT_ROLES);
                    case "deletedAt":
                    		return __visibility.visible(SLOT_DELETED_AT);
                    default: return true;
                }
            }

            @Override
            public int hashCode() {
                int hash = __visibility != null ? __visibility.hashCode() : 0;
                if (__nameValue != null) {
                    hash = 31 * hash + __nameValue.hashCode();
                    // If entity-id is loaded, return directly
                    return hash;
                }
                if (__rolesValue != null) {
                    hash = 31 * hash + __rolesValue.hashCode();
                }
                if (__deletedAtLoaded && __deletedAtValue != null) {
                    hash = 31 * hash + __deletedAtValue.hashCode();
                }
                return hash;
            }

            private int __shallowHashCode() {
                int hash = __visibility != null ? __visibility.hashCode() : 0;
                if (__nameValue != null) {
                    hash = 31 * hash + System.identityHashCode(__nameValue);
                }
                if (__rolesValue != null) {
                    hash = 31 * hash + System.identityHashCode(__rolesValue);
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
                if (__isVisible(PropId.byIndex(SLOT_NAME)) != __other.__isVisible(PropId.byIndex(SLOT_NAME))) {
                    return false;
                }
                boolean __nameLoaded = __nameValue != null;
                if (__nameLoaded != __other.__isLoaded(PropId.byIndex(SLOT_NAME))) {
                    return false;
                }
                if (__nameLoaded) {
                    // If entity-id is loaded, return directly
                    return Objects.equals(__nameValue, __other.name());
                }
                if (__isVisible(PropId.byIndex(SLOT_ROLES)) != __other.__isVisible(PropId.byIndex(SLOT_ROLES))) {
                    return false;
                }
                boolean __rolesLoaded = __rolesValue != null;
                if (__rolesLoaded != __other.__isLoaded(PropId.byIndex(SLOT_ROLES))) {
                    return false;
                }
                if (__rolesLoaded && !Objects.equals(__rolesValue, __other.roles())) {
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
                if (__isVisible(PropId.byIndex(SLOT_ROLES)) != __other.__isVisible(PropId.byIndex(SLOT_ROLES))) {
                    return false;
                }
                boolean __rolesLoaded = __rolesValue != null;
                if (__rolesLoaded != __other.__isLoaded(PropId.byIndex(SLOT_ROLES))) {
                    return false;
                }
                if (__rolesLoaded && __rolesValue != __other.roles()) {
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
                type = Permission.class
        )
        private static class DraftImpl extends Implementor implements DraftSpi, PermissionDraft {
            private DraftContext __ctx;

            private Impl __base;

            private Impl __modified;

            private boolean __resolving;

            private Permission __resolved;

            DraftImpl(DraftContext ctx, Permission base) {
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
            public String name() {
                return (__modified!= null ? __modified : __base).name();
            }

            @Override
            public PermissionDraft setName(String name) {
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
            public List<Role> roles() {
                return __ctx.toDraftList((__modified!= null ? __modified : __base).roles(), Role.class, true);
            }

            @Override
            public List<RoleDraft> roles(boolean autoCreate) {
                if (autoCreate && (!__isLoaded(PropId.byIndex(SLOT_ROLES)))) {
                    setRoles(new ArrayList<>());
                }
                return __ctx.toDraftList((__modified!= null ? __modified : __base).roles(), Role.class, true);
            }

            @Override
            public PermissionDraft setRoles(List<Role> roles) {
                if (__resolved != null) {
                    throw new IllegalStateException("The current draft has been resolved so it cannot be modified");
                }
                if (roles == null) {
                    throw new IllegalArgumentException(
                        "'roles' cannot be null, please specify non-null value or use nullable annotation to decorate this property"
                    );
                }
                Impl __tmpModified = __modified();
                __tmpModified.__rolesValue = NonSharedList.of(__tmpModified.__rolesValue, roles);
                return this;
            }

            @Override
            public PermissionDraft addIntoRoles(DraftConsumer<RoleDraft> block) {
                addIntoRoles(null, block);
                return this;
            }

            @Override
            public PermissionDraft addIntoRoles(Role base, DraftConsumer<RoleDraft> block) {
                roles(true).add((RoleDraft)RoleDraft.$.produce(base, block));
                return this;
            }

            @Override
            @JsonIgnore
            @org.jspecify.annotations.Nullable
            public OffsetDateTime deletedAt() {
                return (__modified!= null ? __modified : __base).deletedAt();
            }

            @Override
            public PermissionDraft setDeletedAt(OffsetDateTime deletedAt) {
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
                    case SLOT_NAME:
                    		setName((String)value);break;
                    case SLOT_ROLES:
                    		setRoles((List<Role>)value);break;
                    case SLOT_DELETED_AT:
                    		setDeletedAt((OffsetDateTime)value);break;
                    default: throw new IllegalArgumentException("Illegal property id for \"com.doruk.infrastructure.persistence.entity.Permission\": \"" + prop + "\"");
                }
            }

            @SuppressWarnings("all")
            @Override
            public void __set(String prop, Object value) {
                switch (prop) {
                    case "name":
                    		setName((String)value);break;
                    case "roles":
                    		setRoles((List<Role>)value);break;
                    case "deletedAt":
                    		setDeletedAt((OffsetDateTime)value);break;
                    default: throw new IllegalArgumentException("Illegal property name for \"com.doruk.infrastructure.persistence.entity.Permission\": \"" + prop + "\"");
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
                    __modified().__visibility = __visibility = Visibility.of(3);
                }
                int __propIndex = prop.asIndex();
                switch (__propIndex) {
                    case -1:
                    		__show(prop.asName(), visible);
                    return;
                    case SLOT_NAME:
                    		__visibility.show(SLOT_NAME, visible);break;
                    case SLOT_ROLES:
                    		__visibility.show(SLOT_ROLES, visible);break;
                    case SLOT_DELETED_AT:
                    		__visibility.show(SLOT_DELETED_AT, visible);break;
                    default: throw new IllegalArgumentException(
                                "Illegal property id for \"com.doruk.infrastructure.persistence.entity.Permission\": \"" + 
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
                    __modified().__visibility = __visibility = Visibility.of(3);
                }
                switch (prop) {
                    case "name":
                    		__visibility.show(SLOT_NAME, visible);break;
                    case "roles":
                    		__visibility.show(SLOT_ROLES, visible);break;
                    case "deletedAt":
                    		__visibility.show(SLOT_DELETED_AT, visible);break;
                    default: throw new IllegalArgumentException(
                                "Illegal property name for \"com.doruk.infrastructure.persistence.entity.Permission\": \"" + 
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
                    case SLOT_NAME:
                    		__modified().__nameValue = null;break;
                    case SLOT_ROLES:
                    		__modified().__rolesValue = null;break;
                    case SLOT_DELETED_AT:
                    		__modified().__deletedAtValue = null;
                    __modified().__deletedAtLoaded = false;break;
                    default: throw new IllegalArgumentException("Illegal property id for \"com.doruk.infrastructure.persistence.entity.Permission\": \"" + prop + "\", it does not exist or its loaded state is not controllable");
                }
            }

            @Override
            public void __unload(String prop) {
                if (__resolved != null) {
                    throw new IllegalStateException("The current draft has been resolved so it cannot be modified");
                }
                switch (prop) {
                    case "name":
                    		__modified().__nameValue = null;break;
                    case "roles":
                    		__modified().__rolesValue = null;break;
                    case "deletedAt":
                    		__modified().__deletedAtValue = null;
                    __modified().__deletedAtLoaded = false;break;
                    default: throw new IllegalArgumentException("Illegal property name for \"com.doruk.infrastructure.persistence.entity.Permission\": \"" + prop + "\", it does not exist or its loaded state is not controllable");
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
                        if (base.__isLoaded(PropId.byIndex(SLOT_ROLES))) {
                            List<Role> oldValue = base.roles();
                            List<Role> newValue = __ctx.resolveList(oldValue);
                            if (oldValue != newValue) {
                                setRoles(newValue);
                            }
                        }
                        __tmpModified = __modified;
                    }
                    else {
                        __tmpModified.__rolesValue = NonSharedList.of(__tmpModified.__rolesValue, __ctx.resolveList(__tmpModified.__rolesValue));
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
            type = Permission.class
    )
    class Builder {
        private final Producer.DraftImpl __draft;

        public Builder() {
            this(null);
        }

        public Builder(@org.jspecify.annotations.Nullable Permission base) {
            __draft = new Producer.DraftImpl(null, base);
        }

        public Builder name(@NonNull String name) {
            if (name != null) {
                __draft.setName(name);
            }
            return this;
        }

        public Builder roles(@NonNull List<Role> roles) {
            if (roles != null) {
                __draft.setRoles(roles);
            }
            return this;
        }

        public Builder deletedAt(@org.jspecify.annotations.Nullable OffsetDateTime deletedAt) {
            __draft.setDeletedAt(deletedAt);
            return this;
        }

        public Permission build() {
            return (Permission)__draft.__modified();
        }
    }
}
