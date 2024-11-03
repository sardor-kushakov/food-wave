package sarik.dev.foodwave.dto.notification;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import sarik.dev.foodwave.enums.user.NotificationType;

import java.util.UUID;

@Data
public class NotificationCreateDTO {

    @NotNull
    private UUID userId;

    @NotNull
    private NotificationType type;

    @NotBlank
    private String title;

    @NotBlank
    private String message;
}
