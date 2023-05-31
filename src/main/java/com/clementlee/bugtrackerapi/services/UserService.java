package com.clementlee.bugtrackerapi.services;

import com.clementlee.bugtrackerapi.dto.UserDTO;

import java.util.List;

public interface UserService {

    UserDTO createUser(UserDTO userDTO);
    List<UserDTO> getAllUsers();
    UserDTO getUserById(int userId);
    UserDTO updateUserFull(UserDTO userDTO, int userId);
    UserDTO updateUserPartial(UserDTO userDTO, int userId);
    void deleteUser(int userId);
    UserDTO updateRole(int userId, String name);
    UserDTO revokeRole(int userId);
}
