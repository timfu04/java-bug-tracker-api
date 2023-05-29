package com.clementlee.bugtrackerapi.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = DateValidator.class)
public @interface ValidateDate {

    public String message() default "";

    public String fieldName();

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
