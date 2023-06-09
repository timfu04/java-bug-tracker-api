package com.clementlee.bugtrackerapi.controllers;

import com.clementlee.bugtrackerapi.dto.RoleDTO;
import com.clementlee.bugtrackerapi.dto.UserDTO;
import com.clementlee.bugtrackerapi.services.impl.UserServiceImpl;
import com.clementlee.bugtrackerapi.validation.markerinterfaces.UserAllFieldsValidationGroup;
import com.clementlee.bugtrackerapi.validation.markerinterfaces.UserEmailValidationGroup;
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
public class UserController {

    private final UserServiceImpl userServiceImpl;

    @PostMapping("user/create")
    public ResponseEntity<UserDTO> createUser(@Validated(UserAllFieldsValidationGroup.class) @RequestBody UserDTO userDTO){
        return new ResponseEntity<>(userServiceImpl.createUser(userDTO), HttpStatus.CREATED);
    }

    @GetMapping("user")
    public ResponseEntity<List<UserDTO>> getAllUsers(){
        return new ResponseEntity<>(userServiceImpl.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("user/{userId}")
    public ResponseEntity<UserDTO> getUserByUserId(@PathVariable(value = "userId") int userId){
        return new ResponseEntity<>(userServiceImpl.getUserByUserId(userId), HttpStatus.OK);
    }

    @PatchMapping("user/{userId}/update-partial")
    public ResponseEntity<UserDTO> updateUserPartialByUserId(@PathVariable(value = "userId") int userId,
                                                             @Validated(UserEmailValidationGroup.class) @RequestBody UserDTO userDTO){
        return new ResponseEntity<>(userServiceImpl.updateUserPartialByUserId(userId, userDTO), HttpStatus.OK);
    }

    @DeleteMapping("user/{userId}/delete")
    public ResponseEntity<String> deleteUserByUserId(@PathVariable(value = "userId") int userId){
        userServiceImpl.deleteUserByUserId(userId);
        return ResponseEntity.ok("User deleted successfully");
    }

    @PatchMapping("user/{userId}/update-role")
    public ResponseEntity<UserDTO> updateRoleByUserIdByRoleName(@PathVariable(value = "userId") int userId, @Valid @RequestBody RoleDTO roleDTO){
        return new ResponseEntity<>(userServiceImpl.updateRoleByUserIdByRoleName(userId, roleDTO.getName()), HttpStatus.OK);
    }

    @PutMapping("user/{userId}/revoke-role")
    public ResponseEntity<UserDTO> revokeRoleByUserId(@PathVariable(value = "userId") int userId){
        return new ResponseEntity<>(userServiceImpl.revokeRoleByUserId(userId), HttpStatus.OK);
    }

}
