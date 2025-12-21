package com.doruk;

import com.doruk.infrastructure.config.AppExecutors;
import com.doruk.infrastructure.persistence.auth.AuthRepository;
import io.micronaut.runtime.Micronaut;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.babyfish.jimmer.sql.JSqlClient;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import javax.swing.plaf.TableHeaderUI;


@OpenAPIDefinition(
        info = @Info(
                title = "microsample",
                version = "1.0.0"
        )
)
public class Application {

    static void main(String[] args) {
        var ctx = Micronaut.run(Application.class, args);
        var client = ctx.getBean(AuthRepository.class);
        var executor = ctx.getBean(AppExecutors.class);

        var testBlocking = Mono.fromCallable(() -> {
            System.out.println(Thread.currentThread().getName());
            return "";
        }).subscribeOn(executor.BLOCKING);

        var testCpu = Mono.fromCallable(() -> {
            System.out.println(Thread.currentThread().getName());
            return "";
        }).subscribeOn(executor.CPU);

        Mono.fromCallable(() -> {
            System.out.println(Thread.currentThread().getName());
            return "";
        }).flatMap(a -> testCpu)
                .publishOn(executor.BLOCKING)
                .flatMap(a -> Mono.fromCallable(() -> {
                    System.out.println(Thread.currentThread().getName());
                    return "";
                }))
                .flatMap(a -> testBlocking)
                .subscribeOn(executor.BLOCKING).block();
    }
}
