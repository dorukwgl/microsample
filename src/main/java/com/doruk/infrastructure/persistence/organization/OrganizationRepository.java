package com.doruk.infrastructure.persistence.organization;

import com.doruk.application.app.organization.dto.MemberDto;
import com.doruk.application.app.organization.dto.OrganizationInfoDto;
import com.doruk.application.app.users.dto.CurrentUserDto;
import com.doruk.application.app.users.dto.ProfileDto;
import com.doruk.application.dto.OrganizationDto;
import com.doruk.application.dto.PageQuery;
import com.doruk.application.dto.PageResponse;
import com.doruk.application.enums.SortOrder;
import com.doruk.domain.shared.enums.LicenseStatus;
import com.doruk.domain.shared.enums.OrganizationType;
import com.doruk.infrastructure.persistence.entity.*;
import com.doruk.infrastructure.persistence.mapper.PageMapper;
import com.doruk.infrastructure.persistence.organization.mapper.OrganizationMapper;
import com.doruk.infrastructure.persistence.users.mapper.UserMapper;
import com.doruk.jooq.tables.LicenseSeats;
import com.doruk.jooq.tables.Licenses;
import com.doruk.jooq.tables.Organizations;
import com.doruk.jooq.tables.Users;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import org.babyfish.jimmer.sql.JSqlClient;
import org.babyfish.jimmer.sql.ast.LikeMode;
import org.babyfish.jimmer.sql.ast.Predicate;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Singleton
@RequiredArgsConstructor
public class OrganizationRepository {

    //TODO free license seat if (left org or removed from org)

    private final JSqlClient sqlClient;
    private final DSLContext dsl;
    private final UserMapper userMapper;

    public Optional<OrganizationInfoDto> findByCode(String orgCode) {
        var o = Organizations.ORGANIZATIONS;
        var u = Users.USERS;

        return dsl.select(
                        o.ID,
                        o.NAME,
                        o.ORG_CODE,
                        o.TYPE,
                        o.CREATED_AT,
                        DSL.count(u.ID).as("memberCount")
                )
                .from(o)
                .where(o.ORG_CODE.eq(orgCode))
                .fetchOptional(OrganizationMapper::toInfoDto);
    }

    public Optional<OrganizationInfoDto> findById(UUID orgId) {
        var o = Organizations.ORGANIZATIONS;
        return dsl
                .select(
                        o.ID,
                        o.NAME,
                        o.ORG_CODE,
                        o.TYPE,
                        o.CREATED_AT,
                        DSL.count(Users.USERS.ID).as("memberCount")
                )
                .from(o)
                .where(o.ID.eq(orgId))
                .fetchOptional(OrganizationMapper::toInfoDto);
    }

    public PageResponse<OrganizationInfoDto> searchOrganizationByCode(String query, PageQuery page) {
        var o = OrganizationsTable.$;
        var res = sqlClient.createQuery(o)
                .where(o.orgCode().ilikeIf(query, LikeMode.ANYWHERE))
                .orderBy(page.order() == SortOrder.ASC ? o.id().asc() : o.id().desc())
                .select(o.fetch(OrganizationsFetcher.$.allScalarFields()))
                .fetchPage(page.page(), page.size());

        return PageMapper.toResponse(res, r -> OrganizationInfoDto.builder()
                .id(r.id())
                .name(r.name())
                .orgCode(r.orgCode())
                .type(r.type())
                .createdAt(r.createdAt())
                .memberCount(0)
                .build());
    }

    public OrganizationInfoDto updateName(UUID orgId, String name) {
        var o = Organizations.ORGANIZATIONS;
        return dsl.update(o)
                .set(o.NAME, name)
                .where(o.ID.eq(orgId))
                .returning()
                .fetchOne(OrganizationMapper::toInfoDto);
    }

    public PageResponse<MemberDto> listMembers(UUID orgId, PageQuery page) {
        var t = UserTable.$;
        var res = sqlClient.createQuery(t)
                .where(t.organizationId().eq(orgId))
                .orderBy(page.order() == SortOrder.ASC ? t.id().asc() : t.id().desc())
                .select(t.fetch(
                        UserFetcher.$
                                .username()
                                .email()
                                .orgAdmin()
                                .status()
                                .createdAt()
                ))
                .fetchPage(page.page(), page.size());

        return PageMapper.toResponse(res, r -> MemberDto.builder()
                .userId(r.id())
                .username(r.username())
                .email(r.email())
                .isOrgAdmin(r.isOrgAdmin())
                .status(r.status())
                .build());
    }

