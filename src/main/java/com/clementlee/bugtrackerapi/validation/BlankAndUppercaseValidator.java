package com.clementlee.bugtrackerapi.validation;

import com.clementlee.bugtrackerapi.models.Priority;
import com.clementlee.bugtrackerapi.models.Severity;
import com.clementlee.bugtrackerapi.models.Status;
import com.clementlee.bugtrackerapi.validation.annotations.ValidateBlankAndUppercase;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.util.StringUtils;

public class BlankAndUppercaseValidator implements ConstraintValidator<ValidateBlankAndUppercase, Object> {

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context) {

        context.disableDefaultConstraintViolation(); // Disable default message

        if (obj.getClass().equals(String.class)){ // If object equals to String class type
            String value = (String) obj; // Convert object into String
            if (StringUtils.hasText(value)){
                context.buildConstraintViolationWithTemplate("Invalid name: Name must be in uppercase")
                        .addConstraintViolation();
                return isUpperCase(value);
            } else {
                // Empty string error message are excluded in GlobalExceptionHandler
                // Allow error message from @NotBlank to be shown
                context.buildConstraintViolationWithTemplate("").addConstraintViolation();
                return false;
            }
        }
        else if (obj.getClass().equals(Status.class)) { // If object equals to Status class type
            Status status = (Status) obj; // Convert object into Status
            if (StringUtils.hasText(status.getName())){
                context.buildConstraintViolationWithTemplate("Invalid status name: Status name must be in uppercase")
                        .addConstraintViolation();
                return isUpperCase(status.getName());
            } else {
                context.buildConstraintViolationWithTemplate("Invalid status: Empty status").addConstraintViolation();
                return false;
            }
        }
        else if (obj.getClass().equals(Severity.class)) { // If object equals to Severity class type
            Severity severity = (Severity) obj; // Convert object into Severity
            if (StringUtils.hasText(severity.getName())){
                context.buildConstraintViolationWithTemplate("Invalid severity name: Severity name must be in uppercase")
                        .addConstraintViolation();
                return isUpperCase(severity.getName());
            } else {
                context.buildConstraintViolationWithTemplate("Invalid severity: Empty severity").addConstraintViolation();
                return false;
            }
        }
        else if (obj.getClass().equals(Priority.class)) { // If object equals to Priority class type
            Priority priority = (Priority) obj; // Convert object into Priority
            if (StringUtils.hasText(priority.getName())){
                context.buildConstraintViolationWithTemplate("Invalid priority name: Priority name must be in uppercase")
                        .addConstraintViolation();
                return isUpperCase(priority.getName());
            } else {
                context.buildConstraintViolationWithTemplate("Invalid priority: Empty priority").addConstraintViolation();
                return false;
            }
        }

        context.buildConstraintViolationWithTemplate("").addConstraintViolation();
        return false;
    }

    // Check whether string value is uppercase
    private boolean isUpperCase(String value){
        return value.equals(value.toUpperCase());
    }

}
