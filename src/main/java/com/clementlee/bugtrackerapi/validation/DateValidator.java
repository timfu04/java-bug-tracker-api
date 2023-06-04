package com.clementlee.bugtrackerapi.validation;

import com.clementlee.bugtrackerapi.dto.ProjectDTO;
import com.clementlee.bugtrackerapi.exceptions.ProjectNotFoundException;
import com.clementlee.bugtrackerapi.models.Project;
import com.clementlee.bugtrackerapi.repositories.ProjectRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.web.servlet.HandlerMapping;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.Map;

@RequiredArgsConstructor
public class DateValidator implements ConstraintValidator<ValidateDate, ProjectDTO> {

    private final HttpServletRequest request; // to get path variable(projectId) from request
    private final ProjectRepository projectRepository;

    @Override
    public boolean isValid(ProjectDTO projectDTO, ConstraintValidatorContext context) {

        context.disableDefaultConstraintViolation(); // disable default message

        LocalDate startDate;
        LocalDate endDate;
        LocalDate currentDate = LocalDate.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/uuuu"); // "uuuu" means year after Java 8

        try{
            Map pathVariables = (Map) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
            String strProjectId = (String) pathVariables.get("projectId");
            int projectId = Integer.parseInt(strProjectId);

            Project project = projectRepository.findById(projectId).orElseThrow(() -> new ProjectNotFoundException("Project could not be found"));

            if (projectDTO.getStartDate() == null){
                projectDTO.setStartDate(project.getStartDate().format(formatter));
            } else if (projectDTO.getStartDate().isBlank()) {
                projectDTO.setStartDate(project.getStartDate().format(formatter));
            }

            if (projectDTO.getEndDate() == null){
                projectDTO.setEndDate(project.getEndDate().format(formatter));
            } else if (projectDTO.getEndDate().isBlank()) {
                projectDTO.setEndDate(project.getEndDate().format(formatter));
            }
        }catch (Exception e1){}

        // Validate date format & ensure day within range for given month and year
        try {
            // "withResolverStyle(ResolverStyle.STRICT)" ensure day within range for given month and year (e.g. 30th Feb throws error)
            startDate = LocalDate.parse(projectDTO.getStartDate(), formatter.withResolverStyle(ResolverStyle.STRICT));
            endDate = LocalDate.parse(projectDTO.getEndDate(), formatter.withResolverStyle(ResolverStyle.STRICT));
        } catch (Exception e2) {
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
