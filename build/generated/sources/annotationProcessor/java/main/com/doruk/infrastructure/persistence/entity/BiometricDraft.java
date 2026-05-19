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

@GeneratedBy(
        type = Biometric.class
)
public interface BiometricDraft extends Biometric, Draft {
    BiometricDraft.Producer $ = Producer.INSTANCE;

    @OldChain
    BiometricDraft setId(UUID id);

    UserDraft user();

    UserDraft user(boolean autoCreate);

    @OldChain
    BiometricDraft setUser(User user);

    @NonNull
    @JsonIgnore
    UUID userId();

    @OldChain
    BiometricDraft setUserId(@NonNull UUID userId);

    @OldChain
    BiometricDraft applyUser(DraftConsumer<UserDraft> block);

    @OldChain
    BiometricDraft applyUser(User base, DraftConsumer<UserDraft> block);

    @OldChain
    BiometricDraft setPublicKey(byte[] publicKey);

    @OldChain
    BiometricDraft setDeviceId(String deviceId);

    @OldChain
    BiometricDraft setLastUsedAt(OffsetDateTime lastUsedAt);

    @GeneratedBy(
            type = Biometric.class
    )
    class Producer {
        static final Producer INSTANCE = new Producer();

        public static final int SLOT_ID = 0;

        public static final int SLOT_USER = 1;

        public static final int SLOT_PUBLIC_KEY = 2;

        public static final int SLOT_DEVICE_ID = 3;

        public static final int SLOT_LAST_USED_AT = 4;

        public static final ImmutableType TYPE = ImmutableType
            .newBuilder(
                "0.10.7",
                Biometric.class,
                Collections.emptyList(),
                (ctx, base) -> new DraftImpl(ctx, (Biometric)base)
            )
            .id(SLOT_ID, "id", UUID.class)
            .add(SLOT_USER, "user", ManyToOne.class, User.class, false)
            .key(SLOT_PUBLIC_KEY, "publicKey", byte[].class, false)
            .key(SLOT_DEVICE_ID, "deviceId", String.class, false)
            .add(SLOT_LAST_USED_AT, "lastUsedAt", ImmutablePropCategory.SCALAR, OffsetDateTime.class, true)
            .build();

        private Producer() {
        }

        public Biometric produce(DraftConsumer<BiometricDraft> block) {
            return (Biometric)Internal.produce(TYPE, null, block);
        }

        public Biometric produce(Biometric base, DraftConsumer<BiometricDraft> block) {
            return (Biometric)Internal.produce(TYPE, base, block);
        }

        public Biometric produce(boolean resolveImmediately, DraftConsumer<BiometricDraft> block) {
            return (Biometric)Internal.produce(TYPE, null, resolveImmediately, block);
        }

        public Biometric produce(Biometric base, boolean resolveImmediately,
                DraftConsumer<BiometricDraft> block) {
            return (Biometric)Internal.produce(TYPE, base, resolveImmediately, block);
        }

        /**
         * Class, not interface, for free-marker
         */
        @GeneratedBy(
                type = Biometric.class
        )
        @JsonPropertyOrder({"dummyPropForJacksonError__", "id", "user", "publicKey", "deviceId", "lastUsedAt"})
        public abstract static class Implementor implements Biometric, ImmutableSpi {
            @Override
            public final Object __get(PropId prop) {
                int __propIndex = prop.asIndex();
                switch (__propIndex) {
                    case -1:
                    		return __get(prop.asName());
                    case SLOT_ID:
                    		return id();
                    case SLOT_USER:
                    		return user();
                    case SLOT_PUBLIC_KEY:
                    		return publicKey();
                    case SLOT_DEVICE_ID:
                    		return deviceId();
                    case SLOT_LAST_USED_AT:
                    		return lastUsedAt();
                    default: throw new IllegalArgumentException("Illegal property name for \"com.doruk.infrastructure.persistence.entity.Biometric\": \"" + prop + "\"");
                }
            }

            @Override
            public final Object __get(String prop) {
                switch (prop) {
                    case "id":
                    		return id();
                    case "user":
                    		return user();
                    case "publicKey":
                    		return publicKey();
                    case "deviceId":
                    		return deviceId();
                    case "lastUsedAt":
                    		return lastUsedAt();
                    default: throw new IllegalArgumentException("Illegal property name for \"com.doruk.infrastructure.persistence.entity.Biometric\": \"" + prop + "\"");
                }
            }

            public final UUID getId() {
                return id();
            }

            public final User getUser() {
                return user();
            }

            public final byte[] getPublicKey() {
                return publicKey();
            }

            public final String getDeviceId() {
                return deviceId();
            }

