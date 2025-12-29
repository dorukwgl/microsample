package com.doruk.application.users.service;

import io.micronaut.http.annotation.Controller;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "User Registrations & Management")
@RequiredArgsConstructor
@Controller("/users")
public class UserService {

}
