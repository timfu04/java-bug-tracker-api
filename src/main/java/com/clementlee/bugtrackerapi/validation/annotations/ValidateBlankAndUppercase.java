package com.clementlee.bugtrackerapi.validation.annotations;

import com.clementlee.bugtrackerapi.validation.BlankAndUppercaseValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD}) // Allow field declaration
@Retention(RetentionPolicy.RUNTIME) // Implement in runtime level
@Documented
@Constraint(validatedBy = BlankAndUppercaseValidator.class) // "ValidateBlankAndUppercase" annotation based on logic in "BlankAndUppercaseValidator" class
public @interface ValidateBlankAndUppercase {

    public String message() default "";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
