package com.doruk.infrastructure.config;

import io.micronaut.context.annotation.Context;
import io.micronaut.scheduling.TaskExecutors;
import jakarta.inject.Named;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.util.concurrent.ExecutorService;

@Context
public final class AppExecutors {
    public final Scheduler CPU;
    public final Scheduler BLOCKING;

    public AppExecutors(
            @Named(TaskExecutors.BLOCKING) ExecutorService blocking,
            @Named("cpu-executor") ExecutorService cpu
    ) {
        this.CPU = Schedulers.fromExecutor(cpu);
        this.BLOCKING = Schedulers.fromExecutor(blocking);
    }

    public Mono<?> onCpu(Mono<?> mono) {
        return mono.subscribeOn(this.CPU);
    }

    public Mono<?> onVirtual(Mono<?> mono) {
        return mono.subscribeOn(this.BLOCKING);
    }
}