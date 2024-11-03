package sarik.dev.foodwave.dto.order.status;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import sarik.dev.foodwave.enums.user.OrderStatus;

import java.util.UUID;

@Data
public class OrderStatusHistoryCreateDTO {

    @NotNull
    private UUID orderId;

    @NotNull
    private OrderStatus status;

    private String changedBy; // Optional field
}
