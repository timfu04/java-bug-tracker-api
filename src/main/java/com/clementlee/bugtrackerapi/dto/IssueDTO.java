package com.clementlee.bugtrackerapi.dto;

import com.clementlee.bugtrackerapi.models.*;
import com.clementlee.bugtrackerapi.validation.annotations.ValidateBlankAndUppercase;
import com.clementlee.bugtrackerapi.validation.markerinterfaces.IssueCreateValidationGroup;
import com.clementlee.bugtrackerapi.validation.markerinterfaces.IssueUpdateValidationGroup;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class IssueDTO {

    private int id;

    @NotBlank(message = "Invalid title: Empty title", groups = {IssueCreateValidationGroup.class})
    private String title;

    @NotBlank(message = "Invalid description: Empty description", groups = {IssueCreateValidationGroup.class})
    private String description;

    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private LocalDateTime resolvedDate;
    private LocalDateTime closedDate;
    private Project project;

    @ValidateBlankAndUppercase(groups = {IssueUpdateValidationGroup.class})
    private Status status;

    @ValidateBlankAndUppercase(groups = {IssueCreateValidationGroup.class, IssueUpdateValidationGroup.class})
    private Severity severity;

    @ValidateBlankAndUppercase(groups = {IssueCreateValidationGroup.class, IssueUpdateValidationGroup.class})
    private Priority priority;

    private UserEntity userReported;
    private List<UserEntity> usersAssigned;
    private List<IssueComment> issueComments;

}
