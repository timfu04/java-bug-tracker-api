package com.clementlee.bugtrackerapi.controllers;

import com.clementlee.bugtrackerapi.dto.ProjectDTO;
import com.clementlee.bugtrackerapi.services.impl.ProjectServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/v1/")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectServiceImpl projectServiceImpl;

    @PostMapping("project/create")
    public ResponseEntity<ProjectDTO> createProject(@Valid @RequestBody ProjectDTO projectDTO){
        return new ResponseEntity<>(projectDTO, HttpStatus.CREATED);
    }








    @GetMapping("project")
    public ResponseEntity<List<String>> getAllProjects(){
        return new ResponseEntity<>(Arrays.asList("hello 2"), HttpStatus.OK);
    }

    @GetMapping("project/{id}")
    public ResponseEntity<String> getProjectById(){
        return new ResponseEntity<>("hello 3", HttpStatus.OK);
    }

    @PutMapping("project/{id}/update")
    public ResponseEntity<String> updateProject(){
        return new ResponseEntity<>("hello 4", HttpStatus.OK);
    }

    @DeleteMapping("project/{id}/delete")
    public ResponseEntity<String> deleteProject(){
        return ResponseEntity.ok("project deleted successfully");
    }

}
