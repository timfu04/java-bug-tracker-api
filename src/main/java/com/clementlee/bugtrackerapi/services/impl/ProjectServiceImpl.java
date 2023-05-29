package com.clementlee.bugtrackerapi.services.impl;

import com.clementlee.bugtrackerapi.dto.ProjectDTO;
import com.clementlee.bugtrackerapi.repositories.ProjectRepository;
import com.clementlee.bugtrackerapi.services.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;

    @Override
    public ProjectDTO createProject(ProjectDTO projectDTO) {
        return null;

    }

}
