package com.doruk.infrastructure.apiclient.dto;

import io.micronaut.serde.annotation.Serdeable;
import org.jspecify.annotations.Nullable;

import java.util.List;

@Serdeable
public record FirebasePushRequest(
        Message message
) {
    @Serdeable
    public record Message(
            String token,
            Notification notification,
            @Nullable Android android,
            @Nullable Apns apns
    ) {}

    @Serdeable
    public record Notification(
            String title,
            String body,
            @Nullable String image
    ) {}

    @Serdeable
    public record Android(
            @Nullable AndroidNotification notification
    ) {
        @Serdeable
        public record AndroidNotification(
                @Nullable String icon,
                @Nullable String image
        ) {}
    }

    @Serdeable
    public record Apns(
            Payload payload,
            @Nullable FcmOptions fcm_options
    ) {
        @Serdeable
        public record Payload(Aps aps) {
            @Serdeable
            public record Aps(int mutableContent) {
                public Aps() { this(1); }
            }
        }

        @Serdeable
        public record FcmOptions(@Nullable String image) {}
    }
}

@Serdeable
record FirebaseErrorResponse(List<ErrorDetail> error) {
    @Serdeable
    record ErrorDetail(String message, String status) {}
}
