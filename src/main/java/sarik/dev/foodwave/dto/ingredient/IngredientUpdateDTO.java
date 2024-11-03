package sarik.dev.foodwave.dto.ingredient;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.UUID;

@Data
public class IngredientUpdateDTO {

    private UUID id;

    @NotBlank
    @Size(max = 100)
    private String ingredientName;

    @NotBlank
    @Size(max = 20)
    private String unit;
}
