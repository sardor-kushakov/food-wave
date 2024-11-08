package sarik.dev.foodwave.dto.order.dispatch;

import lombok.Data;
import lombok.Builder;
import sarik.dev.foodwave.dto.order.OrderDTO;
import sarik.dev.foodwave.entity.admin.OrderDispatch;
import sarik.dev.foodwave.enums.admin.DispatchStatus;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class OrderDispatchDTO {

    private UUID id;
    private OrderDTO order;
    private String courierName;
    private String courierContact;
    private DispatchStatus dispatchStatus;
    private LocalDateTime estimatedDeliveryTime;
    private LocalDateTime dispatchedAt;
    private LocalDateTime lastUpdated;

    /**
     * Converts an OrderDispatch entity to an OrderDispatchDTO.
     *
     * @param orderDispatch the OrderDispatch entity
     * @return an OrderDispatchDTO with data from the entity
     */
    public static OrderDispatchDTO fromEntity(OrderDispatch orderDispatch) {
        return OrderDispatchDTO.builder()
                .id(orderDispatch.getId())
                .order(OrderDTO.fromEntity(orderDispatch.getOrder())) // Assuming OrderDTO has a fromEntity method
                .courierName(orderDispatch.getCourierName())
                .courierContact(orderDispatch.getCourierContact())
                .dispatchStatus(orderDispatch.getDispatchStatus())
                .estimatedDeliveryTime(orderDispatch.getEstimatedDeliveryTime())
                .dispatchedAt(orderDispatch.getDispatchedAt())
                .lastUpdated(orderDispatch.getLastUpdated())
                .build();
    }
}
