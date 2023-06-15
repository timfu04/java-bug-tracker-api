package com.clementlee.bugtrackerapi.services;

import com.clementlee.bugtrackerapi.dto.IssueCommentDTO;

import java.util.List;

public interface IssueCommentService {

    IssueCommentDTO createIssueCommentByUserIdByProjectIdByIssueId(int userId, int projectId, int issueId, IssueCommentDTO issueCommentDTO);
    List<IssueCommentDTO> getAllIssueCommentsByUserIdByProjectIdByIssueId(int userId, int projectId, int issueId);
    List<IssueCommentDTO> getAllIssueCommentsByUserId(int userId);
    IssueCommentDTO getIssueCommentByUserIdByProjectIdByIssueIdByIssueCommentId(int userId, int projectId, int issueId, int issueCommentId);
    IssueCommentDTO updateIssueCommentByUserIdByProjectIdByIssueIdByIssueCommentId(int userId, int projectId, int issueId, int issueCommentId,
                                                                                   IssueCommentDTO issueCommentDTO);
    void deleteIssueCommentByUserIdByProjectIdByIssueIdByIssueCommentId(int userId, int projectId, int issueId, int issueCommentId);
    IssueCommentDTO updateUpdatedDateByUserIdByProjectIdByIssueIdByIssueCommentId(int userId, int projectId, int issueId, int issueCommentId);

    List<IssueCommentDTO> getAllIssueComments();
    IssueCommentDTO getIssueCommentByIssueCommentId(int issueCommentId);
    IssueCommentDTO updateIssueCommentByIssueCommentId(int issueCommentId, IssueCommentDTO issueCommentDTO);
    void deleteIssueCommentByIssueCommentId(int issueCommentId);
    IssueCommentDTO updateUpdatedDateByIssueCommentId(int issueCommentId);

}
