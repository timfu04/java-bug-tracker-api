package com.clementlee.bugtrackerapi.controllers;

import com.clementlee.bugtrackerapi.dto.IssueDTO;
import com.clementlee.bugtrackerapi.models.Priority;
import com.clementlee.bugtrackerapi.models.Severity;
import com.clementlee.bugtrackerapi.models.Status;
import com.clementlee.bugtrackerapi.services.impl.IssueServiceImpl;
import com.clementlee.bugtrackerapi.validation.markerinterfaces.IssueCreateValidationGroup;
import com.clementlee.bugtrackerapi.validation.markerinterfaces.IssueUpdateValidationGroup;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
@RequiredArgsConstructor
public class IssueController {

    private final IssueServiceImpl issueServiceImpl;

    @PostMapping("user/{userId}/project/{projectId}/issue/create")
    public ResponseEntity<IssueDTO> createIssueByUserIdByProjectId(@PathVariable(value = "userId") int userId,
                                                                   @PathVariable(value = "projectId") int projectId,
                                                                   @Validated(IssueCreateValidationGroup.class) @RequestBody IssueDTO issueDTO){
        return new ResponseEntity<>(issueServiceImpl.createIssueByUserIdByProjectId(userId, projectId, issueDTO), HttpStatus.CREATED);
    }

    @GetMapping("user/{userId}/project/{projectId}/issue")
    public ResponseEntity<List<IssueDTO>> getAllIssuesByUserIdByProjectId(@PathVariable(value = "userId") int userId,
                                                                          @PathVariable(value = "projectId") int projectId){
        return new ResponseEntity<>(issueServiceImpl.getAllIssuesByUserIdByProjectId(userId, projectId), HttpStatus.OK);
    }

    @GetMapping("user/{userId}/issue")
    public ResponseEntity<List<IssueDTO>> getAllIssuesAssignedByUserId(@PathVariable(value = "userId") int userId){
        return new ResponseEntity<>(issueServiceImpl.getAllIssuesAssignedByUserId(userId),HttpStatus.OK);
    }

    @GetMapping("user/{userId}/project/{projectId}/issue/{issueId}")
    public ResponseEntity<IssueDTO> getIssueByUserIdByProjectIdByIssueId(@PathVariable(value = "userId") int userId,
                                                                         @PathVariable(value = "projectId") int projectId,
                                                                         @PathVariable(value = "issueId") int issueId){
        return new ResponseEntity<>(issueServiceImpl.getIssueByUserIdByProjectIdByIssueId(userId, projectId, issueId), HttpStatus.OK);
    }

    @PatchMapping("user/{userId}/project/{projectId}/issue/{issueId}/update-partial")
    public ResponseEntity<IssueDTO> updateIssuePartialByUserIdByProjectIdByIssueId(@PathVariable(value = "userId") int userId,
                                                                                   @PathVariable(value = "projectId") int projectId,
                                                                                   @PathVariable(value = "issueId") int issueId,
                                                                                   @Validated(IssueUpdateValidationGroup.class) @RequestBody IssueDTO issueDTO){
        return new ResponseEntity<>(issueServiceImpl
                .updateIssuePartialByUserIdByProjectIdByIssueId(userId, projectId, issueId, issueDTO), HttpStatus.OK);
    }

    @DeleteMapping("user/{userId}/project/{projectId}/issue/{issueId}/delete")
    public ResponseEntity<String> deleteIssueByUserIdByProjectIdByIssueId(@PathVariable(value = "userId") int userId,
                                                                          @PathVariable(value = "projectId") int projectId,
                                                                          @PathVariable(value = "issueId") int issueId){
        issueServiceImpl.deleteIssueByUserIdByProjectIdByIssueId(userId, projectId, issueId);
        return new ResponseEntity<>("Issue deleted successfully", HttpStatus.OK);
    }

    @PatchMapping("user/{userId}/project/{projectId}/issue/{issueId}/update-updated-date")
    public ResponseEntity<IssueDTO> updateUpdatedDateByUserIdByProjectIdByIssueId(@PathVariable(value = "userId") int userId,
                                                                                  @PathVariable(value = "projectId") int projectId,
                                                                                  @PathVariable(value = "issueId") int issueId){
        return new ResponseEntity<>(issueServiceImpl.updateUpdatedDateByUserIdByProjectIdByIssueId(userId, projectId, issueId), HttpStatus.OK);
    }

