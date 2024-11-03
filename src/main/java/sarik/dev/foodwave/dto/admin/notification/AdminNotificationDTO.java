package sarik.dev.foodwave.dto.admin.notification;

import lombok.Data;
import sarik.dev.foodwave.dto.admin.AdminDTO;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class AdminNotificationDTO {

    private UUID id;
    private AdminDTO admin;
    private String message;
    private LocalDateTime createdAt;
    private boolean isRead;
}
