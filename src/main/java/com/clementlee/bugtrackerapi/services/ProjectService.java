package com.clementlee.bugtrackerapi.services;

import com.clementlee.bugtrackerapi.dto.ProjectDTO;

public interface ProjectService {

    ProjectDTO createProject(ProjectDTO projectDTO);
}
