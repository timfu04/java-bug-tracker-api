package com.clementlee.bugtrackerapi.validation.annotations;

import com.clementlee.bugtrackerapi.validation.ProjectDateValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = ProjectDateValidator.class)
public @interface ValidateProjectDate {

    String message() default "";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
