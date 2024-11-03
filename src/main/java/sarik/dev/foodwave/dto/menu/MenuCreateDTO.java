package sarik.dev.foodwave.dto.menu;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.UUID;

@Data
public class MenuCreateDTO {

    @NotBlank
    @Size(max = 100)
    private String name;

    private String description;
    private UUID restaurantId;
}
