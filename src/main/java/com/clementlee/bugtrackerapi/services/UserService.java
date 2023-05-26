package com.clementlee.bugtrackerapi.services;

import com.clementlee.bugtrackerapi.dto.UserDTO;

import java.util.List;

public interface UserService {

    UserDTO createUser(UserDTO userDTO);
    List<UserDTO> getAllUsers();
    UserDTO getUserById(int id);
    UserDTO updateUserFull(UserDTO userDTO, int id);
    UserDTO updateUserPartial(UserDTO userDTO, int id);
    void deleteUser(int id);
    UserDTO updateRole(int id, String name);
    UserDTO revokeRole(int id);
}
