package sarik.dev.foodwave.dto.payment;

import lombok.Data;
import sarik.dev.foodwave.dto.order.OrderDTO;
import sarik.dev.foodwave.enums.user.PaymentMethod;
import sarik.dev.foodwave.enums.user.PaymentStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class PaymentDTO {

    private UUID id;
    private OrderDTO order;
    private PaymentMethod paymentMethod;
    private BigDecimal amount;
    private PaymentStatus status;
    private LocalDateTime paymentDate;
}
