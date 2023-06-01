package com.clementlee.bugtrackerapi.repositories;

import com.clementlee.bugtrackerapi.models.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Integer> {

    List<Project> findByUserId(int userId); // find projects by user id

}