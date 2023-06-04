package com.clementlee.bugtrackerapi.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "projects")
@Entity
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String description;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate endDate;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "fk_users_id", referencedColumnName = "id")
    private UserEntity user;

    @JsonIgnore // Skip "issues" field when serializing "Project" object into JSON (avoid infinite recursion in bidirectional relationships)
    @OneToMany(mappedBy = "project")
    private List<Issue> issues;

}
