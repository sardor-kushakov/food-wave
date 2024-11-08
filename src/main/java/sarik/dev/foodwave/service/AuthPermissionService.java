package sarik.dev.foodwave.service;

import sarik.dev.foodwave.dto.auth.permission.AuthPermissionCreateDTO;
import sarik.dev.foodwave.dto.auth.permission.AuthPermissionDTO;
import sarik.dev.foodwave.dto.auth.permission.AuthPermissionUpdateDTO;

import java.util.List;
import java.util.UUID;

public interface AuthPermissionService {

    AuthPermissionDTO createPermission(AuthPermissionCreateDTO authPermissionCreateDTO);

    AuthPermissionDTO getPermissionById(UUID permissionId);

    AuthPermissionDTO getPermissionByName(String name);

    List<AuthPermissionDTO> getAllPermissions();

    AuthPermissionDTO updatePermission(UUID permissionId, AuthPermissionUpdateDTO authPermissionUpdateDTO);

    void deletePermission(UUID permissionId);

    void addRoleToPermission(UUID permissionId, UUID roleId);

    void removeRoleFromPermission(UUID permissionId, UUID roleId);
}
