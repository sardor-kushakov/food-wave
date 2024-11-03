package sarik.dev.foodwave.dto.order.history;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class OrderHistoryCreateDTO {

    private UUID userId;
    private UUID orderId;

    @NotNull
    private BigDecimal totalPrice;

    @NotBlank
    private String status;
}
