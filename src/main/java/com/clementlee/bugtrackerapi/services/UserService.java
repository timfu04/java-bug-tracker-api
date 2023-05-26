package com.clementlee.bugtrackerapi.services;

import com.clementlee.bugtrackerapi.dto.UserDTO;

import java.util.List;

public interface UserService {

    UserDTO createUser(UserDTO userDTO);
    List<UserDTO> getAllUsers();
    UserDTO getUserById(int id);
    UserDTO updateUser(UserDTO userDTO, int id);
    void deleteUser(int id);

}
