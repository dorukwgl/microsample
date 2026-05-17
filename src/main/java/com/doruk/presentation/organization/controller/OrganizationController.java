package com.doruk.presentation.organization.controller;

import com.doruk.application.app.organization.dto.*;
import com.doruk.application.app.organization.service.OrganizationService;
import com.doruk.application.dto.PageResponse;
import com.doruk.presentation.dto.PageQueryMapper;
import com.doruk.presentation.dto.PageQueryRequest;
import com.doruk.presentation.organization.dto.MemberRoleUpdateRequest;
import com.doruk.presentation.organization.dto.OrganizationInviteRequest;
import com.doruk.presentation.organization.dto.OrganizationUpdateRequest;
import io.micronaut.http.annotation.*;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.authentication.Authentication;
import io.micronaut.security.rules.SecurityRule;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Tag(name = "Organization Management", description = "Organization CRUD and member management")
@RequiredArgsConstructor
@Secured(SecurityRule.IS_AUTHENTICATED)
@Controller("/organizations")
public class OrganizationController {
    private final OrganizationService service;

    /**
     *      * DELETE /:userId/seats/revoke-all          remove user from all seats across all licenses
     *      * DELETE /:licenseId/seats/release-all      remove all users seat allocation from given license
     */

    // View/Edit Own Org Info
    @Operation(
            summary = "Get current user's organization",
            description = "Returns organization information for authenticated user's organization. Any organization member can view this information."
    )
    @Get("/me")
    public OrganizationInfoDto getMyOrganization(Authentication auth) {
        return service.getMyOrganization(auth.getName());
    }

    @Operation(
            summary = "Update organization name",
            description = "Updates organization name. Only organization admins can perform this operation. Personal organizations cannot be edited."
    )
    @Put()
    public OrganizationInfoDto updateMyOrganization(
            Authentication auth,
            @Valid @Body OrganizationUpdateRequest request
    ) {
        return service.updateMyOrganization(auth.getName(),
                new OrganizationUpdateDto(request.name()));
    }

    @Operation(
            summary = "Search organizations",
            description = "Alternative public search endpoint. Finds organizations by code. Returns basic organization information."
    )
    @Get("/search?")
    public PageResponse<OrganizationInfoDto> searchOrganization(@QueryValue String orgCode, @Valid @QueryValue PageQueryRequest page) {
        return service.searchOrganizationByCode(orgCode, PageQueryMapper.toQuery(page));
    }

    // Organization Search/Discovery
    @Operation(
            summary = "Search organization by code",
            description = "Public endpoint to find an organization by its unique code. Returns basic organization information. Used for organization discovery and invitation links."
    )
    @Get("/find/{code}")
    public OrganizationInfoDto getOrganizationByCode(@PathVariable String code) {
        return service.getOrganizationByCode(code);
    }

    // Join/Leave Organizations
    @Operation(
            summary = "Join an organization",
            description = """
                    Allows a personal user to join an enterprise organization by its code.
                    Requirements:
                    - User must have a personal organization (will be deleted)
                    - User must not have active licenses on personal organization
                    - User must not already be in an enterprise organization
                    - Organization code must be valid
                    Result: User joins as regular member, personal organization is deleted
                    """
    )
    @Post("/{code}/join")
    public OrganizationInfoDto joinOrganization(Authentication auth, @PathVariable String code) {
        return service.joinOrganization(auth.getName(), code);
    }

    @Operation(
            summary = "Leave current organization",
            description = """
                    Allows an enterprise user to leave their current organization.
                    Requirements:
                    - User must be in an enterprise organization
                    - Allocated seats will be released
                    Result: User gets a new personal organization
                    """
    )
    @Delete("/leave")
    public void leaveOrganization(Authentication auth) {
        service.leaveOrganization(auth.getName());
    }

    // Member Management
    @Operation(
            summary = "List organization members",
            description = "Returns a list of all members in the user's organization. Any organization member can view this information."
    )
    @Get("/members?")
    public PageResponse<MemberDto> listMembers(Authentication auth, @Valid @QueryValue PageQueryRequest page) {
        return service.listMembers(auth.getName(), PageQueryMapper.toQuery(page));
    }

    @Operation(
            summary = "Update member role",
            description = """
                    Promotes or demotes a member to/from admin role.
                    Requirements:
                    - Only organization admins can update member roles
                    - Cannot remove the last admin
                    """
    )
    @Patch("/members/{userId}")
    public void updateMemberRole(
            Authentication auth,
            @PathVariable UUID userId,
            @Valid @Body MemberRoleUpdateRequest request
    ) {
        service.updateMemberRole(auth.getName(), userId, request.isAdmin());
    }

    @Operation(
            summary = "Remove member from organization",
            description = """
                    Removes a member from the organization.
                    Requirements:
                    - Only organization admins can remove members
                    - Cannot remove yourself
                    - Removed user will get a new personal organization
                    """
    )
    @Delete("/members/{userId}")
    public void removeMember(Authentication auth, @PathVariable UUID userId) {
        service.removeMember(auth.getName(), userId);
    }

    // Invitation System
    @Operation(
            summary = "Send organization invitation",
            description = """
                    Sends an email invitation to join the organization.
                    Requirements:
                    - Only organization admins can send invitations
                    - Invite contains a link to the public organization search
                    - Recipient must register and manually join using the code
                    """
    )
    @Post("/invite")
    public OrganizationInviteResponseDto sendInvite(
            Authentication auth,
            @Valid @Body OrganizationInviteRequest request
    ) {
        var dto = new OrganizationInviteDto(request.email(), auth.getName());
        return service.sendInvite(auth.getName(), dto);
    }
}