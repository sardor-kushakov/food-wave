package sarik.dev.foodwave.service;

import sarik.dev.foodwave.dto.transaction.TransactionCreateDTO;
import sarik.dev.foodwave.dto.transaction.TransactionDTO;
import sarik.dev.foodwave.enums.user.TransactionStatus;

import java.util.List;
import java.util.UUID;

public interface TransactionService {

    TransactionDTO createTransaction(TransactionCreateDTO transactionCreateDTO);

    TransactionDTO getTransactionById(UUID transactionId);

    List<TransactionDTO> getTransactionsByPaymentId(UUID paymentId);

    List<TransactionDTO> getTransactionsByStatus(TransactionStatus status);

    TransactionDTO updateTransactionStatus(UUID transactionId, TransactionStatus newStatus);

    void deleteTransaction(UUID transactionId);
}
