package com.clementlee.bugtrackerapi.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD}) // allow field declaration
@Retention(RetentionPolicy.RUNTIME) // implement in runtime level
@Documented
@Constraint(validatedBy = UppercaseValidator.class) // "Uppercase" annotation based on logic in "UppercaseValidator" class
public @interface Uppercase {

    public String message() default "";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
