package com.clementlee.bugtrackerapi.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

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

    @Column(unique = true, nullable = false)
    private LocalDate startDate;

    @Column(unique = true, nullable = false)
    private LocalDate endDate;

    @ManyToOne
    @JoinColumn(name = "fk_users_id", referencedColumnName = "id")
    private UserEntity userEntity;

}
