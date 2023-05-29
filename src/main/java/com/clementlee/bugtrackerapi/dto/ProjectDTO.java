package com.clementlee.bugtrackerapi.dto;

import com.clementlee.bugtrackerapi.validation.ValidateDate;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ProjectDTO {

    private int id;

    @NotBlank(message = "Invalid name: Empty name")
    private String name;

    @NotBlank(message = "Invalid description: Empty description")
    private String description;

    @NotBlank(message = "Invalid start date: Empty start date")
    @ValidateDate(fieldName = "startDate")
    private String startDate;

    @NotBlank(message = "Invalid end date: Empty end date")
    @ValidateDate(fieldName = "endDate")
    private String endDate;

}
