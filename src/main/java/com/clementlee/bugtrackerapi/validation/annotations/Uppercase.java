package com.clementlee.bugtrackerapi.validation.annotations;

import com.clementlee.bugtrackerapi.validation.UppercaseValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD}) // Allow field declaration
@Retention(RetentionPolicy.RUNTIME) // Implement in runtime level
@Documented
@Constraint(validatedBy = UppercaseValidator.class) // "Uppercase" annotation based on logic in "UppercaseValidator" class
public @interface Uppercase {

    public String message() default "";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
