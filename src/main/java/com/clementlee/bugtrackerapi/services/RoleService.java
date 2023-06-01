package com.clementlee.bugtrackerapi.services;

import com.clementlee.bugtrackerapi.dto.RoleDTO;

import java.util.List;

public interface RoleService {

    RoleDTO createRole (RoleDTO roleDTO);
    List<RoleDTO> getAllRoles();
    RoleDTO getRoleByRoleId(int roleId);
    RoleDTO updateRoleByRoleId(RoleDTO roleDTO, int roleId);
    void deleteRoleByRoleId(int roleId);

}
