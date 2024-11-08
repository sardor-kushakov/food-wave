package sarik.dev.foodwave.dto.login;

import lombok.Builder;
import lombok.Data;
import sarik.dev.foodwave.dto.user.UserDTO;
import sarik.dev.foodwave.entity.user.LoginHistory;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class LoginHistoryDTO {

    private UUID id;
    private UserDTO user;
    private LocalDateTime loginTime;
    private String ipAddress;
    private String deviceInfo;

    /**
     * Converts a LoginHistory entity to a LoginHistoryDTO.
     *
     * @param loginHistory the LoginHistory entity
     * @return a LoginHistoryDTO with data from the entity
     */
    public static LoginHistoryDTO fromEntity(LoginHistory loginHistory) {
        return LoginHistoryDTO.builder()
                .id(loginHistory.getId())
                .user(UserDTO.fromEntity(loginHistory.getUser())) // Assuming UserDTO has a fromEntity method
                .loginTime(loginHistory.getLoginTime())
                .ipAddress(loginHistory.getIpAddress())
                .deviceInfo(loginHistory.getDeviceInfo())
                .build();
    }
}
