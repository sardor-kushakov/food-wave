package sarik.dev.foodwave.dto.login;

import lombok.Data;
import sarik.dev.foodwave.dto.user.UserDTO;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class LoginHistoryDTO {

    private UUID id;
    private UserDTO user;
    private LocalDateTime loginTime;
    private String ipAddress;
    private String deviceInfo;
}
