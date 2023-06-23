package com.clementlee.bugtrackerapi.services.impl;

import com.clementlee.bugtrackerapi.dto.IssueCommentDTO;
import com.clementlee.bugtrackerapi.exceptions.*;
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
        if (project.getUsersInvolved().contains(userEntity)){ // If project involves given user
            if (issue.getProject().equals(project)){ // If issue belongs to given project
                if (issue.getUsersAssigned().contains(userEntity)){ // If issue assigned to given user
                    IssueComment issueComment = new IssueComment();
                    issueComment.setCommentText(issueCommentDTO.getCommentText());
                    issueComment.setCreatedDate(LocalDateTime.now());
                    issueComment.setUser(userEntity);
                    issueComment.setIssue(issue);
                    IssueComment newIssueComment = issueCommentRepository.save(issueComment);
                    return mapToIssueCommentDto(newIssueComment);
                } else {
                    throw new IssueNotAssignedToUserException("Issue not assigned to this user");
                }
            } else {
                throw new IssueNotInThisProjectException("Issue not in this project");
            }
        } else {
            throw new UserNotInProjectException("User not in this project");
        }
    }

    @Override
    public List<IssueCommentDTO> getAllIssueCommentsByUserIdByProjectIdByIssueId(int userId, int projectId, int issueId) {
        UserEntity userEntity = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User could not be found"));
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new ProjectNotFoundException("Project could not be found"));
        Issue issue = issueRepository.findById(issueId).orElseThrow(() -> new IssueNotFoundException("Issue could not be found"));
        if (project.getUsersInvolved().contains(userEntity)){ // If project involves given user
            if (issue.getProject().equals(project)){ // If issue belongs to given project
                if (issue.getUsersAssigned().contains(userEntity)){ // If issue assigned to given user
                    return issue.getIssueComments().stream().map(issueComment -> mapToIssueCommentDto(issueComment)).collect(Collectors.toList());
                } else {
                    throw new IssueNotAssignedToUserException("Issue not assigned to this user");
                }
            } else {
                throw new IssueNotInThisProjectException("Issue not in this project");
            }
        } else {
            throw new UserNotInProjectException("User not in this project");
        }
    }

    @Override
    public List<IssueCommentDTO> getAllIssueCommentsByUserId(int userId) {
        UserEntity userEntity = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User could not be found"));
        if (userEntity.getIssueComments().isEmpty()){
            throw new IssueCommentNotFoundException("No issue comment created by this user");
        }
        return userEntity.getIssueComments().stream().map(issueComment -> mapToIssueCommentDto(issueComment)).collect(Collectors.toList());
    }

    @Override
    public IssueCommentDTO getIssueCommentByUserIdByProjectIdByIssueIdByIssueCommentId(int userId, int projectId, int issueId, int issueCommentId) {
        UserEntity userEntity = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User could not be found"));
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new ProjectNotFoundException("Project could not be found"));
        Issue issue = issueRepository.findById(issueId).orElseThrow(() -> new IssueNotFoundException("Issue could not be found"));
        IssueComment issueComment = issueCommentRepository.findById(issueCommentId)
                .orElseThrow(() -> new IssueCommentNotFoundException("Issue comment could not be found"));

        if (project.getUsersInvolved().contains(userEntity)){ // If project involves given user
            if (issue.getProject().equals(project)){ // If issue belongs to given project
                if (issue.getUsersAssigned().contains(userEntity)){ // If issue assigned to given user
                    if (issueComment.getIssue().equals(issue)){ // If issue comment belongs to given issue
                        if (issueComment.getUser().equals(userEntity)){ // If issue comment created by given user
                            return mapToIssueCommentDto(issueComment);
                        } else {
                            throw new IssueCommentNotCreatedByThisUserException("Issue comment not created by this user");
                        }
                    } else {
                        throw new IssueCommentNotInThisIssueException("Issue comment not in this issue");
                    }
                } else {
                    throw new IssueNotAssignedToUserException("Issue not assigned to this user");
                }
            } else {
                throw new IssueNotInThisProjectException("Issue not in this project");
            }
        } else {
            throw new UserNotInProjectException("User not in this project");
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

        if (project.getUsersInvolved().contains(userEntity)){ // If project involves given user
            if (issue.getProject().equals(project)){ // If issue belongs to given project
                if (issue.getUsersAssigned().contains(userEntity)){ // If issue assigned to given user
                    if (issueComment.getIssue().equals(issue)){ // If issue comment belongs to given issue
                        if (issueComment.getUser().equals(userEntity)){ // If issue comment created by given user
                            issueComment.setCommentText(issueCommentDTO.getCommentText());
                            IssueComment updatedIssueComment = issueCommentRepository.save(issueComment);
                            return mapToIssueCommentDto(updatedIssueComment);
                        } else {
                            throw new IssueCommentNotCreatedByThisUserException("Issue comment not created by this user");
                        }
                    } else {
                        throw new IssueCommentNotInThisIssueException("Issue comment not in this issue");
                    }
                } else {
                    throw new IssueNotAssignedToUserException("Issue not assigned to this user");
                }
            } else {
                throw new IssueNotInThisProjectException("Issue not in this project");
            }
        } else {
            throw new UserNotInProjectException("User not in this project");
        }
    }

    @Override
    public void deleteIssueCommentByUserIdByProjectIdByIssueIdByIssueCommentId(int userId, int projectId, int issueId, int issueCommentId) {
        UserEntity userEntity = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User could not be found"));
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new ProjectNotFoundException("Project could not be found"));
        Issue issue = issueRepository.findById(issueId).orElseThrow(() -> new IssueNotFoundException("Issue could not be found"));
        IssueComment issueComment = issueCommentRepository.findById(issueCommentId)
                .orElseThrow(() -> new IssueCommentNotFoundException("Issue comment could not be found"));

        if (project.getUsersInvolved().contains(userEntity)){ // If project involves given user
            if (issue.getProject().equals(project)){ // If issue belongs to given project
                if (issue.getUsersAssigned().contains(userEntity)){ // If issue assigned to given user
                    if (issueComment.getIssue().equals(issue)){ // If issue comment belongs to given issue
                        if (issueComment.getUser().equals(userEntity)){ // If issue comment created by given user
                            issueCommentRepository.deleteById(issueCommentId);
                        } else {
                            throw new IssueCommentNotCreatedByThisUserException("Issue comment not created by this user");
                        }
                    } else {
                        throw new IssueCommentNotInThisIssueException("Issue comment not in this issue");
                    }
                } else {
                    throw new IssueNotAssignedToUserException("Issue not assigned to this user");
                }
            } else {
                throw new IssueNotInThisProjectException("Issue not in this project");
            }
        } else {
            throw new UserNotInProjectException("User not in this project");
        }
    }

    @Override
    public IssueCommentDTO updateUpdatedDateByUserIdByProjectIdByIssueIdByIssueCommentId(int userId, int projectId, int issueId, int issueCommentId) {
        UserEntity userEntity = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User could not be found"));
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new ProjectNotFoundException("Project could not be found"));
        Issue issue = issueRepository.findById(issueId).orElseThrow(() -> new IssueNotFoundException("Issue could not be found"));
        IssueComment issueComment = issueCommentRepository.findById(issueCommentId)
                .orElseThrow(() -> new IssueCommentNotFoundException("Issue comment could not be found"));

        if (project.getUsersInvolved().contains(userEntity)){ // If project involves given user
            if (issue.getProject().equals(project)){ // If issue belongs to given project
                if (issue.getUsersAssigned().contains(userEntity)){ // If issue assigned to given user
                    if (issueComment.getIssue().equals(issue)){ // If issue comment belongs to given issue
                        if (issueComment.getUser().equals(userEntity)){ // If issue comment created by given user
                            issueComment.setUpdatedDate(LocalDateTime.now());
                            IssueComment updatedIssueComment = issueCommentRepository.save(issueComment);
                            return mapToIssueCommentDto(updatedIssueComment);
                        } else {
                            throw new IssueCommentNotCreatedByThisUserException("Issue comment not created by this user");
                        }
                    } else {
                        throw new IssueCommentNotInThisIssueException("Issue comment not in this issue");
                    }
                } else {
                    throw new IssueNotAssignedToUserException("Issue not assigned to this user");
                }
            } else {
                throw new IssueNotInThisProjectException("Issue not in this project");
            }
        } else {
            throw new UserNotInProjectException("User not in this project");
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
        IssueComment updatedIssueComment = issueCommentRepository.save(issueComment);
        return mapToIssueCommentDto(updatedIssueComment);
    }

    @Override
    public void deleteIssueCommentByIssueCommentId(int issueCommentId) {
        issueCommentRepository.findById(issueCommentId).orElseThrow(() -> new IssueCommentNotFoundException("Issue comment could not be found"));
        issueCommentRepository.deleteById(issueCommentId);
    }

    @Override
    public IssueCommentDTO updateUpdatedDateByIssueCommentId(int issueCommentId) {
        IssueComment issueComment = issueCommentRepository.findById(issueCommentId)
                .orElseThrow(() -> new IssueCommentNotFoundException("Issue comment could not be found"));
        issueComment.setUpdatedDate(LocalDateTime.now());
        IssueComment updatedIssueComment = issueCommentRepository.save(issueComment);
        return mapToIssueCommentDto(updatedIssueComment);
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
