package com.doruk.infrastructure.config;

import io.micronaut.scheduling.TaskExecutors;

public final class AppExecutors {
    public static final String BLOCKING = TaskExecutors.BLOCKING;

    public static final String CPU = "cpu";

    private AppExecutors() {}
}