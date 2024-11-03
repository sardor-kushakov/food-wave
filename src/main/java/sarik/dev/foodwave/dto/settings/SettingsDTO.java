package sarik.dev.foodwave.dto.settings;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class SettingsDTO {

    private UUID id;
    private String key;
    private String value;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
