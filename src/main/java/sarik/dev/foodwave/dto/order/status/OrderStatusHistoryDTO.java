package sarik.dev.foodwave.dto.order.status;

import lombok.Data;
import sarik.dev.foodwave.dto.order.OrderDTO;
import sarik.dev.foodwave.entity.user.OrderStatusHistory;
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

    public static OrderStatusHistoryDTO fromEntity(OrderStatusHistory orderStatusHistory) {
        OrderStatusHistoryDTO dto = new OrderStatusHistoryDTO();
        dto.setId(orderStatusHistory.getId());

        // Agar Order entity si mavjud bo'lsa, uni DTO ga o'tkazish
        if (orderStatusHistory.getOrder() != null) {
            dto.setOrder(OrderDTO.fromEntity(orderStatusHistory.getOrder())); // OrderDTO uchun fromEntity metodini yozing
        }

        dto.setStatus(orderStatusHistory.getStatus());
        dto.setChangedAt(orderStatusHistory.getChangedAt());
        dto.setChangedBy(orderStatusHistory.getChangedBy());

        return dto;
    }
}
