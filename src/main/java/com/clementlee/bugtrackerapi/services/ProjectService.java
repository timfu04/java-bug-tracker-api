package com.clementlee.bugtrackerapi.services;

import com.clementlee.bugtrackerapi.dto.ProjectDTO;

import java.util.List;

public interface ProjectService {

    ProjectDTO createProjectByUserId(int userId, ProjectDTO projectDTO);
    List<ProjectDTO> getAllProjectsByUserId(int userId);
    ProjectDTO getProjectByUserIdByProjectId(int userId, int projectId);






    List<ProjectDTO> getAllProjects();
    ProjectDTO updateProjectFullByProjectId(int projectId, ProjectDTO projectDTO);
    ProjectDTO updateProjectPartialByProjectId(int projectId, ProjectDTO projectDTO);
    void deleteProjectByProjectId(int projectId);

}
