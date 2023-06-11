package com.clementlee.bugtrackerapi.services;

import com.clementlee.bugtrackerapi.dto.ProjectDTO;

import java.util.List;

public interface ProjectService {

    ProjectDTO createProjectByUserId(int userId, ProjectDTO projectDTO);
    List<ProjectDTO> getAllProjectsCreatedByUserId(int userId);
    ProjectDTO getProjectCreatedByUserIdByProjectId(int userId, int projectId);
    // Update name or description or start date or end date or all of them
    ProjectDTO updateProjectPartialByUserIdByProjectId(int userId, int projectId, ProjectDTO projectDTO);
    void deleteProjectByUserIdByProjectId(int userId, int projectId);
    ProjectDTO addUserIntoProjectByUserIdByProjectId(int userCreatorId, int projectId, int userIdToAdd);
    void removeUserFromProjectByUserIdByProjectId(int userCreatorId, int projectId, int userIdToRemove);

    List<ProjectDTO> getAllProjects();
    ProjectDTO getProjectByProjectId(int projectId);
    ProjectDTO updateProjectPartialByProjectId(int projectId, ProjectDTO projectDTO);
    void deleteProjectByProjectId(int projectId);

    ProjectDTO addUserIntoProject(int projectId, int userId);
    void removeUserFromProject(int projectId, int userId);

}
