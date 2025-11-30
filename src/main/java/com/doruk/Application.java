package com.doruk;

import io.micronaut.runtime.Micronaut;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
    info = @Info(
            title = "microsample",
            version = "1.0.0"
    )
)
public class Application {

    public static void main(String[] args) {
        var ctx = Micronaut.run(Application.class, args);

        Runtime.getRuntime().addShutdownHook(new Thread(ctx::stop));
    }
}
