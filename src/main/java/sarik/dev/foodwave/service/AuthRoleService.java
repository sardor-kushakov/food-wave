package sarik.dev.foodwave.service;

import sarik.dev.foodwave.dto.auth.role.AuthRoleCreateDTO;
import sarik.dev.foodwave.dto.auth.role.AuthRoleDTO;
import sarik.dev.foodwave.dto.auth.role.AuthRoleUpdateDTO;

import java.util.List;
import java.util.UUID;

public interface AuthRoleService {

    AuthRoleDTO createRole(AuthRoleCreateDTO authRoleCreateDTO);

    AuthRoleDTO getRoleById(UUID roleId);

    AuthRoleDTO getRoleByName(String name);

    List<AuthRoleDTO> getAllRoles();

    AuthRoleDTO updateRole(UUID roleId, AuthRoleUpdateDTO authRoleUpdateDTO);

    void deleteRole(UUID roleId);

    void addPermissionToRole(UUID roleId, UUID permissionId);

    void removePermissionFromRole(UUID roleId, UUID permissionId);
}
