package com.clementlee.bugtrackerapi.services;

import com.clementlee.bugtrackerapi.dto.UserDTO;

import java.util.List;

public interface UserService {

    UserDTO createUser(UserDTO userDTO);
    List<UserDTO> getAllUsers();
    UserDTO getUserByUserId(int userId);
    UserDTO updateUserPartialByUserId(int userId, UserDTO userDTO); // Update username or password or email or all of them
    void deleteUserByUserId(int userId);
    UserDTO updateRoleByUserIdByRoleName(int userId, String roleName);
    UserDTO revokeRoleByUserId(int userId);

}
