package com.clementlee.bugtrackerapi.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RoleDTO {

    private int id;

    @NotBlank(message = "Invalid name: Empty name")
    private String name;

}
