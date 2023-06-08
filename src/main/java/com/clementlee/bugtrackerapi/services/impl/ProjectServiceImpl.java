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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/uuuu");

        Project project = new Project();
        project.setName(projectDTO.getName());
        project.setDescription((projectDTO.getDescription()));
        project.setStartDate(LocalDate.parse(projectDTO.getStartDate(), formatter)); // Convert String to LocalDate
        project.setEndDate(LocalDate.parse(projectDTO.getEndDate(), formatter)); // Convert String to LocalDate
        project.setUsersInvolved(Arrays.asList(userEntity));
        project.setUserCreated(userEntity);
        Project newProject = projectRepository.save(project);

        userEntity.getProjectsInvolved().add(newProject);
        userEntity.getProjectsCreated().add(newProject);
        userRepository.save(userEntity);

        return mapToProjectDto(newProject);
    }

    @Override
    public List<ProjectDTO> getAllProjectsCreatedByUserId(int userId) {
        List<Project> projects = projectRepository.findByUserCreatedId(userId);
        if (projects.isEmpty()){
            throw new ProjectNotFoundException("Project could not be found");
        }
        // Convert list of Project to list of ProjectDTO
        return projects.stream().map(project -> mapToProjectDto(project)).collect(Collectors.toList());
    }

    @Override
    public ProjectDTO getProjectCreatedByUserIdByProjectId(int userId, int projectId) {
        UserEntity userEntity = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User could not be found"));
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new ProjectNotFoundException("Project could not be found"));
        if (userEntity.getId() != project.getUserCreated().getId()){
            throw new ProjectNotFoundException("Project could not be found");
        }
        return mapToProjectDto(project);
    }

    @Override
    public ProjectDTO updateProjectFullByUserIdByProjectId(int userId, int projectId, ProjectDTO projectDTO) {
        UserEntity userEntity = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User could not be found"));
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new ProjectNotFoundException("Project could not be found"));
        if (userEntity.getId() != project.getUserCreated().getId()){
            throw new ProjectNotFoundException("Project could not be found");
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/uuuu");
        project.setName(projectDTO.getName());
        project.setDescription(projectDTO.getDescription());
        project.setStartDate(LocalDate.parse(projectDTO.getStartDate(), formatter));
        project.setEndDate(LocalDate.parse(projectDTO.getEndDate(), formatter));
        Project updatedProject = projectRepository.save(project);
        return mapToProjectDto(updatedProject);
    }

    @Override
    public ProjectDTO updateProjectPartialByUserIdByProjectId(int userId, int projectId, ProjectDTO projectDTO) {
        UserEntity userEntity = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User could not be found"));
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new ProjectNotFoundException("Project could not be found"));
        if (userEntity.getId() != project.getUserCreated().getId()){
            throw new ProjectNotFoundException("Project could not be found");
        }
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
    public void deleteProjectByUserIdByProjectId(int userId, int projectId) {
        UserEntity userEntity = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User could not be found"));
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new ProjectNotFoundException("Project could not be found"));
        if (userEntity.getId() != project.getUserCreated().getId()){
            throw new ProjectNotFoundException("Project could not be found");
        }
        projectRepository.deleteById(projectId);
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
    public ProjectDTO updateProjectFullByProjectId(int projectId, ProjectDTO projectDTO) {
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new ProjectNotFoundException("Project could not be found"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/uuuu");
        project.setName(projectDTO.getName());
        project.setDescription(projectDTO.getDescription());
        project.setStartDate(LocalDate.parse(projectDTO.getStartDate(), formatter));
        project.setEndDate(LocalDate.parse(projectDTO.getEndDate(), formatter));
        Project updatedProject = projectRepository.save(project);
        return mapToProjectDto(updatedProject);
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
    public ProjectDTO addUserIntoProject(int projectId, int userId) {
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new ProjectNotFoundException("Project could not be found"));
        UserEntity userEntity = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User could not be found"));
        // Get list of user involved ids from project
        List<Integer> userInvolvedIds = project.getUsersInvolved().stream().map(user -> user.getId()).collect(Collectors.toList());
        if (userInvolvedIds.contains(userEntity.getId())){
            throw new DuplicateUserInProjectException("User already exist in this project");
        }
        project.getUsersInvolved().add(userEntity);
        Project newProject = projectRepository.save(project);
        userEntity.getProjectsInvolved().add(newProject);
        userRepository.save(userEntity);
        return mapToProjectDto(newProject);
    }

    @Override
    public void removeUserFromProject(int projectId, int userId) {
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new ProjectNotFoundException("Project could not be found"));
        UserEntity userEntity = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User could not be found"));
        // Get list of user involved ids from project
        List<Integer> userInvolvedIds = project.getUsersInvolved().stream().map(user -> user.getId()).collect(Collectors.toList());
        if (userInvolvedIds.contains(userEntity.getId())){ // If list of user involved ids contains given user id
            List<UserEntity> usersInvolved = project.getUsersInvolved();
            usersInvolved.remove(userInvolvedIds.indexOf(userEntity.getId())); // Remove user based on user id from the current user involved list
            project.setUsersInvolved(usersInvolved);
            projectRepository.save(project);
        }else {
            throw new UserNotFoundException("User could not be found in this project");
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
        return projectDTO;
    }

}
