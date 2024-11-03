package sarik.dev.foodwave.dto.transaction;

import lombok.Data;
import sarik.dev.foodwave.dto.payment.PaymentDTO;
import sarik.dev.foodwave.enums.user.TransactionStatus;
import sarik.dev.foodwave.enums.user.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class TransactionDTO {

    private UUID id;
    private PaymentDTO payment;
    private TransactionType transactionType;
    private BigDecimal amount;
    private TransactionStatus status;
    private String referenceNumber;
    private LocalDateTime transactionDate;
}
