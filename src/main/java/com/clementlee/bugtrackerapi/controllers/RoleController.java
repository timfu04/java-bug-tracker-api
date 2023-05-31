package com.clementlee.bugtrackerapi.controllers;

import com.clementlee.bugtrackerapi.dto.RoleDTO;
import com.clementlee.bugtrackerapi.services.impl.RoleServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
@RequiredArgsConstructor
public class RoleController {

    private final RoleServiceImpl roleServiceImpl;

    @PostMapping("role/create")
    public ResponseEntity<RoleDTO> createRole(@Valid @RequestBody RoleDTO roleDTO){
        return new ResponseEntity<>(roleServiceImpl.createRole(roleDTO), HttpStatus.CREATED);
    }

    @GetMapping("role")
    public ResponseEntity<List<RoleDTO>> getAllRoles(){
        return new ResponseEntity<>(roleServiceImpl.getAllRoles(), HttpStatus.OK);
    }

    @GetMapping("role/{roleId}")
    public ResponseEntity<RoleDTO> getRoleById(@PathVariable(value = "roleId") int roleId){
        return new ResponseEntity<>(roleServiceImpl.getRoleById(roleId), HttpStatus.OK);
    }

    @PutMapping("role/{roleId}/update")
    public ResponseEntity<RoleDTO> updateRole(@Valid @RequestBody RoleDTO roleDTO, @PathVariable(value = "roleId") int roleId){
        return new ResponseEntity<>(roleServiceImpl.updateRole(roleDTO, roleId), HttpStatus.OK);
    }

    @DeleteMapping("role/{roleId}/delete")
    public ResponseEntity<String> deleteRole(@PathVariable(value = "roleId") int roleId){
        roleServiceImpl.deleteRole(roleId);
        return ResponseEntity.ok("Role deleted successfully");
    }

}
