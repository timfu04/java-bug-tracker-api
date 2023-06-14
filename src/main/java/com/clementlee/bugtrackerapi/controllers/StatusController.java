package com.clementlee.bugtrackerapi.controllers;

import com.clementlee.bugtrackerapi.dto.StatusDTO;
import com.clementlee.bugtrackerapi.services.impl.StatusServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
@RequiredArgsConstructor
public class StatusController {

    private final StatusServiceImpl statusServiceImpl;

    @PostMapping("status/create")
    public ResponseEntity<StatusDTO> createStatus(@Valid @RequestBody StatusDTO statusDTO){
        return new ResponseEntity<>(statusServiceImpl.createStatus(statusDTO), HttpStatus.CREATED);
    }

    @GetMapping("status")
    public ResponseEntity<List<StatusDTO>> getAllStatuses(){
        return new ResponseEntity<>(statusServiceImpl.getAllStatuses(), HttpStatus.OK);
    }

    @GetMapping("status/{statusId}")
    public ResponseEntity<StatusDTO> getStatusByStatusId(@PathVariable(value = "statusId") int statusId){
        return new ResponseEntity<>(statusServiceImpl.getStatusByStatusId(statusId), HttpStatus.OK);
    }

    @PutMapping("status/{statusId}/update")
    public ResponseEntity<StatusDTO> updateStatusByStatusId(@PathVariable(value = "statusId") int statusId, @Valid @RequestBody StatusDTO statusDTO){
        return new ResponseEntity<>(statusServiceImpl.updateStatusByStatusId(statusId, statusDTO), HttpStatus.OK);
    }

    @DeleteMapping("status/{statusId}/delete")
    public ResponseEntity<String> deleteStatusByStatusId(@PathVariable(value = "statusId") int statusId){
        statusServiceImpl.deleteStatusByStatusId(statusId);
        return new ResponseEntity<>("Status deleted successfully", HttpStatus.OK);
    }

}
