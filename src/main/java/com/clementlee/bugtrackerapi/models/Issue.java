package com.clementlee.bugtrackerapi.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "issues")
@Entity
public class Issue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true, nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;
    private LocalDateTime resolvedDate;
    private LocalDateTime closedDate;

    @JsonBackReference(value = "projects-issues")
    @ManyToOne
    @JoinColumn(name = "fk_projects_id", referencedColumnName = "id", nullable = false)
    private Project project;

    @JsonBackReference(value = "statuses-issues")
    @ManyToOne
    @JoinColumn(name = "fk_statuses_id", referencedColumnName = "id", nullable = false)
    private Status status;

    @JsonBackReference(value = "severities-issues")
    @ManyToOne
    @JoinColumn(name = "fk_severities_id", referencedColumnName = "id", nullable = false)
    private Severity severity;

    @JsonBackReference(value = "priorities-issues")
    @ManyToOne
    @JoinColumn(name = "fk_priorities_id", referencedColumnName = "id", nullable = false)
    private Priority priority;

    @JsonBackReference(value = "users-issues-reported")
    @ManyToOne
    @JoinColumn(name = "fk_users_id_reported", referencedColumnName = "id", nullable = false)
    private UserEntity userReported;

    @JsonBackReference(value = "users-issues-assigned")
    @ManyToMany
    @JoinTable(
            name = "users_issues_assigned",
            joinColumns = @JoinColumn(name = "fk_users_id_assigned", referencedColumnName = "id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "fk_issues_id_assigned", referencedColumnName = "id", nullable = false)
    )
    private List<UserEntity> usersAssigned;

    @JsonIgnore
    @OneToMany(mappedBy = "issue")
    private List<IssueComment> issueComments;

}
