package com.clementlee.bugtrackerapi.services.impl;

import com.clementlee.bugtrackerapi.dto.UserDTO;
import com.clementlee.bugtrackerapi.exceptions.RoleNotFoundException;
import com.clementlee.bugtrackerapi.exceptions.UserNotFoundException;
import com.clementlee.bugtrackerapi.models.Role;
import com.clementlee.bugtrackerapi.models.UserEntity;
import com.clementlee.bugtrackerapi.repositories.RoleRepository;
import com.clementlee.bugtrackerapi.repositories.UserRepository;
import com.clementlee.bugtrackerapi.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        final String defaultRoleName = "MEMBER";
        Role role = roleRepository.findByName(defaultRoleName.toUpperCase())
                .orElseThrow(() -> new RoleNotFoundException("Role could not be found"));
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(userDTO.getUsername());
        userEntity.setPassword(userDTO.getPassword());
        userEntity.setEmail(userDTO.getEmail());
        userEntity.setRole(role); // Assign "MEMBER" as default role for new user
        UserEntity newUser = userRepository.save(userEntity);
        return mapToUserDto(newUser);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<UserEntity> users = userRepository.findAll();
        return users.stream().map(user -> mapToUserDto(user)).collect(Collectors.toList()); // Convert list of UserEntity to list of UserDTO
    }

    @Override
    public UserDTO getUserByUserId(int userId) {
        UserEntity userEntity = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User could not be found"));
        return mapToUserDto(userEntity);
    }

    @Override
    public UserDTO updateUserPartialByUserId(int userId, UserDTO userDTO) {
        UserEntity userEntity = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User could not be found"));
        if (StringUtils.hasText(userDTO.getUsername())){
            userEntity.setUsername(userDTO.getUsername());
        }
        if (StringUtils.hasText(userDTO.getPassword())){
            userEntity.setPassword(userDTO.getPassword());
        }
        if (StringUtils.hasText(userDTO.getEmail())){
            userEntity.setEmail(userDTO.getEmail());
        }
        UserEntity updatedUser = userRepository.save(userEntity);
        return mapToUserDto(updatedUser);
    }

    @Override
    public void deleteUserByUserId(int userId) {
        userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User could not be found"));
        userRepository.deleteById(userId);
    }

    @Override
    public UserDTO updateRoleByUserIdByRoleName(int userId, String roleName) {
        UserEntity userEntity = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User could not be found"));
        Role newRole = roleRepository.findByName(roleName.toUpperCase())
                .orElseThrow(() -> new RoleNotFoundException("Role could not be found"));
        userEntity.setRole(newRole);
        UserEntity updatedUser = userRepository.save(userEntity);
        return mapToUserDto(updatedUser);
    }

    // Set to default role (MEMBER)
    @Override
    public UserDTO revokeRoleByUserId(int userId) {
        final String defaultRoleName = "MEMBER";
        Role newRole = roleRepository.findByName(defaultRoleName.toUpperCase())
                .orElseThrow(() -> new RoleNotFoundException("Role could not be found"));
        UserEntity userEntity = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User could not be found"));
        userEntity.setRole(newRole);
        UserEntity updatedUser = userRepository.save(userEntity);
        return mapToUserDto(updatedUser);
    }

    // Map UserEntity to UserDTO
    private UserDTO mapToUserDto(UserEntity userEntity){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(userEntity.getId());
        userDTO.setUsername(userEntity.getUsername());
        userDTO.setPassword(userEntity.getPassword());
        userDTO.setEmail(userEntity.getEmail());
        userDTO.setRole(userEntity.getRole());
        userDTO.setProjectsInvolved(userEntity.getProjectsInvolved());
        userDTO.setProjectsCreated(userEntity.getProjectsCreated());
        userDTO.setIssuesReported(userEntity.getIssuesReported());
        userDTO.setIssuesAssigned(userEntity.getIssuesAssigned());
        userDTO.setIssueComments(userEntity.getIssueComments());
        return userDTO;
    }

}
