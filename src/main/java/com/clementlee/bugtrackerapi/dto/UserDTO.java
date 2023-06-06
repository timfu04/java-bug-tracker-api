package com.clementlee.bugtrackerapi.dto;

import com.clementlee.bugtrackerapi.models.Project;
import com.clementlee.bugtrackerapi.models.Role;
import com.clementlee.bugtrackerapi.validation.markerinterface.UserAllFieldsValidationGroup;
import com.clementlee.bugtrackerapi.validation.markerinterface.UserEmailValidationGroup;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

@Data
public class UserDTO {

    private int id;

    @NotBlank(message = "Invalid username: Empty username", groups = {UserAllFieldsValidationGroup.class})
    private String username;

    @NotBlank(message = "Invalid password: Empty password", groups = {UserAllFieldsValidationGroup.class})
    private String password;

    @NotBlank(message = "Invalid email: Empty email", groups = {UserAllFieldsValidationGroup.class})
    @Email(message = "Invalid email: Incorrect email format", groups = {UserAllFieldsValidationGroup.class, UserEmailValidationGroup.class})
    private String email;

    private Role role;

    private List<Project> projectsInvolved;

    private List<Project> projectsCreated;

}
