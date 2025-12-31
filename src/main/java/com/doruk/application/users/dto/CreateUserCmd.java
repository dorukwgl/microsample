package com.doruk.application.users.dto;

public record CreateUserCmd(
        String username,
        String password,
        String email,
        String phone
) {
}
