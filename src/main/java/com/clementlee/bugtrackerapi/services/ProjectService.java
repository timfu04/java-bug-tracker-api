package com.clementlee.bugtrackerapi.services;

import com.clementlee.bugtrackerapi.dto.ProjectDTO;

import java.util.List;

public interface ProjectService {

    ProjectDTO createProject(ProjectDTO projectDTO, int userId);

    List<ProjectDTO> getAllProjectsByUserId(int userId);

    ProjectDTO getProjectByIdByUserId(int projectId, int userId);

    List<ProjectDTO> getAllProjects();

    ProjectDTO updateProject(int projectId);

    void deleteProjectByProjectId(int projectId);

}
