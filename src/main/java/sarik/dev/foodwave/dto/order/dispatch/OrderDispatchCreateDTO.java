package sarik.dev.foodwave.dto.order.dispatch;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import sarik.dev.foodwave.enums.admin.DispatchStatus;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class OrderDispatchCreateDTO {

    private UUID orderId;

    @NotBlank
    private String courierName;

    @NotBlank
    private String courierContact;

    @NotNull
    private DispatchStatus dispatchStatus;

    private LocalDateTime estimatedDeliveryTime;
}
