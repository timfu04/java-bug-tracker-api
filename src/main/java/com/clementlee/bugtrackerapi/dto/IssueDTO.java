package com.clementlee.bugtrackerapi.dto;

import com.clementlee.bugtrackerapi.models.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class IssueDTO {

    private int id;
    private String title;
    private String description;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private LocalDateTime resolvedDate;
    private LocalDateTime closedDate;
    private Project project;
    private Status status;
    private Severity severity;
    private Priority priority;
    private UserEntity userReported;
    private List<UserEntity> usersAssigned;

}
