package sarik.dev.foodwave.dto.order.history;

import lombok.Data;
import lombok.Builder;
import sarik.dev.foodwave.dto.order.OrderDTO;
import sarik.dev.foodwave.dto.user.UserDTO;
import sarik.dev.foodwave.entity.admin.OrderHistory;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class OrderHistoryDTO {

    private UUID id;
    private UserDTO user;
    private OrderDTO order;
    private BigDecimal totalPrice;
    private String status;
    private LocalDateTime orderDate;

    /**
     * Converts an OrderHistory entity to an OrderHistoryDTO.
     *
     * @param orderHistory the OrderHistory entity
     * @return an OrderHistoryDTO with data from the entity
     */
    public static OrderHistoryDTO fromEntity(OrderHistory orderHistory) {
        return OrderHistoryDTO.builder()
                .id(orderHistory.getId())
                .user(UserDTO.fromEntity(orderHistory.getUser())) // Assuming UserDTO has a fromEntity method
                .order(OrderDTO.fromEntity(orderHistory.getOrder())) // Assuming OrderDTO has a fromEntity method
                .totalPrice(orderHistory.getTotalPrice())
                .status(orderHistory.getStatus())
                .orderDate(orderHistory.getOrderDate())
                .build();
    }
}
