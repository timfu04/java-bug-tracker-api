package com.clementlee.bugtrackerapi.services;

import com.clementlee.bugtrackerapi.dto.IssueDTO;

import java.util.List;

public interface IssueService {

    IssueDTO createIssueByUserIdByProjectId(int userId, int projectId, IssueDTO issueDTO);
    List<IssueDTO> getAllIssuesByUserIdByProjectId(int userId, int projectId);
    List<IssueDTO> getAllIssuesAssignedByUserId(int userId);
    IssueDTO getIssueByUserIdByProjectIdByIssueId(int userId, int projectId, int issueId);
    // Update title or description or status or severity or priority or all of them
    IssueDTO updateIssuePartialByUserIdByProjectIdByIssueId(int userId, int projectId, int issueId, IssueDTO issueDTO);
    void deleteIssueByUserIdByProjectIdByIssueId(int userId, int projectId, int issueId);

    IssueDTO updateUpdatedDateByUserIdByProjectIdByIssueId(int userId, int projectId, int issueId);
    IssueDTO updateResolvedDateByUserIdByProjectIdByIssueId(int userId, int projectId, int issueId);
    IssueDTO updateClosedDateByUserIdByProjectIdByIssueId(int userId, int projectId, int issueId);
    IssueDTO updateStatusByUserIdByProjectIdByIssueIdByStatusName(int userId, int projectId, int issueId, String statusName);
    IssueDTO updateSeverityByUserIdByProjectIdByIssueIdBySeverityName(int userId, int projectId, int issueId, String severityName);
    IssueDTO updatePriorityByUserIdByProjectIdByIssueIdByPriorityName(int userId, int projectId, int issueId, String priorityName);

    List<IssueDTO> getAllIssues();
    IssueDTO getIssueByIssueId(int issueId);
    IssueDTO updateIssuePartialByIssueId(int issueId, IssueDTO issueDTO);
    void deleteIssueByIssueId(int issueId);

    IssueDTO updateUpdatedDateByIssueId(int issueId);
    IssueDTO updateResolvedDateByIssueId(int issueId);
    IssueDTO updateClosedDateByIssueId(int issueId);
    IssueDTO updateStatusByIssueId(int issueId, String statusName);
    IssueDTO updateSeverityByIssueId(int issueId, String severityName);
    IssueDTO updatePriorityByIssueId(int issueId, String priorityName);

    IssueDTO assignUserToIssueByProjectIdByIssueIdByUserToAddId(int projectId, int issueId, int userToAddId);
    void removeUserFromIssueByProjectIdByIssueIdByUserToRemoveId(int projectId, int issueId, int userToRemoveId);

}
