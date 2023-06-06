//package com.clementlee.bugtrackerapi.controllers;
//
//import com.clementlee.bugtrackerapi.dto.IssueDTO;
//import com.clementlee.bugtrackerapi.dto.ProjectDTO;
//import com.clementlee.bugtrackerapi.services.impl.IssueServiceImpl;
//import com.clementlee.bugtrackerapi.validation.markerinterface.ProjectDateValidationGroup;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/v1/")
//@RequiredArgsConstructor
//public class IssueController {
//
//    private final IssueServiceImpl issueServiceImpl;
//
//    @PostMapping("user/{userId}/project/{projectId}/issue/create")
//    public ResponseEntity<IssueDTO> createIssueByUserIdByProjectId(@PathVariable(value = "userId") int userId,
//                                                                   @PathVariable(value = "projectId") int projectId,
//                                                                   @RequestBody IssueDTO issueDTO){
//        return new ResponseEntity<>(issueServiceImpl.createIssueByUserIdByProjectId(userId, projectId, issueDTO), HttpStatus.OK);
//    }
//
//    @PutMapping("issue/{issueId}/update-updated-date")
//    public ResponseEntity<IssueDTO> updateUpdatedDate(@PathVariable(value = "issueId") int issueId){
//        return new ResponseEntity<>(issueServiceImpl.updateUpdatedDate(issueId), HttpStatus.OK);
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
//    @GetMapping("issue")
//    public ResponseEntity<List<IssueDTO>> getAllIssues(){
//        return new ResponseEntity<>(issueServiceImpl.getAllIssues(), HttpStatus.OK);
//    }
//
//    @PatchMapping("issue/{issueId}/update-partial")
//    public ResponseEntity<IssueDTO> updateIssuePartialByIssueId(@PathVariable(value = "issueId") int issueId,
//                                                                @RequestBody IssueDTO issueDTO){
//        return new ResponseEntity<>(issueServiceImpl.updateIssuePartialByIssueId(issueId, issueDTO), HttpStatus.OK);
//    }
//
//    @DeleteMapping("issue/{issueId}/delete")
//    public ResponseEntity<String> deleteIssueByIssueId(@PathVariable(value = "issueId") int issueId){
//        issueServiceImpl.deleteIssueByIssueId(issueId);
//        return ResponseEntity.ok("Issue deleted successfully");
//    }
//
//}