    @PatchMapping("user/{userId}/project/{projectId}/issue/{issueId}/update-resolved-date")
    public ResponseEntity<IssueDTO> updateResolvedDateByUserIdByProjectIdByIssueId(@PathVariable(value = "userId") int userId,
                                                                                   @PathVariable(value = "projectId") int projectId,
                                                                                   @PathVariable(value = "issueId") int issueId){
        return new ResponseEntity<>(issueServiceImpl.updateResolvedDateByUserIdByProjectIdByIssueId(userId, projectId, issueId), HttpStatus.OK);
    }

    @PatchMapping("user/{userId}/project/{projectId}/issue/{issueId}/update-closed-date")
    public ResponseEntity<IssueDTO> updateClosedDateByUserIdByProjectIdByIssueId(@PathVariable(value = "userId") int userId,
                                                                                 @PathVariable(value = "projectId") int projectId,
                                                                                 @PathVariable(value = "issueId") int issueId){
        return new ResponseEntity<>(issueServiceImpl.updateClosedDateByUserIdByProjectIdByIssueId(userId, projectId, issueId), HttpStatus.OK);
    }

    @PatchMapping("user/{userId}/project/{projectId}/issue/{issueId}/update-status")
    public ResponseEntity<IssueDTO> updateStatusByUserIdByProjectIdByIssueIdByStatusName(@PathVariable(value = "userId") int userId,
                                                                                         @PathVariable(value = "projectId") int projectId,
                                                                                         @PathVariable(value = "issueId") int issueId,
                                                                                         @RequestBody Status status){
        return new ResponseEntity<>(issueServiceImpl
                .updateStatusByUserIdByProjectIdByIssueIdByStatusName(userId, projectId, issueId, status.getName()), HttpStatus.OK);
    }

    @PatchMapping("user/{userId}/project/{projectId}/issue/{issueId}/update-severity")
    public ResponseEntity<IssueDTO> updateSeverityByUserIdByProjectIdByIssueIdBySeverityName(@PathVariable(value = "userId") int userId,
                                                                                         @PathVariable(value = "projectId") int projectId,
                                                                                         @PathVariable(value = "issueId") int issueId,
                                                                                         @RequestBody Severity severity){
        return new ResponseEntity<>(issueServiceImpl
                .updateSeverityByUserIdByProjectIdByIssueIdBySeverityName(userId, projectId, issueId, severity.getName()), HttpStatus.OK);
    }

    @PatchMapping("user/{userId}/project/{projectId}/issue/{issueId}/update-priority")
    public ResponseEntity<IssueDTO> updatePriorityByUserIdByProjectIdByIssueIdByPriorityName(@PathVariable(value = "userId") int userId,
                                                                                         @PathVariable(value = "projectId") int projectId,
                                                                                         @PathVariable(value = "issueId") int issueId,
                                                                                         @RequestBody Priority priority){
        return new ResponseEntity<>(issueServiceImpl
                .updatePriorityByUserIdByProjectIdByIssueIdByPriorityName(userId, projectId, issueId, priority.getName()), HttpStatus.OK);
    }

    @GetMapping("issue")
    public ResponseEntity<List<IssueDTO>> getAllIssues(){
        return new ResponseEntity<>(issueServiceImpl.getAllIssues(), HttpStatus.OK);
    }

    @GetMapping("issue/{issueId}")
    public ResponseEntity<IssueDTO> getIssueByIssueId(@PathVariable(value = "issueId") int issueId){
        return new ResponseEntity<>(issueServiceImpl.getIssueByIssueId(issueId), HttpStatus.OK);
    }

    @PatchMapping("issue/{issueId}/update-partial")
    public ResponseEntity<IssueDTO> updateIssuePartialByIssueId(@PathVariable(value = "issueId") int issueId,
                                                                @Validated(IssueUpdateValidationGroup.class) @RequestBody IssueDTO issueDTO){
        return new ResponseEntity<>(issueServiceImpl.updateIssuePartialByIssueId(issueId, issueDTO), HttpStatus.OK);
    }

    @PatchMapping("issue/{issueId}/update-updated-date")
    public ResponseEntity<IssueDTO> updateUpdatedDateByIssueId(@PathVariable(value = "issueId") int issueId){
        return new ResponseEntity<>(issueServiceImpl.updateUpdatedDateByIssueId(issueId), HttpStatus.OK);
    }

    @DeleteMapping("issue/{issueId}/delete")
    public ResponseEntity<String> deleteIssueByIssueId(@PathVariable(value = "issueId") int issueId){
        issueServiceImpl.deleteIssueByIssueId(issueId);
        return new ResponseEntity<>("Issue deleted successfully", HttpStatus.OK);
    }

}