    public void updateMemberRole(UUID orgId, UUID userId, boolean isAdmin) {
        var u = Users.USERS;
        dsl.update(u)
                .set(u.IS_ORG_ADMIN, isAdmin)
                .where(u.ID.eq(userId))
                .and(u.ORGANIZATION_ID.eq(orgId))
                .execute();
    }

    public void removeMember(UUID orgId, UUID userId) {
        var u = Users.USERS;

        dsl.transactionResult(() -> {
            // Get the user's current organization
            var user = dsl.selectFrom(u)
                    .where(u.ID.eq(userId).and(u.ORGANIZATION_ID.eq(orgId)))
                    .fetchOptional();
            // if user not in given org, return
            if (user.isEmpty())
                return null;

            // Create new personal organization for the removed user
            UUID newPersonalOrgId = createPersonalOrganization();

            // Update user to the new personal organization
            dsl.update(u)
                    .set(u.ORGANIZATION_ID, newPersonalOrgId)
                    .set(u.IS_ORG_ADMIN, false)
                    .where(u.ID.eq(userId))
                    .execute();

            return null;
        });
    }

    public CurrentUserDto getCurrentUser(String userId) {
        // Hybrid approach: Jimmer for main query + JOOQ for optimized roles query

        // Use JOOQ for roles - only queries join table (no redundant join to roles table)
        var r = com.doruk.jooq.tables.UserRoles.USER_ROLES;
        List<String> roles = dsl.select(r.NAME)
                .from(r)
                .where(r.USER_ID.eq(UUID.fromString(userId)))
                .fetch(r.NAME);

        // Use Jimmer for main query (user + organization + profile)
        var t = UserTable.$;
        User user = sqlClient.createQuery(t)
                .where(t.id().eq(UUID.fromString(userId)))
                .select(
                        t.fetch(
                                UserFetcher.$
                                        .username()
                                        .email()
                                        .phone()
                                        .emailVerified()
                                        .phoneVerified()
                                        .multiFactorAuth()
                                        .status()
                                        .orgAdmin(true)
                                        .createdAt()
                                        .updatedAt()
                                        .organization(
                                                OrganizationsFetcher.$
                                                        .name()
                                                        .orgCode()
                                                        .type()
                                        )
                                        .profile(
                                                UserProfileFetcher.$
                                                        .fullName()
                                                        .address()
                                                        .city()
                                                        .state()
                                                        .country()
                                                        .postalCode()
                                                        .createdAt()
                                                        .updatedAt()
                                        )
                        )
                )
                .execute()
                .stream()
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("User doesn't exist in the database"));

        // Map to CurrentUserDto
        var organization = user.organization();
        var profile = user.profile();

