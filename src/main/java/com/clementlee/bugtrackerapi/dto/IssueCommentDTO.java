package com.clementlee.bugtrackerapi.dto;

import com.clementlee.bugtrackerapi.models.Issue;
import com.clementlee.bugtrackerapi.models.UserEntity;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class IssueCommentDTO {

    private int id;
    private String commentText;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private UserEntity user;
    private Issue issue;

}
