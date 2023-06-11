package com.clementlee.bugtrackerapi.dto;

import com.clementlee.bugtrackerapi.models.Issue;
import com.clementlee.bugtrackerapi.validation.annotations.ValidateUppercase;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

@Data
public class StatusDTO {

    private int id;

    @NotBlank(message = "Invalid name: Empty name")
    @ValidateUppercase(message = "Invalid name: Name must be in uppercase")
    private String name;

    private List<Issue> issues;

}
