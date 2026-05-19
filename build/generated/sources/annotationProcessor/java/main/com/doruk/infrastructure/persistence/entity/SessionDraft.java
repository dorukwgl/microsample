package com.doruk.infrastructure.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.annotation.Nullable;
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
import org.jspecify.annotations.NonNull;

@GeneratedBy(
        type = Session.class
)
public interface SessionDraft extends Session, Draft {
    SessionDraft.Producer $ = Producer.INSTANCE;

    @OldChain
    SessionDraft setId(long id);

    UserDraft user();

    UserDraft user(boolean autoCreate);

    @OldChain
    SessionDraft setUser(User user);

    @NonNull
    @JsonIgnore
    UUID userId();

    @OldChain
    SessionDraft setUserId(@NonNull UUID userId);

    @OldChain
    SessionDraft applyUser(DraftConsumer<UserDraft> block);

    @OldChain
    SessionDraft applyUser(User base, DraftConsumer<UserDraft> block);

    @OldChain
    SessionDraft setSessionId(String sessionId);

    @OldChain
    SessionDraft setDeviceInfo(String deviceInfo);

    @OldChain
    SessionDraft setDeviceId(String deviceId);

    @OldChain
    SessionDraft setExpiresAt(OffsetDateTime expiresAt);

    @OldChain
    SessionDraft setCreatedAt(OffsetDateTime createdAt);

    List<Integer> cachedPermissions(boolean autoCreate);

    @OldChain
    SessionDraft setCachedPermissions(List<Integer> cachedPermissions);

    @GeneratedBy(
            type = Session.class
    )
    class Producer {
        static final Producer INSTANCE = new Producer();

        public static final int SLOT_ID = 0;

        public static final int SLOT_USER = 1;

        public static final int SLOT_SESSION_ID = 2;

        public static final int SLOT_DEVICE_INFO = 3;

        public static final int SLOT_DEVICE_ID = 4;

        public static final int SLOT_EXPIRES_AT = 5;

        public static final int SLOT_CREATED_AT = 6;

        public static final int SLOT_CACHED_PERMISSIONS = 7;

        public static final ImmutableType TYPE = ImmutableType
            .newBuilder(
                "0.10.7",
                Session.class,
                Collections.emptyList(),
                (ctx, base) -> new DraftImpl(ctx, (Session)base)
            )
            .id(SLOT_ID, "id", long.class)
            .add(SLOT_USER, "user", ManyToOne.class, User.class, false)
            .key(SLOT_SESSION_ID, "sessionId", String.class, false)
            .add(SLOT_DEVICE_INFO, "deviceInfo", ImmutablePropCategory.SCALAR, String.class, true)
            .key(SLOT_DEVICE_ID, "deviceId", String.class, true)
            .add(SLOT_EXPIRES_AT, "expiresAt", ImmutablePropCategory.SCALAR, OffsetDateTime.class, false)
            .add(SLOT_CREATED_AT, "createdAt", ImmutablePropCategory.SCALAR, OffsetDateTime.class, false)
            .add(SLOT_CACHED_PERMISSIONS, "cachedPermissions", ImmutablePropCategory.SCALAR_LIST, Integer.class, false)
            .build();

        private Producer() {
        }

        public Session produce(DraftConsumer<SessionDraft> block) {
            return (Session)Internal.produce(TYPE, null, block);
        }

        public Session produce(Session base, DraftConsumer<SessionDraft> block) {
            return (Session)Internal.produce(TYPE, base, block);
        }

        public Session produce(boolean resolveImmediately, DraftConsumer<SessionDraft> block) {
            return (Session)Internal.produce(TYPE, null, resolveImmediately, block);
        }

        public Session produce(Session base, boolean resolveImmediately,
                DraftConsumer<SessionDraft> block) {
            return (Session)Internal.produce(TYPE, base, resolveImmediately, block);
        }

