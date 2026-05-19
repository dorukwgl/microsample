package com.doruk.presentation.files;

import com.doruk.application.dto.StoredObject;
import com.doruk.application.enums.ObjectVisibility;
import com.doruk.application.interfaces.ObjectStorage;
import io.micronaut.context.env.Environment;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.authentication.Authentication;
import io.micronaut.security.rules.SecurityRule;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;

import java.net.URI;
import java.time.Duration;

import static com.doruk.jooq.tables.MediaStore.MEDIA_STORE;

@Tag(name = "Accessing Private Files")
@Secured(SecurityRule.IS_ANONYMOUS)
@RequiredArgsConstructor
@Controller("/res")
public class PrivateFilesController {
    private final ObjectStorage storage;
    private final DSLContext dsl;
    private final Environment environment;

    @Get("/{filename:.*}")
    public HttpResponse<?> stream(String filename, @Nullable Authentication auth) {
        var record = dsl.select(MEDIA_STORE.VISIBILITY, MEDIA_STORE.OBJECT_KEY)
                .from(MEDIA_STORE)
                .where(MEDIA_STORE.OBJECT_KEY.eq(filename))
                .and(MEDIA_STORE.DELETED_AT.isNull())
                .fetchOne();

        if (record == null) return HttpResponse.notFound();

        var dbVisibility = record.value1();
        var objectKey = record.value2();
        var visibility = ObjectVisibility.valueOf(dbVisibility.name());

        if (visibility == ObjectVisibility.PRIVATE && auth == null)
            return HttpResponse.status(HttpStatus.UNAUTHORIZED);

        var stored = StoredObject.builder()
                .objectKey(objectKey)
                .visibility(visibility)
                .originalName(null)
                .size(0)
                .mimeType(null)
                .build();

        if (visibility == ObjectVisibility.PRIVATE) {
            if (environment.getActiveNames().contains("object-storage-s3")) {
                var signedUrl = storage.signUrl(stored, Duration.ofMinutes(5));
                return HttpResponse.redirect(URI.create(signedUrl));
            }
            return HttpResponse.ok()
                    .header("X-Accel-Redirect", "/protected/" + objectKey)
                    .header("Content-Disposition", "inline; filename=\"" + objectKey + "\"");
        }

        return HttpResponse.redirect(URI.create(storage.resolveUrl(stored)));
    }
}
