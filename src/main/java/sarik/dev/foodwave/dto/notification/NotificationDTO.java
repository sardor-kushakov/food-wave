package sarik.dev.foodwave.dto.notification;

import lombok.Builder;
import lombok.Data;
import sarik.dev.foodwave.dto.user.UserDTO;
import sarik.dev.foodwave.entity.user.Notification;
import sarik.dev.foodwave.enums.user.NotificationType;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class NotificationDTO {

    private UUID id;
    private UserDTO user;
    private NotificationType type;
    private String title;
    private String message;
    private boolean isRead;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    /**
     * Converts a Notification entity to a NotificationDTO.
     *
     * @param notification the Notification entity
     * @return a NotificationDTO with data from the entity
     */
    public static NotificationDTO fromEntity(Notification notification) {
        return NotificationDTO.builder()
                .id(notification.getId())
                .user(UserDTO.fromEntity(notification.getUser())) // Assuming UserDTO has a fromEntity method
                .type(notification.getType())
                .title(notification.getTitle())
                .message(notification.getMessage())
                .isRead(notification.isRead())
                .createdAt(notification.getCreatedAt())
                .updatedAt(notification.getUpdatedAt())
                .build();
    }
}
