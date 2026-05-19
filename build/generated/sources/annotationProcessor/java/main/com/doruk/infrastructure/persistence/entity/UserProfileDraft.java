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
import org.babyfish.jimmer.sql.OneToOne;
import org.jspecify.annotations.NonNull;

@GeneratedBy(
        type = UserProfile.class
)
public interface UserProfileDraft extends UserProfile, Draft {
    UserProfileDraft.Producer $ = Producer.INSTANCE;

    @OldChain
    UserProfileDraft setId(UUID id);

    UserDraft user();

    UserDraft user(boolean autoCreate);

    @OldChain
    UserProfileDraft setUser(User user);

    @NonNull
    @JsonIgnore
    UUID userId();

    @OldChain
    UserProfileDraft setUserId(@NonNull UUID userId);

    @OldChain
    UserProfileDraft applyUser(DraftConsumer<UserDraft> block);

    @OldChain
    UserProfileDraft applyUser(User base, DraftConsumer<UserDraft> block);

    @OldChain
    UserProfileDraft setFullName(String fullName);

    MediaStoreDraft profileIcon();

    MediaStoreDraft profileIcon(boolean autoCreate);

    @OldChain
    UserProfileDraft setProfileIcon(MediaStore profileIcon);

    @JsonIgnore
    long profileIconId();

    @OldChain
    UserProfileDraft setProfileIconId(long profileIconId);

    @OldChain
    UserProfileDraft applyProfileIcon(DraftConsumer<MediaStoreDraft> block);

    @OldChain
    UserProfileDraft applyProfileIcon(MediaStore base, DraftConsumer<MediaStoreDraft> block);

    @OldChain
    UserProfileDraft setAddress(String address);

    @OldChain
    UserProfileDraft setCity(String city);

    @OldChain
    UserProfileDraft setState(String state);

    @OldChain
    UserProfileDraft setCountry(String country);

    @OldChain
    UserProfileDraft setPostalCode(String postalCode);

    @OldChain
    UserProfileDraft setCreatedAt(OffsetDateTime createdAt);

    @OldChain
    UserProfileDraft setUpdatedAt(OffsetDateTime updatedAt);

    @GeneratedBy(
            type = UserProfile.class
    )
    class Producer {
        static final Producer INSTANCE = new Producer();

        public static final int SLOT_ID = 0;

        public static final int SLOT_USER = 1;

        public static final int SLOT_FULL_NAME = 2;

        public static final int SLOT_PROFILE_ICON = 3;

        public static final int SLOT_ADDRESS = 4;

        public static final int SLOT_CITY = 5;

        public static final int SLOT_STATE = 6;

        public static final int SLOT_COUNTRY = 7;

        public static final int SLOT_POSTAL_CODE = 8;

        public static final int SLOT_CREATED_AT = 9;

        public static final int SLOT_UPDATED_AT = 10;

        public static final ImmutableType TYPE = ImmutableType
            .newBuilder(
                "0.10.7",
                UserProfile.class,
                Collections.emptyList(),
                (ctx, base) -> new DraftImpl(ctx, (UserProfile)base)
            )
            .id(SLOT_ID, "id", UUID.class)
            .add(SLOT_USER, "user", OneToOne.class, User.class, false)
            .add(SLOT_FULL_NAME, "fullName", ImmutablePropCategory.SCALAR, String.class, true)
            .add(SLOT_PROFILE_ICON, "profileIcon", OneToOne.class, MediaStore.class, false)
            .add(SLOT_ADDRESS, "address", ImmutablePropCategory.SCALAR, String.class, true)
            .add(SLOT_CITY, "city", ImmutablePropCategory.SCALAR, String.class, true)
            .add(SLOT_STATE, "state", ImmutablePropCategory.SCALAR, String.class, true)
            .add(SLOT_COUNTRY, "country", ImmutablePropCategory.SCALAR, String.class, true)
            .add(SLOT_POSTAL_CODE, "postalCode", ImmutablePropCategory.SCALAR, String.class, true)
            .add(SLOT_CREATED_AT, "createdAt", ImmutablePropCategory.SCALAR, OffsetDateTime.class, false)
            .add(SLOT_UPDATED_AT, "updatedAt", ImmutablePropCategory.SCALAR, OffsetDateTime.class, false)
            .build();

        private Producer() {
        }

        public UserProfile produce(DraftConsumer<UserProfileDraft> block) {
            return (UserProfile)Internal.produce(TYPE, null, block);
        }

        public UserProfile produce(UserProfile base, DraftConsumer<UserProfileDraft> block) {
            return (UserProfile)Internal.produce(TYPE, base, block);
        }

