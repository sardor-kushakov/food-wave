package sarik.dev.foodwave.dto.payment;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import sarik.dev.foodwave.enums.user.PaymentMethod;

import java.util.UUID;
import java.math.BigDecimal;

@Data
public class PaymentCreateDTO {

    @NotNull
    private UUID orderId;

    @NotNull
    private PaymentMethod paymentMethod;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal amount;
}
