package sarik.dev.foodwave.service;

import sarik.dev.foodwave.dto.user.UserCreateDTO;
import sarik.dev.foodwave.dto.user.UserDTO;
import sarik.dev.foodwave.dto.user.UserUpdateDTO;

import java.util.List;
import java.util.UUID;

public interface UserService {

    UserDTO createUser(UserCreateDTO userCreateDTO);

    UserDTO getUserById(UUID userId);

    UserDTO getUserByEmail(String email);

    List<UserDTO> getAllActiveUsers();

    UserDTO updateUser(UUID userId, UserUpdateDTO userUpdateDTO);

    void deleteUser(UUID userId);

    void recordUserLogin(UUID userId);
}
