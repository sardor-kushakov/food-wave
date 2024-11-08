package sarik.dev.foodwave.dto.order.item;

import lombok.Builder;
import lombok.Data;
import sarik.dev.foodwave.dto.dish.DishDTO;
import sarik.dev.foodwave.dto.order.OrderDTO;
import sarik.dev.foodwave.entity.user.OrderItem;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
public class OrderItemDTO {

    private UUID id;
    private OrderDTO order;
    private DishDTO dish;
    private Integer quantity;
    private BigDecimal totalPrice;

    /**
     * Converts an OrderItem entity to an OrderItemDTO.
     *
     * @param orderItem the OrderItem entity
     * @return an OrderItemDTO with data from the entity
     */
    public static OrderItemDTO fromEntity(OrderItem orderItem) {
        return OrderItemDTO.builder()
                .id(orderItem.getId())
                .order(OrderDTO.fromEntity(orderItem.getOrder())) // Assuming OrderDTO has a fromEntity method
                .dish(DishDTO.fromEntity(orderItem.getDish())) // Assuming DishDTO has a fromEntity method
                .quantity(orderItem.getQuantity())
                .totalPrice(orderItem.getTotalPrice())
                .build();
    }
}
