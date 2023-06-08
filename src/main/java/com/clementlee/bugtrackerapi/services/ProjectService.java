package com.clementlee.bugtrackerapi.services;

import com.clementlee.bugtrackerapi.dto.ProjectDTO;

import java.util.List;

public interface ProjectService {

    ProjectDTO createProjectByUserId(int userId, ProjectDTO projectDTO);
    List<ProjectDTO> getAllProjectsCreatedByUserId(int userId);
    ProjectDTO getProjectCreatedByUserIdByProjectId(int userId, int projectId);
    ProjectDTO updateProjectFullByUserIdByProjectId(int userId, int projectId, ProjectDTO projectDTO);
    ProjectDTO updateProjectPartialByUserIdByProjectId(int userId, int projectId, ProjectDTO projectDTO);
    void deleteProjectByUserIdByProjectId(int userId, int projectId);
    List<ProjectDTO> getAllProjects();
    ProjectDTO getProjectByProjectId(int projectId);
    ProjectDTO updateProjectFullByProjectId(int projectId, ProjectDTO projectDTO);
    ProjectDTO updateProjectPartialByProjectId(int projectId, ProjectDTO projectDTO);
    void deleteProjectByProjectId(int projectId);
    ProjectDTO addUserIntoProject(int projectId, int userId);
    void removeUserFromProject(int projectId, int userId);

}
