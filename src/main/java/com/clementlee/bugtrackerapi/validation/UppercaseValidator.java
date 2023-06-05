package com.clementlee.bugtrackerapi.validation;

import com.clementlee.bugtrackerapi.validation.annotations.Uppercase;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UppercaseValidator implements ConstraintValidator<Uppercase, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value.equals(value.toUpperCase());
    }

}
