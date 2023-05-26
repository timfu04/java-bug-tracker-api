package com.clementlee.bugtrackerapi.controllers;

import com.clementlee.bugtrackerapi.dto.UserDTO;
import com.clementlee.bugtrackerapi.services.impl.UserServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userServiceImpl;

    @PostMapping("user/create")
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userDTO){
        return new ResponseEntity<>(userServiceImpl.createUser(userDTO), HttpStatus.CREATED);
    }

    @GetMapping("user")
    public ResponseEntity<List<UserDTO>> getAllUsers(){
        return new ResponseEntity<>(userServiceImpl.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("user/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable(value = "id") int userId){
        return new ResponseEntity<>(userServiceImpl.getUserById(userId), HttpStatus.OK);
    }

    @PutMapping("user/{id}/update")
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO, @PathVariable(value = "id") int userId){
        return new ResponseEntity<>(userServiceImpl.updateUser(userDTO, userId), HttpStatus.OK);
    }

    @DeleteMapping("user/{id}/delete")
    public ResponseEntity<String> deleteUser(@PathVariable(value = "id") int userId){
        userServiceImpl.deleteUser(userId);
        return ResponseEntity.ok("User deleted successfully");
    }

}
