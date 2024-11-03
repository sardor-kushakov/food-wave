package sarik.dev.foodwave.dto.admin.notification;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.UUID;

@Data
public class AdminNotificationCreateDTO {

    private UUID adminId;

    @NotBlank
    private String message;
}
