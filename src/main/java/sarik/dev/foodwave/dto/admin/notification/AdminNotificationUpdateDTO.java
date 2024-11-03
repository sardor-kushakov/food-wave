package sarik.dev.foodwave.dto.admin.notification;

import lombok.Data;

import java.util.UUID;

@Data
public class AdminNotificationUpdateDTO {

    private UUID id;
    private boolean isRead;
}