        /**
         * Class, not interface, for free-marker
         */
        @GeneratedBy(
                type = Session.class
        )
        @JsonPropertyOrder({"dummyPropForJacksonError__", "id", "user", "sessionId", "deviceInfo", "deviceId", "expiresAt", "createdAt", "cachedPermissions"})
        public abstract static class Implementor implements Session, ImmutableSpi {
            @Override
            public final Object __get(PropId prop) {
                int __propIndex = prop.asIndex();
                switch (__propIndex) {
                    case -1:
                    		return __get(prop.asName());
                    case SLOT_ID:
                    		return (Long)id();
                    case SLOT_USER:
                    		return user();
                    case SLOT_SESSION_ID:
                    		return sessionId();
                    case SLOT_DEVICE_INFO:
                    		return deviceInfo();
                    case SLOT_DEVICE_ID:
                    		return deviceId();
                    case SLOT_EXPIRES_AT:
                    		return expiresAt();
                    case SLOT_CREATED_AT:
                    		return createdAt();
                    case SLOT_CACHED_PERMISSIONS:
                    		return cachedPermissions();
                    default: throw new IllegalArgumentException("Illegal property name for \"com.doruk.infrastructure.persistence.entity.Session\": \"" + prop + "\"");
                }
            }

            @Override
            public final Object __get(String prop) {
                switch (prop) {
                    case "id":
                    		return (Long)id();
                    case "user":
                    		return user();
                    case "sessionId":
                    		return sessionId();
                    case "deviceInfo":
                    		return deviceInfo();
                    case "deviceId":
                    		return deviceId();
                    case "expiresAt":
                    		return expiresAt();
                    case "createdAt":
                    		return createdAt();
                    case "cachedPermissions":
                    		return cachedPermissions();
                    default: throw new IllegalArgumentException("Illegal property name for \"com.doruk.infrastructure.persistence.entity.Session\": \"" + prop + "\"");
                }
            }

            public final long getId() {
                return id();
            }

            public final User getUser() {
                return user();
            }

            public final String getSessionId() {
                return sessionId();
            }

            @Nullable
            public final String getDeviceInfo() {
                return deviceInfo();
            }

            @Nullable
            public final String getDeviceId() {
                return deviceId();
            }

            public final OffsetDateTime getExpiresAt() {
                return expiresAt();
            }

            public final OffsetDateTime getCreatedAt() {
                return createdAt();
            }

            public final List<Integer> getCachedPermissions() {
                return cachedPermissions();
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
                type = Session.class
        )
        private static class Impl extends Implementor implements Cloneable, Serializable {
            private Visibility __visibility;

            long __idValue;

            boolean __idLoaded = false;

            User __userValue;

            String __sessionIdValue;

            String __deviceInfoValue;

            boolean __deviceInfoLoaded = false;

            String __deviceIdValue;

            boolean __deviceIdLoaded = false;

            OffsetDateTime __expiresAtValue;

            OffsetDateTime __createdAtValue;

            NonSharedList<Integer> __cachedPermissionsValue;

            @Override
            @JsonIgnore
            public long id() {
                if (!__idLoaded) {
                    throw new UnloadedException(Session.class, "id");
                }
                return __idValue;
            }

            @Override
            @JsonIgnore
            public User user() {
                if (__userValue == null) {
                    throw new UnloadedException(Session.class, "user");
                }
                return __userValue;
            }

            @Override
            @JsonIgnore
            public String sessionId() {
                if (__sessionIdValue == null) {
                    throw new UnloadedException(Session.class, "sessionId");
                }
                return __sessionIdValue;
            }

            @Override
            @JsonIgnore
            @org.jspecify.annotations.Nullable
            public String deviceInfo() {
                if (!__deviceInfoLoaded) {
                    throw new UnloadedException(Session.class, "deviceInfo");
                }
                return __deviceInfoValue;
            }

            @Override
            @JsonIgnore
            @org.jspecify.annotations.Nullable
            public String deviceId() {
                if (!__deviceIdLoaded) {
                    throw new UnloadedException(Session.class, "deviceId");
                }
                return __deviceIdValue;
            }