        return CurrentUserDto.builder()
                .id(user.id().toString())
                .username(user.username())
                .email(user.email())
                .phone(user.phone())
                .emailVerified(user.emailVerified())
                .phoneVerified(user.phoneVerified())
                .multiFactorAuth(user.multiFactorAuth())
                .organization(organization != null ?
                        OrganizationDto.builder()
                        .id(organization.id())
                        .name(organization.name())
                        .orgCode(organization.orgCode())
                        .type(organization.type())
                        .build() : null)
                .isOrgAdmin(user.isOrgAdmin())
                .status(user.status())
                .createdAt(user.createdAt())
                .updatedAt(user.updatedAt())
                .profile(profile != null ?
                        ProfileDto.builder()
                        .userId(profile.id().toString())
                        .fullName(profile.fullName())
                        .address(profile.address())
                        .city(profile.city())
                        .state(profile.state())
                        .country(profile.country())
                        .postalCode(profile.postalCode())
                        .createdAt(profile.createdAt())
                        .updatedAt(profile.updatedAt())
                        .build() : null)
                .roles(roles)
                .build();
    }

    public Optional<OrganizationInfoDto> joinOrganization(UUID userId, String orgCode) {
        var u = Users.USERS;
        var o = com.doruk.jooq.tables.Organizations.ORGANIZATIONS;

        return dsl.transactionResult(() -> {
            // Get target organization
            var targetOrg = dsl.selectFrom(o)
                    .where(o.ORG_CODE.eq(orgCode).and(o.TYPE.eq(OrganizationType.ENTERPRISE)))
                    .fetchOptional();

            if (targetOrg.isEmpty())
                return Optional.empty();

            var orgRecord = targetOrg.get();

            // Get current user org
            var currentUser = dsl.selectFrom(u)
                    .where(u.ID.eq(userId))
                    .fetchOptional()
                    .orElseThrow(() -> new IllegalStateException("User doesn't exist in the database"));

            UUID oldOrgId = currentUser.getOrganizationId();

            // Update user organization
            dsl.update(u)
                    .set(u.ORGANIZATION_ID, orgRecord.getId())
                    .set(u.IS_ORG_ADMIN, false)  // Join as regular member
                    .where(u.ID.eq(userId))
                    .execute();

            // Delete personal org
            dsl.deleteFrom(o)
                    .where(o.ID.eq(oldOrgId))
                    .execute();

            return Optional.of(OrganizationMapper.toInfoDto(orgRecord));
        });
    }

    public void leaveOrganization(UUID userId, UUID orgId) {
        var u = Users.USERS;

        dsl.transaction(txn -> {
            var ctx = DSL.using(txn);
            // Release allocated seats
            releaseAllocatedSeat(userId, orgId);

            // Create new personal organization
            UUID newPersonalOrgId = createPersonalOrganization();

            // Update user
            ctx.update(u)
                    .set(u.ORGANIZATION_ID, newPersonalOrgId)
                    .set(u.IS_ORG_ADMIN, false)
                    .where(u.ID.eq(userId))
                    .execute();
        });
    }

    private UUID createPersonalOrganization() {
        var o = com.doruk.jooq.tables.Organizations.ORGANIZATIONS;
        return dsl.insertInto(o)
                .set(o.NAME, (String) null)
                .set(o.TYPE, OrganizationType.PERSONAL)
                .set(o.ORG_CODE, (String) null)
                .returning(o.ID)
                .fetchOptional()
                .orElseThrow(() -> new IllegalStateException("Failed to create user organization."))
                .getId();
    }

    public boolean isLastAdmin(UUID orgId, UUID userId) {
        var t = UserTable.$;

        return !sqlClient.createQuery(t)
                .where(Predicate.and(
                        t.organizationId().eq(orgId),
                        t.orgAdmin().eq(true),
                        t.id().ne(userId)
                ))
                .exists();
    }

    // Check if individual org have an active license
    public boolean hasActiveLicenses(UUID orgId) {
        var t = LicensesTable.$;
        return sqlClient.createQuery(t)
                .where(Predicate.and(t.organizationId().eq(orgId),
                        t.status().eq(LicenseStatus.ACTIVE),
                        t.validUntil().gt(OffsetDateTime.now())
                ))
                .exists();
    }

    public void releaseAllocatedSeat(UUID userId, UUID orgId) {
        var ls = LicenseSeats.LICENSE_SEATS;
        var l = Licenses.LICENSES;

        dsl.transaction(txn -> {
            var ctx = DSL.using(txn);

            // Find all active licenses for this organization and lock them
            var activeLicenseIds = ctx.select(l.ID)
                    .from(l)
                    .where(l.ORGANIZATION_ID.eq(orgId))
                    .and(l.STATUS.eq(LicenseStatus.ACTIVE))
                    .forUpdate()
                    .fetch(l.ID);

            for (UUID licenseId : activeLicenseIds) {
                // Delete license seat for this user and license
                int deleted = ctx.deleteFrom(ls)
                        .where(ls.LICENSE_ID.eq(licenseId))
                        .and(ls.USER_ID.eq(userId))
                        .execute();

                // If a seat was deleted, decrement the assigned seats count
                if (deleted > 0) {
                    ctx.update(l)
                            .set(l.ASSIGNED_SEATS, l.ASSIGNED_SEATS.minus(1))
                            .set(l.UPDATED_AT, OffsetDateTime.now())
                            .where(l.ID.eq(licenseId))
                            .and(l.ASSIGNED_SEATS.gt(0))
                            .execute();
                }
            }
        });
    }
}