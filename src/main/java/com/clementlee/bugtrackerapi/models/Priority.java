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
@Table(name = "priorities")
@Entity
public class Priority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true, nullable = false)
    private String name;

    @JsonIgnore // Skip "issues" field when serializing "Priority" object into JSON (avoid infinite recursion in bidirectional relationships)
    @OneToMany(mappedBy = "priority")
    private List<Issue> issues;

}
