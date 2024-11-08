package sarik.dev.foodwave.dto.order;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import sarik.dev.foodwave.dto.order.item.OrderItemCreateDTO;
import sarik.dev.foodwave.enums.user.PaymentMethod;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Data
public class OrderCreateDTO {

    @NotNull
    private UUID userId;

    private List<OrderItemCreateDTO> items; // New order items to add
    @NotNull
    private BigDecimal totalPrice;

    @NotNull
    private PaymentMethod paymentMethod;
}
