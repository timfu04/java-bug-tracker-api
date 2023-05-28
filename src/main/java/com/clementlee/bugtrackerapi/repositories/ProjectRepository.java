package com.clementlee.bugtrackerapi.repositories;

import com.clementlee.bugtrackerapi.models.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Integer> {
}
