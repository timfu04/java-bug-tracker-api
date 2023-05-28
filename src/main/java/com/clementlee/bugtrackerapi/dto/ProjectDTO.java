package com.clementlee.bugtrackerapi.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProjectDTO {

    private int id;

    @NotBlank(message = "Invalid name: Empty name")
    private String name;

    @NotBlank(message = "Invalid description: Empty description")
    private String description;

    @NotBlank(message = "Invalid start date: Empty start date")
    private LocalDateTime start_date;

    @NotBlank(message = "Invalid end date: Empty end date")
    private LocalDateTime end_date;

}
