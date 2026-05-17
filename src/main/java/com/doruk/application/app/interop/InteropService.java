package com.doruk.application.app.interop;

import com.doruk.application.app.interop.dto.LicenseInfoResponse;
import com.doruk.application.exception.ForbiddenException;
import com.doruk.application.exception.NotFoundException;
import com.doruk.domain.shared.enums.LicenseStatus;
import com.doruk.infrastructure.persistence.interop.InteropRepository;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Singleton
@RequiredArgsConstructor
public class InteropService {
    private final InteropRepository repo;

    public LicenseInfoResponse getLicenseInfo(UUID userId, UUID skuId) {
        return repo.getLicenseInfo(userId, skuId)
                .filter(l -> {
                    if (l.status() == LicenseStatus.REVOKED || l.status() == LicenseStatus.SUSPENDED)
                        throw new ForbiddenException("License is revoked or suspended. Consult with the Administrator.");
                    return true;
                })
                .orElseThrow(() -> new NotFoundException("License not activated for the given product for given user."));
    }
}