        public UserProfile produce(boolean resolveImmediately,
                DraftConsumer<UserProfileDraft> block) {
            return (UserProfile)Internal.produce(TYPE, null, resolveImmediately, block);
        }

        public UserProfile produce(UserProfile base, boolean resolveImmediately,
                DraftConsumer<UserProfileDraft> block) {
            return (UserProfile)Internal.produce(TYPE, base, resolveImmediately, block);
        }

        /**
         * Class, not interface, for free-marker
         */
        @GeneratedBy(
                type = UserProfile.class
        )
        @JsonPropertyOrder({"dummyPropForJacksonError__", "id", "user", "fullName", "profileIcon", "address", "city", "state", "country", "postalCode", "createdAt", "updatedAt"})
        public abstract static class Implementor implements UserProfile, ImmutableSpi {
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
                    case SLOT_FULL_NAME:
                    		return fullName();
                    case SLOT_PROFILE_ICON:
                    		return profileIcon();
                    case SLOT_ADDRESS:
                    		return address();
                    case SLOT_CITY:
                    		return city();
                    case SLOT_STATE:
                    		return state();
                    case SLOT_COUNTRY:
                    		return country();
                    case SLOT_POSTAL_CODE:
                    		return postalCode();
                    case SLOT_CREATED_AT:
                    		return createdAt();
                    case SLOT_UPDATED_AT:
                    		return updatedAt();
                    default: throw new IllegalArgumentException("Illegal property name for \"com.doruk.infrastructure.persistence.entity.UserProfile\": \"" + prop + "\"");
                }
            }

            @Override
            public final Object __get(String prop) {
                switch (prop) {
                    case "id":
                    		return id();
                    case "user":
                    		return user();
                    case "fullName":
                    		return fullName();
                    case "profileIcon":
                    		return profileIcon();
                    case "address":
                    		return address();
                    case "city":
                    		return city();
                    case "state":
                    		return state();
                    case "country":
                    		return country();
                    case "postalCode":
                    		return postalCode();
                    case "createdAt":
                    		return createdAt();
                    case "updatedAt":
                    		return updatedAt();
                    default: throw new IllegalArgumentException("Illegal property name for \"com.doruk.infrastructure.persistence.entity.UserProfile\": \"" + prop + "\"");
                }
            }

            public final UUID getId() {
                return id();
            }

            public final User getUser() {
                return user();
            }

            @Nullable
            public final String getFullName() {
                return fullName();
            }

            public final MediaStore getProfileIcon() {
                return profileIcon();
            }

            @Nullable
            public final String getAddress() {
                return address();
            }

            @Nullable
            public final String getCity() {
                return city();
            }

            @Nullable
            public final String getState() {
                return state();
            }

            @Nullable
            public final String getCountry() {
                return country();
            }

            @Nullable
            public final String getPostalCode() {
                return postalCode();
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
                type = UserProfile.class
        )
        private static class Impl extends Implementor implements Cloneable, Serializable {
            private Visibility __visibility;

            UUID __idValue;

            User __userValue;

            String __fullNameValue;

            boolean __fullNameLoaded = false;

            MediaStore __profileIconValue;

            String __addressValue;

            boolean __addressLoaded = false;

            String __cityValue;

            boolean __cityLoaded = false;

            String __stateValue;

            boolean __stateLoaded = false;

            String __countryValue;

            boolean __countryLoaded = false;

            String __postalCodeValue;

            boolean __postalCodeLoaded = false;

            OffsetDateTime __createdAtValue;

            OffsetDateTime __updatedAtValue;

            @Override
            @JsonIgnore
            public UUID id() {
                if (__idValue == null) {
                    throw new UnloadedException(UserProfile.class, "id");
                }
                return __idValue;
            }

            @Override
            @JsonIgnore
            public User user() {
                if (__userValue == null) {
                    throw new UnloadedException(UserProfile.class, "user");
                }
                return __userValue;
            }

            @Override
            @JsonIgnore
            @org.jspecify.annotations.Nullable
            public String fullName() {
                if (!__fullNameLoaded) {
                    throw new UnloadedException(UserProfile.class, "fullName");
                }
                return __fullNameValue;
            }

            @Override
            @JsonIgnore
            public MediaStore profileIcon() {
                if (__profileIconValue == null) {
                    throw new UnloadedException(UserProfile.class, "profileIcon");
                }
                return __profileIconValue;
            }

            @Override
            @JsonIgnore
            @org.jspecify.annotations.Nullable
            public String address() {
                if (!__addressLoaded) {
                    throw new UnloadedException(UserProfile.class, "address");
                }
                return __addressValue;
            }

            @Override
            @JsonIgnore
            @org.jspecify.annotations.Nullable
            public String city() {
                if (!__cityLoaded) {
                    throw new UnloadedException(UserProfile.class, "city");
                }
                return __cityValue;
            }

