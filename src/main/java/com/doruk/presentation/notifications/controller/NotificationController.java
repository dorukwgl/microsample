package com.doruk.presentation.notifications.controller;

import com.doruk.application.app.notifications.dto.NotificationResponse;
import com.doruk.application.app.notifications.service.NotificationService;
import com.doruk.application.dto.PageResponse;
import com.doruk.infrastructure.annotataions.Routes;
import io.micronaut.http.annotation.Controller;
import com.doruk.presentation.dto.PageQueryMapper;
import com.doruk.presentation.dto.PageQueryRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Put;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.authentication.Authentication;
import io.micronaut.security.rules.SecurityRule;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Tag(name = "Notifications")
@Secured(SecurityRule.IS_AUTHENTICATED)
@RequiredArgsConstructor
@Controller(Routes.APP + "/notifications")
public class NotificationController {
    private final NotificationService service;

    @Operation(summary = "List notifications", description = "Returns paginated notifications for the current user")
    @Get("/{?pageRequest*}")
    public PageResponse<NotificationResponse> list(PageQueryRequest pageRequest, Authentication auth) {
        return service.getNotifications(UUID.fromString(auth.getName()), PageQueryMapper.toQuery(pageRequest));
    }

    @Operation(summary = "Mark notification as read")
    @Put("/{id}/read")
    public HttpResponse<?> markAsRead(long id, Authentication auth) {
        service.markAsRead(id, UUID.fromString(auth.getName()));
        return HttpResponse.ok();
    }

    @Operation(summary = "Delete a notification")
    @Delete("/{id}")
    public HttpResponse<?> delete(long id, Authentication auth) {
        service.delete(id, UUID.fromString(auth.getName()));
        return HttpResponse.ok();
    }
}
