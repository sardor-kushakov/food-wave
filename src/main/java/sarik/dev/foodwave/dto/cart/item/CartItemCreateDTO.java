package sarik.dev.foodwave.dto.cart.item;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class CartItemCreateDTO {

    @NotNull
    private UUID cartId;

    @NotNull
    private UUID dishId;

    @NotNull
    @Min(1)
    private Integer quantity;
}
