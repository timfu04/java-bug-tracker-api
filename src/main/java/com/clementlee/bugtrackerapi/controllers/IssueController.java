package com.clementlee.bugtrackerapi.controllers;

import com.clementlee.bugtrackerapi.dto.IssueDTO;
import com.clementlee.bugtrackerapi.dto.PriorityDTO;
import com.clementlee.bugtrackerapi.dto.SeverityDTO;
import com.clementlee.bugtrackerapi.dto.StatusDTO;
import com.clementlee.bugtrackerapi.services.impl.IssueServiceImpl;
import com.clementlee.bugtrackerapi.validation.markerinterfaces.IssueCreateValidationGroup;
import com.clementlee.bugtrackerapi.validation.markerinterfaces.IssueUpdateValidationGroup;
import jakarta.validation.Valid;
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
    public ResponseEntity<IssueDTO> updateIssuePartialByUserIdByProjectIdByIssueId(
                                                                        @PathVariable(value = "userId") int userId,
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
                                                                                         @Valid @RequestBody StatusDTO statusDTO){
        return new ResponseEntity<>(issueServiceImpl
                .updateStatusByUserIdByProjectIdByIssueIdByStatusName(userId, projectId, issueId, statusDTO.getName()), HttpStatus.OK);
    }

    @PatchMapping("user/{userId}/project/{projectId}/issue/{issueId}/update-severity")
    public ResponseEntity<IssueDTO> updateSeverityByUserIdByProjectIdByIssueIdBySeverityName(@PathVariable(value = "userId") int userId,
                                                                                             @PathVariable(value = "projectId") int projectId,
                                                                                             @PathVariable(value = "issueId") int issueId,
                                                                                             @Valid @RequestBody SeverityDTO severityDTO){
        return new ResponseEntity<>(issueServiceImpl
                .updateSeverityByUserIdByProjectIdByIssueIdBySeverityName(userId, projectId, issueId, severityDTO.getName()), HttpStatus.OK);
    }

    @PatchMapping("user/{userId}/project/{projectId}/issue/{issueId}/update-priority")
    public ResponseEntity<IssueDTO> updatePriorityByUserIdByProjectIdByIssueIdByPriorityName(@PathVariable(value = "userId") int userId,
                                                                                             @PathVariable(value = "projectId") int projectId,
                                                                                             @PathVariable(value = "issueId") int issueId,
                                                                                             @Valid @RequestBody PriorityDTO priorityDTO){
        return new ResponseEntity<>(issueServiceImpl
                .updatePriorityByUserIdByProjectIdByIssueIdByPriorityName(userId, projectId, issueId, priorityDTO.getName()), HttpStatus.OK);
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

    @DeleteMapping("issue/{issueId}/delete")
    public ResponseEntity<String> deleteIssueByIssueId(@PathVariable(value = "issueId") int issueId){
        issueServiceImpl.deleteIssueByIssueId(issueId);
        return new ResponseEntity<>("Issue deleted successfully", HttpStatus.OK);
    }

    @PatchMapping("issue/{issueId}/update-updated-date")
    public ResponseEntity<IssueDTO> updateUpdatedDateByIssueId(@PathVariable(value = "issueId") int issueId){
        return new ResponseEntity<>(issueServiceImpl.updateUpdatedDateByIssueId(issueId), HttpStatus.OK);
    }

    @PatchMapping("issue/{issueId}/update-resolved-date")
    public ResponseEntity<IssueDTO> updateResolvedDateByIssueId(@PathVariable(value = "issueId") int issueId){
        return new ResponseEntity<>(issueServiceImpl.updateResolvedDateByIssueId(issueId), HttpStatus.OK);
    }

    @PatchMapping("issue/{issueId}/update-closed-date")
    public ResponseEntity<IssueDTO> updateClosedDateByIssueId(@PathVariable(value = "issueId") int issueId){
        return new ResponseEntity<>(issueServiceImpl.updateClosedDateByIssueId(issueId), HttpStatus.OK);
    }

    @PatchMapping("issue/{issueId}/update-status")
    public ResponseEntity<IssueDTO> updateStatusByIssueId(@PathVariable(value = "issueId") int issueId,
                                                          @Valid @RequestBody StatusDTO statusDTO){
        return new ResponseEntity<>(issueServiceImpl.updateStatusByIssueId(issueId, statusDTO.getName()), HttpStatus.OK);
    }

    @PatchMapping("issue/{issueId}/update-severity")
    public ResponseEntity<IssueDTO> updateSeverityByIssueId(@PathVariable(value = "issueId") int issueId,
                                                            @Valid @RequestBody SeverityDTO severityDTO){
        return new ResponseEntity<>(issueServiceImpl.updateSeverityByIssueId(issueId, severityDTO.getName()), HttpStatus.OK);
    }

    @PatchMapping("issue/{issueId}/update-priority")
    public ResponseEntity<IssueDTO> updatePriorityByIssueId(@PathVariable(value = "issueId") int issueId,
                                                            @Valid @RequestBody PriorityDTO priorityDTO){
        return new ResponseEntity<>(issueServiceImpl.updatePriorityByIssueId(issueId, priorityDTO.getName()), HttpStatus.OK);
    }

    @PutMapping("project/{projectId}/issue/{issueId}/add-user/user/{userToAddId}")
    public ResponseEntity<IssueDTO> assignUserToIssueByProjectIdByIssueIdByUserToAddId(@PathVariable(value = "projectId") int projectId,
                                                                                       @PathVariable(value = "issueId") int issueId,
                                                                                       @PathVariable(value = "userToAddId") int userToAddId){
        return new ResponseEntity<>(issueServiceImpl.assignUserToIssueByProjectIdByIssueIdByUserToAddId(projectId, issueId, userToAddId), HttpStatus.OK);
    }

    @DeleteMapping("project/{projectId}/issue/{issueId}/remove-user/user/{userToRemoveId}")
    public ResponseEntity<String> removeUserFromIssueByProjectIdByIssueIdByUserToRemoveId(@PathVariable(value = "projectId") int projectId,
                                                      @PathVariable(value = "issueId") int issueId,
                                                      @PathVariable(value = "userToRemoveId") int userToRemoveId){
        issueServiceImpl.removeUserFromIssueByProjectIdByIssueIdByUserToRemoveId(projectId, issueId, userToRemoveId);
        return new ResponseEntity<>("User removed from issue successfully", HttpStatus.OK);
    }

}
