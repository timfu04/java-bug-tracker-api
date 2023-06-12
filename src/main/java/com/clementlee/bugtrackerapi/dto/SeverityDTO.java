package com.clementlee.bugtrackerapi.dto;

import com.clementlee.bugtrackerapi.models.Issue;
import com.clementlee.bugtrackerapi.validation.annotations.ValidateBlankAndUppercase;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

@Data
public class SeverityDTO {

    private int id;

    @NotBlank(message = "Invalid name: Empty name")
    @ValidateBlankAndUppercase
    private String name;

    private List<Issue> issues;

}
