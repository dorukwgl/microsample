package com.doruk.domain.license.entity;

import com.doruk.domain.exception.DomainException;
import com.doruk.domain.exception.LicenseInactiveException;
import com.doruk.domain.exception.NoSeatsAvailableException;
import com.doruk.domain.shared.enums.LicenseStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
@Builder
@Getter
public class License {
    private final UUID id;
    private LicenseStatus status;
    private final int totalSeats;
    @Getter
    private int assignedSeats;

    public void allocateSeat() {
        if (status != LicenseStatus.ACTIVE)
            throw new LicenseInactiveException("Cannot allocate seat on inactive license");
        if (assignedSeats >= totalSeats)
            throw new NoSeatsAvailableException("No seats available");
        assignedSeats++;
    }

    public void releaseSeat() {
        if (assignedSeats <= 0)
            throw new DomainException("No seats to release");
        assignedSeats--;
    }
}
