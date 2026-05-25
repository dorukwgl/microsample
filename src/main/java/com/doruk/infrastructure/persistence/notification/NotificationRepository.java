package com.doruk.infrastructure.persistence.notification;

import com.doruk.application.app.notifications.dto.NotificationResponse;
import com.doruk.application.dto.NotificationDto;
import com.doruk.application.dto.PageQuery;
import com.doruk.application.dto.PageResponse;
import com.doruk.application.enums.SortOrder;
import com.doruk.infrastructure.persistence.entity.NotificationTable;
import com.doruk.infrastructure.persistence.mapper.PageMapper;
import com.doruk.jooq.tables.Notifications;
import com.doruk.jooq.tables.UserDevices;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import org.babyfish.jimmer.sql.JSqlClient;
import org.jooq.DSLContext;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Singleton
@RequiredArgsConstructor
public class NotificationRepository {
    private final JSqlClient sqlClient;
    private final DSLContext dsl;

    public void saveAll(List<NotificationDto> dtos) {
        var n = Notifications.NOTIFICATIONS;
        var now = OffsetDateTime.now();
        for (var dto : dtos) {
            dsl.insertInto(n)
                    .set(n.USER_ID, dto.userId())
                    .set(n.TITLE, dto.title())
                    .set(n.MESSAGE, dto.message())
                    .set(n.ICON, dto.icon())
                    .set(n.ATTACHMENT, dto.attachment())
                    .set(n.ATTACHMENT_TYPE, dto.attachmentType())
                    .set(n.IS_READ, false)
                    .set(n.CREATED_AT, now)
                    .set(n.UPDATED_AT, now)
                    .execute();
        }
    }

    public PageResponse<NotificationResponse> findByUserId(UUID userId, PageQuery page) {
        var n = NotificationTable.$;
        var res = sqlClient.createQuery(n)
                .where(n.userId().eq(userId))
                .orderBy(page.order() == SortOrder.DESC ? n.createdAt().desc() : n.createdAt().asc())
                .select(n)
                .fetchPage(page.page(), page.size());

        return PageMapper.toResponse(res, rs -> NotificationResponse.builder()
                .id(rs.id())
                .title(rs.title())
                .message(rs.message())
                .iconUrl(rs.icon())
                .attachmentUrl(rs.attachment())
                .attachmentType(rs.attachmentType())
                .isRead(rs.isRead())
                .createdAt(rs.createdAt())
                .build());
    }

    public void markAsRead(long id, UUID userId) {
        var n = Notifications.NOTIFICATIONS;
        dsl.update(n)
                .set(n.IS_READ, true)
                .set(n.UPDATED_AT, OffsetDateTime.now())
                .where(n.ID.eq(id).and(n.USER_ID.eq(userId)))
                .execute();
    }

    public void delete(long id, UUID userId) {
        var n = Notifications.NOTIFICATIONS;
        dsl.deleteFrom(n)
                .where(n.ID.eq(id).and(n.USER_ID.eq(userId)))
                .execute();
    }

    public List<String> findDeviceTokens(UUID userId) {
        var d = UserDevices.USER_DEVICES;
        return dsl.select(d.NOTIFICATION_DEVICE_ID)
                .from(d)
                .where(d.USER_ID.eq(userId))
                .and(d.NOTIFICATION_DEVICE_ID.isNotNull())
                .fetch(rs -> rs.get(d.NOTIFICATION_DEVICE_ID));
    }
}
