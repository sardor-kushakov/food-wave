package sarik.dev.foodwave.dto.order;

import lombok.Data;
import sarik.dev.foodwave.enums.user.OrderStatus;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class OrderUpdateDTO {

    private UUID id;
    private BigDecimal totalPrice;
    private OrderStatus status;
}
