package sarik.dev.foodwave.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sarik.dev.foodwave.dto.login.LoginHistoryCreateDTO;
import sarik.dev.foodwave.dto.login.LoginHistoryDTO;
import sarik.dev.foodwave.entity.user.LoginHistory;
import sarik.dev.foodwave.entity.user.User;
import sarik.dev.foodwave.repository.LoginHistoryRepository;
import sarik.dev.foodwave.repository.UserRepository;
import sarik.dev.foodwave.service.LoginHistoryService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LoginHistoryServiceImpl implements LoginHistoryService {

    private final LoginHistoryRepository loginHistoryRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public LoginHistoryDTO createLoginHistory(LoginHistoryCreateDTO loginHistoryCreateDTO) {
        User user = userRepository.findById(loginHistoryCreateDTO.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        LoginHistory loginHistory = LoginHistory.builder()
                .user(user)
                .ipAddress(loginHistoryCreateDTO.getIpAddress())
                .deviceInfo(loginHistoryCreateDTO.getDeviceInfo())
                .build();

        loginHistoryRepository.save(loginHistory);
        return LoginHistoryDTO.fromEntity(loginHistory);
    }

    @Override
    public LoginHistoryDTO getLoginHistoryById(UUID loginHistoryId) {
        LoginHistory loginHistory = loginHistoryRepository.findById(loginHistoryId)
                .orElseThrow(() -> new IllegalArgumentException("Login history not found"));
        return LoginHistoryDTO.fromEntity(loginHistory);
    }

    @Override
    public List<LoginHistoryDTO> getLoginHistoryByUserId(UUID userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        return loginHistoryRepository.findByUser(user).stream()
                .map(LoginHistoryDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<LoginHistoryDTO> getAllLoginHistories() {
        return loginHistoryRepository.findAll().stream()
                .map(LoginHistoryDTO::fromEntity)
                .collect(Collectors.toList());
    }
}
