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
@Table(name = "issues")
@Entity
public class Issue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true, nullable = false)
    private String title;

    @Column(unique = true, nullable = false)
    private String description;

    @Column(unique = true, nullable = false)
    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;
    private LocalDateTime resolvedDate;
    private LocalDateTime closedDate;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "fk_projects_id", referencedColumnName = "id")
    private Project project;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "fk_statuses_id", referencedColumnName = "id")
    private Status status;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "fk_severities_id", referencedColumnName = "id")
    private Severity severity;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "fk_priorities_id", referencedColumnName = "id")
    private Priority priority;

}
