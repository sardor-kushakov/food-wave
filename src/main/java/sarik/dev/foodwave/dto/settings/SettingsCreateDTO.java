package sarik.dev.foodwave.dto.settings;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SettingsCreateDTO {

    @NotBlank
    private String key;

    @NotBlank
    private String value;
}
