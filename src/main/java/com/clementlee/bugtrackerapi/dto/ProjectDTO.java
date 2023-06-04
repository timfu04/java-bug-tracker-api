package com.clementlee.bugtrackerapi.dto;

import com.clementlee.bugtrackerapi.models.Issue;
import com.clementlee.bugtrackerapi.models.UserEntity;
import com.clementlee.bugtrackerapi.validation.ProjectAllFieldsValidationGroup;
import com.clementlee.bugtrackerapi.validation.ProjectDateValidationGroup;
import com.clementlee.bugtrackerapi.validation.ValidateDate;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@ValidateDate(groups = {ProjectAllFieldsValidationGroup.class, ProjectDateValidationGroup.class})
public class ProjectDTO {

    private int id;

    @NotBlank(message = "Invalid name: Empty name", groups = {ProjectAllFieldsValidationGroup.class})
    private String name;

    @NotBlank(message = "Invalid description: Empty description", groups = {ProjectAllFieldsValidationGroup.class})
    private String description;

    @NotBlank(message = "Invalid start date: Empty start date", groups = {ProjectAllFieldsValidationGroup.class})
    private String startDate;

    @NotBlank(message = "Invalid end date: Empty end date", groups = {ProjectAllFieldsValidationGroup.class})
    private String endDate;

    private UserEntity user;
}
