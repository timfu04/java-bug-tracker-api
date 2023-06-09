package com.clementlee.bugtrackerapi.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "issues_comments")
@Entity
public class IssueComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String commentText;

    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "fk_users_id", referencedColumnName = "id")
    private UserEntity user;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "fk_issues_id", referencedColumnName = "id")
    private Issue issue;

}
