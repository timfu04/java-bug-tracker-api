package com.clementlee.bugtrackerapi.services;

import com.clementlee.bugtrackerapi.dto.ProjectDTO;
import com.clementlee.bugtrackerapi.models.Project;

public interface ProjectService {

    ProjectDTO createProject(ProjectDTO projectDTO);
}
