package sarik.dev.foodwave.dto.login;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class LoginHistoryCreateDTO {

    @NotNull
    private UUID userId;

    private String ipAddress;
    private String deviceInfo;
}
