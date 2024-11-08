package sarik.dev.foodwave.dto.category;

import lombok.Data;
import lombok.Builder;
import sarik.dev.foodwave.dto.dish.DishDTO;
import sarik.dev.foodwave.entity.admin.Category;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@Builder
public class CategoryDTO {

    private UUID id;
    private String name;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Set<DishDTO> dishes;

    /**
     * Converts a Category entity to a CategoryDTO.
     *
     * @param category the Category entity
     * @return a CategoryDTO with data from the entity
     */
    public static CategoryDTO fromEntity(Category category) {
        return CategoryDTO.builder()
                .id(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .createdAt(category.getCreatedAt())
                .updatedAt(category.getUpdatedAt())
                .dishes(category.getDishes().stream()
                        .map(DishDTO::fromEntity) // Assuming DishDTO has a fromEntity method
                        .collect(Collectors.toSet()))
                .build();
    }
}
