package sarik.dev.foodwave.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sarik.dev.foodwave.dto.user.UserCreateDTO;
import sarik.dev.foodwave.dto.user.UserDTO;
import sarik.dev.foodwave.dto.user.UserUpdateDTO;
import sarik.dev.foodwave.entity.user.User;
import sarik.dev.foodwave.repository.UserRepository;
import sarik.dev.foodwave.service.UserService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserDTO createUser(UserCreateDTO userCreateDTO) {
        User user = User.builder()
                .firstName(userCreateDTO.getFirstName())
                .lastName(userCreateDTO.getLastName())
                .email(userCreateDTO.getEmail())
                .password(userCreateDTO.getPassword())
                .phoneNumber(userCreateDTO.getPhoneNumber())
                .gender(userCreateDTO.getGender())
                .userRole(userCreateDTO.getUserRole())
                .profilePictureUrl(userCreateDTO.getProfilePictureUrl())
                .isActive(userCreateDTO.isActive())
                .build();

        userRepository.save(user);
        return UserDTO.fromEntity(user);
    }

    @Override
    public UserDTO getUserById(UUID userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        return UserDTO.fromEntity(user);
    }

    @Override
    public UserDTO getUserByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        return UserDTO.fromEntity(user);
    }

    @Override
    public List<UserDTO> getAllActiveUsers() {
        return userRepository.findAll().stream()
                .filter(User::isActive)
                .map(UserDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public UserDTO updateUser(UUID userId, UserUpdateDTO userUpdateDTO) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        user.setFirstName(userUpdateDTO.getFirstName());
        user.setLastName(userUpdateDTO.getLastName());
        user.setEmail(userUpdateDTO.getEmail());
        user.setPhoneNumber(userUpdateDTO.getPhoneNumber());
        user.setGender(userUpdateDTO.getGender());
        user.setProfilePictureUrl(userUpdateDTO.getProfilePictureUrl());

        userRepository.save(user);
        return UserDTO.fromEntity(user);
    }

    @Override
    @Transactional
    public void deleteUser(UUID userId) {
        userRepository.deleteById(userId);
    }

    @Override
    @Transactional
    public void recordUserLogin(UUID userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        user.recordLogin();
        userRepository.save(user);
    }
}
