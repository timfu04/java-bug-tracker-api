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

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "fk_roles_id", referencedColumnName = "id")
    private Role role;

    @JsonIgnore // Skip "projects" field when serializing "UserEntity" object into JSON (avoid infinite recursion in bidirectional relationships)
    @OneToMany(mappedBy = "user")
    private List<Project> projects;

}
