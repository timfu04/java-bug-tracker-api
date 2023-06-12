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

    @Column(nullable = false)
    private String commentText;

    @Column(nullable = false)
    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;

    @JsonBackReference(value = "users-issue_comments")
    @ManyToOne
    @JoinColumn(name = "fk_users_id", referencedColumnName = "id", nullable = false)
    private UserEntity user;

    @JsonBackReference(value = "issues-issue_comments")
    @ManyToOne
    @JoinColumn(name = "fk_issues_id", referencedColumnName = "id", nullable = false)
    private Issue issue;

}
