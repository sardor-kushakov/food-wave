package sarik.dev.foodwave.dto.transaction;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import sarik.dev.foodwave.enums.user.TransactionType;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class TransactionCreateDTO {

    @NotNull
    private UUID paymentId;

    @NotNull
    private TransactionType transactionType;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal amount;

    private String referenceNumber; // Optional field
}
