package com.clementlee.bugtrackerapi.controllers;

import com.clementlee.bugtrackerapi.dto.ProjectDTO;
import com.clementlee.bugtrackerapi.services.impl.ProjectServiceImpl;
import com.clementlee.bugtrackerapi.validation.markerinterfaces.ProjectAllFieldsValidationGroup;
import com.clementlee.bugtrackerapi.validation.markerinterfaces.ProjectDateValidationGroup;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectServiceImpl projectServiceImpl;

    @PostMapping("user/{userId}/project/create")
    public ResponseEntity<ProjectDTO> createProjectByUserId(@PathVariable(value = "userId") int userId,
                                                            @Validated(ProjectAllFieldsValidationGroup.class) @RequestBody ProjectDTO projectDTO){
        return new ResponseEntity<>(projectServiceImpl.createProjectByUserId(userId, projectDTO), HttpStatus.CREATED);
    }

    @GetMapping("user/{userId}/project")
    public ResponseEntity<List<ProjectDTO>> getAllProjectsCreatedByUserId(@PathVariable(value = "userId") int userId){
        return new ResponseEntity<>(projectServiceImpl.getAllProjectsCreatedByUserId(userId), HttpStatus.OK);
    }

    @GetMapping("user/{userId}/project/{projectId}")
    public ResponseEntity<ProjectDTO> getProjectCreatedByUserIdByProjectId(@PathVariable(value = "userId") int userId,
                                                                           @PathVariable(value = "projectId") int projectId){
        return new ResponseEntity<>(projectServiceImpl.getProjectCreatedByUserIdByProjectId(userId, projectId), HttpStatus.OK);
    }

    @PatchMapping("user/{userId}/project/{projectId}/update-partial")
    public ResponseEntity<ProjectDTO> updateProjectPartialByUserIdByProjectId(@PathVariable(value = "userId") int userId,
                                                                              @PathVariable(value = "projectId") int projectId,
                                                                              @Validated(ProjectDateValidationGroup.class) @RequestBody ProjectDTO projectDTO){
        return new ResponseEntity<>(projectServiceImpl.updateProjectPartialByUserIdByProjectId(userId, projectId, projectDTO), HttpStatus.OK);
    }

    @DeleteMapping("user/{userId}/project/{projectId}/delete")
    public ResponseEntity<String> deleteProjectByUserIdByProjectId(@PathVariable(value = "userId") int userId,
                                                                   @PathVariable(value = "projectId") int projectId){
        projectServiceImpl.deleteProjectByUserIdByProjectId(userId, projectId);
        return new ResponseEntity<>("Project deleted successfully", HttpStatus.OK);
    }

    @PutMapping("user/{userCreatorId}/project/{projectId}/add-user/user/{userIdToAdd}")
    public ResponseEntity<ProjectDTO> addUserIntoProjectByUserCreatorIdByProjectIdByUserId(@PathVariable(value = "userCreatorId") int userCreatorId,
                                                                                           @PathVariable(value = "projectId") int projectId,
                                                                                           @PathVariable(value = "userIdToAdd") int userIdToAdd){
        return new ResponseEntity<>(projectServiceImpl
                .addUserIntoProjectByUserCreatorIdByProjectIdByUserId(userCreatorId, projectId, userIdToAdd), HttpStatus.OK);
    }

    @DeleteMapping("user/{userCreatorId}/project/{projectId}/remove-user/user/{userIdToRemove}")
    public ResponseEntity<String> removeUserFromProjectByUserCreatorIdByProjectIdByUserId(@PathVariable(value = "userCreatorId") int userCreatorId,
                                                                                          @PathVariable(value = "projectId") int projectId,
                                                                                          @PathVariable(value = "userIdToRemove") int userIdToRemove){
        projectServiceImpl.removeUserFromProjectByUserCreatorIdByProjectIdByUserId(userCreatorId, projectId, userIdToRemove);
        return new ResponseEntity<>("User removed from project successfully", HttpStatus.OK);
    }

    @GetMapping("project")
    public ResponseEntity<List<ProjectDTO>> getAllProjects(){
        return new ResponseEntity<>(projectServiceImpl.getAllProjects(), HttpStatus.OK);
    }

    @GetMapping("project/{projectId}")
    public ResponseEntity<ProjectDTO> getProjectByProjectId(@PathVariable(value = "projectId") int projectId){
        return new ResponseEntity<>(projectServiceImpl.getProjectByProjectId(projectId), HttpStatus.OK);
    }

    @PatchMapping("project/{projectId}/update-partial")
    public ResponseEntity<ProjectDTO> updateProjectPartialByProjectId(@PathVariable(value = "projectId") int projectId,
                                                                      @Validated(ProjectDateValidationGroup.class) @RequestBody ProjectDTO projectDTO){
        return new ResponseEntity<>(projectServiceImpl.updateProjectPartialByProjectId(projectId, projectDTO), HttpStatus.OK);
    }

    @DeleteMapping("project/{projectId}/delete")
    public ResponseEntity<String> deleteProjectByProjectId(@PathVariable(value = "projectId") int projectId){
        projectServiceImpl.deleteProjectByProjectId(projectId);
        return ResponseEntity.ok("Project deleted successfully");
    }

    @PutMapping("project/{projectId}/add-user/user/{userId}")
    public ResponseEntity<ProjectDTO> addUserIntoProjectByProjectIdByUserId(@PathVariable(value = "projectId") int projectId,
                                                                            @PathVariable(value = "userId") int userId){
        return new ResponseEntity<>(projectServiceImpl.addUserIntoProjectByProjectIdByUserId(projectId, userId), HttpStatus.OK);
    }

    @DeleteMapping("project/{projectId}/remove-user/user/{userId}")
    public ResponseEntity<String> removeUserFromProjectByProjectIdByUserId(@PathVariable(value = "projectId") int projectId,
                                                                           @PathVariable(value = "userId") int userId){
        projectServiceImpl.removeUserFromProjectByProjectIdByUserId(projectId, userId);
        return new ResponseEntity<>("User removed from project successfully", HttpStatus.OK);
    }

}
