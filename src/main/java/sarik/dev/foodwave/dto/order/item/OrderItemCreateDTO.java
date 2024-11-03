package sarik.dev.foodwave.dto.order.item;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class OrderItemCreateDTO {

    @NotNull
    private UUID orderId;

    @NotNull
    private UUID dishId;

    @NotNull
    @Min(1)
    private Integer quantity;
}
