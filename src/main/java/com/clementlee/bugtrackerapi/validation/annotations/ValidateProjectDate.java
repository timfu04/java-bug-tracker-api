package com.clementlee.bugtrackerapi.validation.annotations;

import com.clementlee.bugtrackerapi.validation.ProjectDateValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.TYPE}) // Class level declaration
@Retention(RetentionPolicy.RUNTIME) // Implement on runtime level
@Documented
@Constraint(validatedBy = ProjectDateValidator.class)
public @interface ValidateProjectDate {

    String message() default "";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
