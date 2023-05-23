package com.clementlee.bugtrackerapi.services;

import com.clementlee.bugtrackerapi.dto.RoleDTO;

import java.util.List;

public interface RoleService {
    RoleDTO createRole (RoleDTO roleDTO);
    List<RoleDTO> getAllRoles();
    RoleDTO getRoleById(int id);
    RoleDTO updateRole(RoleDTO roleDTO, int id);
    void deleteRole(int id);
}
