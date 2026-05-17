package com.doruk.infrastructure.persistence.organization.mapper;

import com.doruk.application.app.organization.dto.OrganizationInfoDto;
import com.doruk.domain.shared.enums.OrganizationType;
import com.doruk.jooq.tables.records.OrganizationsRecord;
import org.jooq.Record5;
import org.jooq.Record6;

import java.time.OffsetDateTime;
import java.util.UUID;

public class OrganizationMapper {

    public static OrganizationInfoDto toInfoDto(Record6<UUID, String, String, OrganizationType, OffsetDateTime, Integer> record) {
        return OrganizationInfoDto.builder()
                .id(record.value1())
                .name(record.value2())
                .orgCode(record.value3())
                .type(record.value4())
                .createdAt(record.value5())
                .memberCount(0)  // Set separately
                .build();
    }

    public static OrganizationInfoDto toInfoDto(OrganizationsRecord record) {
        return OrganizationInfoDto.builder()
                .id(record.getId())
                .name(record.getName())
                .orgCode(record.getOrgCode())
                .type(record.getType())
                .createdAt(record.getCreatedAt())
                .memberCount(0)  // Set separately
                .build();
    }
}