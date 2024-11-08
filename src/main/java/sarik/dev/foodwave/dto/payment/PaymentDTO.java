package sarik.dev.foodwave.dto.payment;

import lombok.Data;
import lombok.Builder;
import sarik.dev.foodwave.dto.order.OrderDTO;
import sarik.dev.foodwave.entity.user.Payment;
import sarik.dev.foodwave.enums.user.PaymentMethod;
import sarik.dev.foodwave.enums.user.PaymentStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class PaymentDTO {

    private UUID id;
    private OrderDTO order;
    private PaymentMethod paymentMethod;
    private BigDecimal amount;
    private PaymentStatus status;
    private LocalDateTime paymentDate;

    /**
     * Converts a Payment entity to a PaymentDTO.
     *
     * @param payment the Payment entity
     * @return a PaymentDTO with data from the entity
     */
    public static PaymentDTO fromEntity(Payment payment) {
        return PaymentDTO.builder()
                .id(payment.getId())
                .order(OrderDTO.fromEntity(payment.getOrder())) // Assuming OrderDTO has a fromEntity method
                .paymentMethod(payment.getPaymentMethod())
                .amount(payment.getAmount())
                .status(payment.getStatus())
                .paymentDate(payment.getPaymentDate())
                .build();
    }
}
