package com.clementlee.bugtrackerapi.services.impl;

import com.clementlee.bugtrackerapi.dto.UserDTO;
import com.clementlee.bugtrackerapi.exceptions.UserNotFoundException;
import com.clementlee.bugtrackerapi.models.UserEntity;
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

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(userDTO.getUsername());
        userEntity.setPassword(userDTO.getPassword());
        userEntity.setEmail(userDTO.getEmail());
        UserEntity newUser = userRepository.save(userEntity);
        return mapToUserDto(newUser);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<UserEntity> users = userRepository.findAll();
        return users.stream().map(user -> mapToUserDto(user)).collect(Collectors.toList());
    }

    @Override
    public UserDTO getUserById(int id) {
        UserEntity userEntity = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User could not be found"));
        return mapToUserDto(userEntity);
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO, int id) {
        UserEntity userEntity = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User could not be found"));
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
    public void deleteUser(int id) {
        UserEntity userEntity = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User could not be found"));
        userRepository.deleteById(id);
    }

    // Map UserEntity to UserDTO
    private UserDTO mapToUserDto(UserEntity userEntity){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(userEntity.getId());
        userDTO.setUsername(userEntity.getUsername());
        userDTO.setPassword(userEntity.getPassword());
        userDTO.setEmail(userEntity.getEmail());
        return userDTO;
    }

    // Map UserDTO to UserEntity
    private UserEntity mapToUserEntity(UserDTO userDTO){
        UserEntity userEntity = new UserEntity();
        userEntity.setId(userDTO.getId());
        userEntity.setUsername(userDTO.getUsername());
        userEntity.setPassword(userDTO.getPassword());
        userEntity.setEmail(userDTO.getEmail());
        return userEntity;
    }

}
