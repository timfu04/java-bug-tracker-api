package com.clementlee.bugtrackerapi.services.impl;

import com.clementlee.bugtrackerapi.dto.IssueCommentDTO;
import com.clementlee.bugtrackerapi.exceptions.IssueCommentNotFoundException;
import com.clementlee.bugtrackerapi.exceptions.IssueNotFoundException;
import com.clementlee.bugtrackerapi.exceptions.ProjectNotFoundException;
import com.clementlee.bugtrackerapi.exceptions.UserNotFoundException;
import com.clementlee.bugtrackerapi.models.Issue;
import com.clementlee.bugtrackerapi.models.IssueComment;
import com.clementlee.bugtrackerapi.models.Project;
import com.clementlee.bugtrackerapi.models.UserEntity;
import com.clementlee.bugtrackerapi.repositories.IssueCommentRepository;
import com.clementlee.bugtrackerapi.repositories.IssueRepository;
import com.clementlee.bugtrackerapi.repositories.ProjectRepository;
import com.clementlee.bugtrackerapi.repositories.UserRepository;
import com.clementlee.bugtrackerapi.services.IssueCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class IssueCommentServiceImpl implements IssueCommentService {

    private final IssueCommentRepository issueCommentRepository;
    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;
    private final IssueRepository issueRepository;

    @Override
    public IssueCommentDTO createIssueCommentByUserIdByProjectIdByIssueId(int userId, int projectId, int issueId, IssueCommentDTO issueCommentDTO) {
        UserEntity userEntity = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User could not be found"));
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new ProjectNotFoundException("Project could not be found"));
        Issue issue = issueRepository.findById(issueId).orElseThrow(() -> new IssueNotFoundException("Issue could not be found"));

        if (userEntity.getProjectsInvolved().contains(project)){ // If list of projects involved from user contains the given project
            if (project.getIssues().contains(issue)){
                IssueComment issueComment = new IssueComment();
                issueComment.setCommentText(issueCommentDTO.getCommentText());
                issueComment.setCreatedDate(LocalDateTime.now());
                issueComment.setUser(userEntity);
                issueComment.setIssue(issue);
                IssueComment newIssueComment = issueCommentRepository.save(issueComment);

                userEntity.getIssueComments().add(newIssueComment);
                userRepository.save(userEntity);

                issue.getIssueComments().add(newIssueComment);
                issueRepository.save(issue);

                return mapToIssueCommentDto(newIssueComment);

            } else {
                throw new IssueNotFoundException("Issue could not be found");
            }
        } else {
            throw new ProjectNotFoundException("Project could not be found");
        }
    }

    @Override
    public List<IssueCommentDTO> getAllIssueCommentsByIssueId(int issueId) {
        Issue issue = issueRepository.findById(issueId).orElseThrow(() -> new IssueNotFoundException("Issue could not be found"));
        return issue.getIssueComments().stream().map(issueComment -> mapToIssueCommentDto(issueComment)).collect(Collectors.toList());
    }

    @Override
    public List<IssueCommentDTO> getAllIssueCommentsByUserId(int userId) {
        UserEntity userEntity = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User could not be found"));
        return userEntity.getIssueComments().stream().map(issueComment -> mapToIssueCommentDto(issueComment)).collect(Collectors.toList());
    }

    @Override
    public IssueCommentDTO getIssueCommentByUserIdByProjectIdByIssueIdByIssueCommentId(int userId, int projectId, int issueId, int issueCommentId) {
        UserEntity userEntity = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User could not be found"));
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new ProjectNotFoundException("Project could not be found"));
        Issue issue = issueRepository.findById(issueId).orElseThrow(() -> new IssueNotFoundException("Issue could not be found"));
        IssueComment issueComment = issueCommentRepository.findById(issueCommentId)
                .orElseThrow(() -> new IssueCommentNotFoundException("Issue comment could not be found"));

        if (userEntity.getProjectsInvolved().contains(project)){ // If list of projects involved from user contains the given project
            if (project.getIssues().contains(issue)){
                if (issue.getIssueComments().contains(issueComment)){
                    return mapToIssueCommentDto(issueComment);
                } else {
                    throw new IssueCommentNotFoundException("Issue comment could not be found");
                }
            } else {
                throw new IssueNotFoundException("Issue could not be found");
            }
        } else {
            throw new ProjectNotFoundException("Project could not be found");
        }
    }

    @Override
    public IssueCommentDTO updateIssueCommentByUserIdByProjectIdByIssueIdByIssueCommentId(int userId, int projectId, int issueId,
                                                                                          int issueCommentId, IssueCommentDTO issueCommentDTO) {
        UserEntity userEntity = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User could not be found"));
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new ProjectNotFoundException("Project could not be found"));
        Issue issue = issueRepository.findById(issueId).orElseThrow(() -> new IssueNotFoundException("Issue could not be found"));
        IssueComment issueComment = issueCommentRepository.findById(issueCommentId)
                .orElseThrow(() -> new IssueCommentNotFoundException("Issue comment could not be found"));

        if (userEntity.getProjectsInvolved().contains(project)){ // If list of projects involved from user contains the given project
            if (project.getIssues().contains(issue)){
                if (issue.getIssueComments().contains(issueComment)){
                    issueComment.setCommentText(issueCommentDTO.getCommentText());
                    IssueComment newIssueComment = issueCommentRepository.save(issueComment);
                    return mapToIssueCommentDto(newIssueComment);
                } else {
                    throw new IssueCommentNotFoundException("Issue comment could not be found");
                }
            } else {
                throw new IssueNotFoundException("Issue could not be found");
            }
        } else {
            throw new ProjectNotFoundException("Project could not be found");
        }
    }

    @Override
    public void deleteIssueCommentByUserIdByProjectIdByIssueIdByIssueCommentId(int userId, int projectId, int issueId, int issueCommentId) {
        UserEntity userEntity = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User could not be found"));
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new ProjectNotFoundException("Project could not be found"));
        Issue issue = issueRepository.findById(issueId).orElseThrow(() -> new IssueNotFoundException("Issue could not be found"));
        IssueComment issueComment = issueCommentRepository.findById(issueCommentId)
                .orElseThrow(() -> new IssueCommentNotFoundException("Issue comment could not be found"));

        if (userEntity.getProjectsInvolved().contains(project)){ // If list of projects involved from user contains the given project
            if (project.getIssues().contains(issue)){
                if (issue.getIssueComments().contains(issueComment)){
                    issueCommentRepository.delete(issueComment);
                } else {
                    throw new IssueCommentNotFoundException("Issue comment could not be found");
                }
            } else {
                throw new IssueNotFoundException("Issue could not be found");
            }
        } else {
            throw new ProjectNotFoundException("Project could not be found");
        }
    }

    @Override
    public List<IssueCommentDTO> getAllIssueComments() {
        List<IssueComment> issueComments = issueCommentRepository.findAll();
        return issueComments.stream().map(issueComment -> mapToIssueCommentDto(issueComment)).collect(Collectors.toList());
    }

    @Override
    public IssueCommentDTO getIssueCommentByIssueCommentId(int issueCommentId) {
        IssueComment issueComment = issueCommentRepository.findById(issueCommentId)
                .orElseThrow(() -> new IssueCommentNotFoundException("Issue comment could not be found"));
        return mapToIssueCommentDto(issueComment);
    }

    @Override
    public IssueCommentDTO updateIssueCommentByIssueCommentId(int issueCommentId, IssueCommentDTO issueCommentDTO) {
        IssueComment issueComment = issueCommentRepository.findById(issueCommentId)
                .orElseThrow(() -> new IssueCommentNotFoundException("Issue comment could not be found"));
        issueComment.setCommentText(issueCommentDTO.getCommentText());
        IssueComment newIssueComment = issueCommentRepository.save(issueComment);
        return mapToIssueCommentDto(newIssueComment);
    }

    @Override
    public void deleteIssueCommentByIssueCommentId(int issueCommentId) {
        issueCommentRepository.findById(issueCommentId).orElseThrow(() -> new IssueCommentNotFoundException("Issue comment could not be found"));
        issueCommentRepository.deleteById(issueCommentId);
    }

    @Override
    public IssueCommentDTO updateUpdatedDateByUserIdByProjectIdByIssueIdByIssueCommentId(int userId, int projectId, int issueId, int issueCommentId) {
        UserEntity userEntity = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User could not be found"));
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new ProjectNotFoundException("Project could not be found"));
        Issue issue = issueRepository.findById(issueId).orElseThrow(() -> new IssueNotFoundException("Issue could not be found"));
        IssueComment issueComment = issueCommentRepository.findById(issueCommentId)
                .orElseThrow(() -> new IssueCommentNotFoundException("Issue comment could not be found"));

        if (userEntity.getProjectsInvolved().contains(project)){ // If list of projects involved from user contains the given project
            if (project.getIssues().contains(issue)){
                if (issue.getIssueComments().contains(issueComment)){
                    issueComment.setUpdatedDate(LocalDateTime.now());
                    IssueComment updatedIssueComment = issueCommentRepository.save(issueComment);
                    return mapToIssueCommentDto(updatedIssueComment);
                } else {
                    throw new IssueCommentNotFoundException("Issue comment could not be found");
                }
            } else {
                throw new IssueNotFoundException("Issue could not be found");
            }
        } else {
            throw new ProjectNotFoundException("Project could not be found");
        }
    }

    // Map IssueComment to IssueCommentDTO
    private IssueCommentDTO mapToIssueCommentDto(IssueComment issueComment){
        IssueCommentDTO issueCommentDTO = new IssueCommentDTO();
        issueCommentDTO.setId(issueComment.getId());
        issueCommentDTO.setCommentText(issueComment.getCommentText());
        issueCommentDTO.setCreatedDate(issueComment.getCreatedDate());
        issueCommentDTO.setUpdatedDate(issueComment.getUpdatedDate());
        issueCommentDTO.setUser(issueComment.getUser());
        issueCommentDTO.setIssue(issueComment.getIssue());
        return issueCommentDTO;
    }

}
