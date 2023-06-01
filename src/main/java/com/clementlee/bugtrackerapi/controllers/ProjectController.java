package com.clementlee.bugtrackerapi.controllers;

import com.clementlee.bugtrackerapi.dto.ProjectDTO;
import com.clementlee.bugtrackerapi.services.impl.ProjectServiceImpl;
import com.clementlee.bugtrackerapi.validation.DateValidator;
import com.clementlee.bugtrackerapi.validation.ProjectAllFieldsValidationGroup;
import com.clementlee.bugtrackerapi.validation.ProjectDateValidationGroup;
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
    public ResponseEntity<List<ProjectDTO>> getAllProjectsByUserId(@PathVariable int userId){
        return new ResponseEntity<>(projectServiceImpl.getAllProjectsByUserId(userId), HttpStatus.OK);
    }

    @GetMapping("user/{userId}/project/{projectId}")
    public ResponseEntity<ProjectDTO> getProjectByIdByUserId(@PathVariable int projectId, @PathVariable int userId){
        return new ResponseEntity<>(projectServiceImpl.getProjectByUserIdByProjectId(projectId, userId), HttpStatus.OK);
    }

    @GetMapping("project")
    public ResponseEntity<List<ProjectDTO>> getAllProjects(){
        return new ResponseEntity<>(projectServiceImpl.getAllProjects(), HttpStatus.OK);
    }

    @PutMapping("project/{projectId}/update-full")
    public ResponseEntity<ProjectDTO> updateProjectFullByProjectId(@PathVariable(value = "projectId") int projectId,
                                                                   @Validated(ProjectAllFieldsValidationGroup.class) @RequestBody ProjectDTO projectDTO){
        return new ResponseEntity<>(projectServiceImpl.updateProjectFullByProjectId(projectId, projectDTO), HttpStatus.OK);
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





//    @DeleteMapping("role/{roleId}/delete")
//    public ResponseEntity<String> deleteRole(@PathVariable(value = "roleId") int roleId){
//        roleServiceImpl.deleteRole(roleId);
//        return ResponseEntity.ok("Role deleted successfully");
//    }

//    @PostMapping("user/create")
//    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userDTO){
//        return new ResponseEntity<>(userServiceImpl.createUser(userDTO), HttpStatus.CREATED);
//    }




//    @GetMapping("user/{user_id}/project")
//    public ResponseEntity<List<ProjectDTO>> getAllProjects(@PathVariable(value = "user_id") int user_id){
//        return new ResponseEntity<>(, HttpStatus.OK);
//    }
//







//    @GetMapping("project/{id}")
//    public ResponseEntity<String> getProjectById(){
//        return new ResponseEntity<>("hello 3", HttpStatus.OK);
//    }
//
//    @PutMapping("project/{id}/update")
//    public ResponseEntity<String> updateProject(){
//        return new ResponseEntity<>("hello 4", HttpStatus.OK);
//    }
//

}
