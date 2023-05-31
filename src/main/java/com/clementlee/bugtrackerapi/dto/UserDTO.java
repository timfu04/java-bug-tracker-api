package com.clementlee.bugtrackerapi.dto;

import com.clementlee.bugtrackerapi.models.Project;
import com.clementlee.bugtrackerapi.models.Role;
import com.clementlee.bugtrackerapi.validation.EmailValidationGroup;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserDTO {

    private int id;

    @NotBlank(message = "Invalid username: Empty username")
    private String username;

    @NotBlank(message = "Invalid password: Empty password")
    private String password;

    @NotBlank(message = "Invalid email: Empty email")
    @Email(message = "Invalid email: Incorrect email format", groups = EmailValidationGroup.class)
    private String email;

    private Role role;

    private List<Project> projects;
}
