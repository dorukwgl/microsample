package com.doruk.infrastructure.persistence.users.mapper;

import com.doruk.application.app.users.dto.CurrentUserDto;
import com.doruk.application.dto.OrganizationDto;
import com.doruk.application.app.users.dto.ProfileDto;
import com.doruk.domain.shared.enums.MultiAuthType;
import com.doruk.domain.shared.enums.UserAccountStatus;
import jakarta.inject.Singleton;
import org.jooq.Record14;

import java.time.OffsetDateTime;
import java.util.List;


@Singleton
public class UserMapper {
    public CurrentUserDto toCurrentUserDto(Record14<String, String, String, String, Boolean,
            Boolean, MultiAuthType, UserAccountStatus, Boolean, OffsetDateTime,
            OffsetDateTime, ProfileDto, OrganizationDto, List<String>> usr) {

        return CurrentUserDto.builder()
                .id(usr.value1())
                .username(usr.value2())
                .email(usr.value3())
                .phone(usr.value4())
                .emailVerified(usr.value5())
                .phoneVerified(usr.value6())
                .multiFactorAuth(usr.value7())
                .status(usr.value8())
                .isOrgAdmin(usr.value9())
                .createdAt(usr.value10())
                .updatedAt(usr.value11())
                .profile(usr.value12())
                .organization(usr.value13())
                .roles(usr.value14())
                .build();
    }
}