            @Override
            @JsonIgnore
            public OffsetDateTime expiresAt() {
                if (__expiresAtValue == null) {
                    throw new UnloadedException(Session.class, "expiresAt");
                }
                return __expiresAtValue;
            }

            @Override
            @JsonIgnore
            public OffsetDateTime createdAt() {
                if (__createdAtValue == null) {
                    throw new UnloadedException(Session.class, "createdAt");
                }
                return __createdAtValue;
            }

            @Override
            @JsonIgnore
            public List<Integer> cachedPermissions() {
                if (__cachedPermissionsValue == null) {
                    throw new UnloadedException(Session.class, "cachedPermissions");
                }
                return __cachedPermissionsValue;
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
                    		return __idLoaded;
                    case SLOT_USER:
                    		return __userValue != null;
                    case SLOT_SESSION_ID:
                    		return __sessionIdValue != null;
                    case SLOT_DEVICE_INFO:
                    		return __deviceInfoLoaded;
                    case SLOT_DEVICE_ID:
                    		return __deviceIdLoaded;
                    case SLOT_EXPIRES_AT:
                    		return __expiresAtValue != null;
                    case SLOT_CREATED_AT:
                    		return __createdAtValue != null;
                    case SLOT_CACHED_PERMISSIONS:
                    		return __cachedPermissionsValue != null;
                    default: throw new IllegalArgumentException("Illegal property name for \"com.doruk.infrastructure.persistence.entity.Session\": \"" + prop + "\"");
                }
            }

