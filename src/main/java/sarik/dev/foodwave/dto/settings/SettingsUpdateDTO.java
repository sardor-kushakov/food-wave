package sarik.dev.foodwave.dto.settings;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.UUID;

@Data
public class SettingsUpdateDTO {

    private UUID id;

    @NotBlank
    private String key;

    @NotBlank
    private String value;
}
