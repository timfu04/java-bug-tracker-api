package com.clementlee.bugtrackerapi.controllers;

import com.clementlee.bugtrackerapi.dto.SeverityDTO;
import com.clementlee.bugtrackerapi.services.impl.SeverityServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
@RequiredArgsConstructor
public class SeverityController {

    private final SeverityServiceImpl severityServiceImpl;

    @PostMapping("severity/create")
    public ResponseEntity<SeverityDTO> createSeverity(@Valid @RequestBody SeverityDTO severityDTO){
        return new ResponseEntity<>(severityServiceImpl.createSeverity(severityDTO), HttpStatus.CREATED);
    }

    @GetMapping("severity")
    public ResponseEntity<List<SeverityDTO>> getAllSeverities(){
        return new ResponseEntity<>(severityServiceImpl.getAllSeverities(), HttpStatus.OK);
    }

    @GetMapping("severity/{severityId}")
    public ResponseEntity<SeverityDTO> getSeverityBySeverityId(@PathVariable(value = "severityId") int severityId){
        return new ResponseEntity<>(severityServiceImpl.getSeverityBySeverityId(severityId), HttpStatus.OK);
    }

    @PutMapping("severity/{severityId}/update")
    public ResponseEntity<SeverityDTO> updateSeverityBySeverityId(@PathVariable(value = "severityId") int severityId,
     @Valid                                                             @RequestBody SeverityDTO severityDTO){
        return new ResponseEntity<>(severityServiceImpl.updateSeverityBySeverityId(severityId, severityDTO), HttpStatus.OK);
    }

    @DeleteMapping("severity/{severityId}/delete")
    public ResponseEntity<String> deleteSeverityBySeverityId(@PathVariable(value = "severityId") int severityId){
        severityServiceImpl.deleteSeverityBySeverityId(severityId);
        return ResponseEntity.ok("Severity deleted successfully");
    }

}
