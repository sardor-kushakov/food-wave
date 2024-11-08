package sarik.dev.foodwave.service;

import sarik.dev.foodwave.dto.login.LoginHistoryCreateDTO;
import sarik.dev.foodwave.dto.login.LoginHistoryDTO;

import java.util.List;
import java.util.UUID;

public interface LoginHistoryService {

    LoginHistoryDTO createLoginHistory(LoginHistoryCreateDTO loginHistoryCreateDTO);

    LoginHistoryDTO getLoginHistoryById(UUID loginHistoryId);

    List<LoginHistoryDTO> getLoginHistoryByUserId(UUID userId);

    List<LoginHistoryDTO> getAllLoginHistories();
}
