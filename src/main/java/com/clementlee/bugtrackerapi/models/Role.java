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

    @JsonIgnore // Skip "users" field when serializing "Role" object into JSON (avoid infinite recursion in bidirectional relationships)
    @OneToMany(mappedBy = "role")
    private List<UserEntity> users;

}
