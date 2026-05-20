package com.doruk.presentation.dto;

import com.doruk.application.enums.SortOrder;
import io.micronaut.http.annotation.QueryValue;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;

@Serdeable
public record PageQueryRequest(
        @Nullable
        @Positive
        @QueryValue
        Integer page,

        @Nullable
        @Min(1)
        @Max(100)
        @QueryValue
        Integer size,

        @Nullable
        @QueryValue
        SortOrder order
) {
}
