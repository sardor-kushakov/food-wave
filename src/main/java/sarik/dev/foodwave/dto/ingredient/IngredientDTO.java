package sarik.dev.foodwave.dto.ingredient;

import lombok.Builder;
import lombok.Data;
import sarik.dev.foodwave.dto.dish.DishDTO;
import sarik.dev.foodwave.entity.admin.Ingredient;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@Builder
public class IngredientDTO {

    private UUID id;
    private String ingredientName;
    private String unit;
    private Set<DishDTO> dishes;

    /**
     * Converts an Ingredient entity to an IngredientDTO.
     *
     * @param ingredient the Ingredient entity
     * @return an IngredientDTO with data from the entity
     */
    public static IngredientDTO fromEntity(Ingredient ingredient) {
        return IngredientDTO.builder()
                .id(ingredient.getId())
                .ingredientName(ingredient.getIngredientName())
                .unit(ingredient.getUnit())
                .dishes(ingredient.getDishes().stream()
                        .map(DishDTO::fromEntity)
                        .collect(Collectors.toSet()))
                .build();
    }
}
