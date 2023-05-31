package com.clementlee.bugtrackerapi.validation;

import com.clementlee.bugtrackerapi.dto.ProjectDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

public class DateValidator implements ConstraintValidator<ValidateDate, ProjectDTO> {

    @Override
    public boolean isValid(ProjectDTO projectDTO, ConstraintValidatorContext context) {

        context.disableDefaultConstraintViolation(); // disable default message

        LocalDate startDate;
        LocalDate endDate;
        LocalDate currentDate = LocalDate.now();

        // Validate date format & ensure day within range for given month and year
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/uuuu"); // "uuuu" means year after Java 8
            // "withResolverStyle(ResolverStyle.STRICT)" ensure day within range for given month and year (e.g. 30th Feb throws error)
            startDate = LocalDate.parse(projectDTO.getStartDate(), formatter.withResolverStyle(ResolverStyle.STRICT));
            endDate = LocalDate.parse(projectDTO.getEndDate(), formatter.withResolverStyle(ResolverStyle.STRICT));
        } catch (Exception e1) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Invalid date: Day given not within given month and must be in dd/mm/yyyy format")
                    .addConstraintViolation();
            return false;
        }

        // Ensure start date & end date is before the current date
        if (startDate.isBefore(currentDate)) {
            context.buildConstraintViolationWithTemplate("Invalid start date: Start date cannot be before the current date")
                    .addConstraintViolation(); // custom start date error message
            return false;
        } else if (endDate.isBefore(currentDate)) {
            context.buildConstraintViolationWithTemplate("Invalid end date: End date cannot be before the current date")
                    .addConstraintViolation(); // custom end date error message
            return false;
        }

        // Ensure start date is same or before end date
        // Ensure end date is same or after start date
        if (startDate.isAfter(endDate)){
            context.buildConstraintViolationWithTemplate("Invalid start date: Start date cannot be after the end date")
                    .addConstraintViolation(); // custom start date error message
            return false;
        }

        return true;
    }

}
