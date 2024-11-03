package sarik.dev.foodwave.dto.order.dispatch;

import lombok.Data;
import sarik.dev.foodwave.dto.order.OrderDTO;
import sarik.dev.foodwave.enums.admin.DispatchStatus;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class OrderDispatchDTO {

    private UUID id;
    private OrderDTO order;
    private String courierName;
    private String courierContact;
    private DispatchStatus dispatchStatus;
    private LocalDateTime estimatedDeliveryTime;
    private LocalDateTime dispatchedAt;
    private LocalDateTime lastUpdated;
}
