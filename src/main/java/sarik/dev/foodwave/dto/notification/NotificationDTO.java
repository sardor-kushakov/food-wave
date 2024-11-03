package sarik.dev.foodwave.dto.notification;

import lombok.Data;
import sarik.dev.foodwave.dto.user.UserDTO;
import sarik.dev.foodwave.enums.user.NotificationType;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class NotificationDTO {

    private UUID id;
    private UserDTO user;
    private NotificationType type;
    private String title;
    private String message;
    private boolean isRead;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
