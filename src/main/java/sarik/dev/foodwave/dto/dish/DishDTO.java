package sarik.dev.foodwave.dto.dish;

import lombok.Data;
import sarik.dev.foodwave.dto.category.CategoryDTO;
import sarik.dev.foodwave.dto.ingredient.IngredientDTO;
import sarik.dev.foodwave.dto.review.ReviewDTO;
import sarik.dev.foodwave.entity.user.Dish;
import sarik.dev.foodwave.enums.user.DishType;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

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

    public static DishDTO fromEntity(Dish dish) {
        DishDTO dto = new DishDTO();
        dto.setId(dish.getId());
        dto.setName(dish.getName());
        dto.setDescription(dish.getDescription());
        dto.setPrice(dish.getPrice());
        dto.setImageUrl(dish.getImageUrl());
        dto.setDishType(dish.getDishType());
        dto.setAvailable(dish.isAvailable());
        dto.setCreatedAt(dish.getCreatedAt());
        dto.setUpdatedAt(dish.getUpdatedAt());

        // Assuming CategoryDTO has a fromEntity method
        dto.setCategory(dish.getCategory() != null ? CategoryDTO.fromEntity(dish.getCategory()) : null);

        // Mapping ingredients if IngredientDTO has a fromEntity method
        dto.setIngredients(dish.getIngredients().stream()
                .map(IngredientDTO::fromEntity)
                .collect(Collectors.toSet()));

        // Mapping reviews if ReviewDTO has a fromEntity method
        dto.setReviews(dish.getReviews().stream()
                .map(ReviewDTO::fromEntity)
                .collect(Collectors.toSet()));

        return dto;
    }
}