            @Nullable
            public final OffsetDateTime getLastUsedAt() {
                return lastUsedAt();
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
                type = Biometric.class
        )
        private static class Impl extends Implementor implements Cloneable, Serializable {
            private Visibility __visibility;

            UUID __idValue;

            User __userValue;

            byte[] __publicKeyValue;

            String __deviceIdValue;

            OffsetDateTime __lastUsedAtValue;

            boolean __lastUsedAtLoaded = false;

            @Override
            @JsonIgnore
            public UUID id() {
                if (__idValue == null) {
                    throw new UnloadedException(Biometric.class, "id");
                }
                return __idValue;
            }

            @Override
            @JsonIgnore
            public User user() {
                if (__userValue == null) {
                    throw new UnloadedException(Biometric.class, "user");
                }
                return __userValue;
            }

            @Override
            @JsonIgnore
            public byte[] publicKey() {
                if (__publicKeyValue == null) {
                    throw new UnloadedException(Biometric.class, "publicKey");
                }
                return __publicKeyValue;
            }

            @Override
            @JsonIgnore
            public String deviceId() {
                if (__deviceIdValue == null) {
                    throw new UnloadedException(Biometric.class, "deviceId");
                }
                return __deviceIdValue;
            }

            @Override
            @JsonIgnore
            @org.jspecify.annotations.Nullable
            public OffsetDateTime lastUsedAt() {
                if (!__lastUsedAtLoaded) {
                    throw new UnloadedException(Biometric.class, "lastUsedAt");
                }
                return __lastUsedAtValue;
            }

