package com.example.spring_web.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;

public class EmployeeValidator implements ConstraintValidator<EmployeeRoleValidation,String> {
    @Override
    public boolean isValid(String inputRole, ConstraintValidatorContext constraintValidatorContext) {
        if(inputRole == null) return false;
        List<String> str = List.of("USER","ADMIN");
        return str.contains(inputRole);
    }
}
