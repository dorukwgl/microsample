package com.doruk.infrastructure.persistence.entity;

import org.babyfish.jimmer.DraftConsumer;
import org.babyfish.jimmer.internal.GeneratedBy;

@GeneratedBy
public interface Immutables {
    static Biometric createBiometric(DraftConsumer<BiometricDraft> block) {
        return BiometricDraft.$.produce(block);
    }

    static Biometric createBiometric(Biometric base, DraftConsumer<BiometricDraft> block) {
        return BiometricDraft.$.produce(base, block);
    }

    static LicenseAddons createLicenseAddons(DraftConsumer<LicenseAddonsDraft> block) {
        return LicenseAddonsDraft.$.produce(block);
    }

    static LicenseAddons createLicenseAddons(LicenseAddons base,
            DraftConsumer<LicenseAddonsDraft> block) {
        return LicenseAddonsDraft.$.produce(base, block);
    }

    static LicenseSeats createLicenseSeats(DraftConsumer<LicenseSeatsDraft> block) {
        return LicenseSeatsDraft.$.produce(block);
    }

    static LicenseSeats createLicenseSeats(LicenseSeats base,
            DraftConsumer<LicenseSeatsDraft> block) {
        return LicenseSeatsDraft.$.produce(base, block);
    }

    static Licenses createLicenses(DraftConsumer<LicensesDraft> block) {
        return LicensesDraft.$.produce(block);
    }

    static Licenses createLicenses(Licenses base, DraftConsumer<LicensesDraft> block) {
        return LicensesDraft.$.produce(base, block);
    }

    static MediaStore createMediaStore(DraftConsumer<MediaStoreDraft> block) {
        return MediaStoreDraft.$.produce(block);
    }

    static MediaStore createMediaStore(MediaStore base, DraftConsumer<MediaStoreDraft> block) {
        return MediaStoreDraft.$.produce(base, block);
    }

    static Organizations createOrganizations(DraftConsumer<OrganizationsDraft> block) {
        return OrganizationsDraft.$.produce(block);
    }

    static Organizations createOrganizations(Organizations base,
            DraftConsumer<OrganizationsDraft> block) {
        return OrganizationsDraft.$.produce(base, block);
    }

    static Permission createPermission(DraftConsumer<PermissionDraft> block) {
        return PermissionDraft.$.produce(block);
    }

    static Permission createPermission(Permission base, DraftConsumer<PermissionDraft> block) {
        return PermissionDraft.$.produce(base, block);
    }

    static ProductServers createProductServers(DraftConsumer<ProductServersDraft> block) {
        return ProductServersDraft.$.produce(block);
    }

    static ProductServers createProductServers(ProductServers base,
            DraftConsumer<ProductServersDraft> block) {
        return ProductServersDraft.$.produce(base, block);
    }

    static Role createRole(DraftConsumer<RoleDraft> block) {
        return RoleDraft.$.produce(block);
    }

    static Role createRole(Role base, DraftConsumer<RoleDraft> block) {
        return RoleDraft.$.produce(base, block);
    }

    static Session createSession(DraftConsumer<SessionDraft> block) {
        return SessionDraft.$.produce(block);
    }

    static Session createSession(Session base, DraftConsumer<SessionDraft> block) {
        return SessionDraft.$.produce(base, block);
    }

    static User createUser(DraftConsumer<UserDraft> block) {
        return UserDraft.$.produce(block);
    }

    static User createUser(User base, DraftConsumer<UserDraft> block) {
        return UserDraft.$.produce(base, block);
    }

    static UserProfile createUserProfile(DraftConsumer<UserProfileDraft> block) {
        return UserProfileDraft.$.produce(block);
    }

    static UserProfile createUserProfile(UserProfile base, DraftConsumer<UserProfileDraft> block) {
        return UserProfileDraft.$.produce(base, block);
    }
}
