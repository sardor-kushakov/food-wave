package sarik.dev.foodwave.dto.transaction;

import lombok.Data;
import sarik.dev.foodwave.enums.user.TransactionStatus;

import java.util.UUID;

@Data
public class TransactionUpdateDTO {

    private UUID id;
    private TransactionStatus status;
}
