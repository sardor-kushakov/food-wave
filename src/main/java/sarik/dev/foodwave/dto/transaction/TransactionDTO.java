package sarik.dev.foodwave.dto.transaction;

import lombok.Builder;
import lombok.Data;
import sarik.dev.foodwave.dto.payment.PaymentDTO;
import sarik.dev.foodwave.entity.user.Transaction;
import sarik.dev.foodwave.enums.user.TransactionStatus;
import sarik.dev.foodwave.enums.user.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class TransactionDTO {

    private UUID id;
    private PaymentDTO payment;
    private TransactionType transactionType;
    private BigDecimal amount;
    private TransactionStatus status;
    private String referenceNumber;
    private LocalDateTime transactionDate;

    /**
     * Converts a Transaction entity to a TransactionDTO.
     *
     * @param transaction the Transaction entity
     * @return a TransactionDTO with data from the entity
     */
    public static TransactionDTO fromEntity(Transaction transaction) {
        return TransactionDTO.builder()
                .id(transaction.getId())
                .payment(PaymentDTO.fromEntity(transaction.getPayment())) // Assuming PaymentDTO has a fromEntity method
                .transactionType(transaction.getTransactionType())
                .amount(transaction.getAmount())
                .status(transaction.getStatus())
                .referenceNumber(transaction.getReferenceNumber())
                .transactionDate(transaction.getTransactionDate())
                .build();
    }
}
