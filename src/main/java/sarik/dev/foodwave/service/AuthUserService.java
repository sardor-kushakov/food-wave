package sarik.dev.foodwave.service;

import sarik.dev.foodwave.dto.auth.user.AuthUserCreateDTO;
import sarik.dev.foodwave.dto.auth.user.AuthUserDTO;
import sarik.dev.foodwave.dto.auth.user.AuthUserUpdateDTO;

import java.util.List;
import java.util.UUID;

public interface AuthUserService {

    AuthUserDTO createUser(AuthUserCreateDTO authUserCreateDTO);

    AuthUserDTO getUserById(UUID userId);

    AuthUserDTO getUserByEmail(String email);

    List<AuthUserDTO> getAllUsers();

    AuthUserDTO updateUser(UUID userId, AuthUserUpdateDTO authUserUpdateDTO);

    void deleteUser(UUID userId);

    void addRoleToUser(UUID userId, UUID roleId);

    void removeRoleFromUser(UUID userId, UUID roleId);
}
