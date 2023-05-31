package com.clementlee.bugtrackerapi.services.impl;

import com.clementlee.bugtrackerapi.dto.ProjectDTO;
import com.clementlee.bugtrackerapi.dto.UserDTO;
import com.clementlee.bugtrackerapi.exceptions.ProjectNotFoundException;
import com.clementlee.bugtrackerapi.exceptions.UserNotFoundException;
import com.clementlee.bugtrackerapi.models.Project;
import com.clementlee.bugtrackerapi.models.UserEntity;
import com.clementlee.bugtrackerapi.repositories.ProjectRepository;
import com.clementlee.bugtrackerapi.repositories.UserRepository;
import com.clementlee.bugtrackerapi.services.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    @Override
    public ProjectDTO createProject(ProjectDTO projectDTO, int userId) {
        UserEntity userEntity = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User could not be found"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/uuuu");
        Project project = new Project();
        project.setName(projectDTO.getName());
        project.setDescription((projectDTO.getDescription()));
        project.setStartDate(LocalDate.parse(projectDTO.getStartDate(), formatter)); // convert String to LocalDate
        project.setEndDate(LocalDate.parse(projectDTO.getEndDate(), formatter)); // convert String to LocalDate
        project.setUserEntity(userEntity);
        Project newProject = projectRepository.save(project);
        userEntity.getProjects().add(newProject);
        userRepository.save(userEntity);
        return mapToProjectDto(newProject);
    }

    @Override
    public List<ProjectDTO> getAllProjectsByUserId(int userId) {
        List<Project> projects = projectRepository.findByUserEntityId(userId);
        return projects.stream().map(project -> mapToProjectDto(project)).collect(Collectors.toList());
    }

    @Override
    public ProjectDTO getProjectByIdByUserId(int projectId, int userId) {
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new ProjectNotFoundException("Project could not be found"));
        UserEntity userEntity = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User could not be found"));
        if (project.getUserEntity().getId() != userEntity.getId()){
            throw new ProjectNotFoundException("Project could not be found");
        }
        return mapToProjectDto(project);
    }


    @Override
    public List<ProjectDTO> getAllProjects() {
        List<Project> projects = projectRepository.findAll();
        return projects.stream().map(project -> mapToProjectDto(project)).collect(Collectors.toList());
    }

    @Override
    public ProjectDTO updateProject(int projectId) {
        return null;
    }

    @Override
    public void deleteProjectByProjectId(int projectId) {
        projectRepository.findById(projectId).orElseThrow(() -> new ProjectNotFoundException("Project could not be found"));
        projectRepository.deleteById(projectId);
    }


    // Map Project to ProjectDTO
    private ProjectDTO mapToProjectDto(Project project){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/uuuu");
        ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.setId(project.getId());
        projectDTO.setName(project.getName());
        projectDTO.setDescription(project.getDescription());
        projectDTO.setStartDate(project.getStartDate().format(formatter)); // convert LocalDate into String
        projectDTO.setEndDate(project.getEndDate().format(formatter)); // convert LocalDate into String
        projectDTO.setUserEntity(project.getUserEntity());
        return projectDTO;
    }






}