            @Override
            public boolean __isLoaded(String prop) {
                switch (prop) {
                    case "id":
                    		return __idLoaded;
                    case "user":
                    		return __userValue != null;
                    case "sessionId":
                    		return __sessionIdValue != null;
                    case "deviceInfo":
                    		return __deviceInfoLoaded;
                    case "deviceId":
                    		return __deviceIdLoaded;
                    case "expiresAt":
                    		return __expiresAtValue != null;
                    case "createdAt":
                    		return __createdAtValue != null;
                    case "cachedPermissions":
                    		return __cachedPermissionsValue != null;
                    default: throw new IllegalArgumentException("Illegal property name for \"com.doruk.infrastructure.persistence.entity.Session\": \"" + prop + "\"");
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
                    case SLOT_USER:
                    		return __visibility.visible(SLOT_USER);
                    case SLOT_SESSION_ID:
                    		return __visibility.visible(SLOT_SESSION_ID);
                    case SLOT_DEVICE_INFO:
                    		return __visibility.visible(SLOT_DEVICE_INFO);
                    case SLOT_DEVICE_ID:
                    		return __visibility.visible(SLOT_DEVICE_ID);
                    case SLOT_EXPIRES_AT:
                    		return __visibility.visible(SLOT_EXPIRES_AT);
                    case SLOT_CREATED_AT:
                    		return __visibility.visible(SLOT_CREATED_AT);
                    case SLOT_CACHED_PERMISSIONS:
                    		return __visibility.visible(SLOT_CACHED_PERMISSIONS);
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
                    case "user":
                    		return __visibility.visible(SLOT_USER);
                    case "sessionId":
                    		return __visibility.visible(SLOT_SESSION_ID);
                    case "deviceInfo":
                    		return __visibility.visible(SLOT_DEVICE_INFO);
                    case "deviceId":
                    		return __visibility.visible(SLOT_DEVICE_ID);
                    case "expiresAt":
                    		return __visibility.visible(SLOT_EXPIRES_AT);
                    case "createdAt":
                    		return __visibility.visible(SLOT_CREATED_AT);
                    case "cachedPermissions":
                    		return __visibility.visible(SLOT_CACHED_PERMISSIONS);
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
                if (__userValue != null) {
                    hash = 31 * hash + __userValue.hashCode();
                }
                if (__sessionIdValue != null) {
                    hash = 31 * hash + __sessionIdValue.hashCode();
                }
                if (__deviceInfoLoaded && __deviceInfoValue != null) {
                    hash = 31 * hash + __deviceInfoValue.hashCode();
                }
                if (__deviceIdLoaded && __deviceIdValue != null) {
                    hash = 31 * hash + __deviceIdValue.hashCode();
                }
                if (__expiresAtValue != null) {
                    hash = 31 * hash + __expiresAtValue.hashCode();
                }
                if (__createdAtValue != null) {
                    hash = 31 * hash + __createdAtValue.hashCode();
                }
                if (__cachedPermissionsValue != null) {
                    hash = 31 * hash + __cachedPermissionsValue.hashCode();
                }
                return hash;
            }

            private int __shallowHashCode() {
                int hash = __visibility != null ? __visibility.hashCode() : 0;
                if (__idLoaded) {
                    hash = 31 * hash + Long.hashCode(__idValue);
                }
                if (__userValue != null) {
                    hash = 31 * hash + System.identityHashCode(__userValue);
                }
                if (__sessionIdValue != null) {
                    hash = 31 * hash + System.identityHashCode(__sessionIdValue);
                }
                if (__deviceInfoLoaded) {
                    hash = 31 * hash + System.identityHashCode(__deviceInfoValue);
                }
                if (__deviceIdLoaded) {
                    hash = 31 * hash + System.identityHashCode(__deviceIdValue);
                }
                if (__expiresAtValue != null) {
                    hash = 31 * hash + System.identityHashCode(__expiresAtValue);
                }
                if (__createdAtValue != null) {
                    hash = 31 * hash + System.identityHashCode(__createdAtValue);
                }
                if (__cachedPermissionsValue != null) {
                    hash = 31 * hash + System.identityHashCode(__cachedPermissionsValue);
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
                if (__isVisible(PropId.byIndex(SLOT_SESSION_ID)) != __other.__isVisible(PropId.byIndex(SLOT_SESSION_ID))) {
                    return false;
                }
                boolean __sessionIdLoaded = __sessionIdValue != null;
                if (__sessionIdLoaded != __other.__isLoaded(PropId.byIndex(SLOT_SESSION_ID))) {
                    return false;
                }
                if (__sessionIdLoaded && !Objects.equals(__sessionIdValue, __other.sessionId())) {
                    return false;
                }
                if (__isVisible(PropId.byIndex(SLOT_DEVICE_INFO)) != __other.__isVisible(PropId.byIndex(SLOT_DEVICE_INFO))) {
                    return false;
                }
                boolean __deviceInfoLoaded = this.__deviceInfoLoaded;
                if (__deviceInfoLoaded != __other.__isLoaded(PropId.byIndex(SLOT_DEVICE_INFO))) {
                    return false;
                }
                if (__deviceInfoLoaded && !Objects.equals(__deviceInfoValue, __other.deviceInfo())) {
                    return false;
                }
                if (__isVisible(PropId.byIndex(SLOT_DEVICE_ID)) != __other.__isVisible(PropId.byIndex(SLOT_DEVICE_ID))) {
                    return false;
                }
                boolean __deviceIdLoaded = this.__deviceIdLoaded;
                if (__deviceIdLoaded != __other.__isLoaded(PropId.byIndex(SLOT_DEVICE_ID))) {
                    return false;
                }
                if (__deviceIdLoaded && !Objects.equals(__deviceIdValue, __other.deviceId())) {
                    return false;
                }
                if (__isVisible(PropId.byIndex(SLOT_EXPIRES_AT)) != __other.__isVisible(PropId.byIndex(SLOT_EXPIRES_AT))) {
                    return false;
                }
                boolean __expiresAtLoaded = __expiresAtValue != null;
                if (__expiresAtLoaded != __other.__isLoaded(PropId.byIndex(SLOT_EXPIRES_AT))) {
                    return false;
                }
                if (__expiresAtLoaded && !Objects.equals(__expiresAtValue, __other.expiresAt())) {
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
                if (__isVisible(PropId.byIndex(SLOT_CACHED_PERMISSIONS)) != __other.__isVisible(PropId.byIndex(SLOT_CACHED_PERMISSIONS))) {
                    return false;
                }
                boolean __cachedPermissionsLoaded = __cachedPermissionsValue != null;
                if (__cachedPermissionsLoaded != __other.__isLoaded(PropId.byIndex(SLOT_CACHED_PERMISSIONS))) {
                    return false;
                }
                if (__cachedPermissionsLoaded && !Objects.equals(__cachedPermissionsValue, __other.cachedPermissions())) {
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
                if (__isVisible(PropId.byIndex(SLOT_SESSION_ID)) != __other.__isVisible(PropId.byIndex(SLOT_SESSION_ID))) {
                    return false;
                }
                boolean __sessionIdLoaded = __sessionIdValue != null;
                if (__sessionIdLoaded != __other.__isLoaded(PropId.byIndex(SLOT_SESSION_ID))) {
                    return false;
                }
                if (__sessionIdLoaded && __sessionIdValue != __other.sessionId()) {
                    return false;
                }
                if (__isVisible(PropId.byIndex(SLOT_DEVICE_INFO)) != __other.__isVisible(PropId.byIndex(SLOT_DEVICE_INFO))) {
                    return false;
                }
                boolean __deviceInfoLoaded = this.__deviceInfoLoaded;
                if (__deviceInfoLoaded != __other.__isLoaded(PropId.byIndex(SLOT_DEVICE_INFO))) {
                    return false;
                }
                if (__deviceInfoLoaded && __deviceInfoValue != __other.deviceInfo()) {
                    return false;
                }
                if (__isVisible(PropId.byIndex(SLOT_DEVICE_ID)) != __other.__isVisible(PropId.byIndex(SLOT_DEVICE_ID))) {
                    return false;
                }
                boolean __deviceIdLoaded = this.__deviceIdLoaded;
                if (__deviceIdLoaded != __other.__isLoaded(PropId.byIndex(SLOT_DEVICE_ID))) {
                    return false;
                }
                if (__deviceIdLoaded && __deviceIdValue != __other.deviceId()) {
                    return false;
                }
                if (__isVisible(PropId.byIndex(SLOT_EXPIRES_AT)) != __other.__isVisible(PropId.byIndex(SLOT_EXPIRES_AT))) {
                    return false;
                }
                boolean __expiresAtLoaded = __expiresAtValue != null;
                if (__expiresAtLoaded != __other.__isLoaded(PropId.byIndex(SLOT_EXPIRES_AT))) {
                    return false;
                }
                if (__expiresAtLoaded && __expiresAtValue != __other.expiresAt()) {
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
                if (__isVisible(PropId.byIndex(SLOT_CACHED_PERMISSIONS)) != __other.__isVisible(PropId.byIndex(SLOT_CACHED_PERMISSIONS))) {
                    return false;
                }
                boolean __cachedPermissionsLoaded = __cachedPermissionsValue != null;
                if (__cachedPermissionsLoaded != __other.__isLoaded(PropId.byIndex(SLOT_CACHED_PERMISSIONS))) {
                    return false;
                }
                if (__cachedPermissionsLoaded && __cachedPermissionsValue != __other.cachedPermissions()) {
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
                type = Session.class
        )
        private static class DraftImpl extends Implementor implements DraftSpi, SessionDraft {
            private DraftContext __ctx;

            private Impl __base;

            private Impl __modified;

            private boolean __resolving;

            private Session __resolved;

            DraftImpl(DraftContext ctx, Session base) {
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
            public SessionDraft setId(long id) {
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
            public SessionDraft setUser(User user) {
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

            @NonNull
            @JsonIgnore
            @Override
            public UUID userId() {
                return user().id();
            }

            @OldChain
            @Override
            public SessionDraft setUserId(@NonNull UUID userId) {
                user(true).setId(Objects.requireNonNull(userId, "\"user\" cannot be null"));
                return this;
            }

            @Override
            public SessionDraft applyUser(DraftConsumer<UserDraft> block) {
                applyUser(null, block);
                return this;
            }

            @Override
            public SessionDraft applyUser(User base, DraftConsumer<UserDraft> block) {
                setUser(UserDraft.$.produce(base, block));
                return this;
            }

            @Override
            @JsonIgnore
            public String sessionId() {
                return (__modified!= null ? __modified : __base).sessionId();
            }

            @Override
            public SessionDraft setSessionId(String sessionId) {
                if (__resolved != null) {
                    throw new IllegalStateException("The current draft has been resolved so it cannot be modified");
                }
                if (sessionId == null) {
                    throw new IllegalArgumentException(
                        "'sessionId' cannot be null, please specify non-null value or use nullable annotation to decorate this property"
                    );
                }
                Impl __tmpModified = __modified();
                __tmpModified.__sessionIdValue = sessionId;
                return this;
            }

            @Override
            @JsonIgnore
            @org.jspecify.annotations.Nullable
            public String deviceInfo() {
                return (__modified!= null ? __modified : __base).deviceInfo();
            }

            @Override
            public SessionDraft setDeviceInfo(String deviceInfo) {
                if (__resolved != null) {
                    throw new IllegalStateException("The current draft has been resolved so it cannot be modified");
                }
                Impl __tmpModified = __modified();
                __tmpModified.__deviceInfoValue = deviceInfo;
                __tmpModified.__deviceInfoLoaded = true;
                return this;
            }

            @Override
            @JsonIgnore
            @org.jspecify.annotations.Nullable
            public String deviceId() {
                return (__modified!= null ? __modified : __base).deviceId();
            }

            @Override
            public SessionDraft setDeviceId(String deviceId) {
                if (__resolved != null) {
                    throw new IllegalStateException("The current draft has been resolved so it cannot be modified");
                }
                Impl __tmpModified = __modified();
                __tmpModified.__deviceIdValue = deviceId;
                __tmpModified.__deviceIdLoaded = true;
                return this;
            }

            @Override
            @JsonIgnore
            public OffsetDateTime expiresAt() {
                return (__modified!= null ? __modified : __base).expiresAt();
            }

            @Override
            public SessionDraft setExpiresAt(OffsetDateTime expiresAt) {
                if (__resolved != null) {
                    throw new IllegalStateException("The current draft has been resolved so it cannot be modified");
                }
                if (expiresAt == null) {
                    throw new IllegalArgumentException(
                        "'expiresAt' cannot be null, please specify non-null value or use nullable annotation to decorate this property"
                    );
                }
                Impl __tmpModified = __modified();
                __tmpModified.__expiresAtValue = expiresAt;
                return this;
            }

            @Override
            @JsonIgnore
            public OffsetDateTime createdAt() {
                return (__modified!= null ? __modified : __base).createdAt();
            }

            @Override
            public SessionDraft setCreatedAt(OffsetDateTime createdAt) {
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
            public List<Integer> cachedPermissions() {
                return __ctx.toDraftList((__modified!= null ? __modified : __base).cachedPermissions(), Integer.class, false);
            }

            @Override
            public List<Integer> cachedPermissions(boolean autoCreate) {
                if (autoCreate && (!__isLoaded(PropId.byIndex(SLOT_CACHED_PERMISSIONS)))) {
                    setCachedPermissions(new ArrayList<>());
                }
                return __ctx.toDraftList((__modified!= null ? __modified : __base).cachedPermissions(), Integer.class, false);
            }

            @Override
            public SessionDraft setCachedPermissions(List<Integer> cachedPermissions) {
                if (__resolved != null) {
                    throw new IllegalStateException("The current draft has been resolved so it cannot be modified");
                }
                if (cachedPermissions == null) {
                    throw new IllegalArgumentException(
                        "'cachedPermissions' cannot be null, please specify non-null value or use nullable annotation to decorate this property"
                    );
                }
                Impl __tmpModified = __modified();
                __tmpModified.__cachedPermissionsValue = NonSharedList.of(__tmpModified.__cachedPermissionsValue, cachedPermissions);
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
                    case SLOT_USER:
                    		setUser((User)value);break;
                    case SLOT_SESSION_ID:
                    		setSessionId((String)value);break;
                    case SLOT_DEVICE_INFO:
                    		setDeviceInfo((String)value);break;
                    case SLOT_DEVICE_ID:
                    		setDeviceId((String)value);break;
                    case SLOT_EXPIRES_AT:
                    		setExpiresAt((OffsetDateTime)value);break;
                    case SLOT_CREATED_AT:
                    		setCreatedAt((OffsetDateTime)value);break;
                    case SLOT_CACHED_PERMISSIONS:
                    		setCachedPermissions((List<Integer>)value);break;
                    default: throw new IllegalArgumentException("Illegal property id for \"com.doruk.infrastructure.persistence.entity.Session\": \"" + prop + "\"");
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
                    case "user":
                    		setUser((User)value);break;
                    case "sessionId":
                    		setSessionId((String)value);break;
                    case "deviceInfo":
                    		setDeviceInfo((String)value);break;
                    case "deviceId":
                    		setDeviceId((String)value);break;
                    case "expiresAt":
                    		setExpiresAt((OffsetDateTime)value);break;
                    case "createdAt":
                    		setCreatedAt((OffsetDateTime)value);break;
                    case "cachedPermissions":
                    		setCachedPermissions((List<Integer>)value);break;
                    default: throw new IllegalArgumentException("Illegal property name for \"com.doruk.infrastructure.persistence.entity.Session\": \"" + prop + "\"");
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
                    case SLOT_USER:
                    		__visibility.show(SLOT_USER, visible);break;
                    case SLOT_SESSION_ID:
                    		__visibility.show(SLOT_SESSION_ID, visible);break;
                    case SLOT_DEVICE_INFO:
                    		__visibility.show(SLOT_DEVICE_INFO, visible);break;
                    case SLOT_DEVICE_ID:
                    		__visibility.show(SLOT_DEVICE_ID, visible);break;
                    case SLOT_EXPIRES_AT:
                    		__visibility.show(SLOT_EXPIRES_AT, visible);break;
                    case SLOT_CREATED_AT:
                    		__visibility.show(SLOT_CREATED_AT, visible);break;
                    case SLOT_CACHED_PERMISSIONS:
                    		__visibility.show(SLOT_CACHED_PERMISSIONS, visible);break;
                    default: throw new IllegalArgumentException(
                                "Illegal property id for \"com.doruk.infrastructure.persistence.entity.Session\": \"" + 
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
                    case "user":
                    		__visibility.show(SLOT_USER, visible);break;
                    case "sessionId":
                    		__visibility.show(SLOT_SESSION_ID, visible);break;
                    case "deviceInfo":
                    		__visibility.show(SLOT_DEVICE_INFO, visible);break;
                    case "deviceId":
                    		__visibility.show(SLOT_DEVICE_ID, visible);break;
                    case "expiresAt":
                    		__visibility.show(SLOT_EXPIRES_AT, visible);break;
                    case "createdAt":
                    		__visibility.show(SLOT_CREATED_AT, visible);break;
                    case "cachedPermissions":
                    		__visibility.show(SLOT_CACHED_PERMISSIONS, visible);break;
                    default: throw new IllegalArgumentException(
                                "Illegal property name for \"com.doruk.infrastructure.persistence.entity.Session\": \"" + 
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
                    case SLOT_USER:
                    		__modified().__userValue = null;break;
                    case SLOT_SESSION_ID:
                    		__modified().__sessionIdValue = null;break;
                    case SLOT_DEVICE_INFO:
                    		__modified().__deviceInfoValue = null;
                    __modified().__deviceInfoLoaded = false;break;
                    case SLOT_DEVICE_ID:
                    		__modified().__deviceIdValue = null;
                    __modified().__deviceIdLoaded = false;break;
                    case SLOT_EXPIRES_AT:
                    		__modified().__expiresAtValue = null;break;
                    case SLOT_CREATED_AT:
                    		__modified().__createdAtValue = null;break;
                    case SLOT_CACHED_PERMISSIONS:
                    		__modified().__cachedPermissionsValue = null;break;
                    default: throw new IllegalArgumentException("Illegal property id for \"com.doruk.infrastructure.persistence.entity.Session\": \"" + prop + "\", it does not exist or its loaded state is not controllable");
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
                    case "user":
                    		__modified().__userValue = null;break;
                    case "sessionId":
                    		__modified().__sessionIdValue = null;break;
                    case "deviceInfo":
                    		__modified().__deviceInfoValue = null;
                    __modified().__deviceInfoLoaded = false;break;
                    case "deviceId":
                    		__modified().__deviceIdValue = null;
                    __modified().__deviceIdLoaded = false;break;
                    case "expiresAt":
                    		__modified().__expiresAtValue = null;break;
                    case "createdAt":
                    		__modified().__createdAtValue = null;break;
                    case "cachedPermissions":
                    		__modified().__cachedPermissionsValue = null;break;
                    default: throw new IllegalArgumentException("Illegal property name for \"com.doruk.infrastructure.persistence.entity.Session\": \"" + prop + "\", it does not exist or its loaded state is not controllable");
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
                        if (base.__isLoaded(PropId.byIndex(SLOT_USER))) {
                            User oldValue = base.user();
                            User newValue = __ctx.resolveObject(oldValue);
                            if (oldValue != newValue) {
                                setUser(newValue);
                            }
                        }
                        if (base.__isLoaded(PropId.byIndex(SLOT_CACHED_PERMISSIONS))) {
                            List<Integer> oldValue = base.cachedPermissions();
                            List<Integer> newValue = __ctx.resolveList(oldValue);
                            if (oldValue != newValue) {
                                setCachedPermissions(newValue);
                            }
                        }
                        __tmpModified = __modified;
                    }
                    else {
                        __tmpModified.__userValue = __ctx.resolveObject(__tmpModified.__userValue);
                        __tmpModified.__cachedPermissionsValue = NonSharedList.of(__tmpModified.__cachedPermissionsValue, __ctx.resolveList(__tmpModified.__cachedPermissionsValue));
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
            type = Session.class
    )
    class Builder {
        private final Producer.DraftImpl __draft;

        public Builder() {
            this(null);
        }

        public Builder(@org.jspecify.annotations.Nullable Session base) {
            __draft = new Producer.DraftImpl(null, base);
        }

        public Builder id(@NonNull Long id) {
            if (id != null) {
                __draft.setId(id);
            }
            return this;
        }

        public Builder user(@NonNull User user) {
            if (user != null) {
                __draft.setUser(user);
            }
            return this;
        }

        public Builder sessionId(@NonNull String sessionId) {
            if (sessionId != null) {
                __draft.setSessionId(sessionId);
            }
            return this;
        }

        public Builder deviceInfo(@org.jspecify.annotations.Nullable String deviceInfo) {
            __draft.setDeviceInfo(deviceInfo);
            return this;
        }

        public Builder deviceId(@org.jspecify.annotations.Nullable String deviceId) {
            __draft.setDeviceId(deviceId);
            return this;
        }

        public Builder expiresAt(@NonNull OffsetDateTime expiresAt) {
            if (expiresAt != null) {
                __draft.setExpiresAt(expiresAt);
            }
            return this;
        }

        public Builder createdAt(@NonNull OffsetDateTime createdAt) {
            if (createdAt != null) {
                __draft.setCreatedAt(createdAt);
            }
            return this;
        }

        public Builder cachedPermissions(@NonNull List<Integer> cachedPermissions) {
            if (cachedPermissions != null) {
                __draft.setCachedPermissions(cachedPermissions);
            }
            return this;
        }

        public Session build() {
            return (Session)__draft.__modified();
        }
    }
}
