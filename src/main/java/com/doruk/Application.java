package com.doruk;

import com.doruk.infrastructure.persistence.entity.*;
import io.micronaut.runtime.Micronaut;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.babyfish.jimmer.sql.JSqlClient;


@OpenAPIDefinition(
        info = @Info(
                title = "microsample",
                version = "1.0.0"
        )
)
public class Application {

    static void main(String[] args) {
        var ctx = Micronaut.run(Application.class, args);

        var envs = ctx.getEnvironment().getActiveNames();

        // for testing
        var client = ctx.getBean(JSqlClient.class);

        System.out.println(envs);
    }
}
