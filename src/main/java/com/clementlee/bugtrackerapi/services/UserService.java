package com.clementlee.bugtrackerapi.services;

import com.clementlee.bugtrackerapi.dto.UserDTO;

import java.util.List;

public interface UserService {

    UserDTO createUser(UserDTO userDTO);
    List<UserDTO> getAllUsers();
    UserDTO getUserByUserId(int userId);
    UserDTO updateUserFullByUserId(UserDTO userDTO, int userId);
    UserDTO updateUserPartialByUserId(UserDTO userDTO, int userId);
    void deleteUserByUserId(int userId);
    UserDTO updateRoleByUserId(int userId, String name);
    UserDTO revokeRoleByUserId(int userId);
}
