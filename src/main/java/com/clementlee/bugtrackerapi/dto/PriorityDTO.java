package com.clementlee.bugtrackerapi.dto;

import com.clementlee.bugtrackerapi.models.Issue;
import com.clementlee.bugtrackerapi.validation.annotations.Uppercase;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

@Data
public class PriorityDTO {

    private int id;

    @NotBlank(message = "Invalid name: Empty name")
    @Uppercase(message = "Invalid name: Name must be in uppercase")
    private String name;

    private List<Issue> issues;

}
