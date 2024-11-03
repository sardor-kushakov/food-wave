package sarik.dev.foodwave.dto.dish;

import lombok.Data;
import sarik.dev.foodwave.dto.category.CategoryDTO;
import sarik.dev.foodwave.dto.ingredient.IngredientDTO;
import sarik.dev.foodwave.dto.review.ReviewDTO;
import sarik.dev.foodwave.enums.user.DishType;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Data
public class DishDTO {

    private UUID id;
    private String name;
    private String description;
    private BigDecimal price;
    private String imageUrl;
    private DishType dishType;
    private boolean isAvailable;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private CategoryDTO category;
    private Set<IngredientDTO> ingredients;
    private Set<ReviewDTO> reviews;
}
