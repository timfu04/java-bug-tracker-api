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

    @JsonBackReference(value = "users-projects-involved")
    @ManyToMany
    @JoinTable(
            name = "users_projects_involved",
            joinColumns = @JoinColumn(name = "fk_users_id_involved", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "fk_projects_id_involved", referencedColumnName = "id")
    )
    private List<UserEntity> usersInvolved;

    @JsonBackReference(value = "users-projects-created")
    @ManyToOne
    @JoinColumn(name = "fk_users_id_created", referencedColumnName = "id")
    private UserEntity userCreated;






//    @JsonIgnore // Skip "issues" field when serializing "Project" object into JSON (avoid infinite recursion in bidirectional relationships)
//    @OneToMany(mappedBy = "project")
//    private List<Issue> issues;

}
