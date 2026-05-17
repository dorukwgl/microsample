package com.doruk.application.app.organization.service;

import com.doruk.application.dto.PageQuery;
import com.doruk.application.dto.PageResponse;
import com.doruk.application.enums.TemplateType;
import com.doruk.application.exception.*;
import com.doruk.application.policies.OrganizationPolicy;
import com.doruk.application.app.organization.dto.*;
import com.doruk.application.interfaces.MailService;
import com.doruk.domain.shared.enums.OrganizationType;
import com.doruk.infrastructure.config.AppConfig;
import com.doruk.infrastructure.persistence.organization.OrganizationRepository;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Singleton
@RequiredArgsConstructor
public class OrganizationService {
    private final OrganizationRepository orgRepo;
    private final MailService mailService;
    private final AppConfig config;

    // View/Edit Own Org Info
    public OrganizationInfoDto getMyOrganization(String userId) {
        var currentUser = orgRepo.getCurrentUser(userId);
        var orgId = currentUser.organization().id();

        return orgRepo.findById(orgId)
                .orElseThrow(() -> new IncompleteStateException("Current organization not found"));
    }

    public OrganizationInfoDto updateMyOrganization(String userId, OrganizationUpdateDto dto) {
        var currentUser = orgRepo.getCurrentUser(userId);
        var orgId = currentUser.organization().id();
        var currentOrg = orgRepo.findById(orgId)
                .orElseThrow(() -> new IncompleteStateException("Current organization not found"));

        // Business rule: Personal orgs can't be edited
        if (currentOrg.type() == OrganizationType.PERSONAL)
            throw new InvalidInputException("Personal organizations cannot be edited");

        // Authorization check
        if (!OrganizationPolicy.canUserEditOrganization(currentUser, currentOrg))
            throw new ForbiddenException("Please ask your admin to perform this action");

        return orgRepo.updateName(orgId, dto.name());
    }

    // Organization Search/Discovery
    public OrganizationInfoDto getOrganizationByCode(String orgCode) {
        return orgRepo.findByCode(orgCode)
                .orElseThrow(() -> new NotFoundException(orgCode));
    }

    // Search organization by code
    public PageResponse<OrganizationInfoDto> searchOrganizationByCode(String orgCode, PageQuery page) {
        return orgRepo.searchOrganizationByCode(orgCode, page);
    }

    public OrganizationInfoDto joinOrganization(String userId, String orgCode) {
        var currentUser = orgRepo.getCurrentUser(userId);

        // Authorization check
        if (!OrganizationPolicy.canUserJoinOrganization(currentUser))
            throw new ForbiddenException("Only users with personal accounts can join an organization");

        // check if has existing license
        if (orgRepo.hasActiveLicenses(currentUser.organization().id()))
            throw new ForbiddenException("You have active personal license. You'll lose access to this license once you join the organization.");

        return orgRepo.joinOrganization(UUID.fromString(userId), orgCode)
                .orElseThrow(() -> new NotFoundException("Organization not found"));
    }

    public void leaveOrganization(String userId) {
        // Verify code matches current organization
        var currentUser = orgRepo.getCurrentUser(userId);
        var currentOrg = currentUser.organization();

        // Authorization check
        if (!OrganizationPolicy.canUserLeaveOrganization(currentOrg))
            throw new ForbiddenException("Only users with enterprise accounts can leave an organization");

        // prevent if last admin
        if (orgRepo.isLastAdmin(currentOrg.id(), UUID.fromString(currentUser.id())))
            throw new InvalidInputException("Last admin cannot leave the organization");

        orgRepo.leaveOrganization(UUID.fromString(currentUser.id()), currentUser.organization().id());
    }

    // Member Management
    public PageResponse<MemberDto> listMembers(String userId, PageQuery page) {
        var currentUser = orgRepo.getCurrentUser(userId);
        var orgId = currentUser.organization().id();

        return orgRepo.listMembers(orgId, page);
    }

    public void updateMemberRole(String userId, UUID targetUserId, boolean makeAdmin) {
        var currentUser = orgRepo.getCurrentUser(userId);
        var orgId = currentUser.organization().id();

        // check if its personal account
        if (currentUser.organization().type() == OrganizationType.PERSONAL)
            throw new ForbiddenException();

        // Authorization check
        if (!OrganizationPolicy.canUserManageMembers(currentUser))
            throw new ForbiddenException("Only organization admins can manage members");

        // Prevent removing last admin
        if (!makeAdmin && orgRepo.isLastAdmin(orgId, targetUserId))
            throw new InvalidInputException("Cannot remove the last organization admin");

        orgRepo.updateMemberRole(orgId, targetUserId, makeAdmin);
    }

    public void removeMember(String userId, UUID targetUserId) {
        var currentUser = orgRepo.getCurrentUser(userId);
        var orgId = currentUser.organization().id();

        // check if its personal account
        if (currentUser.organization().type() == OrganizationType.PERSONAL)
            throw new ForbiddenException();

        // Authorization check
        if (!OrganizationPolicy.canUserManageMembers(currentUser))
            throw new ForbiddenException("Only organization admins can remove members");

        // Cannot remove self
        if (userId.equals(targetUserId.toString()))
            throw new InvalidInputException("Cannot remove yourself from the organization");

        orgRepo.removeMember(orgId, targetUserId);
    }

    // Invitation System
    public OrganizationInviteResponseDto sendInvite(String userId, OrganizationInviteDto dto) {
        var currentUser = orgRepo.getCurrentUser(userId);
        var orgId = currentUser.organization().id();
        var currentOrg = orgRepo.findById(orgId)
                .orElseThrow(() -> new NotFoundException("Current organization not found"));

        // Authorization check
        if (!OrganizationPolicy.canUserManageMembers(currentUser))
            throw new ForbiddenException("Only organization admins can send invitations");

        // Generate invite link
        String inviteLink = String.format("%s/organizations/search?orgCode=%s",
                config.appUrl(),
                currentOrg.orgCode());

        // Send email
        var mailParams = new MailService.MailParams(
                dto.email().split("@")[0],  // Simple name extraction from email
                dto.email(),
                inviteLink,
                0  // No OTP needed
        );

        mailService.sendMail(mailParams, TemplateType.ORGANIZATION_INVITE);

        return OrganizationInviteResponseDto.builder()
                .success(true)
                .message("Invitation sent successfully")
                .inviteLink(inviteLink)
                .build();
    }
}