package com.example.CatalogService.aspect;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;


@Aspect
public class LoggingAspect {

    @AfterReturning(value = "@annotation(ToLog)", returning = "returnedValue")
    public void log (Object returnedValue) {
        System.out.println("Method executed and returned: " + returnedValue);
        }
}
