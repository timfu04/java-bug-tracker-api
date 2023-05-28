package com.clementlee.bugtrackerapi.dto;

import com.clementlee.bugtrackerapi.validation.Uppercase;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RoleDTO {

    private int id;

    @NotBlank(message = "Invalid name: Empty name")
    @Uppercase
    private String name;

}
