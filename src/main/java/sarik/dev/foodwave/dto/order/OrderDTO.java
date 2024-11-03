package sarik.dev.foodwave.dto.order;

import lombok.Data;
import sarik.dev.foodwave.dto.order.item.OrderItemDTO;
import sarik.dev.foodwave.dto.user.UserDTO;
import sarik.dev.foodwave.enums.user.OrderStatus;
import sarik.dev.foodwave.enums.user.PaymentMethod;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Data
public class OrderDTO {

    private UUID id;
    private UserDTO user;
    private Set<OrderItemDTO> items;
    private BigDecimal totalPrice;
    private OrderStatus status;
    private PaymentMethod paymentMethod;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
