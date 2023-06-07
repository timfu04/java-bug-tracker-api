package com.clementlee.bugtrackerapi.dto;

import com.clementlee.bugtrackerapi.models.UserEntity;
import com.clementlee.bugtrackerapi.validation.markerinterfaces.ProjectAllFieldsValidationGroup;
import com.clementlee.bugtrackerapi.validation.markerinterfaces.ProjectDateValidationGroup;
import com.clementlee.bugtrackerapi.validation.annotations.ValidateProjectDate;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

@Data
@ValidateProjectDate(groups = {ProjectAllFieldsValidationGroup.class, ProjectDateValidationGroup.class})
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

    private List<UserEntity> usersInvolved;

    private UserEntity userCreated;

}
