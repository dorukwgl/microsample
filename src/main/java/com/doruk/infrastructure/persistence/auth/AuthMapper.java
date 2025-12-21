package com.doruk.infrastructure.persistence.auth;

import com.doruk.application.auth.dto.AuthDto;
import com.doruk.domain.shared.enums.Permissions;
import com.doruk.infrastructure.persistence.entity.Permission;
import com.doruk.infrastructure.persistence.entity.User;
import io.micronaut.context.annotation.Mapper;
import io.micronaut.context.annotation.Mapper.Mapping;

import java.util.List;
import java.util.stream.Collectors;

public interface AuthMapper {
//    @Mapping(
//            to = "permissions",
//            from = "#{entity.roles.}"
//    )
    @Mapper
    AuthDto toDto(User entity);

//    default List<Permissions> mapPermisions(User user) {
//        return user.roles().stream()
//                .map(role -> role.permissions().stream()
//                        .map(Permission::name)
//                ).flatMap(List::stream).toList();
//    }
}
