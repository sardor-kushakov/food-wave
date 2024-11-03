package sarik.dev.foodwave.dto.dish;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import sarik.dev.foodwave.enums.user.DishType;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.Set;

@Data
public class DishUpdateDTO {

    private UUID id;

    @NotBlank
    @Size(max = 100)
    private String name;

    private String description;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than zero")
    private BigDecimal price;

    private String imageUrl;

    @NotNull
    private DishType dishType;

    private boolean isAvailable;
    private UUID categoryId;
    private Set<UUID> ingredientIds;
}
