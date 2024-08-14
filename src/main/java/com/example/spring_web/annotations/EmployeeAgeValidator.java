package com.example.spring_web.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EmployeeAgeValidator implements ConstraintValidator<EmployeeAgeValidation,Integer> {
    @Override
    public boolean isValid(Integer input, ConstraintValidatorContext constraintValidatorContext) {
//        if(input%2 == 0){
//            return true;
//        }
//        return false;

        return input % 2 == 0;
    }
}
