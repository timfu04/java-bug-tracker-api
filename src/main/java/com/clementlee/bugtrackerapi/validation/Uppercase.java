package com.clementlee.bugtrackerapi.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD}) // can be declared in field level
@Retention(RetentionPolicy.RUNTIME) // implement in runtime level
@Documented
@Constraint(validatedBy = UppercaseValidator.class) // "Uppercase" annotation takes logic from "UppercaseValidator" class
public @interface Uppercase {

    public String message() default "Invalid name: Name must be in uppercase";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
