package sarik.dev.foodwave.dto.payment;

import lombok.Data;
import sarik.dev.foodwave.enums.user.PaymentStatus;

import java.util.UUID;

@Data
public class PaymentUpdateDTO {

    private UUID id;
    private PaymentStatus status;
}
