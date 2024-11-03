package sarik.dev.foodwave.dto.category;

import lombok.Data;
import sarik.dev.foodwave.dto.dish.DishDTO;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Data
public class CategoryDTO {

    private UUID id;
    private String name;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Set<DishDTO> dishes;
}
