package com.example.CatalogService.aspect;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ToLog {
    String value() default "DEFAULT_LOG_MESSAGE";
}

