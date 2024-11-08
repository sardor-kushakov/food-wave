package sarik.dev.foodwave.dto.settings;

import lombok.Data;
import lombok.Builder;
import sarik.dev.foodwave.entity.admin.Settings;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class SettingsDTO {

    private UUID id;
    private String key;
    private String value;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    /**
     * Converts a Settings entity to a SettingsDTO.
     *
     * @param settings the Settings entity
     * @return a SettingsDTO with data from the entity
     */
    public static SettingsDTO fromEntity(Settings settings) {
        return SettingsDTO.builder()
                .id(settings.getId())
                .key(settings.getKey())
                .value(settings.getValue())
                .createdAt(settings.getCreatedAt())
                .updatedAt(settings.getUpdatedAt())
                .build();
    }
}