            @Override
            public Impl clone() {
                try {
                    Impl copy = (Impl) super.clone();
                    Visibility originalVisibility = this.__visibility;
                    if (originalVisibility != null) {
                        Visibility newVisibility = Visibility.of(5);
                        for (int propId = 0; propId < 5; propId++) {
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
                    case SLOT_USER:
                    		return __userValue != null;
                    case SLOT_PUBLIC_KEY:
                    		return __publicKeyValue != null;
                    case SLOT_DEVICE_ID:
                    		return __deviceIdValue != null;
                    case SLOT_LAST_USED_AT:
                    		return __lastUsedAtLoaded;
                    default: throw new IllegalArgumentException("Illegal property name for \"com.doruk.infrastructure.persistence.entity.Biometric\": \"" + prop + "\"");
                }
            }

            @Override
            public boolean __isLoaded(String prop) {
                switch (prop) {
                    case "id":
                    		return __idValue != null;
                    case "user":
                    		return __userValue != null;
                    case "publicKey":
                    		return __publicKeyValue != null;
                    case "deviceId":
                    		return __deviceIdValue != null;
                    case "lastUsedAt":
                    		return __lastUsedAtLoaded;
                    default: throw new IllegalArgumentException("Illegal property name for \"com.doruk.infrastructure.persistence.entity.Biometric\": \"" + prop + "\"");
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
                    case SLOT_PUBLIC_KEY:
                    		return __visibility.visible(SLOT_PUBLIC_KEY);
                    case SLOT_DEVICE_ID:
                    		return __visibility.visible(SLOT_DEVICE_ID);
                    case SLOT_LAST_USED_AT:
                    		return __visibility.visible(SLOT_LAST_USED_AT);
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
                    case "publicKey":
                    		return __visibility.visible(SLOT_PUBLIC_KEY);
                    case "deviceId":
                    		return __visibility.visible(SLOT_DEVICE_ID);
                    case "lastUsedAt":
                    		return __visibility.visible(SLOT_LAST_USED_AT);
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
                if (__userValue != null) {
                    hash = 31 * hash + __userValue.hashCode();
                }
                if (__publicKeyValue != null) {
                    hash = 31 * hash + __publicKeyValue.hashCode();
                }
                if (__deviceIdValue != null) {
                    hash = 31 * hash + __deviceIdValue.hashCode();
                }
                if (__lastUsedAtLoaded && __lastUsedAtValue != null) {
                    hash = 31 * hash + __lastUsedAtValue.hashCode();
                }
                return hash;
            }

            private int __shallowHashCode() {
                int hash = __visibility != null ? __visibility.hashCode() : 0;
                if (__idValue != null) {
                    hash = 31 * hash + System.identityHashCode(__idValue);
                }
                if (__userValue != null) {
                    hash = 31 * hash + System.identityHashCode(__userValue);
                }
                if (__publicKeyValue != null) {
                    hash = 31 * hash + System.identityHashCode(__publicKeyValue);
                }
                if (__deviceIdValue != null) {
                    hash = 31 * hash + System.identityHashCode(__deviceIdValue);
                }
                if (__lastUsedAtLoaded) {
                    hash = 31 * hash + System.identityHashCode(__lastUsedAtValue);
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
                if (__isVisible(PropId.byIndex(SLOT_DEVICE_ID)) != __other.__isVisible(PropId.byIndex(SLOT_DEVICE_ID))) {
                    return false;
                }
                boolean __deviceIdLoaded = __deviceIdValue != null;
                if (__deviceIdLoaded != __other.__isLoaded(PropId.byIndex(SLOT_DEVICE_ID))) {
                    return false;
                }
                if (__deviceIdLoaded && !Objects.equals(__deviceIdValue, __other.deviceId())) {
                    return false;
                }
                if (__isVisible(PropId.byIndex(SLOT_LAST_USED_AT)) != __other.__isVisible(PropId.byIndex(SLOT_LAST_USED_AT))) {
                    return false;
                }
                boolean __lastUsedAtLoaded = this.__lastUsedAtLoaded;
                if (__lastUsedAtLoaded != __other.__isLoaded(PropId.byIndex(SLOT_LAST_USED_AT))) {
                    return false;
                }
                if (__lastUsedAtLoaded && !Objects.equals(__lastUsedAtValue, __other.lastUsedAt())) {
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
                if (__isVisible(PropId.byIndex(SLOT_DEVICE_ID)) != __other.__isVisible(PropId.byIndex(SLOT_DEVICE_ID))) {
                    return false;
                }
                boolean __deviceIdLoaded = __deviceIdValue != null;
                if (__deviceIdLoaded != __other.__isLoaded(PropId.byIndex(SLOT_DEVICE_ID))) {
                    return false;
                }
                if (__deviceIdLoaded && __deviceIdValue != __other.deviceId()) {
                    return false;
                }
                if (__isVisible(PropId.byIndex(SLOT_LAST_USED_AT)) != __other.__isVisible(PropId.byIndex(SLOT_LAST_USED_AT))) {
                    return false;
                }
                boolean __lastUsedAtLoaded = this.__lastUsedAtLoaded;
                if (__lastUsedAtLoaded != __other.__isLoaded(PropId.byIndex(SLOT_LAST_USED_AT))) {
                    return false;
                }
                if (__lastUsedAtLoaded && __lastUsedAtValue != __other.lastUsedAt()) {
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
                type = Biometric.class
        )
        private static class DraftImpl extends Implementor implements DraftSpi, BiometricDraft {
            private DraftContext __ctx;

            private Impl __base;

            private Impl __modified;

            private boolean __resolving;

            private Biometric __resolved;

            DraftImpl(DraftContext ctx, Biometric base) {
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
            public BiometricDraft setId(UUID id) {
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
            public BiometricDraft setUser(User user) {
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
            public BiometricDraft setUserId(@NonNull UUID userId) {
                user(true).setId(Objects.requireNonNull(userId, "\"user\" cannot be null"));
                return this;
            }

            @Override
            public BiometricDraft applyUser(DraftConsumer<UserDraft> block) {
                applyUser(null, block);
                return this;
            }

            @Override
            public BiometricDraft applyUser(User base, DraftConsumer<UserDraft> block) {
                setUser(UserDraft.$.produce(base, block));
                return this;
            }

            @Override
            @JsonIgnore
            public byte[] publicKey() {
                return (__modified!= null ? __modified : __base).publicKey();
            }

            @Override
            public BiometricDraft setPublicKey(byte[] publicKey) {
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
            public String deviceId() {
                return (__modified!= null ? __modified : __base).deviceId();
            }

            @Override
            public BiometricDraft setDeviceId(String deviceId) {
                if (__resolved != null) {
                    throw new IllegalStateException("The current draft has been resolved so it cannot be modified");
                }
                if (deviceId == null) {
                    throw new IllegalArgumentException(
                        "'deviceId' cannot be null, please specify non-null value or use nullable annotation to decorate this property"
                    );
                }
                Impl __tmpModified = __modified();
                __tmpModified.__deviceIdValue = deviceId;
                return this;
            }

            @Override
            @JsonIgnore
            @org.jspecify.annotations.Nullable
            public OffsetDateTime lastUsedAt() {
                return (__modified!= null ? __modified : __base).lastUsedAt();
            }

            @Override
            public BiometricDraft setLastUsedAt(OffsetDateTime lastUsedAt) {
                if (__resolved != null) {
                    throw new IllegalStateException("The current draft has been resolved so it cannot be modified");
                }
                Impl __tmpModified = __modified();
                __tmpModified.__lastUsedAtValue = lastUsedAt;
                __tmpModified.__lastUsedAtLoaded = true;
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
                    case SLOT_USER:
                    		setUser((User)value);break;
                    case SLOT_PUBLIC_KEY:
                    		setPublicKey((byte[])value);break;
                    case SLOT_DEVICE_ID:
                    		setDeviceId((String)value);break;
                    case SLOT_LAST_USED_AT:
                    		setLastUsedAt((OffsetDateTime)value);break;
                    default: throw new IllegalArgumentException("Illegal property id for \"com.doruk.infrastructure.persistence.entity.Biometric\": \"" + prop + "\"");
                }
            }

            @SuppressWarnings("all")
            @Override
            public void __set(String prop, Object value) {
                switch (prop) {
                    case "id":
                    		setId((UUID)value);break;
                    case "user":
                    		setUser((User)value);break;
                    case "publicKey":
                    		setPublicKey((byte[])value);break;
                    case "deviceId":
                    		setDeviceId((String)value);break;
                    case "lastUsedAt":
                    		setLastUsedAt((OffsetDateTime)value);break;
                    default: throw new IllegalArgumentException("Illegal property name for \"com.doruk.infrastructure.persistence.entity.Biometric\": \"" + prop + "\"");
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
                    __modified().__visibility = __visibility = Visibility.of(5);
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
                    case SLOT_PUBLIC_KEY:
                    		__visibility.show(SLOT_PUBLIC_KEY, visible);break;
                    case SLOT_DEVICE_ID:
                    		__visibility.show(SLOT_DEVICE_ID, visible);break;
                    case SLOT_LAST_USED_AT:
                    		__visibility.show(SLOT_LAST_USED_AT, visible);break;
                    default: throw new IllegalArgumentException(
                                "Illegal property id for \"com.doruk.infrastructure.persistence.entity.Biometric\": \"" + 
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
                    __modified().__visibility = __visibility = Visibility.of(5);
                }
                switch (prop) {
                    case "id":
                    		__visibility.show(SLOT_ID, visible);break;
                    case "user":
                    		__visibility.show(SLOT_USER, visible);break;
                    case "publicKey":
                    		__visibility.show(SLOT_PUBLIC_KEY, visible);break;
                    case "deviceId":
                    		__visibility.show(SLOT_DEVICE_ID, visible);break;
                    case "lastUsedAt":
                    		__visibility.show(SLOT_LAST_USED_AT, visible);break;
                    default: throw new IllegalArgumentException(
                                "Illegal property name for \"com.doruk.infrastructure.persistence.entity.Biometric\": \"" + 
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
                    case SLOT_USER:
                    		__modified().__userValue = null;break;
                    case SLOT_PUBLIC_KEY:
                    		__modified().__publicKeyValue = null;break;
                    case SLOT_DEVICE_ID:
                    		__modified().__deviceIdValue = null;break;
                    case SLOT_LAST_USED_AT:
                    		__modified().__lastUsedAtValue = null;
                    __modified().__lastUsedAtLoaded = false;break;
                    default: throw new IllegalArgumentException("Illegal property id for \"com.doruk.infrastructure.persistence.entity.Biometric\": \"" + prop + "\", it does not exist or its loaded state is not controllable");
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
                    case "user":
                    		__modified().__userValue = null;break;
                    case "publicKey":
                    		__modified().__publicKeyValue = null;break;
                    case "deviceId":
                    		__modified().__deviceIdValue = null;break;
                    case "lastUsedAt":
                    		__modified().__lastUsedAtValue = null;
                    __modified().__lastUsedAtLoaded = false;break;
                    default: throw new IllegalArgumentException("Illegal property name for \"com.doruk.infrastructure.persistence.entity.Biometric\": \"" + prop + "\", it does not exist or its loaded state is not controllable");
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
                        __tmpModified = __modified;
                    }
                    else {
                        __tmpModified.__userValue = __ctx.resolveObject(__tmpModified.__userValue);
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
            type = Biometric.class
    )
    class Builder {
        private final Producer.DraftImpl __draft;

        public Builder() {
            this(null);
        }

        public Builder(@org.jspecify.annotations.Nullable Biometric base) {
            __draft = new Producer.DraftImpl(null, base);
        }

        public Builder id(@NonNull UUID id) {
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

        public Builder publicKey(byte @NonNull [] publicKey) {
            if (publicKey != null) {
                __draft.setPublicKey(publicKey);
            }
            return this;
        }

        public Builder deviceId(@NonNull String deviceId) {
            if (deviceId != null) {
                __draft.setDeviceId(deviceId);
            }
            return this;
        }

        public Builder lastUsedAt(@org.jspecify.annotations.Nullable OffsetDateTime lastUsedAt) {
            __draft.setLastUsedAt(lastUsedAt);
            return this;
        }

        public Biometric build() {
            return (Biometric)__draft.__modified();
        }
    }
}
