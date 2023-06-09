package com.clementlee.bugtrackerapi.controllers;


import com.clementlee.bugtrackerapi.dto.IssueCommentDTO;
import com.clementlee.bugtrackerapi.services.impl.IssueCommentServiceImpl;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
@RequiredArgsConstructor
public class IssueCommentController {

    private final IssueCommentServiceImpl issueCommentServiceImpl;

    @PostMapping("user/{userId}/project/{projectId}/issue/{issueId}/issue-comment/create")
    public ResponseEntity<IssueCommentDTO> createIssueCommentByUserIdByProjectIdByIssueId(@PathVariable(value = "userId") int userId,
                                                                                          @PathVariable(value = "projectId") int projectId,
                                                                                          @PathVariable(value = "issueId") int issueId,
                                                                                          @RequestBody IssueCommentDTO issueCommentDTO){
        return new ResponseEntity<>(issueCommentServiceImpl
                .createIssueCommentByUserIdByProjectIdByIssueId(userId, projectId, issueId, issueCommentDTO), HttpStatus.CREATED);
    }

    @GetMapping("issue/{issueId}/issue-comment")
    public ResponseEntity<List<IssueCommentDTO>> getAllIssueCommentsByIssueId(@PathVariable(value = "issueId") int issueId){
        return new ResponseEntity<>(issueCommentServiceImpl.getAllIssueCommentsByIssueId(issueId), HttpStatus.OK);
    }

    @GetMapping("user/{userId}/issue-comment")
    public ResponseEntity<List<IssueCommentDTO>> getAllIssueCommentsByUserId(@PathVariable(value = "userId") int userId){
        return new ResponseEntity<>(issueCommentServiceImpl.getAllIssueCommentsByUserId(userId), HttpStatus.OK);
    }

    @GetMapping("user/{userId}/project/{projectId}/issue/{issueId}/issue-comment/{issueCommentId}")
    public ResponseEntity<IssueCommentDTO> getIssueCommentByUserIdByProjectIdByIssueIdByIssueCommentId(@PathVariable(value = "userId") int userId,
                                                                                                       @PathVariable(value = "projectId")int projectId,
                                                                                                       @PathVariable(value = "issueId")int issueId,
                                                                                                       @PathVariable(value = "issueCommentId")int issueCommentId){
        return new ResponseEntity<>(issueCommentServiceImpl
                .getIssueCommentByUserIdByProjectIdByIssueIdByIssueCommentId(userId, projectId, issueId, issueCommentId), HttpStatus.OK);
    }

    @PatchMapping("user/{userId}/project/{projectId}/issue/{issueId}/issue-comment/{issueCommentId}/update")
    public ResponseEntity<IssueCommentDTO> updateIssueCommentByUserIdByProjectIdByIssueIdByIssueCommentId(@PathVariable(value = "userId") int userId,
                                                                                                       @PathVariable(value = "projectId")int projectId,
                                                                                                       @PathVariable(value = "issueId")int issueId,
                                                                                                       @PathVariable(value = "issueCommentId")int issueCommentId,
                                                                                                       @RequestBody IssueCommentDTO issueCommentDTO){
        return new ResponseEntity<>(issueCommentServiceImpl
                .updateIssueCommentByUserIdByProjectIdByIssueIdByIssueCommentId(userId, projectId, issueId, issueCommentId, issueCommentDTO), HttpStatus.OK);
    }

    @DeleteMapping("user/{userId}/project/{projectId}/issue/{issueId}/issue-comment/{issueCommentId}/delete")
    public ResponseEntity<String> deleteIssueCommentByUserIdByProjectIdByIssueIdByIssueCommentId(@PathVariable(value = "userId") int userId,
                                                                                                          @PathVariable(value = "projectId")int projectId,
                                                                                                          @PathVariable(value = "issueId")int issueId,
                                                                                                          @PathVariable(value = "issueCommentId")int issueCommentId){
        issueCommentServiceImpl.deleteIssueCommentByUserIdByProjectIdByIssueIdByIssueCommentId(userId, projectId, issueId, issueCommentId);
        return new ResponseEntity<>("Issue comment deleted successfully", HttpStatus.OK);
    }



    @GetMapping("issue-comment")
    public ResponseEntity<List<IssueCommentDTO>> getAllIssueCommentsByUserId(){
        return new ResponseEntity<>(issueCommentServiceImpl.getAllIssueComments(), HttpStatus.OK);
    }

    @GetMapping("issue-comment/{issueCommentId}")
    public ResponseEntity<IssueCommentDTO> getIssueCommentByIssueCommentId(@PathVariable(value = "issueCommentId") int issueCommentId){
        return new ResponseEntity<>(issueCommentServiceImpl.getIssueCommentByIssueCommentId(issueCommentId), HttpStatus.OK);
    }

    @PatchMapping("issue-comment/{issueCommentId}/update")
    public ResponseEntity<IssueCommentDTO> updateIssueCommentByIssueCommentId(@PathVariable(value = "issueCommentId") int issueCommentId,
                                                                              @RequestBody IssueCommentDTO issueCommentDTO){
        return new ResponseEntity<>(issueCommentServiceImpl.updateIssueCommentByIssueCommentId(issueCommentId, issueCommentDTO), HttpStatus.OK);
    }

    @DeleteMapping("issue-comment/{issueCommentId}/delete")
    public ResponseEntity<String> deleteIssueCommentByIssueCommentId(@PathVariable(value = "issueCommentId") int issueCommentId){
        issueCommentServiceImpl.deleteIssueCommentByIssueCommentId(issueCommentId);
        return new ResponseEntity<>("Issue comment deleted successfully", HttpStatus.OK);
    }

    @PatchMapping("user/{userId}/project/{projectId}/issue/{issueId}/issue-comment/{issueCommentId}/update-updated-date")
    public ResponseEntity<IssueCommentDTO> updateUpdatedDateByUserIdByProjectIdByIssueIdByIssueCommentId(@PathVariable(value = "userId") int userId,
                                                                                                          @PathVariable(value = "projectId")int projectId,
                                                                                                          @PathVariable(value = "issueId")int issueId,
                                                                                                          @PathVariable(value = "issueCommentId")int issueCommentId){
        return new ResponseEntity<>(issueCommentServiceImpl
                .updateUpdatedDateByUserIdByProjectIdByIssueIdByIssueCommentId(userId, projectId, issueId, issueCommentId), HttpStatus.OK);
    }

}
