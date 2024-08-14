package com.example.spring_web.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint( validatedBy = {EmployeeValidator.class})
public @interface EmployeeRoleValidation {
    String message() default "Role can be USER or ADMIN";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
