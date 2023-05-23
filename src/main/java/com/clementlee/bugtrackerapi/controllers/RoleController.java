package com.clementlee.bugtrackerapi.controllers;

import com.clementlee.bugtrackerapi.dto.RoleDTO;
import com.clementlee.bugtrackerapi.services.impl.RoleServiceImpl;
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
    public ResponseEntity<RoleDTO> createRole(@RequestBody RoleDTO roleDTO){
        return new ResponseEntity<>(roleServiceImpl.createRole(roleDTO), HttpStatus.CREATED);
    }

    @GetMapping("role")
    public ResponseEntity<List<RoleDTO>> getAllRoles(){
        return ResponseEntity.ok(roleServiceImpl.getAllRoles());
    }

    @GetMapping("role/{id}")
    public ResponseEntity<RoleDTO> getRoleById(@PathVariable(value = "id") int roleId){
        return ResponseEntity.ok(roleServiceImpl.getRoleById(roleId));
    }

    @PutMapping("role/{id}/update")
    public ResponseEntity<RoleDTO> updateRole(@RequestBody RoleDTO roleDTO, @PathVariable(value = "id") int roleId){
        return ResponseEntity.ok(roleServiceImpl.updateRole(roleDTO, roleId));
    }

    @DeleteMapping("role/{id}/delete")
    public ResponseEntity<String> deleteRole(@PathVariable(value = "id") int roleId){
        roleServiceImpl.deleteRole(roleId);
        return ResponseEntity.ok("Role successfully deleted");
    }

}
