//package com.clementlee.bugtrackerapi.services.impl;
//
//import com.clementlee.bugtrackerapi.dto.IssueDTO;
//import com.clementlee.bugtrackerapi.exceptions.*;
//import com.clementlee.bugtrackerapi.models.*;
//import com.clementlee.bugtrackerapi.repositories.*;
//import com.clementlee.bugtrackerapi.services.IssueService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import org.springframework.util.StringUtils;
//
//import java.time.LocalDateTime;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//@RequiredArgsConstructor
//public class IssueServiceImpl implements IssueService {
//
//    private final IssueRepository issueRepository;
//    private final UserRepository userRepository;
//    private final ProjectRepository projectRepository;
//    private final StatusRepository statusRepository;
//    private final SeverityRepository severityRepository;
//    private final PriorityRepository priorityRepository;
//
//    @Override
//    public IssueDTO createIssueByUserIdByProjectId(int userId, int projectId, IssueDTO issueDTO) {
//
//        UserEntity userEntity = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User could not be found"));
//        Project project = projectRepository.findById(projectId).orElseThrow(() -> new ProjectNotFoundException("Project could not be found"));
//        if (userEntity.getId() != project.getUserCreated().getId()){
//            throw new ProjectNotFoundException("Project could not be found");
//        }
//
//        String defaultStatusName = "OPEN";
//        Status status = statusRepository.findByName(defaultStatusName.toUpperCase())
//                .orElseThrow(() -> new StatusNotFoundException("Status could not be found"));
//
//        Severity severity = severityRepository.findByName(issueDTO.getSeverity().getName())
//                .orElseThrow(() -> new SeverityNotFoundException("Severity could not be found"));
//
//        Priority priority = priorityRepository.findByName(issueDTO.getPriority().getName())
//                .orElseThrow(() -> new PriorityNotFoundException("Priority could not be found"));
//
//        Issue issue = new Issue();
//        issue.setTitle(issueDTO.getTitle());
//        issue.setDescription(issueDTO.getDescription());
//        issue.setProject(project);
//        issue.setStatus(status);
//        issue.setSeverity(severity);
//        issue.setPriority(priority);
//        issue.setCreatedDate(LocalDateTime.now());
//        issue.setUserReported(userEntity);
//        Issue newIssue = issueRepository.save(issue);
//
//        project.getIssues().add(newIssue);
//        projectRepository.save(project);
//
//        status.getIssues().add(newIssue);
//        statusRepository.save(status);
//
//        severity.getIssues().add(newIssue);
//        severityRepository.save(severity);
//
//        priority.getIssues().add(newIssue);
//        priorityRepository.save(priority);
//
//        userEntity.getReportedIssues().add(newIssue);
//        userRepository.save(userEntity);
//
//        return mapToIssueDto(newIssue);
//    }
//
//    @Override
//    public List<IssueDTO> getAllIssuesByProjectId(int projectId) {
//        return null;
//    }
//
//
//
//    @Override
//    public IssueDTO updateUpdatedDate(int issueId) {
//        Issue issue = issueRepository.findById(issueId).orElseThrow(() -> new IssueNotFoundException("Issue could not be found"));
//        issue.setUpdatedDate(LocalDateTime.now());
//        Issue updatedIssue = issueRepository.save(issue);
//        return mapToIssueDto(updatedIssue);
//    }
//
//    @Override
//    public IssueDTO updateResolvedDate(int issueId) {
//        Issue issue = issueRepository.findById(issueId).orElseThrow(() -> new IssueNotFoundException("Issue could not be found"));
//        issue.setResolvedDate(LocalDateTime.now());
//        Issue updatedIssue = issueRepository.save(issue);
//        return mapToIssueDto(updatedIssue);
//    }
//
//    @Override
//    public IssueDTO updateClosedDate(int issueId) {
//        Issue issue = issueRepository.findById(issueId).orElseThrow(() -> new IssueNotFoundException("Issue could not be found"));
//        issue.setClosedDate(LocalDateTime.now());
//        Issue updatedIssue = issueRepository.save(issue);
//        return mapToIssueDto(updatedIssue);
//    }
//
//
//
//
//
//
//
//
//
//
//
//
//    @Override
//    public List<IssueDTO> getAllIssues() {
//        List<Issue> issues = issueRepository.findAll();
//        // Convert list of Issue to list of IssueDTO
//        return issues.stream().map(issue -> mapToIssueDto(issue)).collect(Collectors.toList());
//    }
//
//    @Override
//    public IssueDTO updateIssuePartialByIssueId(int issueId, IssueDTO issueDTO) {
//        Issue issue = issueRepository.findById(issueId).orElseThrow(() -> new IssueNotFoundException("Issue could not be found"));
//        if (StringUtils.hasText(issueDTO.getTitle())){
//            issue.setTitle(issueDTO.getTitle());
//        }
//        if (StringUtils.hasText(issueDTO.getDescription())){
//            issue.setDescription(issueDTO.getDescription());
//        }
//        if (issueDTO.getStatus() != null){
//            Status status = statusRepository.findByName(issueDTO.getStatus().getName().toUpperCase())
//                    .orElseThrow(() -> new StatusNotFoundException("Status could not be found"));
//            issue.setStatus(status);
//        }
//        if (issueDTO.getSeverity() != null){
//            Severity severity = severityRepository.findByName(issueDTO.getSeverity().getName())
//                .orElseThrow(() -> new SeverityNotFoundException("Severity could not be found"));
//            issue.setSeverity(severity);
//        }
//        if (issueDTO.getPriority() != null){
//            Priority priority = priorityRepository.findByName(issueDTO.getPriority().getName())
//                    .orElseThrow(() -> new PriorityNotFoundException("Priority could not be found"));
//            issue.setPriority(priority);
//        }
//        Issue newIssue = issueRepository.save(issue);
//        return mapToIssueDto(newIssue);
//    }
//
//    @Override
//    public void deleteIssueByIssueId(int issueId) {
//        issueRepository.findById(issueId).orElseThrow(() -> new IssueNotFoundException("Issue could not be found"));
//        issueRepository.deleteById(issueId);
//    }
//
//    // Map Issue to IssueDTO
//    private IssueDTO mapToIssueDto(Issue issue){
//        IssueDTO issueDTO = new IssueDTO();
//        issueDTO.setId(issue.getId());
//        issueDTO.setTitle(issue.getTitle());
//        issueDTO.setDescription(issue.getDescription());
//        issueDTO.setProject(issue.getProject());
//        issueDTO.setStatus(issue.getStatus());
//        issueDTO.setSeverity(issue.getSeverity());
//        issueDTO.setPriority(issue.getPriority());
//        issueDTO.setUserReported(issue.getUserReported());
//        issueDTO.setUsersAssigned(issue.getUsersAssigned());
//        return issueDTO;
//    }
//
//}