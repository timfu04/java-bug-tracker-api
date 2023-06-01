package com.clementlee.bugtrackerapi.services.impl;

import com.clementlee.bugtrackerapi.dto.RoleDTO;
import com.clementlee.bugtrackerapi.exceptions.RoleNotFoundException;
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
        return mapToRoleDto(newRole);
    }

    @Override
    public List<RoleDTO> getAllRoles() {
        List<Role> roles = roleRepository.findAll();
        return roles.stream().map(role -> mapToRoleDto(role)).collect(Collectors.toList()); // convert list of Role to list of RoleDTO
    }

    @Override
    public RoleDTO getRoleByRoleId(int roleId) {
        Role role = roleRepository.findById(roleId).orElseThrow(() -> new RoleNotFoundException("Role could not be found"));
        return mapToRoleDto(role);
    }

    @Override
    public RoleDTO updateRoleByRoleId(RoleDTO roleDTO, int roleId) {
        Role role = roleRepository.findById(roleId).orElseThrow(() -> new RoleNotFoundException("Role could not be found"));
        role.setName(roleDTO.getName());
        Role updatedRole = roleRepository.save(role);
        return mapToRoleDto(updatedRole);
    }

    @Override
    public void deleteRoleByRoleId(int roleId) {
        roleRepository.findById(roleId).orElseThrow(() -> new RoleNotFoundException("Role could not be found"));
        roleRepository.deleteById(roleId);
    }

    // Map Role to RoleDTO
    private RoleDTO mapToRoleDto(Role role){
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setId(role.getId());
        roleDTO.setName(role.getName());
        roleDTO.setUsers(role.getUsers());
        return roleDTO;
    }

}
