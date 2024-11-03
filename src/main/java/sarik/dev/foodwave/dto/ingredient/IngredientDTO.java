package sarik.dev.foodwave.dto.ingredient;

import lombok.Data;
import sarik.dev.foodwave.dto.dish.DishDTO;

import java.util.Set;
import java.util.UUID;

@Data
public class IngredientDTO {

    private UUID id;
    private String ingredientName;
    private String unit;
    private Set<DishDTO> dishes;
}
