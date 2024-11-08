package sarik.dev.foodwave.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sarik.dev.foodwave.dto.auth.role.AuthRoleCreateDTO;
import sarik.dev.foodwave.dto.auth.role.AuthRoleDTO;
import sarik.dev.foodwave.dto.auth.role.AuthRoleUpdateDTO;
import sarik.dev.foodwave.entity.auth.AuthPermission;
import sarik.dev.foodwave.entity.auth.AuthRole;
import sarik.dev.foodwave.repository.AuthPermissionRepository;
import sarik.dev.foodwave.repository.AuthRoleRepository;
import sarik.dev.foodwave.service.AuthRoleService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthRoleServiceImpl implements AuthRoleService {

    private final AuthRoleRepository authRoleRepository;
    private final AuthPermissionRepository authPermissionRepository;

    @Override
    @Transactional
    public AuthRoleDTO createRole(AuthRoleCreateDTO authRoleCreateDTO) {
        AuthRole authRole = AuthRole.builder()
                .name(authRoleCreateDTO.getName())
                .build();

        authRoleRepository.save(authRole);
        return AuthRoleDTO.fromEntity(authRole);
    }

    @Override
    public AuthRoleDTO getRoleById(UUID roleId) {
        AuthRole authRole = authRoleRepository.findById(roleId)
                .orElseThrow(() -> new IllegalArgumentException("Role not found"));
        return AuthRoleDTO.fromEntity(authRole);
    }

    @Override
    public AuthRoleDTO getRoleByName(String name) {
        AuthRole authRole = authRoleRepository.findByName(name)
                .orElseThrow(() -> new IllegalArgumentException("Role not found"));
        return AuthRoleDTO.fromEntity(authRole);
    }

    @Override
    public List<AuthRoleDTO> getAllRoles() {
        return authRoleRepository.findAll().stream()
                .map(AuthRoleDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public AuthRoleDTO updateRole(UUID roleId, AuthRoleUpdateDTO authRoleUpdateDTO) {
        AuthRole authRole = authRoleRepository.findById(roleId)
                .orElseThrow(() -> new IllegalArgumentException("Role not found"));

        authRole.setName(authRoleUpdateDTO.getName());
        authRoleRepository.save(authRole);

        return AuthRoleDTO.fromEntity(authRole);
    }

    @Override
    @Transactional
    public void deleteRole(UUID roleId) {
        authRoleRepository.deleteById(roleId);
    }

    @Override
    @Transactional
    public void addPermissionToRole(UUID roleId, UUID permissionId) {
        AuthRole authRole = authRoleRepository.findById(roleId)
                .orElseThrow(() -> new IllegalArgumentException("Role not found"));

        AuthPermission authPermission = authPermissionRepository.findById(permissionId)
                .orElseThrow(() -> new IllegalArgumentException("Permission not found"));

        authRole.addPermission(authPermission);
        authRoleRepository.save(authRole);
    }

    @Override
    @Transactional
    public void removePermissionFromRole(UUID roleId, UUID permissionId) {
        AuthRole authRole = authRoleRepository.findById(roleId)
                .orElseThrow(() -> new IllegalArgumentException("Role not found"));

        AuthPermission authPermission = authPermissionRepository.findById(permissionId)
                .orElseThrow(() -> new IllegalArgumentException("Permission not found"));

        authRole.removePermission(authPermission);
        authRoleRepository.save(authRole);
    }
}
