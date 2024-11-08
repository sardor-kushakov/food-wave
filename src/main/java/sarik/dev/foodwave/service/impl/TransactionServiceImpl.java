package sarik.dev.foodwave.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sarik.dev.foodwave.dto.transaction.TransactionCreateDTO;
import sarik.dev.foodwave.dto.transaction.TransactionDTO;
import sarik.dev.foodwave.entity.user.Transaction;
import sarik.dev.foodwave.entity.user.Payment;
import sarik.dev.foodwave.enums.user.TransactionStatus;
import sarik.dev.foodwave.repository.TransactionRepository;
import sarik.dev.foodwave.repository.PaymentRepository;
import sarik.dev.foodwave.service.TransactionService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final PaymentRepository paymentRepository;

    @Override
    @Transactional
    public TransactionDTO createTransaction(TransactionCreateDTO transactionCreateDTO) {
        Payment payment = paymentRepository.findById(transactionCreateDTO.getPaymentId())
                .orElseThrow(() -> new IllegalArgumentException("Payment not found"));

        Transaction transaction = Transaction.builder()
                .payment(payment)
                .transactionType(transactionCreateDTO.getTransactionType())
                .amount(transactionCreateDTO.getAmount())
                .status(TransactionStatus.PENDING)
                .referenceNumber(transactionCreateDTO.getReferenceNumber())
                .build();

        transactionRepository.save(transaction);
        return TransactionDTO.fromEntity(transaction);
    }

    @Override
    public TransactionDTO getTransactionById(UUID transactionId) {
        Transaction transaction = transactionRepository.findById(transactionId)
                .orElseThrow(() -> new IllegalArgumentException("Transaction not found"));
        return TransactionDTO.fromEntity(transaction);
    }

    @Override
    public List<TransactionDTO> getTransactionsByPaymentId(UUID paymentId) {
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new IllegalArgumentException("Payment not found"));

        return transactionRepository.findByPayment(payment).stream()
                .map(TransactionDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<TransactionDTO> getTransactionsByStatus(TransactionStatus status) {
        return transactionRepository.findByStatus(status).stream()
                .map(TransactionDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public TransactionDTO updateTransactionStatus(UUID transactionId, TransactionStatus newStatus) {
        Transaction transaction = transactionRepository.findById(transactionId)
                .orElseThrow(() -> new IllegalArgumentException("Transaction not found"));

        transaction.updateStatus(newStatus);
        transactionRepository.save(transaction);
        return TransactionDTO.fromEntity(transaction);
    }

    @Override
    @Transactional
    public void deleteTransaction(UUID transactionId) {
        if (transactionRepository.existsById(transactionId)) {
            transactionRepository.deleteById(transactionId);
        } else {
            throw new IllegalArgumentException("Transaction not found");
        }
    }
}
