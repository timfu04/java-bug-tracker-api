package com.clementlee.bugtrackerapi.services;

import com.clementlee.bugtrackerapi.dto.UserDTO;

import java.util.List;

public interface UserService {

    UserDTO createUser(UserDTO userDTO);
    List<UserDTO> getAllUsers();
    UserDTO getUserByUserId(int userId);
    UserDTO updateUserFullByUserId(int userId, UserDTO userDTO);
    UserDTO updateUserPartialByUserId(int userId, UserDTO userDTO);
    void deleteUserByUserId(int userId);
    UserDTO updateRoleByUserIdByRoleName(int userId, String roleName);
    UserDTO revokeRoleByUserId(int userId);

}
