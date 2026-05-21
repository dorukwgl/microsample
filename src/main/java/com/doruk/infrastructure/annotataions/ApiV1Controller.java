package com.doruk.infrastructure.annotataions;

import io.micronaut.http.annotation.Controller;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
@Target(TYPE)
@Controller("/api/v1")
public @interface ApiV1Controller { String value() default ""; }