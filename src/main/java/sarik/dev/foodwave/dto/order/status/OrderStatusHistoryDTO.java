package sarik.dev.foodwave.dto.order.status;

import lombok.Data;
import sarik.dev.foodwave.dto.order.OrderDTO;
import sarik.dev.foodwave.enums.user.OrderStatus;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class OrderStatusHistoryDTO {

    private UUID id;
    private OrderDTO order;
    private OrderStatus status;
    private LocalDateTime changedAt;
    private String changedBy;
}
