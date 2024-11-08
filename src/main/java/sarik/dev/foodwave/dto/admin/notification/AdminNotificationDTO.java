package sarik.dev.foodwave.dto.admin.notification;

import lombok.Builder;
import lombok.Data;
import sarik.dev.foodwave.entity.admin.AdminNotification;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class AdminNotificationDTO {

    private UUID id;
    private String message;
    private boolean isRead;
    private LocalDateTime createdAt;
    private UUID adminId;

    // Entitydan DTO ga aylantiruvchi fromEntity metodi
    public static AdminNotificationDTO fromEntity(AdminNotification notification) {
        return AdminNotificationDTO.builder()
                .id(notification.getId())
                .message(notification.getMessage())
                .isRead(notification.isRead())
                .createdAt(notification.getCreatedAt())
                .adminId(notification.getAdmin().getId()) // Adminning ID sini olish
                .build();
    }
}
