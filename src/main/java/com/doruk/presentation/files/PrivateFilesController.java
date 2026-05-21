package com.doruk.presentation.files;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Accessing Private Files")
@Secured(SecurityRule.IS_AUTHENTICATED)
@Controller("/res")
public class PrivateFilesController {

    @Get("/{filename:.*}")
    public HttpResponse<?> stream(String filename) {
        if (!filename.startsWith("private/")) return HttpResponse.notFound();

        return HttpResponse.ok()
                .header("X-Accel-Redirect", "/protected/" + filename)
                .header("Content-Disposition", "inline; filename=\"" + filename + "\"");
    }
}
