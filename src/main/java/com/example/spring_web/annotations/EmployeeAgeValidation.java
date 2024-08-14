package com.example.spring_web.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {EmployeeAgeValidator.class})
public @interface EmployeeAgeValidation {
    String message() default "Age Should be Even Number.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
