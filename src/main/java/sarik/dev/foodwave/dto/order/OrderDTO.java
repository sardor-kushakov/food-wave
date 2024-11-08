package sarik.dev.foodwave.dto.order;

import lombok.Builder;
import lombok.Data;
import sarik.dev.foodwave.dto.order.item.OrderItemDTO;
import sarik.dev.foodwave.dto.user.UserDTO;
import sarik.dev.foodwave.entity.user.Order;
import sarik.dev.foodwave.enums.user.OrderStatus;
import sarik.dev.foodwave.enums.user.PaymentMethod;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@Builder
public class OrderDTO {

    private UUID id;
    private UserDTO user;
    private Set<OrderItemDTO> items;
    private BigDecimal totalPrice;
    private OrderStatus status;
    private PaymentMethod paymentMethod;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    /**
     * Converts an Order entity to an OrderDTO.
     *
     * @param order the Order entity
     * @return an OrderDTO with data from the entity
     */
    public static OrderDTO fromEntity(Order order) {
        return OrderDTO.builder()
                .id(order.getId())
                .user(UserDTO.fromEntity(order.getUser())) // Assuming UserDTO has a fromEntity method
                .items(order.getItems().stream()
                        .map(OrderItemDTO::fromEntity) // Assuming OrderItemDTO has a fromEntity method
                        .collect(Collectors.toSet()))
                .totalPrice(order.getTotalPrice())
                .status(order.getStatus())
                .paymentMethod(order.getPaymentMethod())
                .createdAt(order.getCreatedAt())
                .updatedAt(order.getUpdatedAt())
                .build();
    }
}
