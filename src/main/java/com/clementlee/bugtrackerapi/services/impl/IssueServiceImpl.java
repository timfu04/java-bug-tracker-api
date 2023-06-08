package com.clementlee.bugtrackerapi.services.impl;

import com.clementlee.bugtrackerapi.dto.IssueDTO;
import com.clementlee.bugtrackerapi.exceptions.*;
import com.clementlee.bugtrackerapi.models.*;
import com.clementlee.bugtrackerapi.repositories.*;
import com.clementlee.bugtrackerapi.services.IssueService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class IssueServiceImpl implements IssueService {

    private final IssueRepository issueRepository;
    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;
    private final StatusRepository statusRepository;
    private final SeverityRepository severityRepository;
    private final PriorityRepository priorityRepository;

    @Override
    public IssueDTO createIssueByUserIdByProjectId(int userId, int projectId, IssueDTO issueDTO) {
        UserEntity userEntity = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User could not be found"));
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new ProjectNotFoundException("Project could not be found"));

        String defaultStatusName = "OPEN";
        Status status = statusRepository.findByName(defaultStatusName.toUpperCase())
                .orElseThrow(() -> new StatusNotFoundException("Status could not be found"));

        Severity severity = severityRepository.findByName(issueDTO.getSeverity().getName().toUpperCase())
                .orElseThrow(() -> new SeverityNotFoundException("Severity could not be found"));

        Priority priority = priorityRepository.findByName(issueDTO.getPriority().getName().toUpperCase())
                .orElseThrow(() -> new PriorityNotFoundException("Priority could not be found"));

        Issue issue = new Issue();
        issue.setTitle(issueDTO.getTitle());
        issue.setDescription(issueDTO.getDescription());
        issue.setCreatedDate(LocalDateTime.now());
        issue.setProject(project);
        issue.setStatus(status);
        issue.setSeverity(severity);
        issue.setPriority(priority);
        issue.setUserReported(userEntity);
        issue.setUsersAssigned(Arrays.asList(userEntity));
        Issue newIssue = issueRepository.save(issue);

        project.getIssues().add(newIssue);
        projectRepository.save(project);

        status.getIssues().add(newIssue);
        statusRepository.save(status);

        severity.getIssues().add(newIssue);
        severityRepository.save(severity);

        priority.getIssues().add(newIssue);
        priorityRepository.save(priority);

        userEntity.getIssuesReported().add(newIssue);
        userEntity.getIssuesAssigned().add(newIssue);
        userRepository.save(userEntity);

        return mapToIssueDto(newIssue);
    }

    @Override
    public List<IssueDTO> getAllIssuesByProjectId(int projectId) {
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new ProjectNotFoundException("Project could not be found"));
        return project.getIssues().stream().map(issue -> mapToIssueDto(issue)).collect(Collectors.toList());
    }

    @Override
    public List<IssueDTO> getAllIssuesAssignedByUserId(int userId) {
        List<Issue> issues = issueRepository.findByUsersAssignedId(userId);
        if (issues.isEmpty()){
            throw new IssueNotFoundException("Issue could not be found");
        }
        return issues.stream().map(issue -> mapToIssueDto(issue)).collect(Collectors.toList());
    }

    @Override
    public IssueDTO getIssueByUserIdByProjectIdByIssueId(int userId, int projectId, int issueId) {
        UserEntity userEntity = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User could not be found"));
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new ProjectNotFoundException("Project could not be found"));
        Issue issue = issueRepository.findById(issueId).orElseThrow(() -> new IssueNotFoundException("Issue could not be found"));

        if (userEntity.getProjectsInvolved().contains(project)){ // If list of projects involved from user contains the given project
            if (project.getIssues().contains(issue)){
                return mapToIssueDto(issue);
            } else {
                throw new IssueNotFoundException("Issue could not be found");
            }
        } else {
            throw new ProjectNotFoundException("Project could not be found");
        }
    }

    @Override
    public IssueDTO updateIssuePartialByUserIdByProjectIdByIssueId(int userId, int projectId, int issueId, IssueDTO issueDTO) {
        UserEntity userEntity = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User could not be found"));
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new ProjectNotFoundException("Project could not be found"));
        Issue issue = issueRepository.findById(issueId).orElseThrow(() -> new IssueNotFoundException("Issue could not be found"));

        if (userEntity.getProjectsInvolved().contains(project)){ // If list of projects involved from user contains the given project
            if (project.getIssues().contains(issue)){

                if (StringUtils.hasText(issueDTO.getTitle())){
                    issue.setTitle(issueDTO.getTitle());
                }
                if (StringUtils.hasText(issueDTO.getDescription())){
                    issue.setDescription(issueDTO.getDescription());
                }
                if (issueDTO.getStatus() != null){
                    Status status = statusRepository.findByName(issueDTO.getStatus().getName().toUpperCase())
                            .orElseThrow(() -> new StatusNotFoundException("Status could not be found"));
                    issue.setStatus(status);
                }
                if (issueDTO.getSeverity() != null){
                    Severity severity = severityRepository.findByName(issueDTO.getSeverity().getName().toUpperCase())
                            .orElseThrow(() -> new SeverityNotFoundException("Severity could not be found"));
                    issue.setSeverity(severity);
                }
                if (issueDTO.getPriority() != null){
                    Priority priority = priorityRepository.findByName(issueDTO.getPriority().getName().toUpperCase())
                            .orElseThrow(() -> new PriorityNotFoundException("Priority could not be found"));
                    issue.setPriority(priority);
                }
                Issue newIssue = issueRepository.save(issue);
                return mapToIssueDto(newIssue);

            } else {
                throw new IssueNotFoundException("Issue could not be found");
            }
        } else {
            throw new ProjectNotFoundException("Project could not be found");
        }
    }

    @Override
    public void deleteIssueByUserIdByProjectIdByIssueId(int userId, int projectId, int issueId) {
        UserEntity userEntity = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User could not be found"));
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new ProjectNotFoundException("Project could not be found"));
        Issue issue = issueRepository.findById(issueId).orElseThrow(() -> new IssueNotFoundException("Issue could not be found"));

        if (userEntity.getProjectsInvolved().contains(project)){ // If list of projects involved from user contains the given project
            if (project.getIssues().contains(issue)){
                issueRepository.delete(issue);
            } else {
                throw new IssueNotFoundException("Issue could not be found");
            }
        } else {
            throw new ProjectNotFoundException("Project could not be found");
        }
    }

    @Override
    public List<IssueDTO> getAllIssues() {
        List<Issue> issues = issueRepository.findAll();
        // Convert list of Issue to list of IssueDTO
        return issues.stream().map(issue -> mapToIssueDto(issue)).collect(Collectors.toList());
    }

    @Override
    public IssueDTO getIssueByIssueId(int issueId) {
        Issue issue = issueRepository.findById(issueId).orElseThrow(() -> new IssueNotFoundException("Issue could not be found"));
        return mapToIssueDto(issue);
    }

    @Override
    public IssueDTO updateIssuePartialByIssueId(int issueId, IssueDTO issueDTO) {
        Issue issue = issueRepository.findById(issueId).orElseThrow(() -> new IssueNotFoundException("Issue could not be found"));
        if (StringUtils.hasText(issueDTO.getTitle())){
            issue.setTitle(issueDTO.getTitle());
        }
        if (StringUtils.hasText(issueDTO.getDescription())){
            issue.setDescription(issueDTO.getDescription());
        }
        if (issueDTO.getStatus() != null){
            Status status = statusRepository.findByName(issueDTO.getStatus().getName().toUpperCase())
                    .orElseThrow(() -> new StatusNotFoundException("Status could not be found"));
            issue.setStatus(status);
        }
        if (issueDTO.getSeverity() != null){
            Severity severity = severityRepository.findByName(issueDTO.getSeverity().getName().toUpperCase())
                .orElseThrow(() -> new SeverityNotFoundException("Severity could not be found"));
            issue.setSeverity(severity);
        }
        if (issueDTO.getPriority() != null){
            Priority priority = priorityRepository.findByName(issueDTO.getPriority().getName().toUpperCase())
                    .orElseThrow(() -> new PriorityNotFoundException("Priority could not be found"));
            issue.setPriority(priority);
        }
        Issue newIssue = issueRepository.save(issue);
        return mapToIssueDto(newIssue);
    }

    @Override
    public void deleteIssueByIssueId(int issueId) {
        issueRepository.findById(issueId).orElseThrow(() -> new IssueNotFoundException("Issue could not be found"));
        issueRepository.deleteById(issueId);
    }

    @Override
    public IssueDTO updateUpdatedDateByUserIdByProjectIdByIssueId(int userId, int projectId, int issueId) {
        UserEntity userEntity = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User could not be found"));
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new ProjectNotFoundException("Project could not be found"));
        Issue issue = issueRepository.findById(issueId).orElseThrow(() -> new IssueNotFoundException("Issue could not be found"));

        if (userEntity.getProjectsInvolved().contains(project)){ // If list of projects involved from user contains the given project
            if (project.getIssues().contains(issue)){
                issue.setUpdatedDate(LocalDateTime.now());
                Issue updatedIssue = issueRepository.save(issue);
                return mapToIssueDto(updatedIssue);
            } else {
                throw new IssueNotFoundException("Issue could not be found");
            }
        } else {
            throw new ProjectNotFoundException("Project could not be found");
        }
    }

    @Override
    public IssueDTO updateResolvedDateByUserIdByProjectIdByIssueId(int userId, int projectId, int issueId) {
        UserEntity userEntity = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User could not be found"));
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new ProjectNotFoundException("Project could not be found"));
        Issue issue = issueRepository.findById(issueId).orElseThrow(() -> new IssueNotFoundException("Issue could not be found"));

        if (userEntity.getProjectsInvolved().contains(project)){ // If list of projects involved from user contains the given project
            if (project.getIssues().contains(issue)){
                issue.setResolvedDate(LocalDateTime.now());
                Issue updatedIssue = issueRepository.save(issue);
                return mapToIssueDto(updatedIssue);
            } else {
                throw new IssueNotFoundException("Issue could not be found");
            }
        } else {
            throw new ProjectNotFoundException("Project could not be found");
        }
    }

    @Override
    public IssueDTO updateClosedDateByUserIdByProjectIdByIssueId(int userId, int projectId, int issueId) {
        UserEntity userEntity = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User could not be found"));
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new ProjectNotFoundException("Project could not be found"));
        Issue issue = issueRepository.findById(issueId).orElseThrow(() -> new IssueNotFoundException("Issue could not be found"));

        if (userEntity.getProjectsInvolved().contains(project)){ // If list of projects involved from user contains the given project
            if (project.getIssues().contains(issue)){
                issue.setClosedDate(LocalDateTime.now());
                Issue updatedIssue = issueRepository.save(issue);
                return mapToIssueDto(updatedIssue);
            } else {
                throw new IssueNotFoundException("Issue could not be found");
            }
        } else {
            throw new ProjectNotFoundException("Project could not be found");
        }
    }


    @Override
    public IssueDTO updateStatusByUserIdByProjectIdByIssueIdByStatusName(int userId, int projectId, int issueId, String statusName) {
        UserEntity userEntity = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User could not be found"));
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new ProjectNotFoundException("Project could not be found"));
        Issue issue = issueRepository.findById(issueId).orElseThrow(() -> new IssueNotFoundException("Issue could not be found"));
        Status status = statusRepository.findByName(statusName.toUpperCase())
                .orElseThrow(() -> new StatusNotFoundException("Status could not be found"));

        if (userEntity.getProjectsInvolved().contains(project)){ // If list of projects involved from user contains the given project
            if (project.getIssues().contains(issue)){
                issue.setStatus(status);
                Issue updatedIssue = issueRepository.save(issue);
                return mapToIssueDto(updatedIssue);
            } else {
                throw new IssueNotFoundException("Issue could not be found");
            }
        } else {
            throw new ProjectNotFoundException("Project could not be found");
        }
    }

    @Override
    public IssueDTO updateSeverityByUserIdByProjectIdByIssueIdBySeverityName(int userId, int projectId, int issueId, String severityName) {
        UserEntity userEntity = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User could not be found"));
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new ProjectNotFoundException("Project could not be found"));
        Issue issue = issueRepository.findById(issueId).orElseThrow(() -> new IssueNotFoundException("Issue could not be found"));
        Severity severity = severityRepository.findByName(severityName.toUpperCase())
                .orElseThrow(() -> new SeverityNotFoundException("Severity could not be found"));

        if (userEntity.getProjectsInvolved().contains(project)){ // If list of projects involved from user contains the given project
            if (project.getIssues().contains(issue)){
                issue.setSeverity(severity);
                Issue updatedIssue = issueRepository.save(issue);
                return mapToIssueDto(updatedIssue);
            } else {
                throw new IssueNotFoundException("Issue could not be found");
            }
        } else {
            throw new ProjectNotFoundException("Project could not be found");
        }
    }

    @Override
    public IssueDTO updatePriorityByUserIdByProjectIdByIssueIdByPriorityName(int userId, int projectId, int issueId, String priorityName) {
        UserEntity userEntity = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User could not be found"));
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new ProjectNotFoundException("Project could not be found"));
        Issue issue = issueRepository.findById(issueId).orElseThrow(() -> new IssueNotFoundException("Issue could not be found"));
        Priority priority = priorityRepository.findByName(priorityName.toUpperCase())
                .orElseThrow(() -> new PriorityNotFoundException("Priority could not be found"));

        if (userEntity.getProjectsInvolved().contains(project)){ // If list of projects involved from user contains the given project
            if (project.getIssues().contains(issue)){
                issue.setPriority(priority);
                Issue updatedIssue = issueRepository.save(issue);
                return mapToIssueDto(updatedIssue);
            } else {
                throw new IssueNotFoundException("Issue could not be found");
            }
        } else {
            throw new ProjectNotFoundException("Project could not be found");
        }
    }

    // Map Issue to IssueDTO
    private IssueDTO mapToIssueDto(Issue issue){
        IssueDTO issueDTO = new IssueDTO();
        issueDTO.setId(issue.getId());
        issueDTO.setTitle(issue.getTitle());
        issueDTO.setDescription(issue.getDescription());
        issueDTO.setCreatedDate(issue.getCreatedDate());
        issueDTO.setUpdatedDate(issue.getUpdatedDate());
        issueDTO.setResolvedDate(issue.getResolvedDate());
        issueDTO.setClosedDate(issue.getClosedDate());
        issueDTO.setProject(issue.getProject());
        issueDTO.setStatus(issue.getStatus());
        issueDTO.setSeverity(issue.getSeverity());
        issueDTO.setPriority(issue.getPriority());
        issueDTO.setUserReported(issue.getUserReported());
        issueDTO.setUsersAssigned(issue.getUsersAssigned());
        return issueDTO;
    }

}