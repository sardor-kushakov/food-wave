package sarik.dev.foodwave.dto.cart.item;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class CartItemUpdateDTO {

    private UUID id;

    @NotNull
    private UUID dishId;

    @NotNull
    @Min(1)
    private Integer quantity;

    private BigDecimal totalPrice;
}
