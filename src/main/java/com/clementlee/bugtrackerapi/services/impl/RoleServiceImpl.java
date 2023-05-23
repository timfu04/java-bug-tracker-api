package com.clementlee.bugtrackerapi.services.impl;

import com.clementlee.bugtrackerapi.dto.RoleDTO;
import com.clementlee.bugtrackerapi.models.Role;
import com.clementlee.bugtrackerapi.repositories.RoleRepository;
import com.clementlee.bugtrackerapi.services.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public RoleDTO createRole(RoleDTO roleDTO) {
        Role role = new Role();
        role.setName(roleDTO.getName());
        Role newRole = roleRepository.save(role);
        return mapToDto(newRole);
    }

    @Override
    public List<RoleDTO> getAllRoles() {
        List<Role> roles = roleRepository.findAll();
        return roles.stream().map(role -> mapToDto(role)).collect(Collectors.toList());
    }

    @Override
    public RoleDTO getRoleById(int id) {
        Role role = roleRepository.findById(id).orElseThrow();
        return mapToDto(role);
    }

    @Override
    public RoleDTO updateRole(RoleDTO roleDTO, int id) {
        Role role = roleRepository.findById(id).orElseThrow();
        role.setName(roleDTO.getName());
        Role updatedRole = roleRepository.save(role);
        return mapToDto(updatedRole);
    }

    @Override
    public void deleteRole(int id) {
        roleRepository.deleteById(id);
    }

    // Mapping methods
    // Map Role to RoleDTO
    private RoleDTO mapToDto(Role role){
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setId(role.getId());
        roleDTO.setName(role.getName());
        return roleDTO;
    }

    // Map RoleDTO to Role
    private Role mapToEntity(RoleDTO roleDTO){
        Role role = new Role();
        role.setId(roleDTO.getId());
        role.setName(roleDTO.getName());
        return role;
    }
}
