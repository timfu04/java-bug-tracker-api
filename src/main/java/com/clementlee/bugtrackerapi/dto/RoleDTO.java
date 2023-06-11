package com.clementlee.bugtrackerapi.dto;

import com.clementlee.bugtrackerapi.models.UserEntity;
import com.clementlee.bugtrackerapi.validation.annotations.ValidateUppercase;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

@Data
public class RoleDTO {

    private int id;

    @NotBlank(message = "Invalid name: Empty name")
    @ValidateUppercase(message = "Invalid name: Name must be in uppercase")
    private String name;

    private List<UserEntity> users;

}
