package com.clementlee.bugtrackerapi.services.impl;

import com.clementlee.bugtrackerapi.dto.ProjectDTO;
import com.clementlee.bugtrackerapi.exceptions.DuplicateUserInProjectException;
import com.clementlee.bugtrackerapi.exceptions.ProjectNotFoundException;
import com.clementlee.bugtrackerapi.exceptions.UserNotFoundException;
import com.clementlee.bugtrackerapi.models.Project;
import com.clementlee.bugtrackerapi.models.UserEntity;
import com.clementlee.bugtrackerapi.repositories.ProjectRepository;
import com.clementlee.bugtrackerapi.repositories.UserRepository;
import com.clementlee.bugtrackerapi.services.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    @Override
    public ProjectDTO createProjectByUserId(int userId, ProjectDTO projectDTO) {
        UserEntity userEntity = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User could not be found"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/uuuu"); // "uuuu" means year with 4 digits
        Project project = new Project();
        project.setName(projectDTO.getName());
        project.setDescription((projectDTO.getDescription()));
        project.setStartDate(LocalDate.parse(projectDTO.getStartDate(), formatter)); // Convert String to LocalDate
        project.setEndDate(LocalDate.parse(projectDTO.getEndDate(), formatter)); // Convert String to LocalDate
        project.setUsersInvolved(Arrays.asList(userEntity));
        project.setUserCreated(userEntity);
        Project newProject = projectRepository.save(project);
        return mapToProjectDto(newProject);
    }

    @Override
    public List<ProjectDTO> getAllProjectsCreatedByUserId(int userId) {
        UserEntity userEntity = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User could not be found"));
        if (userEntity.getProjectsCreated().isEmpty()){ // If list of projects created from user is empty
            throw new ProjectNotFoundException("No project created by this user");
        }
        // Convert list of UserEntity to list of UserDTO
        return userEntity.getProjectsCreated().stream().map(project -> mapToProjectDto(project)).collect(Collectors.toList());
    }

    @Override
    public ProjectDTO getProjectCreatedByUserIdByProjectId(int userId, int projectId) {
        UserEntity userEntity = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User could not be found"));
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new ProjectNotFoundException("Project could not be found"));
        if (userEntity.getProjectsCreated().contains(project)){ // If user created given project
            return mapToProjectDto(project);
        } else {
            throw new ProjectNotFoundException("Project not created by this user");
        }
    }

    @Override
    public ProjectDTO updateProjectPartialByUserIdByProjectId(int userId, int projectId, ProjectDTO projectDTO) {
        UserEntity userEntity = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User could not be found"));
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new ProjectNotFoundException("Project could not be found"));
        if (userEntity.getProjectsCreated().contains(project)){ // If user created given project
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/uuuu");
            if (StringUtils.hasText(projectDTO.getName())){
                project.setName(projectDTO.getName());
            }
            if (StringUtils.hasText(projectDTO.getDescription())){
                project.setDescription(projectDTO.getDescription());
            }
            if (StringUtils.hasText(projectDTO.getStartDate())){
                project.setStartDate(LocalDate.parse(projectDTO.getStartDate(), formatter));
            }
            if (StringUtils.hasText(projectDTO.getEndDate())){
                project.setEndDate(LocalDate.parse(projectDTO.getEndDate(), formatter));
            }
            Project updatedProject = projectRepository.save(project);
            return mapToProjectDto(updatedProject);
        } else {
            throw new ProjectNotFoundException("Project not created by this user");
        }
    }

    @Override
    public void deleteProjectByUserIdByProjectId(int userId, int projectId) {
        UserEntity userEntity = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User could not be found"));
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new ProjectNotFoundException("Project could not be found"));
        if (userEntity.getProjectsCreated().contains(project)){ // If user created given project
            projectRepository.deleteById(projectId);
        } else {
            throw new ProjectNotFoundException("Project not created by this user");
        }
    }

    @Override
    public ProjectDTO addUserIntoProjectByUserCreatorIdByProjectIdByUserId(int userCreatorId, int projectId, int userIdToAdd) {
        UserEntity userCreator = userRepository.findById(userCreatorId).orElseThrow(() -> new UserNotFoundException("User could not be found"));
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new ProjectNotFoundException("Project could not be found"));
        UserEntity userToAdd = userRepository.findById(userIdToAdd).orElseThrow(() -> new UserNotFoundException("User could not be found"));

        if (userCreator.getProjectsCreated().contains(project)) { // If user created given project
            if (!project.getUsersInvolved().contains(userToAdd)) { // If project does not involve user to add
                project.getUsersInvolved().add(userToAdd);
                Project updatedProject = projectRepository.save(project);
                return mapToProjectDto(updatedProject);
            } else {
                throw new DuplicateUserInProjectException("User already exist in project");
            }
        } else {
            throw new ProjectNotFoundException("Project not created by this user");
        }
    }

    @Override
    public void removeUserFromProjectByUserCreatorIdByProjectIdByUserId(int userCreatorId, int projectId, int userIdToRemove) {
        UserEntity userCreator = userRepository.findById(userCreatorId).orElseThrow(() -> new UserNotFoundException("User could not be found"));
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new ProjectNotFoundException("Project could not be found"));
        UserEntity userToRemove = userRepository.findById(userIdToRemove).orElseThrow(() -> new UserNotFoundException("User could not be found"));

        if (userCreator.getProjectsCreated().contains(project)){ // If user created given project
            if (project.getUsersInvolved().contains(userToRemove)){ // If project involves user to remove
                project.getUsersInvolved().remove(userToRemove);
                projectRepository.save(project);
            } else {
                throw new UserNotFoundException("User not in this project");
            }
        } else {
            throw new ProjectNotFoundException("Project not created by this user");
        }
    }

    @Override
    public List<ProjectDTO> getAllProjects() {
        List<Project> projects = projectRepository.findAll();
        // Convert list of Project to list of ProjectDTO
        return projects.stream().map(project -> mapToProjectDto(project)).collect(Collectors.toList());
    }

    @Override
    public ProjectDTO getProjectByProjectId(int projectId) {
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new ProjectNotFoundException("Project could not be found"));
        return mapToProjectDto(project);
    }

    @Override
    public ProjectDTO updateProjectPartialByProjectId(int projectId, ProjectDTO projectDTO) {
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new ProjectNotFoundException("Project could not be found"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/uuuu");
        if (StringUtils.hasText(projectDTO.getName())){
            project.setName(projectDTO.getName());
        }
        if (StringUtils.hasText(projectDTO.getDescription())){
            project.setDescription(projectDTO.getDescription());
        }
        if (StringUtils.hasText(projectDTO.getStartDate())){
            project.setStartDate(LocalDate.parse(projectDTO.getStartDate(), formatter));
        }
        if (StringUtils.hasText(projectDTO.getEndDate())){
            project.setEndDate(LocalDate.parse(projectDTO.getEndDate(), formatter));
        }
        Project updatedProject = projectRepository.save(project);
        return mapToProjectDto(updatedProject);
    }

    @Override
    public void deleteProjectByProjectId(int projectId) {
        projectRepository.findById(projectId).orElseThrow(() -> new ProjectNotFoundException("Project could not be found"));
        projectRepository.deleteById(projectId);
    }

    @Override
    public ProjectDTO addUserIntoProjectByProjectIdByUserId(int projectId, int userId) {
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new ProjectNotFoundException("Project could not be found"));
        UserEntity userEntity = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User could not be found"));
        if (!project.getUsersInvolved().contains(userEntity)){ // If project does not involve user to add
            project.getUsersInvolved().add(userEntity);
            Project updatedProject = projectRepository.save(project);
            return mapToProjectDto(updatedProject);
        } else {
            throw new DuplicateUserInProjectException("User already exist in this project");
        }
    }

    @Override
    public void removeUserFromProjectByProjectIdByUserId(int projectId, int userId) {
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new ProjectNotFoundException("Project could not be found"));
        UserEntity userEntity = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User could not be found"));
        if (project.getUsersInvolved().contains(userEntity)){ // If project involves user to remove
            project.getUsersInvolved().remove(userEntity);
            projectRepository.save(project);
        } else {
            throw new UserNotFoundException("User not in this project");
        }
    }

    // Map Project to ProjectDTO
    private ProjectDTO mapToProjectDto(Project project){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/uuuu");
        ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.setId(project.getId());
        projectDTO.setName(project.getName());
        projectDTO.setDescription(project.getDescription());
        projectDTO.setStartDate(project.getStartDate().format(formatter)); // Convert LocalDate into String
        projectDTO.setEndDate(project.getEndDate().format(formatter)); // Convert LocalDate into String
        projectDTO.setUsersInvolved(project.getUsersInvolved());
        projectDTO.setUserCreated(project.getUserCreated());
        projectDTO.setIssues(project.getIssues());
        return projectDTO;
    }

}
