//package com.clementlee.bugtrackerapi.controllers;
//
//import com.clementlee.bugtrackerapi.dto.PriorityDTO;
//import com.clementlee.bugtrackerapi.services.impl.PriorityServiceImpl;
//import jakarta.validation.Valid;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/v1/")
//@RequiredArgsConstructor
//public class PriorityController {
//
//    private final PriorityServiceImpl priorityServiceImpl;
//
//    @PostMapping("priority/create")
//    public ResponseEntity<PriorityDTO> createPriority(@Valid @RequestBody PriorityDTO priorityDTO){
//        return new ResponseEntity<>(priorityServiceImpl.createPriority(priorityDTO), HttpStatus.CREATED);
//    }
//
//    @GetMapping("priority")
//    public ResponseEntity<List<PriorityDTO>> getAllPriorities(){
//        return new ResponseEntity<>(priorityServiceImpl.getAllPriorities(), HttpStatus.OK);
//    }
//
//    @GetMapping("priority/{priorityId}")
//    public ResponseEntity<PriorityDTO> getPriorityByPriorityId(@PathVariable(value = "priorityId") int priorityId){
//        return new ResponseEntity<>(priorityServiceImpl.getPriorityByPriorityId(priorityId), HttpStatus.OK);
//    }
//
//    @PutMapping("priority/{priorityId}/update")
//    public ResponseEntity<PriorityDTO> updatePriorityByPriorityId(@PathVariable(value = "priorityId") int priorityId,
//                                                                  @Valid @RequestBody PriorityDTO priorityDTO){
//        return new ResponseEntity<>(priorityServiceImpl.updatePriorityByPriorityId(priorityId, priorityDTO), HttpStatus.OK);
//    }
//
//    @DeleteMapping("priority/{priorityId}/delete")
//    public ResponseEntity<String> deletePriorityByPriorityId(@PathVariable(value = "priorityId") int priorityId){
//        priorityServiceImpl.deletePriorityByPriorityId(priorityId);
//        return ResponseEntity.ok("Priority deleted successfully");
//    }
//
//}
