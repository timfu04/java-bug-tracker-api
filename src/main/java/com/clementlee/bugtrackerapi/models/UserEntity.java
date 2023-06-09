package com.clementlee.bugtrackerapi.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
@Entity
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(unique = true, nullable = false)
    private String email;

    @JsonBackReference(value = "roles-users")
    @ManyToOne
    @JoinColumn(name = "fk_roles_id", referencedColumnName = "id", nullable = false)
    private Role role;

    @JsonIgnore // Skip "projectsInvolved" field when serializing "UserEntity" object into JSON (avoid infinite recursion in bidirectional relationships)
    @ManyToMany(mappedBy = "usersInvolved")
    private List<Project> projectsInvolved;

    @JsonIgnore // Skip "projectsCreated" field when serializing "UserEntity" object into JSON (avoid infinite recursion in bidirectional relationships)
    @OneToMany(mappedBy = "userCreated")
    private List<Project> projectsCreated;

    @JsonIgnore // Skip "issuesReported" field when serializing "UserEntity" object into JSON (avoid infinite recursion in bidirectional relationships)
    @OneToMany(mappedBy = "userReported")
    private List<Issue> issuesReported;

    @JsonIgnore // Skip "issuesAssigned" field when serializing "UserEntity" object into JSON (avoid infinite recursion in bidirectional relationships)
    @ManyToMany(mappedBy = "usersAssigned")
    private List<Issue> issuesAssigned;

    @JsonIgnore // Skip "issueComments" field when serializing "UserEntity" object into JSON (avoid infinite recursion in bidirectional relationships)
    @OneToMany(mappedBy = "user")
    private List<IssueComment> issueComments;

}
