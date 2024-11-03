package sarik.dev.foodwave.dto.notification;

import lombok.Data;
import java.util.UUID;

@Data
public class NotificationUpdateDTO {

    private UUID id;
    private boolean isRead;
}
