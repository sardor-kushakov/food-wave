package sarik.dev.foodwave.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sarik.dev.foodwave.dto.auth.permission.AuthPermissionCreateDTO;
import sarik.dev.foodwave.dto.auth.permission.AuthPermissionDTO;
import sarik.dev.foodwave.dto.auth.permission.AuthPermissionUpdateDTO;
import sarik.dev.foodwave.entity.auth.AuthPermission;
import sarik.dev.foodwave.entity.auth.AuthRole;
import sarik.dev.foodwave.repository.AuthPermissionRepository;
import sarik.dev.foodwave.repository.AuthRoleRepository;
import sarik.dev.foodwave.service.AuthPermissionService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthPermissionServiceImpl implements AuthPermissionService {

    private final AuthPermissionRepository authPermissionRepository;
    private final AuthRoleRepository authRoleRepository;

    @Override
    @Transactional
    public AuthPermissionDTO createPermission(AuthPermissionCreateDTO authPermissionCreateDTO) {
        AuthPermission authPermission = AuthPermission.builder().name(authPermissionCreateDTO.getName()).build();

        authPermissionRepository.save(authPermission);
        return AuthPermissionDTO.fromEntity(authPermission);
    }

    @Override
    public AuthPermissionDTO getPermissionById(UUID permissionId) {
        AuthPermission authPermission = authPermissionRepository.findById(permissionId).orElseThrow(() -> new IllegalArgumentException("Permission not found"));
        return AuthPermissionDTO.fromEntity(authPermission);
    }

    @Override
    public AuthPermissionDTO getPermissionByName(String name) {
        AuthPermission authPermission = authPermissionRepository.findByName(name).orElseThrow(() -> new IllegalArgumentException("Permission not found"));
        return AuthPermissionDTO.fromEntity(authPermission);
    }

    @Override
    public List<AuthPermissionDTO> getAllPermissions() {
        return authPermissionRepository.findAll().stream().map(AuthPermissionDTO::fromEntity).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public AuthPermissionDTO updatePermission(UUID permissionId, AuthPermissionUpdateDTO authPermissionUpdateDTO) {
        AuthPermission authPermission = authPermissionRepository.findById(permissionId).orElseThrow(() -> new IllegalArgumentException("Permission not found"));

        authPermission.setName(authPermissionUpdateDTO.getName());
        authPermissionRepository.save(authPermission);

        return AuthPermissionDTO.fromEntity(authPermission);
    }

    @Override
    @Transactional
    public void deletePermission(UUID permissionId) {
        authPermissionRepository.deleteById(permissionId);
    }

    @Override
    @Transactional
    public void addRoleToPermission(UUID permissionId, UUID roleId) {
        AuthPermission authPermission = authPermissionRepository.findById(permissionId).orElseThrow(() -> new IllegalArgumentException("Permission not found"));

        AuthRole authRole = authRoleRepository.findById(roleId).orElseThrow(() -> new IllegalArgumentException("Role not found"));

        authPermission.addRole(authRole);
        authPermissionRepository.save(authPermission);
    }

    @Override
    @Transactional
    public void removeRoleFromPermission(UUID permissionId, UUID roleId) {
        AuthPermission authPermission = authPermissionRepository.findById(permissionId).orElseThrow(() -> new IllegalArgumentException("Permission not found"));

        AuthRole authRole = authRoleRepository.findById(roleId).orElseThrow(() -> new IllegalArgumentException("Role not found"));

        authPermission.removeRole(authRole);
        authPermissionRepository.save(authPermission);
    }
}