            @Override
            @JsonIgnore
            @org.jspecify.annotations.Nullable
            public String state() {
                if (!__stateLoaded) {
                    throw new UnloadedException(UserProfile.class, "state");
                }
                return __stateValue;
            }

            @Override
            @JsonIgnore
            @org.jspecify.annotations.Nullable
            public String country() {
                if (!__countryLoaded) {
                    throw new UnloadedException(UserProfile.class, "country");
                }
                return __countryValue;
            }

            @Override
            @JsonIgnore
            @org.jspecify.annotations.Nullable
            public String postalCode() {
                if (!__postalCodeLoaded) {
                    throw new UnloadedException(UserProfile.class, "postalCode");
                }
                return __postalCodeValue;
            }

            @Override
            @JsonIgnore
            public OffsetDateTime createdAt() {
                if (__createdAtValue == null) {
                    throw new UnloadedException(UserProfile.class, "createdAt");
                }
                return __createdAtValue;
            }

            @Override
            @JsonIgnore
            public OffsetDateTime updatedAt() {
                if (__updatedAtValue == null) {
                    throw new UnloadedException(UserProfile.class, "updatedAt");
                }
                return __updatedAtValue;
            }

            @Override
            public Impl clone() {
                try {
                    Impl copy = (Impl) super.clone();
                    Visibility originalVisibility = this.__visibility;
                    if (originalVisibility != null) {
                        Visibility newVisibility = Visibility.of(11);
                        for (int propId = 0; propId < 11; propId++) {
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
                    case SLOT_FULL_NAME:
                    		return __fullNameLoaded;
                    case SLOT_PROFILE_ICON:
                    		return __profileIconValue != null;
                    case SLOT_ADDRESS:
                    		return __addressLoaded;
                    case SLOT_CITY:
                    		return __cityLoaded;
                    case SLOT_STATE:
                    		return __stateLoaded;
                    case SLOT_COUNTRY:
                    		return __countryLoaded;
                    case SLOT_POSTAL_CODE:
                    		return __postalCodeLoaded;
                    case SLOT_CREATED_AT:
                    		return __createdAtValue != null;
                    case SLOT_UPDATED_AT:
                    		return __updatedAtValue != null;
                    default: throw new IllegalArgumentException("Illegal property name for \"com.doruk.infrastructure.persistence.entity.UserProfile\": \"" + prop + "\"");
                }
            }

            @Override
            public boolean __isLoaded(String prop) {
                switch (prop) {
                    case "id":
                    		return __idValue != null;
                    case "user":
                    		return __userValue != null;
                    case "fullName":
                    		return __fullNameLoaded;
                    case "profileIcon":
                    		return __profileIconValue != null;
                    case "address":
                    		return __addressLoaded;
                    case "city":
                    		return __cityLoaded;
                    case "state":
                    		return __stateLoaded;
                    case "country":
                    		return __countryLoaded;
                    case "postalCode":
                    		return __postalCodeLoaded;
                    case "createdAt":
                    		return __createdAtValue != null;
                    case "updatedAt":
                    		return __updatedAtValue != null;
                    default: throw new IllegalArgumentException("Illegal property name for \"com.doruk.infrastructure.persistence.entity.UserProfile\": \"" + prop + "\"");
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
                    case SLOT_FULL_NAME:
                    		return __visibility.visible(SLOT_FULL_NAME);
                    case SLOT_PROFILE_ICON:
                    		return __visibility.visible(SLOT_PROFILE_ICON);
                    case SLOT_ADDRESS:
                    		return __visibility.visible(SLOT_ADDRESS);
                    case SLOT_CITY:
                    		return __visibility.visible(SLOT_CITY);
                    case SLOT_STATE:
                    		return __visibility.visible(SLOT_STATE);
                    case SLOT_COUNTRY:
                    		return __visibility.visible(SLOT_COUNTRY);
                    case SLOT_POSTAL_CODE:
                    		return __visibility.visible(SLOT_POSTAL_CODE);
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
                    case "user":
                    		return __visibility.visible(SLOT_USER);
                    case "fullName":
                    		return __visibility.visible(SLOT_FULL_NAME);
                    case "profileIcon":
                    		return __visibility.visible(SLOT_PROFILE_ICON);
                    case "address":
                    		return __visibility.visible(SLOT_ADDRESS);
                    case "city":
                    		return __visibility.visible(SLOT_CITY);
                    case "state":
                    		return __visibility.visible(SLOT_STATE);
                    case "country":
                    		return __visibility.visible(SLOT_COUNTRY);
                    case "postalCode":
                    		return __visibility.visible(SLOT_POSTAL_CODE);
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
                if (__userValue != null) {
                    hash = 31 * hash + __userValue.hashCode();
                }
                if (__fullNameLoaded && __fullNameValue != null) {
                    hash = 31 * hash + __fullNameValue.hashCode();
                }
                if (__profileIconValue != null) {
                    hash = 31 * hash + __profileIconValue.hashCode();
                }
                if (__addressLoaded && __addressValue != null) {
                    hash = 31 * hash + __addressValue.hashCode();
                }
                if (__cityLoaded && __cityValue != null) {
                    hash = 31 * hash + __cityValue.hashCode();
                }
                if (__stateLoaded && __stateValue != null) {
                    hash = 31 * hash + __stateValue.hashCode();
                }
                if (__countryLoaded && __countryValue != null) {
                    hash = 31 * hash + __countryValue.hashCode();
                }
                if (__postalCodeLoaded && __postalCodeValue != null) {
                    hash = 31 * hash + __postalCodeValue.hashCode();
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
                if (__userValue != null) {
                    hash = 31 * hash + System.identityHashCode(__userValue);
                }
                if (__fullNameLoaded) {
                    hash = 31 * hash + System.identityHashCode(__fullNameValue);
                }
                if (__profileIconValue != null) {
                    hash = 31 * hash + System.identityHashCode(__profileIconValue);
                }
                if (__addressLoaded) {
                    hash = 31 * hash + System.identityHashCode(__addressValue);
                }
                if (__cityLoaded) {
                    hash = 31 * hash + System.identityHashCode(__cityValue);
                }
                if (__stateLoaded) {
                    hash = 31 * hash + System.identityHashCode(__stateValue);
                }
                if (__countryLoaded) {
                    hash = 31 * hash + System.identityHashCode(__countryValue);
                }
                if (__postalCodeLoaded) {
                    hash = 31 * hash + System.identityHashCode(__postalCodeValue);
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
                if (__isVisible(PropId.byIndex(SLOT_FULL_NAME)) != __other.__isVisible(PropId.byIndex(SLOT_FULL_NAME))) {
                    return false;
                }
                boolean __fullNameLoaded = this.__fullNameLoaded;
                if (__fullNameLoaded != __other.__isLoaded(PropId.byIndex(SLOT_FULL_NAME))) {
                    return false;
                }
                if (__fullNameLoaded && !Objects.equals(__fullNameValue, __other.fullName())) {
                    return false;
                }
                if (__isVisible(PropId.byIndex(SLOT_PROFILE_ICON)) != __other.__isVisible(PropId.byIndex(SLOT_PROFILE_ICON))) {
                    return false;
                }
                boolean __profileIconLoaded = __profileIconValue != null;
                if (__profileIconLoaded != __other.__isLoaded(PropId.byIndex(SLOT_PROFILE_ICON))) {
                    return false;
                }
                if (__profileIconLoaded && !Objects.equals(__profileIconValue, __other.profileIcon())) {
                    return false;
                }
                if (__isVisible(PropId.byIndex(SLOT_ADDRESS)) != __other.__isVisible(PropId.byIndex(SLOT_ADDRESS))) {
                    return false;
                }
                boolean __addressLoaded = this.__addressLoaded;
                if (__addressLoaded != __other.__isLoaded(PropId.byIndex(SLOT_ADDRESS))) {
                    return false;
                }
                if (__addressLoaded && !Objects.equals(__addressValue, __other.address())) {
                    return false;
                }
                if (__isVisible(PropId.byIndex(SLOT_CITY)) != __other.__isVisible(PropId.byIndex(SLOT_CITY))) {
                    return false;
                }
                boolean __cityLoaded = this.__cityLoaded;
                if (__cityLoaded != __other.__isLoaded(PropId.byIndex(SLOT_CITY))) {
                    return false;
                }
                if (__cityLoaded && !Objects.equals(__cityValue, __other.city())) {
                    return false;
                }
                if (__isVisible(PropId.byIndex(SLOT_STATE)) != __other.__isVisible(PropId.byIndex(SLOT_STATE))) {
                    return false;
                }
                boolean __stateLoaded = this.__stateLoaded;
                if (__stateLoaded != __other.__isLoaded(PropId.byIndex(SLOT_STATE))) {
                    return false;
                }
                if (__stateLoaded && !Objects.equals(__stateValue, __other.state())) {
                    return false;
                }
                if (__isVisible(PropId.byIndex(SLOT_COUNTRY)) != __other.__isVisible(PropId.byIndex(SLOT_COUNTRY))) {
                    return false;
                }
                boolean __countryLoaded = this.__countryLoaded;
                if (__countryLoaded != __other.__isLoaded(PropId.byIndex(SLOT_COUNTRY))) {
                    return false;
                }
                if (__countryLoaded && !Objects.equals(__countryValue, __other.country())) {
                    return false;
                }
                if (__isVisible(PropId.byIndex(SLOT_POSTAL_CODE)) != __other.__isVisible(PropId.byIndex(SLOT_POSTAL_CODE))) {
                    return false;
                }
                boolean __postalCodeLoaded = this.__postalCodeLoaded;
                if (__postalCodeLoaded != __other.__isLoaded(PropId.byIndex(SLOT_POSTAL_CODE))) {
                    return false;
                }
                if (__postalCodeLoaded && !Objects.equals(__postalCodeValue, __other.postalCode())) {
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
                if (__isVisible(PropId.byIndex(SLOT_FULL_NAME)) != __other.__isVisible(PropId.byIndex(SLOT_FULL_NAME))) {
                    return false;
                }
                boolean __fullNameLoaded = this.__fullNameLoaded;
                if (__fullNameLoaded != __other.__isLoaded(PropId.byIndex(SLOT_FULL_NAME))) {
                    return false;
                }
                if (__fullNameLoaded && __fullNameValue != __other.fullName()) {
                    return false;
                }
                if (__isVisible(PropId.byIndex(SLOT_PROFILE_ICON)) != __other.__isVisible(PropId.byIndex(SLOT_PROFILE_ICON))) {
                    return false;
                }
                boolean __profileIconLoaded = __profileIconValue != null;
                if (__profileIconLoaded != __other.__isLoaded(PropId.byIndex(SLOT_PROFILE_ICON))) {
                    return false;
                }
                if (__profileIconLoaded && __profileIconValue != __other.profileIcon()) {
                    return false;
                }
                if (__isVisible(PropId.byIndex(SLOT_ADDRESS)) != __other.__isVisible(PropId.byIndex(SLOT_ADDRESS))) {
                    return false;
                }
                boolean __addressLoaded = this.__addressLoaded;
                if (__addressLoaded != __other.__isLoaded(PropId.byIndex(SLOT_ADDRESS))) {
                    return false;
                }
                if (__addressLoaded && __addressValue != __other.address()) {
                    return false;
                }
                if (__isVisible(PropId.byIndex(SLOT_CITY)) != __other.__isVisible(PropId.byIndex(SLOT_CITY))) {
                    return false;
                }
                boolean __cityLoaded = this.__cityLoaded;
                if (__cityLoaded != __other.__isLoaded(PropId.byIndex(SLOT_CITY))) {
                    return false;
                }
                if (__cityLoaded && __cityValue != __other.city()) {
                    return false;
                }
                if (__isVisible(PropId.byIndex(SLOT_STATE)) != __other.__isVisible(PropId.byIndex(SLOT_STATE))) {
                    return false;
                }
                boolean __stateLoaded = this.__stateLoaded;
                if (__stateLoaded != __other.__isLoaded(PropId.byIndex(SLOT_STATE))) {
                    return false;
                }
                if (__stateLoaded && __stateValue != __other.state()) {
                    return false;
                }
                if (__isVisible(PropId.byIndex(SLOT_COUNTRY)) != __other.__isVisible(PropId.byIndex(SLOT_COUNTRY))) {
                    return false;
                }
                boolean __countryLoaded = this.__countryLoaded;
                if (__countryLoaded != __other.__isLoaded(PropId.byIndex(SLOT_COUNTRY))) {
                    return false;
                }
                if (__countryLoaded && __countryValue != __other.country()) {
                    return false;
                }
                if (__isVisible(PropId.byIndex(SLOT_POSTAL_CODE)) != __other.__isVisible(PropId.byIndex(SLOT_POSTAL_CODE))) {
                    return false;
                }
                boolean __postalCodeLoaded = this.__postalCodeLoaded;
                if (__postalCodeLoaded != __other.__isLoaded(PropId.byIndex(SLOT_POSTAL_CODE))) {
                    return false;
                }
                if (__postalCodeLoaded && __postalCodeValue != __other.postalCode()) {
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
                type = UserProfile.class
        )
        private static class DraftImpl extends Implementor implements DraftSpi, UserProfileDraft {
            private DraftContext __ctx;

            private Impl __base;

            private Impl __modified;

            private boolean __resolving;

            private UserProfile __resolved;

            DraftImpl(DraftContext ctx, UserProfile base) {
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
            public UserProfileDraft setId(UUID id) {
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
            public UserProfileDraft setUser(User user) {
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
            public UserProfileDraft setUserId(@NonNull UUID userId) {
                user(true).setId(Objects.requireNonNull(userId, "\"user\" cannot be null"));
                return this;
            }

            @Override
            public UserProfileDraft applyUser(DraftConsumer<UserDraft> block) {
                applyUser(null, block);
                return this;
            }

            @Override
            public UserProfileDraft applyUser(User base, DraftConsumer<UserDraft> block) {
                setUser(UserDraft.$.produce(base, block));
                return this;
            }

            @Override
            @JsonIgnore
            @org.jspecify.annotations.Nullable
            public String fullName() {
                return (__modified!= null ? __modified : __base).fullName();
            }

            @Override
            public UserProfileDraft setFullName(String fullName) {
                if (__resolved != null) {
                    throw new IllegalStateException("The current draft has been resolved so it cannot be modified");
                }
                Impl __tmpModified = __modified();
                __tmpModified.__fullNameValue = fullName;
                __tmpModified.__fullNameLoaded = true;
                return this;
            }

            @Override
            @JsonIgnore
            public MediaStoreDraft profileIcon() {
                return __ctx.toDraftObject((__modified!= null ? __modified : __base).profileIcon());
            }

            @Override
            public MediaStoreDraft profileIcon(boolean autoCreate) {
                if (autoCreate && (!__isLoaded(PropId.byIndex(SLOT_PROFILE_ICON)))) {
                    setProfileIcon(MediaStoreDraft.$.produce(null, null));
                }
                return __ctx.toDraftObject((__modified!= null ? __modified : __base).profileIcon());
            }

            @Override
            public UserProfileDraft setProfileIcon(MediaStore profileIcon) {
                if (__resolved != null) {
                    throw new IllegalStateException("The current draft has been resolved so it cannot be modified");
                }
                if (profileIcon == null) {
                    throw new IllegalArgumentException(
                        "'profileIcon' cannot be null, please specify non-null value or use nullable annotation to decorate this property"
                    );
                }
                Impl __tmpModified = __modified();
                __tmpModified.__profileIconValue = profileIcon;
                return this;
            }

            @JsonIgnore
            @Override
            public long profileIconId() {
                return profileIcon().id();
            }

            @OldChain
            @Override
            public UserProfileDraft setProfileIconId(long profileIconId) {
                profileIcon(true).setId(Objects.requireNonNull(profileIconId, "\"profileIcon\" cannot be null"));
                return this;
            }

            @Override
            public UserProfileDraft applyProfileIcon(DraftConsumer<MediaStoreDraft> block) {
                applyProfileIcon(null, block);
                return this;
            }

            @Override
            public UserProfileDraft applyProfileIcon(MediaStore base,
                    DraftConsumer<MediaStoreDraft> block) {
                setProfileIcon(MediaStoreDraft.$.produce(base, block));
                return this;
            }

            @Override
            @JsonIgnore
            @org.jspecify.annotations.Nullable
            public String address() {
                return (__modified!= null ? __modified : __base).address();
            }

            @Override
            public UserProfileDraft setAddress(String address) {
                if (__resolved != null) {
                    throw new IllegalStateException("The current draft has been resolved so it cannot be modified");
                }
                Impl __tmpModified = __modified();
                __tmpModified.__addressValue = address;
                __tmpModified.__addressLoaded = true;
                return this;
            }

            @Override
            @JsonIgnore
            @org.jspecify.annotations.Nullable
            public String city() {
                return (__modified!= null ? __modified : __base).city();
            }

            @Override
            public UserProfileDraft setCity(String city) {
                if (__resolved != null) {
                    throw new IllegalStateException("The current draft has been resolved so it cannot be modified");
                }
                Impl __tmpModified = __modified();
                __tmpModified.__cityValue = city;
                __tmpModified.__cityLoaded = true;
                return this;
            }

            @Override
            @JsonIgnore
            @org.jspecify.annotations.Nullable
            public String state() {
                return (__modified!= null ? __modified : __base).state();
            }

            @Override
            public UserProfileDraft setState(String state) {
                if (__resolved != null) {
                    throw new IllegalStateException("The current draft has been resolved so it cannot be modified");
                }
                Impl __tmpModified = __modified();
                __tmpModified.__stateValue = state;
                __tmpModified.__stateLoaded = true;
                return this;
            }

            @Override
            @JsonIgnore
            @org.jspecify.annotations.Nullable
            public String country() {
                return (__modified!= null ? __modified : __base).country();
            }

            @Override
            public UserProfileDraft setCountry(String country) {
                if (__resolved != null) {
                    throw new IllegalStateException("The current draft has been resolved so it cannot be modified");
                }
                Impl __tmpModified = __modified();
                __tmpModified.__countryValue = country;
                __tmpModified.__countryLoaded = true;
                return this;
            }

            @Override
            @JsonIgnore
            @org.jspecify.annotations.Nullable
            public String postalCode() {
                return (__modified!= null ? __modified : __base).postalCode();
            }

            @Override
            public UserProfileDraft setPostalCode(String postalCode) {
                if (__resolved != null) {
                    throw new IllegalStateException("The current draft has been resolved so it cannot be modified");
                }
                Impl __tmpModified = __modified();
                __tmpModified.__postalCodeValue = postalCode;
                __tmpModified.__postalCodeLoaded = true;
                return this;
            }

            @Override
            @JsonIgnore
            public OffsetDateTime createdAt() {
                return (__modified!= null ? __modified : __base).createdAt();
            }

            @Override
            public UserProfileDraft setCreatedAt(OffsetDateTime createdAt) {
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
            public UserProfileDraft setUpdatedAt(OffsetDateTime updatedAt) {
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
                    case SLOT_USER:
                    		setUser((User)value);break;
                    case SLOT_FULL_NAME:
                    		setFullName((String)value);break;
                    case SLOT_PROFILE_ICON:
                    		setProfileIcon((MediaStore)value);break;
                    case SLOT_ADDRESS:
                    		setAddress((String)value);break;
                    case SLOT_CITY:
                    		setCity((String)value);break;
                    case SLOT_STATE:
                    		setState((String)value);break;
                    case SLOT_COUNTRY:
                    		setCountry((String)value);break;
                    case SLOT_POSTAL_CODE:
                    		setPostalCode((String)value);break;
                    case SLOT_CREATED_AT:
                    		setCreatedAt((OffsetDateTime)value);break;
                    case SLOT_UPDATED_AT:
                    		setUpdatedAt((OffsetDateTime)value);break;
                    default: throw new IllegalArgumentException("Illegal property id for \"com.doruk.infrastructure.persistence.entity.UserProfile\": \"" + prop + "\"");
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
                    case "fullName":
                    		setFullName((String)value);break;
                    case "profileIcon":
                    		setProfileIcon((MediaStore)value);break;
                    case "address":
                    		setAddress((String)value);break;
                    case "city":
                    		setCity((String)value);break;
                    case "state":
                    		setState((String)value);break;
                    case "country":
                    		setCountry((String)value);break;
                    case "postalCode":
                    		setPostalCode((String)value);break;
                    case "createdAt":
                    		setCreatedAt((OffsetDateTime)value);break;
                    case "updatedAt":
                    		setUpdatedAt((OffsetDateTime)value);break;
                    default: throw new IllegalArgumentException("Illegal property name for \"com.doruk.infrastructure.persistence.entity.UserProfile\": \"" + prop + "\"");
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
                    __modified().__visibility = __visibility = Visibility.of(11);
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
                    case SLOT_FULL_NAME:
                    		__visibility.show(SLOT_FULL_NAME, visible);break;
                    case SLOT_PROFILE_ICON:
                    		__visibility.show(SLOT_PROFILE_ICON, visible);break;
                    case SLOT_ADDRESS:
                    		__visibility.show(SLOT_ADDRESS, visible);break;
                    case SLOT_CITY:
                    		__visibility.show(SLOT_CITY, visible);break;
                    case SLOT_STATE:
                    		__visibility.show(SLOT_STATE, visible);break;
                    case SLOT_COUNTRY:
                    		__visibility.show(SLOT_COUNTRY, visible);break;
                    case SLOT_POSTAL_CODE:
                    		__visibility.show(SLOT_POSTAL_CODE, visible);break;
                    case SLOT_CREATED_AT:
                    		__visibility.show(SLOT_CREATED_AT, visible);break;
                    case SLOT_UPDATED_AT:
                    		__visibility.show(SLOT_UPDATED_AT, visible);break;
                    default: throw new IllegalArgumentException(
                                "Illegal property id for \"com.doruk.infrastructure.persistence.entity.UserProfile\": \"" + 
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
                    __modified().__visibility = __visibility = Visibility.of(11);
                }
                switch (prop) {
                    case "id":
                    		__visibility.show(SLOT_ID, visible);break;
                    case "user":
                    		__visibility.show(SLOT_USER, visible);break;
                    case "fullName":
                    		__visibility.show(SLOT_FULL_NAME, visible);break;
                    case "profileIcon":
                    		__visibility.show(SLOT_PROFILE_ICON, visible);break;
                    case "address":
                    		__visibility.show(SLOT_ADDRESS, visible);break;
                    case "city":
                    		__visibility.show(SLOT_CITY, visible);break;
                    case "state":
                    		__visibility.show(SLOT_STATE, visible);break;
                    case "country":
                    		__visibility.show(SLOT_COUNTRY, visible);break;
                    case "postalCode":
                    		__visibility.show(SLOT_POSTAL_CODE, visible);break;
                    case "createdAt":
                    		__visibility.show(SLOT_CREATED_AT, visible);break;
                    case "updatedAt":
                    		__visibility.show(SLOT_UPDATED_AT, visible);break;
                    default: throw new IllegalArgumentException(
                                "Illegal property name for \"com.doruk.infrastructure.persistence.entity.UserProfile\": \"" + 
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
                    case SLOT_FULL_NAME:
                    		__modified().__fullNameValue = null;
                    __modified().__fullNameLoaded = false;break;
                    case SLOT_PROFILE_ICON:
                    		__modified().__profileIconValue = null;break;
                    case SLOT_ADDRESS:
                    		__modified().__addressValue = null;
                    __modified().__addressLoaded = false;break;
                    case SLOT_CITY:
                    		__modified().__cityValue = null;
                    __modified().__cityLoaded = false;break;
                    case SLOT_STATE:
                    		__modified().__stateValue = null;
                    __modified().__stateLoaded = false;break;
                    case SLOT_COUNTRY:
                    		__modified().__countryValue = null;
                    __modified().__countryLoaded = false;break;
                    case SLOT_POSTAL_CODE:
                    		__modified().__postalCodeValue = null;
                    __modified().__postalCodeLoaded = false;break;
                    case SLOT_CREATED_AT:
                    		__modified().__createdAtValue = null;break;
                    case SLOT_UPDATED_AT:
                    		__modified().__updatedAtValue = null;break;
                    default: throw new IllegalArgumentException("Illegal property id for \"com.doruk.infrastructure.persistence.entity.UserProfile\": \"" + prop + "\", it does not exist or its loaded state is not controllable");
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
                    case "fullName":
                    		__modified().__fullNameValue = null;
                    __modified().__fullNameLoaded = false;break;
                    case "profileIcon":
                    		__modified().__profileIconValue = null;break;
                    case "address":
                    		__modified().__addressValue = null;
                    __modified().__addressLoaded = false;break;
                    case "city":
                    		__modified().__cityValue = null;
                    __modified().__cityLoaded = false;break;
                    case "state":
                    		__modified().__stateValue = null;
                    __modified().__stateLoaded = false;break;
                    case "country":
                    		__modified().__countryValue = null;
                    __modified().__countryLoaded = false;break;
                    case "postalCode":
                    		__modified().__postalCodeValue = null;
                    __modified().__postalCodeLoaded = false;break;
                    case "createdAt":
                    		__modified().__createdAtValue = null;break;
                    case "updatedAt":
                    		__modified().__updatedAtValue = null;break;
                    default: throw new IllegalArgumentException("Illegal property name for \"com.doruk.infrastructure.persistence.entity.UserProfile\": \"" + prop + "\", it does not exist or its loaded state is not controllable");
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
                        if (base.__isLoaded(PropId.byIndex(SLOT_PROFILE_ICON))) {
                            MediaStore oldValue = base.profileIcon();
                            MediaStore newValue = __ctx.resolveObject(oldValue);
                            if (oldValue != newValue) {
                                setProfileIcon(newValue);
                            }
                        }
                        __tmpModified = __modified;
                    }
                    else {
                        __tmpModified.__userValue = __ctx.resolveObject(__tmpModified.__userValue);
                        __tmpModified.__profileIconValue = __ctx.resolveObject(__tmpModified.__profileIconValue);
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
            type = UserProfile.class
    )
    class Builder {
        private final Producer.DraftImpl __draft;

        public Builder() {
            this(null);
        }

        public Builder(@org.jspecify.annotations.Nullable UserProfile base) {
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

        public Builder fullName(@org.jspecify.annotations.Nullable String fullName) {
            __draft.setFullName(fullName);
            return this;
        }

        public Builder profileIcon(@NonNull MediaStore profileIcon) {
            if (profileIcon != null) {
                __draft.setProfileIcon(profileIcon);
            }
            return this;
        }

        public Builder address(@org.jspecify.annotations.Nullable String address) {
            __draft.setAddress(address);
            return this;
        }

        public Builder city(@org.jspecify.annotations.Nullable String city) {
            __draft.setCity(city);
            return this;
        }

        public Builder state(@org.jspecify.annotations.Nullable String state) {
            __draft.setState(state);
            return this;
        }

        public Builder country(@org.jspecify.annotations.Nullable String country) {
            __draft.setCountry(country);
            return this;
        }

        public Builder postalCode(@org.jspecify.annotations.Nullable String postalCode) {
            __draft.setPostalCode(postalCode);
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

        public UserProfile build() {
            return (UserProfile)__draft.__modified();
        }
    }
}
