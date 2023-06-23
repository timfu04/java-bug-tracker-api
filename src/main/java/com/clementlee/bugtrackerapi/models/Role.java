package com.clementlee.bugtrackerapi.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "roles")
@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true, nullable = false)
    private String name;

    // Exclude "users" field when serializing "Role" object into JSON
    // Avoid infinite recursion in bidirectional relationship
    @JsonIgnore
    @OneToMany(mappedBy = "role")
    private List<UserEntity> users;

}
