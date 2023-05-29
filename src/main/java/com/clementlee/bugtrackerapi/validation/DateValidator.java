package com.clementlee.bugtrackerapi.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

public class DateValidator implements ConstraintValidator<ValidateDate, String> {

    private String fieldName;

    @Override
    public void initialize(ValidateDate constraintAnnotation) {
        this.fieldName = constraintAnnotation.fieldName();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        LocalDate givenDate;
        LocalDate currentDate = LocalDate.now();

        try{
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/uuuu"); // "uuuu" means year after Java 8
            // "withResolverStyle(ResolverStyle.STRICT)" ensure day with range for given month and year (e.g. 30th Feb throws error)
            givenDate = LocalDate.parse(value, formatter.withResolverStyle(ResolverStyle.STRICT));
        }catch (Exception e1){
            context.buildConstraintViolationWithTemplate("Invalid date: Day given not within given month and must be in dd/mm/yyyy format")
                    .addConstraintViolation();
            return false;
        }

        if (!givenDate.isBefore(currentDate)){ // if given date is not before current date
            return true;
        }else {
            if (fieldName.equals("startDate")){
                context.buildConstraintViolationWithTemplate("Invalid start date: Start date cannot be before the current date")
                        .addConstraintViolation(); // custom start date error message
            } else if (fieldName.equals("endDate")) {
                context.buildConstraintViolationWithTemplate("Invalid end date: End date cannot be before the current date")
                        .addConstraintViolation(); // custom end date error message
            }
        }

        return false;
    }

}
