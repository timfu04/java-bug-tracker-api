package com.clementlee.bugtrackerapi.validation;

import com.clementlee.bugtrackerapi.models.Priority;
import com.clementlee.bugtrackerapi.models.Severity;
import com.clementlee.bugtrackerapi.models.Status;
import com.clementlee.bugtrackerapi.validation.annotations.ValidateEmptyIssueSeverityPriority;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.util.StringUtils;

public class EmptyIssueSeverityPriorityValidator implements ConstraintValidator<ValidateEmptyIssueSeverityPriority, Object> {

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context) {

        context.disableDefaultConstraintViolation(); // Disable default message

        if (obj.getClass().equals(Status.class)){ // If object equals Status class type
            Status status = (Status) obj;
            if (!StringUtils.hasText(status.getName())){
                context.buildConstraintViolationWithTemplate("Invalid status: Empty status").addConstraintViolation();
                return false;
            }
        } else if (obj.getClass().equals(Severity.class)){ // If object equals Severity class type
            Severity severity = (Severity) obj;
            if (!StringUtils.hasText(severity.getName())){
                context.buildConstraintViolationWithTemplate("Invalid severity: Empty severity").addConstraintViolation();
                return false;
            }
        } else if (obj.getClass().equals(Priority.class)) { // If object equals Priority class type
            Priority priority = (Priority) obj;
            if (!StringUtils.hasText(priority.getName())){
                context.buildConstraintViolationWithTemplate("Invalid priority: Empty priority").addConstraintViolation();
                return false;
            }
        }

        return true;
    }

}
