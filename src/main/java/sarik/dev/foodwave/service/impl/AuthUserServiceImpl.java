package sarik.dev.foodwave.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sarik.dev.foodwave.dto.auth.user.AuthUserCreateDTO;
import sarik.dev.foodwave.dto.auth.user.AuthUserDTO;
import sarik.dev.foodwave.dto.auth.user.AuthUserUpdateDTO;
import sarik.dev.foodwave.entity.auth.AuthRole;
import sarik.dev.foodwave.entity.auth.AuthUser;
import sarik.dev.foodwave.repository.AuthRoleRepository;
import sarik.dev.foodwave.repository.AuthUserRepository;
import sarik.dev.foodwave.service.AuthUserService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthUserServiceImpl implements AuthUserService {

    private final AuthUserRepository authUserRepository;
    private final AuthRoleRepository authRoleRepository;

    @Override
    @Transactional
    public AuthUserDTO createUser(AuthUserCreateDTO authUserCreateDTO) {
        AuthUser authUser = AuthUser.builder()
                .username(authUserCreateDTO.getUsername())
                .email(authUserCreateDTO.getEmail())
                .password(authUserCreateDTO.getPassword())
                .build();

        authUserRepository.save(authUser);
        return AuthUserDTO.fromEntity(authUser);
    }

    @Override
    public AuthUserDTO getUserById(UUID userId) {
        AuthUser authUser = authUserRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        return AuthUserDTO.fromEntity(authUser);
    }

    @Override
    public AuthUserDTO getUserByEmail(String email) {
        AuthUser authUser = authUserRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        return AuthUserDTO.fromEntity(authUser);
    }

    @Override
    public List<AuthUserDTO> getAllUsers() {
        return authUserRepository.findAll().stream()
                .map(AuthUserDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public AuthUserDTO updateUser(UUID userId, AuthUserUpdateDTO authUserUpdateDTO) {
        AuthUser authUser = authUserRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        authUser.setUsername(authUserUpdateDTO.getUsername());
        authUser.setEmail(authUserUpdateDTO.getEmail());

        authUserRepository.save(authUser);
        return AuthUserDTO.fromEntity(authUser);
    }

    @Override
    @Transactional
    public void deleteUser(UUID userId) {
        authUserRepository.deleteById(userId);
    }

    @Override
    @Transactional
    public void addRoleToUser(UUID userId, UUID roleId) {
        AuthUser authUser = authUserRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        AuthRole authRole = authRoleRepository.findById(roleId)
                .orElseThrow(() -> new IllegalArgumentException("Role not found"));

        authUser.addRole(authRole);
        authUserRepository.save(authUser);
    }

    @Override
    @Transactional
    public void removeRoleFromUser(UUID userId, UUID roleId) {
        AuthUser authUser = authUserRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        AuthRole authRole = authRoleRepository.findById(roleId)
                .orElseThrow(() -> new IllegalArgumentException("Role not found"));

        authUser.removeRole(authRole);
        authUserRepository.save(authUser);
    }
}
